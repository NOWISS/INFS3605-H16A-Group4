package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class VisitorCheckIn extends AppCompatActivity {

    private static final String TAG = "TAG";
    private EditText firstname, lastname,mobile,destination;
    private String fn,ln,mb,des,checkinTime,CheckoutTime;
    private Spinner sp;
    private Button btn;
    private TextView checkin, checkout,t5;
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
        checkin = findViewById(R.id.checkin);
        checkout = findViewById(R.id.Checkout);
        sp = findViewById(R.id.spinner_visitor);
        t5 = findViewById(R.id.w5);
        //arrayAdapter is used for the dropdown view
        // set spinner
        String[] lvls = {"ground","level 1","level 2", "level 3", "level 4", "level 5"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lvls);
        sp.setAdapter(arrayAdapter);

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(checkin);
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog1(checkout);
            }
        });

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                des = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btn = findViewById(R.id.visitor_next);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (isEmpty(firstname)==true||isEmpty(lastname)==true||isEmpty(mobile)==true||des.equals(null)||checkin==null||checkout.equals(null)){
                    //Toast.makeText(EmployeeMain.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    t5.setText("Please fill in all information");
                }else {
                    fn = firstname.getText().toString();
                    ln = lastname.getText().toString();
                    mb = mobile.getText().toString();

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
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
                        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
                        String strDate = sdf.format(calendar.getTime());
                        checkinTime = strDate;
                        date_time_in.setText(strDate);
                    }
                };

                new TimePickerDialog(VisitorCheckIn.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(VisitorCheckIn.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

        return String.valueOf(date_time_in);
    }

    private String showDateTimeDialog1(TextView date_time_in) {
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
                        DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
                        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
                        String strDate = sdf.format(calendar.getTime());
                        CheckoutTime = strDate;
                        date_time_in.setText(strDate);
                        Log.d(TAG, "onTimeSet: "+strDate+CheckoutTime);

                    }
                };

                new TimePickerDialog(VisitorCheckIn.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(VisitorCheckIn.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

        return String.valueOf(date_time_in);
    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}