package com.ugb.home_meic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;

public class WakeLocker {
    private static PowerManager.WakeLock wakeLock;

    @SuppressLint("InvalidWakeLockTag")
    public static void acquire(Context context){
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "WakeLock");
        wakeLock.acquire();
    }
    public static void release(){
        if( wakeLock!=null ) wakeLock.release(); wakeLock=null;
    }
}
