package com.example.pedapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Students> studentsArrayList;

    public MyAdapter(Context context, ArrayList<Students> studentsArrayList) {
        this.context = context;
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.studentitem,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Students students = studentsArrayList.get(position);
        holder.name.setText(students.name);
        holder.dept.setText(students.department);
        holder.currentYear.setText(students.year);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String documentname = holder.name.getText().toString()+holder.dept.getText().toString()+ holder.currentYear.getText().toString();
                //Intent intent = new Intent(view.getContext(), AttendancePage.class);
               // intent.putExtra("message", documentname);
                //context.startActivity(intent);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("message", documentname); //InputString: from the EditText
                editor.commit();
                SharedPreferences preff = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor1 = prefs.edit();
                editor.putString("message2", documentname); //InputString: from the EditText
                editor.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,dept,currentYear;
        AutoCompleteTextView status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studentnamesingleitem);
            status = itemView.findViewById(R.id.statusview);
            dept = itemView.findViewById(R.id.deptsingleitem);
            currentYear = itemView.findViewById(R.id.currentyear);

        }
    }



}
