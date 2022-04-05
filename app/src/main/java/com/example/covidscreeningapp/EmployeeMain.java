package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmployeeMain extends AppCompatActivity {

    public static final String TAG = "EMP_MAIN";
    private EditText firstname, lastname,mobile,destination;
    private Button btn;
    private TextView checkin, checkout;
    private String checkinTime,CheckoutTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);

        firstname = findViewById(R.id.fnames);
        lastname = findViewById(R.id.lnames);
        mobile = findViewById(R.id.mobile);
        destination = findViewById(R.id.destination);
        checkin = findViewById(R.id.checkin);
        checkout = findViewById(R.id.Checkout);

        String fn = firstname.getText().toString();
        String ln = lastname.getText().toString();
        String mb = mobile.getText().toString();
        String des = destination.getText().toString();

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

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (firstname.equals("")||lastname.equals("")||mobile.equals("")||destination.equals("")||checkin.equals("")
                ||checkout.equals("")){
                    Toast.makeText(EmployeeMain.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }else {

                    Intent intent = new Intent(EmployeeMain.this, EmployeeCont.class);
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

                new TimePickerDialog(EmployeeMain.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(EmployeeMain.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

        return String.valueOf(date_time_in);
    }
}