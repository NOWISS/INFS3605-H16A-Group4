package com.example.covidscreeningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectPortal extends AppCompatActivity {

    private String TAG = "SelectPortal";
    private Button emp,vis;
    private ImageView lefticon;
    private Spinner lvl,color,reset;
    private String lv,co,ti;
    private RecyclerView recyclerView;
    private double percentage;
    private int i;
    private TextView occupancy,capacity;
    private RecyclerView employeelist;
    private DatabaseReference database;
    private ArrayList<Employee> listemp;
    private ArrayList<VisitorModel> listvis;
    private EmpAdapter myAdapter;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_portal);

        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SelectPortal.this,OrganizationLogin.class);
                startActivity(intent1);
            }
        });

        occupancy = findViewById(R.id.occupancy);
        capacity = findViewById(R.id.capacity);
        occupancy.setText( percentage + " % ");
        capacity.setText( i +" Of 50 Max. ");

/*------------------------------------------------------------------------------------*/
        lvl = findViewById(R.id.level);
        // set spinner
        String[] lvls = {"ground","level 1","level 2", "level 3", "level 4", "level 5"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lvls);
        lvl.setAdapter(arrayAdapter);


        color = findViewById(R.id.color);
        // set spinner
        String[] Color = {"Green","Red","Yellow"};
        ArrayAdapter colorAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,Color);
        color.setAdapter(colorAdapter);
        color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                co = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        reset = findViewById(R.id.time);
        String[] Reset = {"Show All"};
        ArrayAdapter resetAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,Reset);
        reset.setAdapter(resetAdapter);


        recyclerView = findViewById(R.id.displaylist);

        emp = findViewById(R.id.emp);
        vis = findViewById(R.id.vis);

        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeelist = findViewById(R.id.displaylist);
                database = FirebaseDatabase.getInstance().getReference("Employee");
                employeelist.setLayoutManager(new LinearLayoutManager(SelectPortal.this));
                listemp = new ArrayList<>();
                myAdapter = new EmpAdapter(SelectPortal.this,listemp);
                employeelist.setAdapter(myAdapter);
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                            Employee emp = dataSnapshot.getValue(Employee.class);
                            listemp.add(emp);
                        }
                        myAdapter.notifyDataSetChanged();
                        i =  listemp.size();
                        percentage = listemp.size() / 50.0 * 100.0;
                        occupancy.setText( percentage + " % ");
                        capacity.setText( i +" Of 50 Employees Max. ");
                        Log.d(TAG, "onDataChange: "+ i + "percentage"+ percentage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ArrayList<Employee> employees = new ArrayList<>();
                        lv = adapterView.getItemAtPosition(i).toString();
                        Log.d(TAG, "onItemSelected: "+lv+listemp);
                        for (Employee emp : listemp){
                            if (emp.getDestination() != null && emp.getDestination().equals(lv)){
                                employees.add(emp);
                            }else {
                                //Toast.makeText(SelectPortal.this, "No data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        myAdapter = new EmpAdapter(SelectPortal.this,employees);
                        employeelist.setAdapter(myAdapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ArrayList<Employee> employees = new ArrayList<>();
                        co = adapterView.getItemAtPosition(i).toString();
                        Log.d(TAG, "onItemSelected: "+co+listemp);
                        for (Employee employee: listemp){
                            if (employee.getColor() != null && employee.getColor().equals(co)){
                                employees.add(employee);
                            }else {
                                //Toast.makeText(SelectPortal.this, "No data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        myAdapter = new EmpAdapter(SelectPortal.this,employees);
                        employeelist.setAdapter(myAdapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                reset.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        myAdapter = new EmpAdapter(SelectPortal.this,listemp);
                        employeelist.setAdapter(myAdapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
/*----------------------------------------------------------------------------------------------------*/

        vis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView = (RecyclerView) findViewById(R.id.displaylist);
                database = FirebaseDatabase.getInstance().getReference("Visitor");
                listvis = new ArrayList<>();
                adapter = new CustomAdapter(SelectPortal.this,listvis);
                recyclerView.setLayoutManager(new LinearLayoutManager(SelectPortal.this));
                recyclerView.setAdapter(adapter);

                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            VisitorModel vs = dataSnapshot.getValue(VisitorModel.class);
                            listvis.add(vs);
                        }
                        adapter.notifyDataSetChanged();
                        i =  listvis.size();
                        percentage = listvis.size() / 50.0 * 100.0;
                        occupancy.setText( percentage + " % ");
                        capacity.setText( i +" Of 50 Visitors Max. ");
                        Log.d(TAG, "onDataChange: "+ i + "percentage"+ percentage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                lvl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ArrayList<VisitorModel> visitorModels = new ArrayList<>();
                        lv = adapterView.getItemAtPosition(i).toString();
                        Log.d(TAG, "onItemSelected: "+lv+listvis);
                        for (VisitorModel vis: listvis){
                            if (vis.getDestination() != null && vis.getDestination().equals(lv)){
                                visitorModels.add(vis);
                            }else {
                                //Toast.makeText(SelectPortal.this, "No data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        adapter = new CustomAdapter(SelectPortal.this,visitorModels);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ArrayList<VisitorModel> visitorModels = new ArrayList<>();
                        co = adapterView.getItemAtPosition(i).toString();
                        Log.d(TAG, "onItemSelected: "+co+listvis);
                        for (VisitorModel vis: listvis){
                            if (vis.getColor() != null && vis.getColor().equals(co)){
                                visitorModels.add(vis);
                            }else {
                                //Toast.makeText(SelectPortal.this, "No data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        adapter = new CustomAdapter(SelectPortal.this,visitorModels);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                reset.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        adapter = new CustomAdapter(SelectPortal.this,listvis);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
    }
}