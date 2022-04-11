package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SelectPortal extends AppCompatActivity {

    private Button emp,vis;
    private ImageView lefticon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_portal);

        emp = findViewById(R.id.emp);
        vis = findViewById(R.id.vis);
        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SelectPortal.this,OrganizationLogin.class);
                startActivity(intent1);
            }
        });

        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPortal.this,EmployeeListMain.class);
                startActivity(intent);
            }
        });

        vis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPortal.this,visitorList.class);
                startActivity(intent);
            }
        });
    }
}