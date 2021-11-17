package com.example.pedapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PedAdapterForSelectingList extends RecyclerView.Adapter<PedAdapterForSelectingList.PedViewHolder> {
    Context context;
    ArrayList<String> arrayList;

    public PedAdapterForSelectingList(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public PedAdapterForSelectingList.PedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.gamesitem, parent, false);
        return new PedAdapterForSelectingList.PedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedAdapterForSelectingList.PedViewHolder holder, int position) {
        String gamesHelperClass = arrayList.get(position);
        holder.gamename.setText(gamesHelperClass);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String documentname = holder.gamename.getText().toString();
                Intent intent = new Intent(view.getContext(), PresentList.class);
                // intent.putExtra("message", documentname);
                context.startActivity(intent);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("listnameped", documentname); //InputString: from the EditText
                editor.commit();
            }
        });






    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class PedViewHolder extends RecyclerView.ViewHolder{
        TextView gamename;



        public PedViewHolder(@NonNull View itemView) {
            super(itemView);
            gamename = itemView.findViewById(R.id.gamenameitem);
        }
    }
}

