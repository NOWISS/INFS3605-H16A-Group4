package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class VisitorCheckIn extends AppCompatActivity {

    private static final String TAG = "TAG";
    private EditText firstname, lastname,mobile,destination;
    private String FirstName, LastName,MobileNumber,Destination;
    private Spinner sp;
    private Button btn;
    private TextView checkin, checkout;
    private String checkinTime,CheckoutTime;
    private ImageView lefticon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_check_in);

        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(VisitorCheckIn.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        firstname = findViewById(R.id.fnames_visitor);
        lastname = findViewById(R.id.lnames_visitor);
        mobile = findViewById(R.id.mobile_visitor);
        destination = findViewById(R.id.destination_visitor);
        checkin = findViewById(R.id.checkin);
        checkout = findViewById(R.id.Checkout);
        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkinTime =showDateTimeDialog(checkin);
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckoutTime=showDateTimeDialog(checkout);
            }
        });
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
                    extras.putString("checkin", checkinTime);
                    extras.putString("checkout", CheckoutTime);
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
    private String showDateTimeDialog(TextView date_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss z");
                        date_time_in.setText(sdf.format(calendar.getTime()));

                    }
                };

                new TimePickerDialog(VisitorCheckIn.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(VisitorCheckIn.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

        return String.valueOf(date_time_in);
    }
}