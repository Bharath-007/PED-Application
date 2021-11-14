package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddStudent extends AppCompatActivity {


    ArrayAdapter adapter1,adapter2,adapter3,adapter4;
    EditText studentname,rollno;
    EditText l3,l4,l5,l6;
    AutoCompleteTextView department,batch,year,gender;
    Button nextbtn,finishbtn,backbtn;
    FirebaseFirestore firestore;
    HashMap<String,String> hm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String data = prefs.getString("message","no_id");
        //hooks
        studentname = findViewById(R.id.studentname);
        rollno = findViewById(R.id.rollno);

        department = findViewById(R.id.department);
        batch = findViewById(R.id.batch);
        year = findViewById(R.id.year);
        gender = findViewById(R.id.gender);

        l3 = findViewById(R.id.department);
        l4 = findViewById(R.id.batch);
        l5 = findViewById(R.id.year);
        l6 = findViewById(R.id.gender);

        nextbtn = findViewById(R.id.nextbtn);
        finishbtn = findViewById(R.id.finishbutton);
        backbtn = findViewById(R.id.backbtn);


        hm = new HashMap<>();

        firestore = FirebaseFirestore.getInstance();
        //dropdown for department name
        adapter1 = ArrayAdapter.createFromResource(this, R.array.departmentnames, android.R.layout.simple_list_item_1);
        department.setAdapter(adapter1);
        department.setFocusable(false);

        //dropdown for batch
        adapter2 = ArrayAdapter.createFromResource(this, R.array.batch, android.R.layout.simple_selectable_list_item);
        batch.setAdapter(adapter2);
        batch.setFocusable(false);
        //dropdown for current year

        adapter3 = ArrayAdapter.createFromResource(this, R.array.currentYear, android.R.layout.simple_list_item_1);
        year.setAdapter(adapter3);
        year.setFocusable(false);

        adapter4 = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_list_item_1);
        gender.setAdapter(adapter4);
        gender.setFocusable(false);

        //convert edittext to String
        String studentName = studentname.getText().toString();
        String rollNo = rollno.getText().toString().trim();
        String department1 = department.getText().toString();
        String batch1 = batch.getText().toString();
        String year1 = year.getText().toString();
        String gender1 = gender.getText().toString();

        hm.put("name",studentName);
        hm.put("rollno",rollNo);
        hm.put("department",department1);
        hm.put("batch",batch1);
        hm.put("year",year1);

        nextbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddStudent.class));
                DocumentReference documentReference = firestore.document("captains").collection(data).document();
                documentReference.set(hm);


            }
        });
        finishbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                DocumentReference documentReference = firestore.document("captains").collection(data).document();
                documentReference.set(hm);

            }
        });




    }
}