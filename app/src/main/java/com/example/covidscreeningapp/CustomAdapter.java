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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<VisitorModel> list;



    CustomAdapter(Context context,ArrayList<VisitorModel> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row1, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        VisitorModel vs = list.get(position);
        holder.firstname_txt.setText(vs.getMobile());
        holder.lastname_txt.setText(vs.getCheckout());
        holder.mobile_txt.setText(vs.getCheckin());
        holder.destination_txt.setText(vs.getDestination());
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity_visitor.class);
                intent.putExtra("Checkin", vs.getCheckin());
                intent.putExtra("Checkout", vs.getCheckout());
                intent.putExtra("mobile", vs.getMobile());
                intent.putExtra("destination", vs.getDestination());
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
            id_txt = itemView.findViewById(R.id.id_vi);
            firstname_txt = itemView.findViewById(R.id.Firstname_vi);
            lastname_txt = itemView.findViewById(R.id.Lastname_vi);
            mobile_txt = itemView.findViewById(R.id.Mobile_vi);
            destination_txt = itemView.findViewById(R.id.Destination_vi);
            mainlayout = itemView.findViewById(R.id.mainLayout_vi);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainlayout.setAnimation(translate_anim);
        }
    }
}
