package com.example.homeworktask3;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment{
    private RecyclerView recyclerView;
    private final String url = "https://api.thecatapi.com/v1/breeds?api_key=5e526be8-6f2a-491a-b64d-4e182f3b02e2";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private View view;
    private List<Cat> catsList;
    ArrayList<Cat> mcatsList=new ArrayList<Cat>();
    private MenuItem mSearchItem;
    private SearchView sv;
    public EditText searchEditText;
    SearchAdapter adapter = new SearchAdapter(getContext(), catsList);

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.rv_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        catsList = new ArrayList<>();
        FakeDatabase.saveCatToFakeDatabase(catsList);

        final SearchAdapter searchAdapter = new SearchAdapter(getContext(), catsList);
        searchAdapter.setData(catsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(searchAdapter);


        EditText searchEditText = view.findViewById(R.id.searchET);
        jsonRequest();
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        return view;
    }


    private void filter(String text) {
        ArrayList<Cat> filteredList = new ArrayList<>();

        for (Cat item : catsList) {
            if (item.getBreed().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        setuprecyclerview(filteredList);
        adapter.filterList(filteredList);
        System.out.println("hi");

    }


    private void jsonRequest(){
        request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++){
                    try{
                        jsonObject = response.getJSONObject(i);
                        Cat cat = new Cat();
                        cat.setCatId(jsonObject.getString("id"));
                        cat.setBreed(jsonObject.getString("name"));
                        cat.setDescription(jsonObject.getString("description"));
                        cat.setWeight(jsonObject.getString("weight"));
                        cat.setTemperament(jsonObject.getString("temperament"));
                        cat.setOrigin(jsonObject.getString("origin"));
                        cat.setLife_span(jsonObject.getString("life_span"));
                        cat.setWiki_link(jsonObject.getString("wikipedia_url"));
                        cat.setDog_friendly(jsonObject.getInt("dog_friendly"));
                        catsList.add(cat);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setuprecyclerview(catsList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request) ;

    }

    private void setuprecyclerview(List<Cat> catsList) {

        SearchAdapter searchAdapter = new SearchAdapter(getContext(), catsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(searchAdapter);

    }


}


