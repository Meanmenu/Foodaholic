package com.foodaholic.foodaholic.fragments.details;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.model.Review;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maygupta on 12/6/15.
 */
public class AddReviewFragment extends DialogFragment {
    EditText etReviewText;
    RatingBar rbScore;
    ReviewsFragment.AddReviewListener listener;

     public static AddReviewFragment newInstance() {
        AddReviewFragment f = new AddReviewFragment();
        return f;
    }

    public void setListener(ReviewsFragment.AddReviewListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
         AlertDialog.Builder b=  new  AlertDialog.Builder(getActivity())
            .setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Review r = new Review(rbScore.getRating(),
                                    "Mayank Gupta",
                                    "https://scontent.fsnc1-1.fna.fbcdn.net/hprofile-xaf1/v/t1.0-1/p320x320/10968305_1015878085092410_4103411558902970210_n.jpg?oh=3dde591786d08d0df13110ff536a47e1&oe=56F59D55",
                                    sdf.format(new Date()),
                                    etReviewText.getText().toString());
                            listener.finish(r);
                            dismiss();
                        }
                    }
            )
            .setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    }
            );

        LayoutInflater i = getActivity().getLayoutInflater();

        View v = i.inflate(R.layout.add_review_fragment, null);

        ImageView ivFoodImage =  (ImageView) v.findViewById(R.id.ivFoodImage);

        etReviewText =  (EditText) v.findViewById(R.id.etReviewText);
        rbScore = (RatingBar) v.findViewById(R.id.rbScore);

        ivFoodImage.setImageResource(android.R.color.transparent);
        Picasso.with(getActivity().getApplicationContext()).load(R.drawable.apple_bru).into(ivFoodImage);

        b.setView(v);
        return b.create();
    }
}
