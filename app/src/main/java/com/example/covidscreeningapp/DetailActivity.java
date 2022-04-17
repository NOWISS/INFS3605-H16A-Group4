package com.example.covidscreeningapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.covidscreeningapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "Employee Detail";
    TextView fname, lname, mobile, location;
    Button delete_button,Okay;
    String fnamee, lnamee, mobilee, locationn,vax,result;
    private ImageView lefticon,showvax,showresult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fname = findViewById(R.id.fname1);
        lname = findViewById(R.id.lname1);
        mobile = findViewById(R.id.mb1);
        location = findViewById(R.id.location);
        delete_button = findViewById(R.id.btndelete);
        showvax = findViewById(R.id.showvax);
        showresult = findViewById(R.id.showresult);
        getAndSetIntentData();

        lefticon = findViewById(R.id.back);
        // Make the return button
        lefticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DetailActivity.this,SelectPortal.class);
                startActivity(intent1);
            }
        });


        // To-do
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("firstname") &&
                getIntent().hasExtra("lastname") && getIntent().hasExtra("mobile")&& getIntent().hasExtra("destination")){
            //Getting Data from Intent
            fnamee = getIntent().getStringExtra("firstname");
            lnamee = getIntent().getStringExtra("lastname");
            mobilee = getIntent().getStringExtra("mobile");
            locationn = getIntent().getStringExtra("destination");
            vax = getIntent().getStringExtra("vax");
            result = getIntent().getStringExtra("result");

            //Setting Intent Data
            fname.setText("First Name: "+ fnamee);
            lname.setText("Last Name: "+lnamee);
            mobile.setText("Mobile Number: "+mobilee);
            location.setText("Location: "+locationn);
            Glide.with(DetailActivity.this)
                    .load(vax)
                    .fitCenter()
                    .into(showvax);
            Glide.with(DetailActivity.this)
                    .load(result)
                    .fitCenter()
                    .into(showresult);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        Query query = ref.child("Employee").orderByChild("mobile").equalTo(mobilee);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot DeSnapshot: dataSnapshot.getChildren()) {
                                    DeSnapshot.getRef().removeValue();
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e(TAG, "onCancelled", databaseError.toException());
                            }
                        });
                        dialog.dismiss();
                        Intent intentdelete = new Intent(DetailActivity.this,SelectPortal.class);
                        startActivity(intentdelete);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }
}