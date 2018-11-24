package com.example.jbao8.encryptionmp6;

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
    public void toggleUpperCase(View transformButton) {
        TextView indicator = findViewById(R.id.StatusIndicatorForUpperCase);
        if (!BooleansForSettings.toCapitalizeAfterRotation) {
            BooleansForSettings.toCapitalizeAfterRotation = true;
            indicator.setText(R.string.currently_on);
        } else {
            BooleansForSettings.toCapitalizeAfterRotation = false;
            indicator.setText(R.string.currently_off);
        }
    }
    //How to call method in this class from MainActivity? Can't make mathod static and still use
    //find view by ID.
}
