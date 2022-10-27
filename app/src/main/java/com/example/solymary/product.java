package com.example.solymary;

public class product {
    public int id_product;
    private String stock_status;
    private int id_size;

    public product() {

    }

    public int getId_product() {
        return id_product;
    }

    public String getStock_status() {
        return stock_status;
    }

    public int getId_size() {
        return id_size;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    public void setId_size(int id_size) {
        this.id_size = id_size;
    }
}
