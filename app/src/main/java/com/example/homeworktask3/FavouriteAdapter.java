package com.example.homeworktask3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteItemViewHolder> {
    public static ArrayList<Cat> catToAdapt;

    public void setData(ArrayList<Cat> catToAdapt) {
        this.catToAdapt = catToAdapt;
    }
    @NonNull
    @Override
    public FavouriteItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false);

        FavouriteItemViewHolder favouriteItemViewHolder = new FavouriteItemViewHolder(view);
        return favouriteItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteItemViewHolder holder, int position) {
        holder.breedText.setText(catToAdapt.get(position).getBreed());
        holder.descriptionText.setText("Description: " + catToAdapt.get(position).getDescription());
//        holder.tempText.setText(catToAdapt.get(position).getTemperament());
//        holder.weightText.setText(catToAdapt.get(position).getWeight());
//        holder.originText.setText(catToAdapt.get(position).getOrigin());
//        holder.wikiText.setText(catToAdapt.get(position).getWiki_link());
//        holder.lifeSpanText.setText(catToAdapt.get(position).getLife_span());
//        String doogFInt = Integer.toString(catToAdapt.get(position).getDog_friendly());
//        holder.dogText.setText(doogFInt);

    }

    @Override
    public int getItemCount() {
        return catToAdapt.size();
    }

    public static class FavouriteItemViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView breedText;
        public TextView descriptionText;
//        public TextView tempText;
//        public TextView weightText;
//        public TextView originText;
//        public TextView wikiText;
//        public TextView lifeSpanText;
//        public TextView dogText;

        public FavouriteItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            breedText = itemView.findViewById(R.id.fav_name);
            descriptionText = itemView.findViewById(R.id.fav_des);
//            tempText = itemView.findViewById(R.id.fav_temp);
//            weightText = itemView.findViewById(R.id.fav_weight);
//            originText = itemView.findViewById(R.id.fav_origin);
//            wikiText = itemView.findViewById(R.id.fav_wiki);
//            lifeSpanText = itemView.findViewById(R.id.fav_lifespan);
//            dogText = itemView.findViewById(R.id.fav_dog);

        }
    }
}
