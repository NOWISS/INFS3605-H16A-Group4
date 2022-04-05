package com.example.covidscreeningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeeListMain extends AppCompatActivity {

    private EditText firstname, lastname, mobile, destination, ispass;
    private RecyclerView employeelist;
    private DatabaseReference database;
    private ArrayList<Employee> list;
    private EmpAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list_main);

        employeelist = findViewById(R.id.recyclerView);
        database = FirebaseDatabase.getInstance().getReference("Employee");
        employeelist.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new EmpAdapter(EmployeeListMain.this,list);
        employeelist.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Employee emp = dataSnapshot.getValue(Employee.class);
                    list.add(emp);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}