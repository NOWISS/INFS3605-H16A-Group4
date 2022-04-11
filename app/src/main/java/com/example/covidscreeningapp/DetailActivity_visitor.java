package com.example.covidscreeningapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity_visitor extends AppCompatActivity {

    TextView fname, lname, mobile, location;
    Button delete_button;
    String id, fnamee, lnamee, mobilee, locationn;
    private ImageView lefticon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_visitor);

        fname = findViewById(R.id.fname1);
        lname = findViewById(R.id.lname1);
        mobile = findViewById(R.id.mb1);
        location = findViewById(R.id.location);
        delete_button = findViewById(R.id.btndelete);
        getAndSetIntentData();

        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DetailActivity_visitor.this,visitorList.class);
                startActivity(intent1);
            }
        });

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Detail");
        }
        // To-do
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("firstname") &&
                getIntent().hasExtra("lastname") && getIntent().hasExtra("mobile")&& getIntent().hasExtra("destination")){
            //Getting Data from Intent
            fnamee = getIntent().getStringExtra("firstname");
            lnamee = getIntent().getStringExtra("lastname");
            mobilee = getIntent().getStringExtra("mobile");
            locationn = getIntent().getStringExtra("destination");

            //Setting Intent Data
            fname.setText("First Name: "+ fnamee);
            lname.setText("Last Name: "+lnamee);
            mobile.setText("Mobile Number: "+mobilee);
            location.setText("Location: "+locationn);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}