package com.example.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class FinalActivity extends AppCompatActivity {

    private BroadcastReceiver br;
    TextView showStatus;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_final);

        br = new MyBroadcastReceiver();
        showStatus = findViewById(R.id.statusTextView);
        String message = getIntent().getStringExtra(MainActivity.ACTIVITY_MESSAGE);
        if (message != null) {
            Log.d("ss", "onCreate: part2");
        } else {
            String[] messages = getIntent().getStringArrayExtra(UserActivity.SECOND_ACTIVITY_MESSAGE);
            if (messages[1].startsWith("Sys")) {
                showBatterNotification(messages[0]);
            } else {
                showStatus.setText(new StringBuilder().append(messages[0]).append(" ").append(messages[1]));
            }
        }
    }

    private void showBatterNotification(String userInput) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(br, filter);
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging =
                status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPercent = level * 100 / (float)scale;
        showStatus.setText(new StringBuilder().append("Battery Status:").append(batteryPercent).
                append("% and ").append(isCharging ? "Charging" : "Not Charging").
                append(System.lineSeparator()).append("Your Entered Value: ").append(userInput));
    }
}
