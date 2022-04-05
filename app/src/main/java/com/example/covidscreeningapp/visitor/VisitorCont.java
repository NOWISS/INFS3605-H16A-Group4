package com.example.covidscreeningapp.visitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.covidscreeningapp.R;

public class VisitorCont extends AppCompatActivity {

    private Button btn;
    private RadioButton y1,y2,y3,y4,n1,n2,n3,n4,y5,n5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_cont);

        y1 = findViewById(R.id.y1);
        y2 = findViewById(R.id.y2);
        y3 = findViewById(R.id.y3);
        y4 = findViewById(R.id.y4);
        y5 = findViewById(R.id.y5);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
        n4 = findViewById(R.id.n4);
        n5 = findViewById(R.id.n5);
        btn = findViewById(R.id.button);

    }
    // To-do
    public void radioButtonhandler(View view) {

        // Decide what happens when a user clicks on a button
    }
}