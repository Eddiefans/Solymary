package com.example.solymary;

public class costume {
    public int id_costume;
    private String name;
    private int rented;
    private int id_season;
    private int id_product;

    public costume() {

    }

    public int getId_costume() {
        return id_costume;
    }

    public String getName() {
        return name;
    }

    public int getRented() {
        return rented;
    }

    public int getId_season() {
        return id_season;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_costume(int id_costume) {
        this.id_costume = id_costume;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRented(int rented) {
        this.rented = rented;
    }

    public void setId_season(int id_season) {
        this.id_season = id_season;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }
}
