package com.example.covidscreeningapp.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.covidscreeningapp.R;

public class EmployeeCont extends AppCompatActivity {

 private Button btn;
 private RadioButton y1,y2,y3,y4,n1,n2,n3,n4,y5,n5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_cont);
        // Get the Intent that started this activity and extract the string
        Intent intent = new Intent();
        Bundle bundle = intent.getExtras();
        String mb = getIntent().getStringExtra("mobile");
        String des = getIntent().getStringExtra("destination");
        String ln = getIntent().getStringExtra("lastname");
        String fn = getIntent().getStringExtra("firstname");

        y1 = findViewById(R.id.y1);
        y2 = findViewById(R.id.y2);
        y3 = findViewById(R.id.y3);
        y4 = findViewById(R.id.y4);
        y5 = findViewById(R.id.y5);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
        n4 = findViewById(R.id.n4);
        n5 = findViewById(R.id.n5);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeCont.this, UploadCertificate.class);
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
