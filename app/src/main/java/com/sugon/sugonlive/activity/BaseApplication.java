package com.sugon.sugonlive.activity;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;


import com.sugon.sugonlive.net.model.UserBean;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by duke on 2016/12/16.
 */

public class BaseApplication extends Application {
    public static UserBean mUser;
    public static Map<String, Object> mUserInfoMap = new HashMap<>();

    ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        if (mUser == null) {
            mUser = new UserBean();
        }

        mUser.setName("admin");
        mUser.setId(1);
    }
}
