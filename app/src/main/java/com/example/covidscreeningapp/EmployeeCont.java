package com.example.covidscreeningapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class EmployeeCont extends AppCompatActivity {

 private Button btn;
 private RadioButton y1,y2,y3,y4,n1,n2,n3,n4,y5,n5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_cont);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String mb = bundle.getString("mobile");
        String des = bundle.getString("destination");
        String ln = bundle.getString("lastname");
        String fn = bundle.getString("firstname");
        String checkinTime = bundle.getString("checkin");
        String CheckoutTime = bundle.getString("checkout");

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
                Bundle extras = new Bundle();
                extras.putString("firstname",fn);
                // check if message passed
                Log.d(TAG, "onClick:"+fn);
                extras.putString("lastname",ln);
                extras.putString("mobile",mb);
                extras.putString("destination",des);
                extras.putString("checkin", checkinTime);
                extras.putString("checkout", CheckoutTime);
                intent.putExtras(extras);
                startActivity(intent);

            }
        });

    }
    public void radioButtonhandler(View view) {

        // Decide what happens when a user clicks on a button
    }
}
