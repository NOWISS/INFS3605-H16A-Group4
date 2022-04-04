package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.covidscreeningapp.employee.EmployeeMain;
import com.example.covidscreeningapp.visitor.VisitorCheckIn;

public class MainActivity extends AppCompatActivity {
    private Button emp, org, visitor,Okay;
    private Dialog dialog;
    private ImageButton ShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    emp = findViewById(R.id.emp);
        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmployeeMain.class);
                startActivity(intent);
            }
        });

    org = findViewById(R.id.org);
    org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrganizationLogin.class);
                startActivity(intent);
            }
        });
    visitor = findViewById(R.id.visitor);
    visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VisitorCheckIn.class);
                startActivity(intent);
            }
        });

        ShowDialog = findViewById(R.id.dialog_btn);

        //Create the Dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_consent_page);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Set clicking button
        Okay = dialog.findViewById(R.id.btn_okay);
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        ShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(); // Showing the dialog here
            }
        });
    }

}