package com.example.homeworktask3;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    public static ArrayList<Cat> catArrayList = new ArrayList<Cat>();
    private RecyclerView recyclerView1;

    public static ArrayList<Cat> getCatArrayList() {
        return catArrayList;
    }

    public static void setCatArrayList(ArrayList<Cat> catArrayList) {
        FavouriteFragment.catArrayList = catArrayList;
    }

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView1 = view.findViewById(R.id.rv_favourite);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView1.setLayoutManager(layoutManager);
        final FavouriteAdapter favouriteAdapter = new FavouriteAdapter();
        favouriteAdapter.setData(catArrayList);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setAdapter(favouriteAdapter);
        return view;
    }


}
