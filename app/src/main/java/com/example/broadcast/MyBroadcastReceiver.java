package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: ").append(intent.getAction()).append(System.lineSeparator());
        sb.append("Status: ").append(BatteryManager.EXTRA_STATUS).append(System.lineSeparator());
        String log = sb.toString();
        Log.d(TAG, log);
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }
}
