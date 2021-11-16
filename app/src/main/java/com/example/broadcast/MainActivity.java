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

    public int SELECTED_OPTION = 0;
    public String CHOSEN_OPTION;
    public static final String OPTION1 = "HELLO", OPTION2 = "NOLLO";

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
        Log.d("ASDFASE", CHOSEN_OPTION);
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void goToNextActivity(View view) {
        List<Intent> intents = Arrays.asList(
                new Intent(this, UserActivity.class).putExtra(OPTION1,CHOSEN_OPTION),
                new Intent(this, FinalActivity.class).putExtra(OPTION1,CHOSEN_OPTION));
        if (CHOSEN_OPTION == null) Log.d("test", "null");
        else {
            Log.d("POINOOMOW", CHOSEN_OPTION+"NOTHING");
            if (CHOSEN_OPTION.contains("WiFi")) startActivity(intents.get(1));
            else startActivity(intents.get(0));
        }
    }

}