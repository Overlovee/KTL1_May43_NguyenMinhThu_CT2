package com.example.ktl1_may43_nguyenminhthu_ct2.CustomedAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.ktl1_may43_nguyenminhthu_ct2.Models.Country;
import com.example.ktl1_may43_nguyenminhthu_ct2.R;

import java.util.ArrayList;

public class CustomAdapterCountryListView extends ArrayAdapter {
    Context context;
    int layoutItem;
    ArrayList<Country> list = new ArrayList<>();
    public CustomAdapterCountryListView(@NonNull Context context, int resource, @NonNull ArrayList<Country> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutItem = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Country item = list.get(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(layoutItem,null);

        }
        TextView textViewName = (TextView) convertView.findViewById(R.id.textViewName);
        textViewName.setText(item.getCountry());

        ImageView imageViewItem = (ImageView) convertView.findViewById(R.id.imageViewAVT);
        Glide.with(context).load(item.getImageURL()).into(imageViewItem);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
}
