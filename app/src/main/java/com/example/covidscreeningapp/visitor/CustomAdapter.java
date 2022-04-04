package com.example.covidscreeningapp.visitor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidscreeningapp.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id, firstname, lastname, mobile, destination;
    private ArrayList idFiltered, firstnameFiltered, lastnameFilterd, mobileFiltered, destinationFiltered;


    CustomAdapter(Context context,ArrayList id, ArrayList firstname, ArrayList lastname, ArrayList mobile, ArrayList destination){
        this.context = context;
        this.id = id;
        this.idFiltered = id;
        this.firstname = firstname;
        this.firstnameFiltered = firstname;
        this.lastname = lastname;
        this.lastnameFilterd = lastname;
        this.mobile = mobile;
        this.mobileFiltered = mobile;
        this.destination = destination;
        this.destinationFiltered = destination;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.firstname_txt.setText(String.valueOf(firstname.get(position)));
        holder.lastname_txt.setText(String.valueOf(lastname.get(position)));
        holder.mobile_txt.setText(String.valueOf(mobile.get(position)));
        holder.destination_txt.setText(String.valueOf(destination.get(position)));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("firstname", String.valueOf(firstname.get(position)));
                intent.putExtra("lastname", String.valueOf(lastname.get(position)));
                intent.putExtra("mobile", String.valueOf(mobile.get(position)));
                intent.putExtra("destination", String.valueOf(destination.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, firstname_txt, lastname_txt, mobile_txt, destination_txt;
        LinearLayout mainlayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id);
            firstname_txt = itemView.findViewById(R.id.Firstname);
            lastname_txt = itemView.findViewById(R.id.Lastname);
            mobile_txt = itemView.findViewById(R.id.Mobile);
            destination_txt = itemView.findViewById(R.id.Destination);
            mainlayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainlayout.setAnimation(translate_anim);
        }
    }
}
