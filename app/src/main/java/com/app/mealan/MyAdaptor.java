package com.app.mealan;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {

    static Context context;
    static ArrayList<User> list;

    public MyAdaptor(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewlistings,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.number.setText(user.getNumber());
        holder.address.setText(user.getAddress());
        holder.food.setText(user.getFood());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView number, address, food;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            number = itemView.findViewById(R.id.number);
            address = itemView.findViewById(R.id.address);
            food = itemView.findViewById(R.id.food);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            User user = list.get(position);
            String id = user.getId();

            Intent intent = new Intent(context, Profile.class);
            context.startActivity(intent.putExtra("id",id));

        }


    }

}
