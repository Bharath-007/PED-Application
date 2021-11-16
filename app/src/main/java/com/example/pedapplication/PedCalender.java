package com.example.pedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class PedCalender extends AppCompatActivity {

    CalendarView calendarView;
    TextView selectedDate;
    Button nextbtn;

    FirebaseFirestore firestore;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ped_calender);

        nextbtn = findViewById(R.id.calenderNxtbtn);
        firestore = FirebaseFirestore.getInstance();
        calendarView = findViewById(R.id.calender);
        selectedDate = findViewById(R.id.selectedDate);
        nextbtn = findViewById(R.id.calenderNxtbtn);
        date = "";

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
                startActivity(new Intent(getApplicationContext(),PEDSelectGameActivity.class));
            }
        });



    }

}