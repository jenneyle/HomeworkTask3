package com.example.homeworktask3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class CatDetailActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView desTextView;
    private TextView tempTextView;
    private TextView weightTextView;
    private TextView originTextView;
    private TextView lifeSpanTextView;
    private TextView wikiTextView;
    private TextView dogTextView;
    private ImageView catImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_detail_activity_layout);

        final String id  = getIntent().getExtras().getString("id");
        final String name  = getIntent().getExtras().getString("name");
        final String description = getIntent().getExtras().getString("description");
        final String temperament = getIntent().getExtras().getString("temperament");
        final String weight = getIntent().getExtras().getString("weight");
        final String origin = getIntent().getExtras().getString("origin");
        final String wiki = getIntent().getExtras().getString("wikipedia_url");
        final String lifespan = getIntent().getExtras().getString("life_span");
        final int dogfriendly = getIntent().getExtras().getInt("dog_friendly");

        catImageView = findViewById(R.id.detailCatImage);
        nameTextView = findViewById(R.id.detailName);
        desTextView = findViewById(R.id.detailDes);
        tempTextView = findViewById(R.id.detailTemp);
        weightTextView = findViewById(R.id.detailWeight);
        originTextView = findViewById(R.id.detailOrigin);
        lifeSpanTextView = findViewById(R.id.detailLifeSpan);
        wikiTextView = findViewById(R.id.detailWikiLink);
        dogTextView = findViewById(R.id.detailDog);
        Button favouriteBtn = findViewById(R.id.favouriteBtn);

        nameTextView.setText(name);
        desTextView.setText("Description: "+ description);
        tempTextView.setText("Temperament: "+temperament);
        weightTextView.setText("Weight: " + weight.substring(1, weight.lastIndexOf("")-1));
        originTextView.setText("Origin: "+ origin);
        lifeSpanTextView.setText("Life Span: " + lifespan + " years");
        wikiTextView.setText("Wikipedia: "+wiki);
        dogTextView.setText("Dog Friendliness (1: not friendly - 5: extremely friendly): " + (Integer.toString(dogfriendly)));

        favouriteBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                final String idString = id;
                final String nameString = name;
                final String desString = description;
                final String tempString = temperament;
                final String weightString = weight;
                final String originString = origin;
                final String wikiString = wiki;
                final String lifespanString = lifespan;
                final int dogInt = dogfriendly;

                FavouriteFragment.catArrayList.add(new Cat(idString,nameString, desString, tempString, weightString, originString, wikiString, lifespanString, dogInt));
                FavouriteFragment.setCatArrayList(FavouriteFragment.catArrayList);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        final RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());

        final String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + id;


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Image[] catImage = gson.fromJson(response, Image[].class);
                String catUrl = "";

                for(int i = 0; i< catImage.length; i++){
                    catUrl = catImage[i].getUrl();

                }

                Glide.with(CatDetailActivity.this).load(catUrl).into(catImageView);

                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);

        requestQueue.add(stringRequest);

    }
}
