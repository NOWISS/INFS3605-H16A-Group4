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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VisitorCont extends AppCompatActivity {

    private Button btn;
    private int points=0;
    private TextView t5;
    private RadioGroup rg1, rg2, rg3, rg4,rg5;
    private String color;
    private static int latestId = 0;
    private String Green="Green", Yellow = "Yellow", Red = "Red";
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Visitor");
    private ImageView lefticon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_cont);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String mb = bundle.getString("mobile");
        String des = bundle.getString("destination");
        String ln = bundle.getString("lastname");
        String fn = bundle.getString("firstname");
        String checkinTime = bundle.getString("checkin");
        String CheckoutTime = bundle.getString("checkout");

        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(VisitorCont.this,VisitorCheckIn.class);
                startActivity(intent1);
            }
        });

        t5 = findViewById(R.id.w5);
        rg1 = findViewById(R.id.radioGroup1);
        rg2 = findViewById(R.id.radioGroup2);
        rg3 = findViewById(R.id.radioGroup3);
        rg4 = findViewById(R.id.radioGroup4);
        rg5 = findViewById(R.id.radioGroup5);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { points = findRadioButton(checkedId)+points;
                Log.d(TAG, "onCheckedChanged: "+points);
            }});
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { points = findRadioButton1(checkedId)+points;
                Log.d(TAG, "onCheckedChanged: "+points);
            }});
        rg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { points = findRadioButton2(checkedId)+points;
                Log.d(TAG, "onCheckedChanged: "+points);
            }});
        rg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { points = findRadioButton3(checkedId)+points;
                Log.d(TAG, "onCheckedChanged: "+points);
            }});
        rg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { points = findRadioButton4(checkedId)+points;
                Log.d(TAG, "onCheckedChanged: "+points);
            }});

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(points==0||points==1){
                    color = Green;
                }else if (points==2){
                    color = Yellow;
                }else{
                    color = Red;
                }
                VisitorModel vi = new VisitorModel(fn,ln,mb,des,checkinTime,CheckoutTime,color);
                generateId();
                root.child(String.valueOf(latestId)).setValue(vi);

                Intent intent = new Intent(VisitorCont.this, Pass.class);
                Bundle extras = new Bundle();
                extras.putString("firstname",fn);
                // check if message passed
                Log.d(TAG, "onClick:"+fn);
                extras.putString("lastname",ln);
                extras.putString("mobile",mb);
                extras.putString("destination",des);
                extras.putString("checkin", checkinTime);
                extras.putString("checkout", CheckoutTime);
                extras.putString("color",color);
                Log.d(TAG, "onClick: points"+points+color);
                intent.putExtras(extras);
                startActivity(intent);

            }
        });

    }

    private int findRadioButton(int checkedId) {
        if (checkedId==R.id.y1) {
            return 1;
        }else{
            return 0;
        }
    }
    private int findRadioButton1(int checkedId) {
        if (checkedId==R.id.y2) {
            return 1;
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
            return 1;
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

    public static int generateId() {
        return latestId++;
    }
}