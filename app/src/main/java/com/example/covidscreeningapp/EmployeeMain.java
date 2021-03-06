package com.example.covidscreeningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class EmployeeMain extends AppCompatActivity {

    public static final String TAG = "EMP_MAIN";
    private EditText firstname, lastname,mobile,destination;
    private Button btn;
    private TextView checkin, checkout,t5;
    private String fn,ln,mb,des,checkinTime,CheckoutTime;
    private ImageView lefticon;
    private Spinner sp;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private CheckBox mCheckBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);

        firstname = findViewById(R.id.fnames);
        lastname = findViewById(R.id.lnames);
        mobile = findViewById(R.id.mobile);
        checkin = findViewById(R.id.checkin);
        checkout = findViewById(R.id.Checkout);
        sp = findViewById(R.id.spinner);
        mCheckBox = findViewById(R.id.checkBox);
        t5 = findViewById(R.id.w5);

        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EmployeeMain.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        checkSharePreferences();

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

        btn = findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmpty(firstname)==true||isEmpty(lastname)==true||isEmpty(mobile)==true||checkin==null||checkout.equals(null)){
                    //Toast.makeText(EmployeeMain.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    t5.setText("Please fill in all sections");
                }else {


                    fn = firstname.getText().toString();
                    ln = lastname.getText().toString();
                    mb = mobile.getText().toString();
                    if (mCheckBox.isChecked()){
                        mEditor.putString(getString(R.string.checkbox),"True");
                        mEditor.commit();
                        mEditor.putString(getString(R.string.firstname),fn);
                        mEditor.commit();
                        mEditor.putString(getString(R.string.lastname),ln);
                        mEditor.commit();
                        mEditor.putString(getString(R.string.mobile),mb);
                        mEditor.commit();
                    }else{
                        mEditor.putString(getString(R.string.checkbox),"False");
                        mEditor.commit();
                        mEditor.putString(getString(R.string.firstname),"");
                        mEditor.commit();
                        mEditor.putString(getString(R.string.lastname),"");
                        mEditor.commit();
                        mEditor.putString(getString(R.string.mobile),"");
                        mEditor.commit();
                    }

                    Intent intent = new Intent(EmployeeMain.this, EmployeeCont.class);
                    Bundle extras = new Bundle();
                    extras.putString("firstname", fn);
                    Log.d(TAG, "onClick: " + fn);
                    extras.putString("lastname", ln);
                    extras.putString("mobile", mb);
                    extras.putString("destination", des);
                    Log.d(TAG, "DES: " + des);
                    extras.putString("checkin", checkinTime);
                    extras.putString("checkout", CheckoutTime);
                    Log.d(TAG, "time: "+checkinTime+CheckoutTime);
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
                        DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
                        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
                        String strDate = sdf.format(calendar.getTime());
                        checkinTime = strDate;
                        date_time_in.setText(strDate);
                        Log.d(TAG, "onTimeSet: "+strDate+checkinTime);

                    }
                };

                new TimePickerDialog(EmployeeMain.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(EmployeeMain.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

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
                        DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
                        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
                        String strDate = sdf.format(calendar.getTime());
                        CheckoutTime = strDate;
                        date_time_in.setText(strDate);
                        Log.d(TAG, "onTimeSet: "+strDate+CheckoutTime);

                    }
                };

                new TimePickerDialog(EmployeeMain.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(EmployeeMain.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

        return String.valueOf(date_time_in);
    }
    // Check the shared preferences and set
    private void checkSharePreferences(){
        String checkbox = mPreferences.getString(getString(R.string.checkbox),"false");
        String b1 = mPreferences.getString(getString(R.string.firstname),"");
        String b2 = mPreferences.getString(getString(R.string.lastname),"");
        String b3 = mPreferences.getString(getString(R.string.mobile),"");

        firstname.setText(b1);
        lastname.setText(b2);
        mobile.setText(b3);
        if (checkbox.equals("True")){
            mCheckBox.setChecked(true);
        }else{
            mCheckBox.setChecked(false);
        }
    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}