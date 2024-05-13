package com.example.ktl1_may43_nguyenminhthu_ct2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailCountry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_country);
        Intent intent = getIntent();
        String country = intent.getStringExtra("Country");
        String area = intent.getStringExtra("Area");
        String population = intent.getStringExtra("Population");
        String capital = intent.getStringExtra("Capital");
        String imageURL = intent.getStringExtra("imageURL");

        TextView textViewCountry = findViewById(R.id.textViewName);
        textViewCountry.setText("Country: " + country);

        TextView textViewArea = findViewById(R.id.textViewArea);
        textViewArea.setText("Area: " + area);

        TextView textViewPopulation = findViewById(R.id.textViewPopulation);
        textViewPopulation.setText("Population: " + population);

        TextView textViewCapital = findViewById(R.id.textViewCapital);
        textViewCapital.setText("Capital: " + capital);

        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load(imageURL).into(imageView);

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}