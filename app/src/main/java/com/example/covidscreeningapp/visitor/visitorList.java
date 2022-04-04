package com.example.covidscreeningapp.visitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.covidscreeningapp.R;

import java.util.ArrayList;

public class visitorList extends AppCompatActivity {

    private RecyclerView rv1;
    //private VisitorDBHelper myDB;
    private ArrayList<String> id, firstname, lastname, mobile, destination;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list_main);

        rv1 = (RecyclerView) findViewById(R.id.recyclerView);
        //myDB = new VisitorDBHelper(visitorList.this);
        id= new ArrayList<>();
        firstname = new ArrayList<>();
        lastname = new ArrayList<>();
        mobile  = new ArrayList<>();
        destination = new ArrayList<>();

        //storeDatainArraylist();
        adapter = new CustomAdapter(visitorList.this,id,firstname,lastname,mobile,destination);
        rv1.setLayoutManager(new LinearLayoutManager(visitorList.this));
        rv1.setAdapter(adapter);

    }
    /*public void storeDatainArraylist(){
        Cursor cursor = myDB.readData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"Database is empty", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                firstname.add(cursor.getString(1));
                lastname.add(cursor.getString(2));
                mobile.add(cursor.getString(3));
                destination.add(cursor.getString(4));
            }
        }
    }*/
}