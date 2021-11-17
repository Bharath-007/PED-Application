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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements AbsenteesListener, ODListener, RestListener, PresentListener {

    Context context;
    ArrayList<Students> studentsArrayList, absentees, odList, restList, presentList;
    String getDetail;
    AbsenteesListener absenteesListener;
    PresentListener presentListener;
    RestListener restListener;
    ODListener odListener;


    public MyAdapter(Context context, ArrayList<Students> studentsArrayList,AbsenteesListener absenteesListener,RestListener restListener,ODListener odListener, PresentListener presentListener) {
        this.context = context;
        this.studentsArrayList = studentsArrayList;
        this.absenteesListener = absenteesListener;
        this.presentListener = presentListener;
        this.odListener = odListener;
        this.restListener = restListener;
    }
    public MyAdapter(Context context, ArrayList<Students> studentsArrayList) {
        this.context = context;
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.studentitem, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//


        getDetail = "Present";

        absentees = new ArrayList<>();
        odList = new ArrayList<>();
        restList = new ArrayList<>();

        presentList = new ArrayList<>();

        Students students = studentsArrayList.get(position);
        holder.name.setText(students.name);
        holder.dept.setText(students.department);
        holder.currentYear.setText(students.year);
        holder.gender.setText(students.gender);

        holder.rollno.setText(students.rollno);

//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        holder.status.setAdapter(adapter);

        holder.status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                holder.status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        getDetail = holder.status.getItemAtPosition(i).toString();
                        if (getDetail.equalsIgnoreCase("present")) {
                            presentList.add(studentsArrayList.get(position));
                        } else {
                            presentList.remove(studentsArrayList.get(position));
                        }
                        presentListener.onPresentQuantityChange(presentList);
                        if (getDetail.equalsIgnoreCase("absent")) {
                            absentees.add(studentsArrayList.get(position));
                        } else {
                            absentees.remove(studentsArrayList.get(position));
                        }
                        absenteesListener.onAbsenteesQuantityChange(absentees);
                        if (getDetail.equalsIgnoreCase("od")) {
                            odList.add(studentsArrayList.get(position));
                        } else {
                            odList.remove(studentsArrayList.get(position));
                        }
                        odListener.onODQuantityChange(odList);
                        if (getDetail.equalsIgnoreCase("rest")) {
                            restList.add(studentsArrayList.get(position));
                        } else {
                            restList.remove(studentsArrayList.get(position));
                        }
                        restListener.onRestQuantityChange(restList);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    @Override
    public int getItemCount() {
        return studentsArrayList.size();
    }

    @Override
    public void onAbsenteesQuantityChange(ArrayList<Students> arrayList) {

    }

    @Override
    public void onODQuantityChange(ArrayList<Students> arrayList) {

    }

    @Override
    public void onPresentQuantityChange(ArrayList<Students> arrayList) {

    }

    @Override
    public void onRestQuantityChange(ArrayList<Students> arrayList) {

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView name, dept, currentYear, gender, rollno;
        Spinner status;

        ArrayAdapter<CharSequence> adapter;
        HashMap<String, String> hm;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.studentnamesingleitem);
            status = (Spinner) itemView.findViewById(R.id.statusview);
            dept = itemView.findViewById(R.id.deptsingleitem);
            currentYear = itemView.findViewById(R.id.currentyear);
            gender = itemView.findViewById(R.id.genderitem);
            rollno = itemView.findViewById(R.id.rollnoitem);


            adapter = ArrayAdapter.createFromResource(itemView.getContext(), R.array.status, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            status.setAdapter(adapter);


        }

    }


}
