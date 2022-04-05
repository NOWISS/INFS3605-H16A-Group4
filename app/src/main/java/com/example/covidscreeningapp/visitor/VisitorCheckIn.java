package com.example.covidscreeningapp.visitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.covidscreeningapp.R;

import java.util.ArrayList;

public class VisitorCheckIn extends AppCompatActivity {

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
        btn = findViewById(R.id.visitor_next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VisitorCheckIn.this, VisitorCont.class);
                startActivity(intent);
            }
        });

        firstname = findViewById(R.id.fnames_visitor);
        FirstName = firstname.getText().toString();

        lastname = findViewById(R.id.lnames_visitor);
        LastName = lastname.getText().toString();

        mobile = findViewById(R.id.mobile_visitor);
        MobileNumber = mobile.getText().toString();

        destination = findViewById(R.id.destination_visitor);
        Destination = destination.getText().toString();

        sp = findViewById(R.id.spinner_visitor);

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