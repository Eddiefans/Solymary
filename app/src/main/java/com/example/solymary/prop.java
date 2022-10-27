package com.example.solymary;

public class prop {
    public int id_prop;
    private String name;
    private String color;
    private int quantity_available;
    private int quantity_rented;
    private int rented;
    private int id_product;

    public prop() {

    }

    public int getId_prop() {
        return id_prop;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getQuantity_available() {
        return quantity_available;
    }

    public int getQuantity_rented() {
        return quantity_rented;
    }

    public int getRented() {
        return rented;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_prop(int id_prop) {
        this.id_prop = id_prop;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setQuantity_available(int quantity_available) {
        this.quantity_available = quantity_available;
    }

    public void setQuantity_rented(int quantity_rented) {
        this.quantity_rented = quantity_rented;
    }

    public void setRented(int rented) {
        this.rented = rented;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }
}
