package com.example.jbao8.encryptionmp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Lets users choose how to encrypt the text.
 */
public class Settings extends AppCompatActivity {
    private final String TAG = "Settings Class";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void toggleUpperCase(View transformButton) { //indicator status not being preserved?
        TextView indicator = findViewById(R.id.StatusIndicatorForUpperCase);
        if (!BooleansForSettings.isToCapitalizeAfterRotation()) {
            BooleansForSettings.setToCapitalizeAfterRotation(true);
            indicator.setText(R.string.currently_on);
        } else {
            BooleansForSettings.setToCapitalizeAfterRotation(false);
            indicator.setText(R.string.currently_off);
        }
    }
    public void appendText(View appendButton) { // not working?
        EditText textToAppend = findViewById(R.id.textToAppend);
        EditText wordAfterWhichtoAppend = findViewById(R.id.wordAfterWhichtoAppend);
        TextView statusIndicatorForAppending = findViewById(R.id.statusIndicatorForAppending);
        if (BooleansForSettings.isAppendOrNot()) {
            BooleansForSettings.setAppendOrNot(false);
            statusIndicatorForAppending.setText(R.string.currently_off);
            BooleansForSettings.setToAppend("");
            BooleansForSettings.setWhenToAppend("");
            textToAppend.setText(R.string.what_to_append);
            wordAfterWhichtoAppend.setText(R.string.after_which_word);
        } else {
            BooleansForSettings.setAppendOrNot(true);
            statusIndicatorForAppending.setText(R.string.currently_on);
            BooleansForSettings.setToAppend(textToAppend.getText().toString());
            BooleansForSettings.setWhenToAppend(wordAfterWhichtoAppend.getText().toString());
        }
    }
    public void goToMain(View v) {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }
}
