package com.example.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    TextView showStatus;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_final);
        showStatus = findViewById(R.id.statusTextView);
        String message = getIntent().getStringExtra(MainActivity.ACTIVITY_MESSAGE);
        if (message != null) {
            ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                if (cm.isActiveNetworkMetered()) {
                    showStatus.setText(R.string.connectionMsg1);
                } else {
                    showStatus.setText(R.string.connectionMsg2);
                }
            } else {
                showStatus.setText(R.string.connectionMsg3);
            }

        } else {
            String[] messages = getIntent().getStringArrayExtra(UserActivity.SECOND_ACTIVITY_MESSAGE);
            if (messages[1].startsWith("Sys")) {
                showBatterNotification(messages[0]);
            } else {
                MyBroadcastReceiver mbr = new MyBroadcastReceiver();
                IntentFilter filter = new IntentFilter(messages[0]);
                this.registerReceiver(mbr, filter);
                showStatus.setText(new StringBuilder().append(messages[0]).append(" ").append(messages[1]));
            }
        }
    }

    private void showBatterNotification(String userInput) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, filter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPercent = level * 100 / (float)scale;

        showStatus.setText(new StringBuilder().append("Battery Status:").append(batteryPercent).
                append("% and ").append(batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1)
                == BatteryManager.BATTERY_STATUS_CHARGING ? "Charging" : "Not Charging").
                append(System.lineSeparator()).append("Your Entered: ").append(userInput));
    }
}
