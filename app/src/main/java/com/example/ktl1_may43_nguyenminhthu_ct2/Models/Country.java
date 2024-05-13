package com.example.ktl1_may43_nguyenminhthu_ct2.Models;

public class Country {
    private String Country;
    private String Area;
    private String Population;
    private String Capital;
    private String imageURL;
    public Country() {
        this.Country = "Nah";
        this.Area = "Nah";
        this.Population = "0";
        this.Capital = "Nah";
        this.imageURL = "Nah";
    }
    public Country(String country, String area, String population, String capital, String imageURL) {
        this.Country = country;
        this.Area = area;
        this.Population = population;
        this.Capital = capital;
        this.imageURL = imageURL;
    }

    // Getters and setters
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getPopulation() {
        return Population;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
