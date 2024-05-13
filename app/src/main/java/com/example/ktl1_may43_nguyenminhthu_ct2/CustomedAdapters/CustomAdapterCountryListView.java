package com.example.ktl1_may43_nguyenminhthu_ct2.CustomedAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.ktl1_may43_nguyenminhthu_ct2.DetailCountry;
import com.example.ktl1_may43_nguyenminhthu_ct2.Models.Country;
import com.example.ktl1_may43_nguyenminhthu_ct2.Models.World;
import com.example.ktl1_may43_nguyenminhthu_ct2.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

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

        return convertView;
    }
}
