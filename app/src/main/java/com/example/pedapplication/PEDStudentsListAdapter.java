package com.example.pedapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class PEDStudentsListAdapter extends RecyclerView.Adapter<PEDStudentsListAdapter.MyViewHolder> {

    Context context;
    ArrayList<Students> studentsArrayList;


    public PEDStudentsListAdapter(Context context, ArrayList<Students> studentsArrayList) {
        this.context = context;
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ped_student_item, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//

        Students students = studentsArrayList.get(position);
        holder.name.setText(students.name);
        holder.dept.setText(students.department);
        holder.currentYear.setText(students.year);
        holder.gender.setText(students.gender);

        holder.rollno.setText(students.rollno);

//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        holder.status.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView name, dept, currentYear, gender, rollno;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.studentnameped);
            dept = itemView.findViewById(R.id.deptsingleitemped);
            currentYear = itemView.findViewById(R.id.currentyearped);
            gender = itemView.findViewById(R.id.genderitemped);
            rollno = itemView.findViewById(R.id.rollnoitemped);


        }

    }

}

