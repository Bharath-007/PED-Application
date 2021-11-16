package com.example.pedapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PedAdapter extends RecyclerView.Adapter<PedAdapter.PedViewHolder> {
    Context context;
    ArrayList<GamesHelperClass> arrayList;

    public PedAdapter(Context context, ArrayList<GamesHelperClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PedAdapter.PedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.gamesitem, parent, false);
        return new PedAdapter.PedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedAdapter.PedViewHolder holder, int position) {
        GamesHelperClass gamesHelperClass = arrayList.get(position);
        holder.gamename.setText(gamesHelperClass.game);






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

