package com.example.ktl1_may43_nguyenminhthu_ct2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ktl1_may43_nguyenminhthu_ct2.CustomedAdapters.CustomAdapterCountryListView;
import com.example.ktl1_may43_nguyenminhthu_ct2.Models.Country;
import com.example.ktl1_may43_nguyenminhthu_ct2.Models.World;
import com.example.ktl1_may43_nguyenminhthu_ct2.databinding.FragmentCountriesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountriesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CountriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CountriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CountriesFragment newInstance(String param1, String param2) {
        CountriesFragment fragment = new CountriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentCountriesBinding fragmentCountriesBinding;
    private String searchString = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCountriesBinding = FragmentCountriesBinding.inflate(getLayoutInflater());
        return fragmentCountriesBinding.getRoot();
    }

    private ArrayList<World> list;
    private ArrayList<String> listContinents = new ArrayList<>();
    private ArrayList<Country> currentCountries = new ArrayList<>();
    private String currentContinent = "";
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        addEvents();
    }
    private void init(){
        list = getDataFromJSON();

        ArrayAdapter<String> adapterContinents = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listContinents);
        fragmentCountriesBinding.spinnerContinents.setAdapter(adapterContinents);
    }
    private void addEvents(){
        fragmentCountriesBinding.spinnerContinents.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentContinent = listContinents.get(position);

                ArrayList<Country> countries = null;
                for (World world : list) {
                    if (world.getContinentName().equals(currentContinent)) {
                        countries = world.getCountries();
                        break;
                    }
                }
                currentCountries = countries;
                if (countries != null) {
                    CustomAdapterCountryListView adapterCountryListView = new CustomAdapterCountryListView(getContext(), R.layout.custom_country_in_listview, countries);
                    fragmentCountriesBinding.listViewItems.setAdapter(adapterCountryListView);
                } else {
                    Toast.makeText(getContext(), "No countries found for " + currentContinent, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fragmentCountriesBinding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchString = fragmentCountriesBinding.editTextSearch.getText().toString();
                if(searchString != ""){
                    ArrayList<Country> countries = null;
                    for (World world : list) {
                        if (world.getContinentName().equals(currentContinent)) {
                            countries = world.getCountries();
                            break;
                        }
                    }

                    if (countries != null) {
                        ArrayList<Country> countriesSearch = new ArrayList<>();
                        for (Country country : countries) {
                            if (country.getCountry().contains(searchString)) {
                                countriesSearch.add(country);
                                break;
                            }
                        }
                        if(countriesSearch != null || !countriesSearch.isEmpty()){
                            currentCountries = countriesSearch;
                            CustomAdapterCountryListView adapterCountryListView = new CustomAdapterCountryListView(getContext(), R.layout.custom_country_in_listview, countriesSearch);
                            fragmentCountriesBinding.listViewItems.setAdapter(adapterCountryListView);
                        }
                        else {
                            Toast.makeText(getContext(), "No countries found for " + searchString, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "No countries found for " + searchString, Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    ArrayList<Country> countries = null;
                    for (World world : list) {
                        if (world.getContinentName().equals(currentContinent)) {
                            countries = world.getCountries();
                            break;
                        }
                    }
                    currentCountries = countries;

                    if (countries != null) {
                        CustomAdapterCountryListView adapterCountryListView = new CustomAdapterCountryListView(getContext(), R.layout.custom_country_in_listview, countries);
                        fragmentCountriesBinding.listViewItems.setAdapter(adapterCountryListView);
                    } else {
                        Toast.makeText(getContext(), "No countries found for " + currentContinent, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        fragmentCountriesBinding.buttonRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
        fragmentCountriesBinding.listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country item = currentCountries.get(position);
                Intent intent = new Intent(getContext(), DetailCountry.class);
                intent.putExtra("Country", item.getCountry());
                intent.putExtra("Area", item.getArea());
                intent.putExtra("Population", item.getPopulation());
                intent.putExtra("Capital", item.getCapital());
                intent.putExtra("imageURL", item.getImageURL());
                getContext().startActivity(intent);
            }
        });
        fragmentCountriesBinding.listViewItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Country item = currentCountries.get(position);
                showDeleteDialog(item);
                return false;
            }
        });
    }
    private ArrayList<World> getDataFromJSON() {
        ArrayList<World> list = new ArrayList<>();
        listContinents = new ArrayList<>();
        try {
            InputStream inputStream = getContext().openFileInput("data.json");
            int size = inputStream.available();
            byte[] data = new byte[size];
            inputStream.read(data);
            inputStream.close();
            String jsonString = new String(data, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonWorlds = jsonObject.getJSONArray("Worlds");

            for (int i = 0; i < jsonWorlds.length(); i++) {
                JSONObject jsonWorld = jsonWorlds.getJSONObject(i);
                Iterator<String> keys = jsonWorld.keys();
                while (keys.hasNext()) {
                    String continentName = keys.next();
                    JSONArray jsonCountries = jsonWorld.getJSONArray(continentName);
                    listContinents.add(continentName);
                    ArrayList<Country> countries = new ArrayList<>();
                    for (int j = 0; j < jsonCountries.length(); j++) {
                        JSONObject jsonCountry = jsonCountries.getJSONObject(j);
                        String countryName = jsonCountry.getString("Country");
                        String area = jsonCountry.getString("Area");
                        String population = jsonCountry.getString("Population");
                        String capital = jsonCountry.getString("Capital");
                        String imageURL = jsonCountry.getString("imageURL");

                        Country country = new Country(countryName, area, population, capital, imageURL);
                        countries.add(country);
                    }

                    World world = new World(continentName, countries);
                    list.add(world);
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    private void showDeleteDialog(Country country) {
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.delete_options_bottom_sheet_dialog_layout, null);
        dialog.setContentView(view);

        LinearLayout layoutYes = dialog.findViewById(R.id.layoutYes);
        LinearLayout layoutNo = dialog.findViewById(R.id.layoutNo);
        layoutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (World world : list) {
                    if (world.getContinentName().equals(country.getCountry())) {
                        world.getCountries().remove(country);
                        break;
                    }
                }
                currentCountries.remove(country);
                CustomAdapterCountryListView adapterCountryListView = new CustomAdapterCountryListView(getContext(), R.layout.custom_country_in_listview, currentCountries);
                fragmentCountriesBinding.listViewItems.setAdapter(adapterCountryListView);
                dialog.dismiss();
            }
        });
        layoutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}