package com.example.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    
    public String CHOSEN_OPTION;
    public static final String ACTIVITY_MESSAGE = "HELLO";
    private int INDEX = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.broadcasts,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::goToNextActivity);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.CHOSEN_OPTION = parent.getItemAtPosition(position).toString();
        if (this.CHOSEN_OPTION.contains("WiFi")) this.INDEX = 1;
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void goToNextActivity(View view) {
        List<Intent> intents = Arrays.asList(
                new Intent(this, UserActivity.class).putExtra(ACTIVITY_MESSAGE,CHOSEN_OPTION),
                new Intent(this, FinalActivity.class).putExtra(ACTIVITY_MESSAGE,CHOSEN_OPTION));
        startActivity(intents.get(this.INDEX));
    }
}