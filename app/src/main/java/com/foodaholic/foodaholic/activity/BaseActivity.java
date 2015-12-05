package com.foodaholic.foodaholic.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.foodaholic.foodaholic.R;

public class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    protected void toolbarCreation() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


//        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }
}
