package com.example.pedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class PedCalender extends AppCompatActivity {

    CalendarView calendarView;
    TextView selectedDate;
    Button nextbtn;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ped_calender);

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



    }

}