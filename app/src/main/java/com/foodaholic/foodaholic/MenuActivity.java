package com.foodaholic.foodaholic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.foodaholic.foodaholic.service.EddystoneScannerService;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent serviceIntent = new Intent(this, EddystoneScannerService.class);
        startService(serviceIntent);
    }
}
