package com.foodaholic.foodaholic.fragments.details;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
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
import com.foodaholic.foodaholic.model.Review;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
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
    @Bind(R.id.iv_user) ImageView profileImage;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Review r = new Review(rbScore.getRating(),
                "Mayank Gupta",
                "https://scontent.fsnc1-1.fna.fbcdn.net/hprofile-xaf1/v/t1.0-1/p320x320/10968305_1015878085092410_4103411558902970210_n.jpg?oh=3dde591786d08d0df13110ff536a47e1&oe=56F59D55",
                sdf.format(new Date()),
                etReviewText.getText().toString());
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
