package com.example.homeworktask3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchItemViewHolder> {
    public static List<Cat> catsToAdapt;
    public static ArrayList<Cat> arrayList;
    private List<Cat> copyList;
    private Context mContext ;
    private Button searchBtn;


    public SearchAdapter(Context mContext, List<Cat> catsToAdapt) {
        this.mContext = mContext;
        this.catsToAdapt = catsToAdapt;
    }

    public SearchAdapter(List<Cat> catsList){
    }

    public void setData(List<Cat> catsToAdapt) {
        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item, parent, false);

        SearchItemViewHolder searchItemViewHolder = new SearchItemViewHolder(view);
        return searchItemViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, final int position) {
        holder.breedTextView.setText(catsToAdapt.get(position).getBreed());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = view.getContext();
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("id", catsToAdapt.get(position).getCatId());
                intent.putExtra("name", catsToAdapt.get(position).getBreed());
                intent.putExtra("description", catsToAdapt.get(position).getDescription());
                intent.putExtra("temperament", catsToAdapt.get(position).getTemperament());
                intent.putExtra("weight", catsToAdapt.get(position).getWeight());
                intent.putExtra("origin", catsToAdapt.get(position).getOrigin());
                intent.putExtra("life_span", catsToAdapt.get(position).getLife_span());
                intent.putExtra("wikipedia_url", catsToAdapt.get(position).getWiki_link());
                intent.putExtra("dog_friendly", catsToAdapt.get(position).getDog_friendly());
                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }



    public static class SearchItemViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView breedTextView;

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            breedTextView = itemView.findViewById(R.id.breed_name);


        }
    }

    public void filterList(ArrayList<Cat> filteredList) {
        catsToAdapt = filteredList;
        notifyDataSetChanged();
    }


}
