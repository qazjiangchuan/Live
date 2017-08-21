package com.sugon.sugonlive.util;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by duke on 2016/12/5.
 */

public class Utils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Is the live streaming still available
     *
     * @return is the live streaming is available
     */
    public static boolean isLiveStreamingAvailable() {
        // Todo: Please ask your app server, is the live streaming still available
        return true;
    }

    /**
     * @param cxt
     * @return 获取屏幕方向，横屏返回true，竖屏返回false
     */
    public static boolean isScreenLand(Context cxt) {
        if (cxt.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param str
     * @Description TODO MD5加密
     */
    public static String makMd5Digest(String str) {
        byte digest[];
        MessageDigest alg;
        try {
            alg = MessageDigest.getInstance("MD5");
            alg.update(str.getBytes());
            digest = alg.digest();
            return byte2hex(digest);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

    private static String byte2hex(byte b[]) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toLowerCase();
    }

    public static void showToast(Context cxt, Object msg) {
        Toast.makeText(cxt, msg + "", Toast.LENGTH_SHORT).show();
    }
}
