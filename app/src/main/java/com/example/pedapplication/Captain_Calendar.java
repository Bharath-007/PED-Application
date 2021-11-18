package com.example.pedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class Captain_Calendar extends AppCompatActivity {

    CalendarView calendarView;
    TextView selectedDate;
    Button nextbtn;

    FirebaseFirestore firestore;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ped_calender);

        date = "";
        nextbtn = findViewById(R.id.calenderNxtbtn);
        firestore = FirebaseFirestore.getInstance();
        calendarView = findViewById(R.id.calender);
        selectedDate = findViewById(R.id.selectedDate);
        nextbtn = findViewById(R.id.calenderNxtbtn);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "." + i1 + "." + i;
                selectedDate.setText("Selected Date is : " + date);


            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please select a date to proceed", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("date2", date); //InputString: from the EditText
                editor.commit();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });





    }

}