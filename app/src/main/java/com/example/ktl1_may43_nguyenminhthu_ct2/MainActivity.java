package com.example.ktl1_may43_nguyenminhthu_ct2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.ktl1_may43_nguyenminhthu_ct2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        setSupportActionBar(activityMainBinding.toolbar);
        activityMainBinding.bottomNavigationMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                int id = item.getItemId();
                if (id == R.id.navigation_countries) {
                    fragment = new CountriesFragment();
                    activityMainBinding.textViewFragmentTitle.setVisibility(View.GONE);
                    activityMainBinding.searchBar.setVisibility(View.VISIBLE);
                    activityMainBinding.searchBar.setIconifiedByDefault(false);
                }
                else {
                    activityMainBinding.textViewFragmentTitle.setVisibility(View.VISIBLE);
                    activityMainBinding.searchBar.setVisibility(View.GONE);
                    if (id == R.id.navigation_home) {
                        fragment = new HomeFragment();
                    } else if (id == R.id.navigation_countries) {
                        fragment = new CountriesFragment();
                    } else {
                        return false;
                    }
                }
                String title = item.getTitle().toString();
                activityMainBinding.textViewFragmentTitle.setText(title);
                loadFragment(fragment);
                return true;
            }
        });
        activityMainBinding.bottomNavigationMain.setSelectedItemId(R.id.navigation_home);
        loadFragment(new HomeFragment());
        AddEvents();
    }
    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(activityMainBinding.frameFragment.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    void AddEvents(){
        activityMainBinding.searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityMainBinding.searchBar.setIconifiedByDefault(false);
            }
        });
        activityMainBinding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                activityMainBinding.searchBar.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    performSearch("");
                }
                return false;
            }

        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private void performSearch(String searchString) {
        CountriesFragment searchFragment = new CountriesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("search_string", searchString);
        searchFragment.setArguments(bundle);
        loadFragment(searchFragment);
    }
}