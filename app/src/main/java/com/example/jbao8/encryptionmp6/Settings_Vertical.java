package com.example.jbao8.encryptionmp6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Settings_Vertical extends AppCompatActivity {
    private final String TAG = "Settings_Vertical Class";
    private final int MIN_LENGTH_OF_INPUT = 5;
    private final int MIN_LENGTH_OF_WORD = 1;
    private final int MAX_LENGTH_OF_INPUT = 10;
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
        EditText appendAfterThisWord = findViewById(R.id.appendFollowingThisWord);
        if (!BooleansForSettings.isAppendOrNot()) {
            if (whatToAppend.getText().toString().length() < MIN_LENGTH_OF_INPUT
                    || whatToAppend.getText().toString().length() > MAX_LENGTH_OF_INPUT) {
                Log.d(TAG, "invalid append string");
                int duration = Toast.LENGTH_SHORT;
                Context context = getApplicationContext();
                Toast errorToast = Toast.makeText(context, "String to append must have length" +
                        " between 5 and 10", duration);
                errorToast.show();
                return;
            }
            if (appendAfterThisWord.getText().toString().length() < MIN_LENGTH_OF_WORD
                    || appendAfterThisWord.getText().toString().length() > MAX_LENGTH_OF_INPUT) {
                Log.d(TAG, "invalid word to append after string");
                int duration = Toast.LENGTH_SHORT;
                Context context = getApplicationContext();
                Toast errorToast = Toast.makeText(context, "word to append after must have" +
                        " length between 1 and 10", duration);
                errorToast.show();
                return;
            }
            BooleansForSettings.setAppendOrNot(true);
            BooleansForSettings.setToAppend(whatToAppend.getText().toString());
            BooleansForSettings.setWordToAppendAfter(appendAfterThisWord.getText().toString());
            indicator.setText(R.string.currently_on);
        } else {
            BooleansForSettings.setAppendOrNot(false);
            BooleansForSettings.setToAppend("");
            whatToAppend.setText(R.string.what_to_append);
            appendAfterThisWord.setText(R.string.after_which_word);
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
