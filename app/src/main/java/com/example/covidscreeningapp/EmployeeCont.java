package com.example.covidscreeningapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeCont extends AppCompatActivity {

 private Button btn;
 private RadioButton y1,y2,y3,y4,n1,n2,n3,n4,y5,n5;
 private RadioGroup rg1, rg2, rg3, rg4,rg5;
 private TextView t5;
 private int p1,p2,p3,p4,p5;
 private ImageView lefticon;


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

        t5 = findViewById(R.id.w5);
        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EmployeeCont.this,EmployeeMain.class);
                startActivity(intent1);
            }
        });

        rg1 = findViewById(R.id.radioGroup1);
        rg2 = findViewById(R.id.radioGroup2);
        rg3 = findViewById(R.id.radioGroup3);
        rg4 = findViewById(R.id.radioGroup4);
        rg5 = findViewById(R.id.radioGroup5);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { p1 = findRadioButton(checkedId);
                Log.d(TAG, "onCheckedChanged: "+p1);
            }});
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { p2 = findRadioButton1(checkedId)+p1;
                Log.d(TAG, "onCheckedChanged: "+p2);
            }});
        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { p3 = findRadioButton2(checkedId)+p2;
                Log.d(TAG, "onCheckedChanged: "+p3);
            }});
        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { p4 = findRadioButton3(checkedId)+p3;
                Log.d(TAG, "onCheckedChanged: "+p4);
            }});
        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { p5 = findRadioButton4(checkedId)+p4;
                Log.d(TAG, "onCheckedChanged: "+p5);
            }});


        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Id1 = rg1.getCheckedRadioButtonId();
                int Id2 = rg2.getCheckedRadioButtonId();
                int Id3 = rg3.getCheckedRadioButtonId();
                int Id4 = rg4.getCheckedRadioButtonId();
                int Id5 = rg5.getCheckedRadioButtonId();

                if (Id1 == -1||Id2 == -1||Id3 == -1||Id4 == -1||Id5 == -1) {
                    t5.setText("Please answer all questions");

                }   else {

                    Intent intent = new Intent(EmployeeCont.this, UploadCertificate.class);
                    Bundle extras = new Bundle();
                    extras.putString("firstname", fn);
                    // check if message passed
                    Log.d(TAG, "onClick:" + fn);
                    extras.putString("lastname", ln);
                    extras.putString("mobile", mb);
                    extras.putString("destination", des);
                    extras.putString("checkin", checkinTime);
                    extras.putString("checkout", CheckoutTime);
                    extras.putString("points", String.valueOf(p5));
                    Log.d(TAG,"POINTS:"+p5);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });



    }

    private int findRadioButton(int checkedId) {
        if (checkedId==R.id.y1) {
                return 3;
        }else{
            return 0;
        }
    }
    private int findRadioButton1(int checkedId) {
        if (checkedId==R.id.y2) {
            return 3;
        }else{
            return 0;
        }
    }
    private int findRadioButton2(int checkedId) {
        if (checkedId==R.id.y3) {
            return 1;
        }else{
            return 0;
        }
    }
    private int findRadioButton3(int checkedId) {
        if (checkedId==R.id.y4) {
            return 3;
        }else{
            return 0;
        }
    }
    private int findRadioButton4(int checkedId) {
        if (checkedId==R.id.y5) {
            return 1;
        }else{
            return 0;
        }
    }

}
