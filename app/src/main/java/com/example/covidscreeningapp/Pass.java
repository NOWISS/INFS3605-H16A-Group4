package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Pass extends AppCompatActivity {

    private static final String TAG = "PASS";
    private String FirstName, LastName,color,location;
    private TextView tv;
    private ImageView lefticon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);

        tv = findViewById(R.id.textView15);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (intent.hasExtra("color")) {
            Log.d(TAG, "INTENT_MESSAGE = " + intent.getStringExtra("color"));

            FirstName = bundle.getString("firstname");
            LastName = bundle.getString("lastname");
            color = bundle.getString("color");
            location = bundle.getString("location");

            lefticon = findViewById(R.id.back);
            // Make the return button
            lefticon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(Pass.this,MainActivity.class);
                    startActivity(intent1);
                }
            });

        }
        // generate pass token
        if (color.equals("Green")){
            tv.setText(FirstName+" "+LastName+", " + "you are approved to pass!");
            // To-do set background and tick
        }else if (color.equals("Yellow")){
            tv.setText(FirstName+" "+LastName+", " + "you are approved to pass!");
            // To-do set background and tick
        }else{
            tv.setText("Sorry, "+FirstName+" "+LastName+", " + "you are NOT allowed to go to " + location);
            // To-do set background and cross
        }
    }
}