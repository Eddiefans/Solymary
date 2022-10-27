package com.example.solymary;

public class client {
    public int id_client;
    private String names;
    private String lastname;
    private String address;
    private String phone;

    public client() {

    }

    public int getId_client() {
        return id_client;
    }

    public String getNames() {
        return names;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
