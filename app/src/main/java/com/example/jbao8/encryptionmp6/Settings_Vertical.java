package com.example.jbao8.encryptionmp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        if (BooleansForSettings.isToCapitalizeAfterRotation()) {
            capIndicator.setText(R.string.currently_on);
        } else {
            capIndicator.setText(R.string.currently_off);
        }
        TextView appendIndicator = findViewById(R.id.appendingIndicator);
        if (BooleansForSettings.isAppendOrNot()) {
            appendIndicator.setText(R.string.currently_on);
        } else {
            appendIndicator.setText(R.string.currently_off);
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
        if (!BooleansForSettings.isAppendOrNot()) {
            BooleansForSettings.setAppendOrNot(true);
            indicator.setText(R.string.currently_on);
        } else {
            BooleansForSettings.setAppendOrNot(false);
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
