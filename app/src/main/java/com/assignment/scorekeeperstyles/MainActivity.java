package com.assignment.scorekeeperstyles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setting main activity as content
        setContentView(R.layout.activity_main);

        // setting the full screen flag to hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // text views to set team names
        TextView teamBText = findViewById(R.id.teamBTextView);
        TextView teamAText = findViewById(R.id.teamATextView);
        teamAText.setText(R.string.team_a);
        teamBText.setText(R.string.team_b);


        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch toggle = findViewById(R.id.teamSwitch);
        // listener for the switch
        toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });

        // setting the current selection team
        TextView currentSelectionText= findViewById(R.id.currentSelectionTextView);
        currentSelectionText.setText(R.string.current_selected_team);

        // setting custom spinner to display it in the style we need using ArrayAdapter
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.scores_values, R.layout.spinner_data);
        adapter.setDropDownViewResource(R.layout.spinner_data);
        spinner.setAdapter(adapter);

        // listener for spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button increaseButton = findViewById(R.id.increaseButton);
        // listener for increase button
        increaseButton.setOnClickListener(this::increaseScore);

        Button deductButton = findViewById(R.id.deductButton);
        // listener for deduct button
        deductButton.setOnClickListener(this::deductScore);
    }

    /**
     * method for on click of increase button
     */
    public void increaseScore(View view){

    }

    /**
     * method for on click of deduct button
     */
    public void deductScore(View view){

    }
}