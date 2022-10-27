package com.example.solymary;

public class rent {
    public int id_rent;
    private int day_give;
    private int month_give;
    private int year_give;
    private int day_return;
    private int month_return;
    private int year_return;
    private int days;
    private int price;
    private int quantity;
    private int id_client;
    private int id_product;

    public rent(){
    }

    public int getId_rent() {
        return id_rent;
    }

    public int getDay_give() {
        return day_give;
    }

    public int getMonth_give() {
        return month_give;
    }

    public int getYear_give() {
        return year_give;
    }

    public int getDay_return() {
        return day_return;
    }

    public int getMonth_return() {
        return month_return;
    }

    public int getYear_return() {
        return year_return;
    }

    public int getDays() {
        return days;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_rent(int id_rent) {
        this.id_rent = id_rent;
    }

    public void setDay_give(int day_give) {
        this.day_give = day_give;
    }

    public void setMonth_give(int month_give) {
        this.month_give = month_give;
    }

    public void setYear_give(int year_give) {
        this.year_give = year_give;
    }

    public void setDay_return(int day_return) {
        this.day_return = day_return;
    }

    public void setMonth_return(int month_return) {
        this.month_return = month_return;
    }

    public void setYear_return(int year_return) {
        this.year_return = year_return;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }
}
