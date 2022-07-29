/**
 * @author: vamsi krishna allu
 * student number : A00259393
 * MAPD - JAV-1001 - App Development for Android
 */
package com.assignment.scorekeeperstyles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isTeamASelected = true;
    private Integer changeValue = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setting main activity as content
        setContentView(R.layout.activity_main);
        // Below line can be uncommented to view in night mode
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // text views to set team names
        TextView teamBText = findViewById(R.id.teamBTextView);
        TextView teamAText = findViewById(R.id.teamATextView);
        teamAText.setText(R.string.team_a);
        teamBText.setText(R.string.team_b);

        // setting the current selection team
        TextView currentSelectionText= findViewById(R.id.currentSelectionTextView);

        currentSelectionText.setText(getString(R.string.current_team).concat(" ").concat(getString(R.string.team_a)));

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchToggle = findViewById(R.id.teamSwitch);
        // listener for the switch
        switchToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // update the current team selection based on the switch button
            if (isChecked) {
                currentSelectionText.setText(getString(R.string.current_team).concat(" ").concat(getString(R.string.team_b)));
                isTeamASelected = false;
            } else {
                currentSelectionText.setText(getString(R.string.current_team).concat(" ").concat(getString(R.string.team_a)));
                isTeamASelected = true;
            }
        });

        // setting custom spinner to display it in the style we need using ArrayAdapter
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.scores_values, R.layout.spinner_data);
        adapter.setDropDownViewResource(R.layout.spinner_data);
        spinner.setAdapter(adapter);

        // listener for spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // update the change value based on user selection from spinner
                if(i<4){
                    changeValue = i + 1;
                }else{
                    changeValue = 6;
                }
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
        int id = isTeamASelected ? R.id.teamAScoreTextView : R.id.teamBScoreTextView;
        TextView teamText = findViewById(id);
        int teamScore = teamText.getText().toString().equals("") ? changeValue : Integer.parseInt(teamText.getText().toString()) + changeValue;
        // text size has to be updated if score is between 99 and 999 as it is three digit numbers
        if(teamScore > 99 && teamScore <= 999){
            teamText.setTextSize(70);
        }
        // if score is increased beyond 999 toast message is shown indicating cant be increased further
        else if(teamScore > 999){
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), "cannot increase score beyond 999", duration);
            toast.show();
            return;
        }
        // if score is less than 100 then the size is adjusted for fitting two digit numbers
        else {
            teamText.setTextSize(96);
        }
        teamText.setText(String.valueOf(teamScore));
    }

    /**
     * method for on click of deduct button
     */
    public void deductScore(View view){
        int id = isTeamASelected ? R.id.teamAScoreTextView : R.id.teamBScoreTextView;
        TextView teamText = findViewById(id);
        // show toast message if user tries to decrease score less than 0
        if(teamText.getText().toString().equals("") || Integer.parseInt(teamText.getText().toString()) - changeValue < 0){
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), "cannot decrease score below 0", duration);
            toast.show();
        }else{
            int teamScore = Integer.parseInt(teamText.getText().toString()) - changeValue;
            // adjust the size if score is three digit number
            if(teamScore > 99){
                teamText.setTextSize(70);
            }
            // adjust size if score is two digit number
            else{
                teamText.setTextSize(96);
            }
            teamText.setText(String.valueOf(teamScore));
        }
    }
}