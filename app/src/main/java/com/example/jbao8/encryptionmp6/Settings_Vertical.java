package com.example.jbao8.encryptionmp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Settings_Vertical extends AppCompatActivity {
    private final String TAG = "Settings_Vertical Class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "have switched to Settings_Vertical activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__vertical);
        TextView capIndicator = findViewById(R.id.capIndicator);
        EditText whatToAppend = findViewById(R.id.whatToAppend);
        EditText appendFollowingThisWord = findViewById(R.id.appendFollowingThisWord);
        if (BooleansForSettings.isToCapitalizeAfterRotation()) {
            capIndicator.setText(R.string.currently_on);
        } else {
            capIndicator.setText(R.string.currently_off);
        }
        TextView appendIndicator = findViewById(R.id.appendingIndicator);
        if (BooleansForSettings.isAppendOrNot()) {
            appendIndicator.setText(R.string.currently_on);
            whatToAppend.setText(BooleansForSettings.getToAppend());
            appendFollowingThisWord.setText(BooleansForSettings.getWordToAppendAfter());
        } else {
            appendIndicator.setText(R.string.currently_off);
            whatToAppend.setText(R.string.what_to_append);
            appendFollowingThisWord.setText(R.string.after_which_word);
        }
    }
    public void toggleUpperCase(View transformButton) {
        Log.d(TAG, "toggleUpperCase has run");
        TextView indicator = findViewById(R.id.capIndicator);
        if (!BooleansForSettings.isToCapitalizeAfterRotation()) {
            BooleansForSettings.setToCapitalizeAfterRotation(true);
            indicator.setText(R.string.currently_on);
        } else {
            BooleansForSettings.setToCapitalizeAfterRotation(false);
            indicator.setText(R.string.currently_off);
        }
    }
    public void toggleAppendStringToWord(View appendButton) {
        Log.d(TAG, "toggleAppendStringToWord has run");
        TextView indicator = findViewById(R.id.appendingIndicator);
        EditText whatToAppend = findViewById(R.id.whatToAppend);
        EditText appendFollowingThisWord = findViewById(R.id.appendFollowingThisWord);
        if (!BooleansForSettings.isAppendOrNot()) {
            BooleansForSettings.setAppendOrNot(true);
            BooleansForSettings.setToAppend(whatToAppend.getText().toString());
            BooleansForSettings.setWordToAppendAfter(appendFollowingThisWord.getText().toString());
            indicator.setText(R.string.currently_on);
        } else {
            BooleansForSettings.setAppendOrNot(false);
            BooleansForSettings.setToAppend("");
            BooleansForSettings.setWordToAppendAfter("");
            indicator.setText(R.string.currently_off);
        }
    }
    public void goToMain(View v) {
        Log.d(TAG,"goToMain has run");
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }
    // waiting for target device to come online forever?
}
