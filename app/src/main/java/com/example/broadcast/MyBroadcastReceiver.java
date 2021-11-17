package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: ").append(intent.getAction()).append(System.lineSeparator());
        String message = sb.toString();
        Toast.makeText(context, sb, Toast.LENGTH_SHORT).show();
    }
}
