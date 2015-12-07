package com.foodaholic.foodaholic.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelUuid;
import android.util.ArrayMap;
import android.util.Log;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.activity.MenuActivity;
import com.foodaholic.foodaholic.model.PlaceData;
import com.foodaholic.foodaholic.util.BeaconLookUp;
import com.google.common.base.Optional;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Background service to scan for Eddystone beacons and notify the user of
 * proximity. This service exists because Nearby Messages should not (and
 * can not) be run from the background. We will fire up that API once the
 * user decides to engage with the application.
 */
public class EddystoneScannerService extends Service {
    private static final String TAG =
            "EddystoneScannerService";

    private static boolean sRunning;
    public static boolean isRunning() {
        return sRunning;
    }

    // Action to track notification dismissal
    public static final String ACTION_DISMISS =
            "EddystoneScannerService.ACTION_DISMISS";

    // …if you feel like making the log a bit noisier…
    private static boolean DEBUG_SCAN = true;

    // Eddystone service uuid (0xfeaa)
    private static final ParcelUuid UID_SERVICE =
            ParcelUuid.fromString("0000feaa-0000-1000-8000-00805f9b34fb");

    // Filter that forces frame type and namespace id to match
    private static final byte[] NAMESPACE_FILTER_MASK = {
            (byte)0xFF,
            0x00,
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00
    };

    private static String getNamespaceIdFromScan(byte[] scanData) {
        StringBuilder sb = new StringBuilder();
        for (int i=2; i < 12; i++) {
            sb.append(String.format("%02x", scanData[i]));
        }

        return sb.toString();
    }

    // Eddystone frame types
    private static final byte TYPE_UID = 0x00;
    private static final byte TYPE_URL = 0x10;
    private static final byte TYPE_TLM = 0x20;

    private static final int NOTIFICATION_ID = 42;

    private NotificationManager mNotificationManager;
    private BluetoothLeScanner mBluetoothLeScanner;
    private ArrayMap<String, Boolean> mDetectedBeacons;

    @Override
    public void onCreate() {
        super.onCreate();
        sRunning = true;
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        BluetoothManager manager =
                (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        if (manager.getAdapter() != null) {
            mBluetoothLeScanner = manager.getAdapter().getBluetoothLeScanner();
            mDetectedBeacons = new ArrayMap<>();
            startScanning();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ACTION_DISMISS.equals(intent.getAction())) {
            //Mark all currently discovered beacons as "seen"
            markAllRead();
            //Hide the notification, if visible
            mNotificationManager.cancel(NOTIFICATION_ID);
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sRunning = false;

        stopScanning();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* Handle user notifications */
    private void postScanResultNotification(PlaceData place) {
        Bitmap remote_picture = null;

        // Create the style object with BigPictureStyle subclass.
        Notification notiStyle = new
                Notification.BigPictureStyle().setAutoCancel(false)
                .setOngoing(true);
        notiStyle.setBigContentTitle("Pearl's Deluxe Burgers");
        notiStyle.setSummaryText("Do you want to see the menu?");


        remote_picture = BitmapFactory.decodeResource(getResources(),
                R.drawable.pearl_deluxe_burguer);


        // Add the big picture to the style.
        notiStyle.bigPicture(remote_picture);

        Intent contentAction = new Intent(this, MenuActivity.class);
        contentAction.setAction(ACTION_DISMISS);
        PendingIntent content = PendingIntent.getActivity(this, -1, contentAction, 0);

        Intent deleteAction = new Intent(this, EddystoneScannerService.class);
        deleteAction.setAction(ACTION_DISMISS);
        PendingIntent delete = PendingIntent.getService(this, -1, deleteAction, 0);

        Notification note = new Notification.Builder(this)
                .setContentTitle("Pearl's Deluxe Burgers")
                .setContentText("Do you want to see the menu?")
                .setSmallIcon(R.drawable.ic_food)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setContentIntent(content)
                .setAutoCancel(false)
                .setOngoing(true)
                .setStyle(notiStyle)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notiStyle);
    }

    /* Begin scanning for Eddystone advertisers */
    private void startScanning() {
        //Run in background mode
        ScanSettings settings = neew ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                .build();

        mBluetoothLeScanner.startScan(new ArrayList<ScanFilter>(), settings, mScanCallback);
        if (DEBUG_SCAN) Log.d(TAG, "Scanning started…");
    }

    /* Terminate scanning */
    private void stopScanning() {
        mBluetoothLeScanner.stopScan(mScanCallback);
        if (DEBUG_SCAN) Log.d(TAG, "Scanning stopped…");
    }

    /* Handle UID packet discovery on the main thread */
    private void processUidPacket(String deviceAddress, int rssi, byte[] packet) {
        String id = getNamespaceIdFromScan(packet);
        if (DEBUG_SCAN) {
            Log.d(TAG, "Eddystone(" + deviceAddress + ") id = " + id);
        }

        if (!mDetectedBeacons.containsKey(deviceAddress)) {
            Optional<PlaceData> place = BeaconLookUp.getPlaceInformationFromBeacon(id);
            if(place.isPresent()) {
                mDetectedBeacons.put(deviceAddress, false);
                postScanResultNotification(place.get());
            }
        }
    }

    private void markAllRead() {
        for (String key : mDetectedBeacons.keySet()) {
            mDetectedBeacons.put(key, true);
        }
    }

    /* Process each unique BLE scan result */
    private ScanCallback mScanCallback = new ScanCallback() {
        private Handler mCallbackHandler =
                new Handler(Looper.getMainLooper());

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            processResult(result);
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.w(TAG, "Scan Error Code: " + errorCode);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            for (ScanResult result : results) {
                processResult(result);
            }
        }

        private void processResult(ScanResult result) {
            ScanRecord record = result.getScanRecord();
            if (record == null) {
                Log.w(TAG, "Invalid scan record.");
                return;
            }
            final byte[] data = record.getServiceData(UID_SERVICE);
            if (data == null) {
                Log.w(TAG, "Invalid Eddystone scan result.");
                return;
            }

            final String deviceAddress = result.getDevice().getAddress();
            final int rssi = result.getRssi();
            byte frameType = data[0];
            switch (frameType) {
                case TYPE_UID:
                    mCallbackHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            processUidPacket(deviceAddress, rssi, data);
                        }
                    });
                    break;
                case TYPE_TLM:
                case TYPE_URL:
                    //Do nothing, ignoring these
                    return;
                default:
                    Log.w(TAG, "Invalid Eddystone scan result.");
            }
        }
    };
}