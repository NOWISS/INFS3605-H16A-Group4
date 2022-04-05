package com.example.covidscreeningapp.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidscreeningapp.R;

public class EmployeeMain extends AppCompatActivity {

    private EditText firstname, lastname,mobile,destination;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);

        firstname = findViewById(R.id.fnames);
        lastname = findViewById(R.id.lnames);
        mobile = findViewById(R.id.mobile);
        destination = findViewById(R.id.destination);
        String fn = firstname.getText().toString();
        String ln = lastname.getText().toString();
        String mb = mobile.getText().toString();
        String des = destination.getText().toString();


        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //DB = new VisitorDBHelper(EmployeeMain.this);
                //DB.addPerson(fn,ln,Integer.valueOf(mb),des);
                if (firstname.equals("")||lastname.equals("")||mobile.equals("")||destination.equals("")){
                    Toast.makeText(EmployeeMain.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EmployeeMain.this, EmployeeCont.class);
                intent.putExtra("firstname",fn);
                intent.putExtra("lastname",ln);
                intent.putExtra("mobile",mb);
                intent.putExtra("destination",des);
                startActivity(intent);
            }
        });

    }

    public void radioButtonhandler(View view) {

        // Decide what happens when a user clicks on a button
    }
}