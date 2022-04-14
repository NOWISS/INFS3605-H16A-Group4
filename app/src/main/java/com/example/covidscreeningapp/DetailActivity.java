package com.example.covidscreeningapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.covidscreeningapp.R;

public class DetailActivity extends AppCompatActivity {

    TextView fname, lname, mobile, location;
    Button delete_button,Okay;
    String fnamee, lnamee, mobilee, locationn,vax,result;
    private ImageView lefticon,showvax,showresult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fname = findViewById(R.id.fname1);
        lname = findViewById(R.id.lname1);
        mobile = findViewById(R.id.mb1);
        location = findViewById(R.id.location);
        delete_button = findViewById(R.id.btndelete);
        showvax = findViewById(R.id.showvax);
        showresult = findViewById(R.id.showresult);
        getAndSetIntentData();

        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DetailActivity.this,SelectPortal.class);
                startActivity(intent1);
            }
        });


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
            vax = getIntent().getStringExtra("vax");
            result = getIntent().getStringExtra("result");

            //Setting Intent Data
            fname.setText("First Name: "+ fnamee);
            lname.setText("Last Name: "+lnamee);
            mobile.setText("Mobile Number: "+mobilee);
            location.setText("Location: "+locationn);
            Glide.with(DetailActivity.this)
                    .load(vax)
                    .fitCenter()
                    .into(showvax);
            Glide.with(DetailActivity.this)
                    .load(result)
                    .fitCenter()
                    .into(showresult);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}