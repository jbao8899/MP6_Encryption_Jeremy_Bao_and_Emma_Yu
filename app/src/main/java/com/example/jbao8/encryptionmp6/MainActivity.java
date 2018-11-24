package com.example.jbao8.encryptionmp6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity Class";
    private boolean makeUpperCase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void transformText(View transformButton) {
        //Context context = getApplicationContext();
        EditText input = findViewById(R.id.Input);
        TextView output = findViewById(R.id.Output);
        Log.d(TAG, "function ran");
        String toModify = input.getText().toString();
        if (BooleansForSettings.toCapitalizeAfterRotation) {
            toModify = toModify.toUpperCase();
        }
        output.setText(toModify);
    }
    public void goToSettings(View v){
        Intent myIntent = new Intent(getBaseContext(), Settings.class);
        startActivity(myIntent);
    }
}
