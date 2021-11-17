package com.example.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    public static final String SECOND_ACTIVITY_MESSAGE = "msg2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        String intentMessage = getIntent().getStringExtra(MainActivity.ACTIVITY_MESSAGE);
        EditText userInputField = findViewById(R.id.userInputField);
        TextView textView = findViewById(R.id.textView);
        Button nextButton = findViewById(R.id.nextBtn);
        textView.setText(intentMessage);
        if (intentMessage.startsWith("Sys")) userInputField.setHint("Enter Battery Percentage");
        else userInputField.setHint("Enter Custom message");

        nextButton.setOnClickListener(v -> {
            String value = userInputField.getText().toString();
            Intent intent = new Intent(this, FinalActivity.class).putExtra(SECOND_ACTIVITY_MESSAGE,new String[]{value, intentMessage});
            startActivity(intent);
        });
    }
}
