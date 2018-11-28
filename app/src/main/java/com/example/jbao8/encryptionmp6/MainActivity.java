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
        //Context context = getApplicationContext();
        EditText input = findViewById(R.id.Input);
        TextView output = findViewById(R.id.Output);
        Log.d(TAG, "transformText function ran");
        BooleansForSettings.setToModify(input.getText().toString());
        if (BooleansForSettings.isToCapitalizeAfterRotation()) {
            BooleansForSettings.setToModify(BooleansForSettings.getToModify().toUpperCase());
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
