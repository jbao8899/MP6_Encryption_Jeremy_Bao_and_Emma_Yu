package com.example.jbao8.encryptionmp6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
// add library?
// JSON?
// publishing?
// Keep text on MainActivity?

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity Class";
    private static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        Log.d(TAG,"Main Activity started");
        setContentView(R.layout.activity_main);
    }
    public void transformText(View transformButton) {
        EditText input = findViewById(R.id.Input);
        TextView output = findViewById(R.id.Output);
        Log.d(TAG, "transformText function ran");
        BooleansForSettings.setToModify(input.getText().toString());
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
        if (BooleansForSettings.isToCapitalizeAfterRotation()) {
            BooleansForSettings.setToModify(BooleansForSettings.getToModify().toUpperCase());
        }
        if (BooleansForSettings.isRotateOrNot()) {
            char[] toReturn = BooleansForSettings.getToModify().toCharArray();
            toReturn = Encrypt.encrypter(toReturn, BooleansForSettings.getShiftBy());
            if (toReturn == null) {
                int duration = Toast.LENGTH_SHORT;
                Context context = getApplicationContext();
                Toast errorToast = Toast.makeText(context, "Input broke rotate.", duration);
                errorToast.show();
                return;
            }
            BooleansForSettings.setToModify(new String(toReturn));
        }
        BooleansForSettings.setOutput(BooleansForSettings.getToModify());
        output.setText(BooleansForSettings.getOutput());
    }
    public void decryptText(View decryptButton) {
        Log.d(TAG, "decryptText function ran");
        EditText input = findViewById(R.id.Input);
        TextView output = findViewById(R.id.Output);
        BooleansForSettings.setToModify(input.getText().toString());
        if (BooleansForSettings.isRotateOrNot()) {
            char[] toReturn = BooleansForSettings.getToModify().toCharArray();
            toReturn = Encrypt.decrypter(toReturn, BooleansForSettings.getShiftBy());
            if (toReturn == null) {
                int duration = Toast.LENGTH_SHORT;
                Context context = getApplicationContext();
                Toast errorToast = Toast.makeText(context, "Input broke rotate.", duration);
                errorToast.show();
                return;
            }
            BooleansForSettings.setToModify(new String(toReturn));
        }
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
        Log.d(TAG,"goToSettings has run");
        EditText input = findViewById(R.id.Input);
        BooleansForSettings.setToModify(input.getText().toString());
        TextView output = findViewById(R.id.Output);
        BooleansForSettings.setOutput(output.getText().toString());
        Intent myIntent = new Intent(getBaseContext(), Settings_Vertical.class); // not working?
        startActivity(myIntent);
    }
    public void getExampleText(View apiButton) {
        String url = "http://labs.bible.org/api/?passage=John%203:16&type=json";
        Log.d(TAG, "called getExampleText");
        try {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try{
                                JSONObject contents = null;
                                for (int i = 0; i < response.length() ; i++){
                                    if (i == 0) {
                                        contents = response.getJSONObject(i);
                                        Log.d(TAG, "gotContents");
                                    }
                                }
                                EditText input = findViewById(R.id.Input);
                                input.setText(contents.get("text").toString());
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            Log.d(TAG, error.toString() + " failed within ErrorListener");
                        }
                    }
            );
            requestQueue.add(jsonArrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
