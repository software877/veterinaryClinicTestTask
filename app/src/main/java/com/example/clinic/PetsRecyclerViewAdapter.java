package com.example.clinic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinic.models.PetsModel;

import java.util.ArrayList;

public class PetsRecyclerViewAdapter extends RecyclerView.Adapter<PetsRecyclerViewHolder> {

    private ArrayList<PetsModel> pets;
    public @Nullable OnPetClickListener listener;

    @NonNull
    @Override
    public PetsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pets_recycler_view_layout, parent, false);
        return new PetsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetsRecyclerViewHolder holder, int position) {
        if (pets != null) {
            holder.petText.setText(pets.get(position).title);
            new ImageDownloader(holder.petImage)
                    .execute(pets.get(position).imageUrl);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(pets.get(position).contentUrl);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setPets(ArrayList<PetsModel> pets) {
        this.pets = pets;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (pets == null) return 0;
        return pets.size();
    }
}

interface OnPetClickListener {
    void onClick(String contentUrl);
}


class PetsRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView petImage;
    public TextView petText;

    public PetsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        petText = itemView.findViewById(R.id.petText);
        petImage = itemView.findViewById(R.id.petImage);
    }
}