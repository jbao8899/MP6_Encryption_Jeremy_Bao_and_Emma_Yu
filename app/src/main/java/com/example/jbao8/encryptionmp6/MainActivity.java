package com.example.jbao8.encryptionmp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
// add library?
// JSON?
// publishing?
// Keep text on MainActivity?
// Allow copying output?

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity Class";
    private boolean hasStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Main Activity started");
        if (hasStarted) {
            EditText input = findViewById(R.id.Input);
            input.setText(BooleansForSettings.getToModify());
            TextView output = findViewById(R.id.Output);
            output.setText(BooleansForSettings.getOutput());
        }
        setContentView(R.layout.activity_main);
    }
    public void transformText(View transformButton) {
        hasStarted = true;
        EditText input = findViewById(R.id.Input);
        TextView output = findViewById(R.id.Output);
        Log.d(TAG, "transformText function ran");
        BooleansForSettings.setToModify(input.getText().toString());
        if (BooleansForSettings.isToCapitalizeAfterRotation()) {
            BooleansForSettings.setToModify(BooleansForSettings.getToModify().toUpperCase());
        }
        if (BooleansForSettings.isAppendOrNot()) {
            String[] words = BooleansForSettings.getToModify().split(" ");
            String appendedString = "";
            for (String word : words) {
                if (word.toLowerCase().equals(BooleansForSettings.getWordToAppendAfter().toLowerCase())) { // too long?
                    word = word + " " + BooleansForSettings.getToAppend();
                }
                appendedString = appendedString + " " + word;
            }
            BooleansForSettings.setToModify(appendedString);
        }
        BooleansForSettings.setOutput(BooleansForSettings.getToModify());
        output.setText(BooleansForSettings.getOutput());
    }
    public void decryptText(View decryptButton) {
        hasStarted = true;
        Log.d(TAG, "decryptText function ran");
        EditText input = findViewById(R.id.Input);
        TextView output = findViewById(R.id.Output);
        BooleansForSettings.setToModify(input.getText().toString());
        if (BooleansForSettings.isToCapitalizeAfterRotation()) {
            BooleansForSettings.setToModify(BooleansForSettings.getToModify().toLowerCase());
        }
        if (BooleansForSettings.isAppendOrNot()) {
            String[] words = BooleansForSettings.getToModify().split(BooleansForSettings.getToAppend()); // too long?
            String stringWithAppendedRemoved = "";
            for (String word : words) {
                stringWithAppendedRemoved = stringWithAppendedRemoved + word.trim() + " ";
            }
            if (BooleansForSettings.isToCapitalizeAfterRotation()) {
                words = stringWithAppendedRemoved.split(BooleansForSettings.getToAppend().toLowerCase());
                stringWithAppendedRemoved = "";
                for (String word : words) { // deals with case where original appended string
                    // had uppercase letters, which were turned to lowercase by previous block of code.
                    stringWithAppendedRemoved = stringWithAppendedRemoved + word.trim() + " ";
                }
            }
            BooleansForSettings.setToModify(stringWithAppendedRemoved);
        }
        BooleansForSettings.setOutput(BooleansForSettings.getToModify());
        output.setText(BooleansForSettings.getOutput());
    }
    // Library causing failures?
    public void goToSettings(View v){
        hasStarted = true;
        Log.d(TAG,"goToSettings has run");
        EditText input = findViewById(R.id.Input);
        BooleansForSettings.setToModify(input.getText().toString());
        TextView output = findViewById(R.id.Output);
        BooleansForSettings.setOutput(output.getText().toString());
        Intent myIntent = new Intent(getBaseContext(), Settings_Vertical.class); // not working?
        startActivity(myIntent);
    }
}
