package com.example.pedapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PEDSelectGameActivity extends AppCompatActivity {
    PedAdapter myAdapter;
    ArrayList<String> arrayList;
    FirebaseFirestore firestore;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedselect_game);

        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.Rviewgames);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();

        myAdapter = new PedAdapter(PEDSelectGameActivity.this,arrayList);
        recyclerView.setAdapter(myAdapter);
        EventChangeListener();
        System.out.println(arrayList);








    }
    private void EventChangeListener() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        firestore.collection("captains")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {

                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                arrayList.add(dc.getDocument().toString());

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