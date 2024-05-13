package com.example.ktl1_may43_nguyenminhthu_ct2.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class World {
    private String continentName;
    private ArrayList<Country> countries;

    public World(String continentName, ArrayList<Country> countries) {
        this.continentName = continentName;
        this.countries = countries;
    }

    // Getters and setters
    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }
}

