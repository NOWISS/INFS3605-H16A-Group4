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
    private TextView tv,tv28,tv29;
    private ImageView lefticon,icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);

        tv = findViewById(R.id.textView15);
        tv28 = findViewById(R.id.textView28);
        tv29 = findViewById(R.id.textView29);
        icon = findViewById(R.id.imageView3);

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
            tv.setText(FirstName+", "+LastName+" ");
            tv28.setText("You have been approved access to "+location);
            tv29.setText("Your colour code is "+color.toUpperCase()+", which allows you to enter all buildings at the " +
                    "discretion of your organisation");
            icon.setImageResource(R.drawable.green);
        }else if (color.equals("Yellow")){
            tv.setText(FirstName+", "+LastName+" ");
            tv28.setText("You have been approved access to "+location);
            tv29.setText("Your colour code is "+color.toUpperCase()+", which allows you to enter all buildings at the " +
                    "discretion of your organisation");
            icon.setImageResource(R.drawable.yellow);

        }else{
            tv.setText(FirstName+", "+LastName+" ");
            tv28.setText("Sorry, you have NOT been approved to enter " + location);
            tv29.setText("Your screening pass is "+ color.toUpperCase() +", which means you are not alliowed to enter. \n" +
                    "Please refer to NSW Health guidelines and recommendations");
            icon.setImageResource(R.drawable.red);
        }
    }
}