package com.example.solymary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Interface_screens{


    TextView loading;
    LinearLayout nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataContainer dataContainer = DataContainer.get(getApplicationContext());
        dataContainer.triggerGetsQuery(getApplicationContext().getString(R.string.client), this, "1");
        loading = findViewById(R.id.loading);
        nav = findViewById(R.id.bar);
        nav.setVisibility(View.INVISIBLE);
    }

    @Override
    public void nav(int option, int type) {
        if(option!=1){
            Intent in_option = new Intent(this, DataListActivity.class);
            in_option.putExtra("option", option);
            in_option.putExtra("type", 0);
            startActivity(in_option);
        }

    }

    @Override
    public void callback(String cas) {
        DataContainer dataContainer = DataContainer.get(getApplicationContext());
        switch (cas){
            case "1":{
                dataContainer.triggerGetsQuery(getApplicationContext().getString(R.string.costume), this, "3");
                break;
            }
            case "3":{
                dataContainer.triggerGetsQuery(getApplicationContext().getString(R.string.rent), this, "4");
                break;
            }
            case "4":{
                dataContainer.triggerGetsQuery(getApplicationContext().getString(R.string.size), this, "5");
                break;
            }
            case "5":{
                dataContainer.triggerGetsQuery(getApplicationContext().getString(R.string.season), this, "6");
                break;
            }
            case "6":{
                dataContainer.triggerGetsQuery(getApplicationContext().getString(R.string.product), this, "7");
                break;
            }
            case "7":{
                dataContainer.triggerGetsQuery(getApplicationContext().getString(R.string.prop), this, "8");
                break;
            }
            case "8":{
                nav.setVisibility(View.VISIBLE);
                loading.setVisibility(View.INVISIBLE);
                loading.setText("");
                break;
            }
        }
    }

    @Override
    public void change(int type, int id_client) {

    }


}
