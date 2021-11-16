package com.example.pedapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PedAdapter extends RecyclerView.Adapter<PedAdapter.PedViewHolder> {
    Context context;
    ArrayList<String> arrayList;

    public PedAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PedAdapter.PedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.studentitem, parent, false);
        return new PedAdapter.PedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedAdapter.PedViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class PedViewHolder extends RecyclerView.ViewHolder{
        TextView name, dept, currentYear, gender, rollno;



        public PedViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studentnameped);
            dept = itemView.findViewById(R.id.deptsingleitemped);
            currentYear = itemView.findViewById(R.id.currentyearped);
            gender = itemView.findViewById(R.id.genderitemped);
            rollno = itemView.findViewById(R.id.rollnoitemped);
        }
    }
}

