package com.example.pedapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PresentList extends AppCompatActivity {
    ArrayList<Students> arrayList;
    RecyclerView recyclerView;
    PEDStudentsListAdapter myAdapter;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_list);


        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        String listname = prefs1.getString("listnameped", "no_id");

        recyclerView = findViewById(R.id.Rviewpresent);
        arrayList = new ArrayList<>();

        System.out.println(listname);





        firestore = FirebaseFirestore.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EventChangeListener();
        System.out.println(arrayList);
        myAdapter = new PEDStudentsListAdapter(PresentList.this, arrayList);
        recyclerView.setAdapter(myAdapter);




    }

    private void EventChangeListener() {


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String gamename = prefs.getString("gamenameped", "no_id");

        SharedPreferences prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        String listname = prefs1.getString("listnameped", "no_id");

        SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(this);
        String date = prefs2.getString("date1", "no_id");

        System.out.println(listname);
        System.out.println(date);
        System.out.println(gamename);


        firestore.collection("captains").document(gamename).collection("Attendance").document(date).collection(listname.replace(" ","")).orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {

                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                arrayList.add(dc.getDocument().toObject(Students.class));

                            }

                            myAdapter.notifyDataSetChanged();


                        }
//                        if (arrayList.size() == 0) {
//                            if (progressDialog.isShowing()) {
//                                progressDialog.dismiss();
//                            }
//                        }


                    }
                });


    }
}