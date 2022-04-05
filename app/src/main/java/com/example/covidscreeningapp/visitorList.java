package com.example.covidscreeningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class visitorList extends AppCompatActivity {

    private RecyclerView rv1;
    private DatabaseReference database;
    private ArrayList<VisitorModel> list;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list_main);

        rv1 = (RecyclerView) findViewById(R.id.recyclerView_vi);
        database = FirebaseDatabase.getInstance().getReference("Visitor");
        list = new ArrayList<>();
        adapter = new CustomAdapter(visitorList.this,list);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv1.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    VisitorModel vs = dataSnapshot.getValue(VisitorModel.class);
                    list.add(vs);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}