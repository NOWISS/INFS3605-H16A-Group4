package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class VisitorCheckIn extends AppCompatActivity {

    private static final String TAG = "TAG";
    private EditText firstname, lastname,mobile,destination;
    private String FirstName, LastName,MobileNumber,Destination;
    private Spinner sp;
    private Button btn;
    // To-do
    private RadioButton m,f,o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_check_in);

        firstname = findViewById(R.id.fnames_visitor);
        lastname = findViewById(R.id.lnames_visitor);
        mobile = findViewById(R.id.mobile_visitor);
        destination = findViewById(R.id.destination_visitor);

        sp = findViewById(R.id.spinner_visitor);
        btn = findViewById(R.id.visitor_next);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (firstname.equals("")||lastname.equals("")||mobile.equals("")||destination.equals("")){
                    Toast.makeText(VisitorCheckIn.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }else {

                    String fn = firstname.getText().toString();
                    String ln = lastname.getText().toString();
                    String mb = mobile.getText().toString();
                    String des = destination.getText().toString();

                    Intent intent = new Intent(VisitorCheckIn.this, VisitorCont.class);
                    Bundle extras = new Bundle();
                    extras.putString("firstname", fn);
                    Log.d(TAG, "onClick: " + fn);
                    extras.putString("lastname", ln);
                    extras.putString("mobile", mb);
                    extras.putString("destination", des);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });

        // store entrance options by using arraylist
        ArrayList<String> options = new ArrayList<String >();
        options.add("Visiting");
        options.add("I am a contractor");
        options.add("I am a casual worker");

        //arrayAdapter is used for the dropdown view
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,options);
        sp.setAdapter(arrayAdapter);
    }
}