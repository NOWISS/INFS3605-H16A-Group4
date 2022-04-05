package com.example.covidscreeningapp;

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

import java.util.ArrayList;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Employee> list;


   public EmpAdapter(Context context, ArrayList<Employee> list){
        this.context = context;
        this.list = list;
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

        Employee emp = list.get(position);
        holder.firstname_txt.setText(emp.getFirstname());
        holder.lastname_txt.setText(emp.getLastname());
        holder.mobile_txt.setText(emp.getMobile());
        holder.destination_txt.setText(emp.getDestination());
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("firstname", emp.getFirstname());
                intent.putExtra("lastname", emp.getLastname());
                intent.putExtra("mobile", emp.getMobile());
                intent.putExtra("destination", emp.getDestination());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
