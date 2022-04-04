package com.example.covidscreeningapp.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidscreeningapp.R;

public class EmployeeListMain extends AppCompatActivity {

    private Button addbutton;
    private EditText firstname, lastname, mobile, destination, ispass;
    private RecyclerView visitorlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list_main);

        addbutton = findViewById(R.id.add_visitor);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                }

        });
    }
}