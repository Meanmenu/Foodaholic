package com.foodaholic.foodaholic.fragments.details;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.activity.DetailItemMenuActivity;
import com.foodaholic.foodaholic.model.Review;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by maygupta on 12/6/15.
 */
public class AddReviewFragment extends DialogFragment {
    private static final int MAX_CHARS = 200;
    EditText etReviewText;
    ReviewsFragment.AddReviewListener listener;
    EditText review;
    ImageView profileImage;
    ImageView ivReview;

    private int curLength = 0;
    private RatingBar rbScore;

    public static AddReviewFragment newInstance() {
        AddReviewFragment f = new AddReviewFragment();
        return f;
    }

    public void setListener(ReviewsFragment.AddReviewListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_write_review, container);
        ButterKnife.bind(getActivity());
        return inflate;
    }

    @OnClick(R.id.bt_send)
    public void send(View view) {
        final SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = mSettings.getString(DetailItemMenuActivity.ID, null);
        Review r = new Review(rbScore.getRating(),
                mSettings.getString(DetailItemMenuActivity.USERNAME, null),
                "https://graph.facebook.com/"+id+"/picture",
                sdf.format(new Date()),
                etReviewText.getText().toString());
        r.reviewImage = ((BitmapDrawable)ivReview.getDrawable()).getBitmap();
        listener.finish(r);
        dismiss();
    }

    @OnClick(R.id.iv_close)
    public void close(View view) {
        dismiss();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        review = (EditText) view.findViewById(R.id.et_review);
        final TextView characters = (TextView) view.findViewById(R.id.tv_characters);
        rbScore = (RatingBar) view.findViewById(R.id.ratingBar);
        ivReview = (ImageView) view.findViewById(R.id.ivReview);
        // Show soft keyboard automatically and request focus to field
        review.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        final Button send = (Button) view.findViewById(R.id.bt_send);
        etReviewText = (EditText) view.findViewById(R.id.et_review);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send(v);
            }
        });
        profileImage = (ImageView) view.findViewById(R.id.iv_user);

        final SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String id = mSettings.getString(DetailItemMenuActivity.ID, null);
        Picasso.with(getActivity()).load("https://graph.facebook.com/"+id+"/picture").into(profileImage);

        final ImageView close = (ImageView) view.findViewById(R.id.iv_close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close(v);
            }
        });

        review.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
              }

              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {
                  curLength = MAX_CHARS - s.length();
                  characters.setText("" + curLength);
                  if (curLength < 0) {
                      characters.setTextColor(Color.RED);
                      send.setBackgroundColor(getResources().getColor(R.color.primaryLight));
                  } else {
                      characters.setTextColor(getResources().getColor(android.R.color.darker_gray));
                      send.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                  }
              }

              @Override
              public void afterTextChanged(Editable s) {
              }
        });
    }

    public void setReview(Bitmap takenImage) {
        ivReview.setImageBitmap(takenImage);
    }

        @Override
    public void onStart(){
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

}
