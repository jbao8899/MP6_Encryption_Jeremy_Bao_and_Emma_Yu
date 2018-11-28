package com.example.jbao8.encryptionmp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Settings_Vertical extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings__vertical);
        TextView capIndicator = findViewById(R.id.capIndicator);
        if (BooleansForSettings.isToCapitalizeAfterRotation()) {
            capIndicator.setText(R.string.currently_on);
        } else {
            capIndicator.setText(R.string.currently_off);
        }
    }
    public void toggleUpperCase(View transformButton) { //indicator status not being preserved?
        TextView indicator = findViewById(R.id.capIndicator);
        if (!BooleansForSettings.isToCapitalizeAfterRotation()) {
            BooleansForSettings.setToCapitalizeAfterRotation(true);
            indicator.setText(R.string.currently_on);
        } else {
            BooleansForSettings.setToCapitalizeAfterRotation(false);
            indicator.setText(R.string.currently_off);
        }
    }
    public void goToMain(View v) {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }
    // waiting for target device to come online forever?
}
