package com.sugon.sugonlive.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pjc on 2017/8/2.
 */

public class BaseActivity extends AppCompatActivity {
    public Context appContext;
    protected String TAG = "SugonLive";
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appContext = getApplicationContext();
        mContext = this;
    }
}
