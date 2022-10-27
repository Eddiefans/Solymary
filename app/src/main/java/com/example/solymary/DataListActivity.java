package com.example.solymary;

import androidx.appcompat.app.AppCompatActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class DataListActivity extends AppCompatActivity implements Interface_screens{


    public int option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        Bundle options=getIntent().getExtras();
        FragmentManager manager_screens = getFragmentManager();
        FragmentTransaction transaction_screens = manager_screens.beginTransaction();
        option =  options.getInt("option");
        int type = options.getInt("type");
        switch (option){
            case 2:{
                transaction_screens.replace(R.id.list_contained, new RentListFragment());
                break;
            }
            case 4:{
                transaction_screens.replace(R.id.list_contained, new ClientListFragment());
                break;
            }
            case 5:{
                if(type==0){
                    transaction_screens.replace(R.id.list_contained, new CostumeListFragment());
                }else {
                    transaction_screens.replace(R.id.list_contained, new PropListFragment());
                }
                break;
            }
            case 6:{
                int id_client = options.getInt("id_client");
                if(type==0){
                    transaction_screens.replace(R.id.list_contained, CostumeListSelect.newInstance(id_client));
                }else{
                    transaction_screens.replace(R.id.list_contained, PropListSelect.newInstance(id_client));
                }
                break;
            }
            case 7:{
                transaction_screens.replace(R.id.list_contained, ClientListSelect.newInstance());
                break;
            }
        }
        transaction_screens.commit();
    }



    @Override
    public void nav(int option, int type) {
        switch(option){
            case 1: {
                finish();
                break;
            }
            case 2:{
                Intent MenuSelection = new Intent(this, DataListActivity.class);
                MenuSelection.putExtra("option", option);
                startActivity(MenuSelection);
                MenuSelection.putExtra("type", type);
                finish();
                break;

            }
            case 4:{
                Intent MenuSelection = new Intent(this, DataListActivity.class);
                MenuSelection.putExtra("option", option);
                startActivity(MenuSelection);
                MenuSelection.putExtra("type", type);
                finish();
                break;
            }
            case 5:{
                Intent MenuSelection = new Intent(this, DataListActivity.class);
                MenuSelection.putExtra("option", option);
                MenuSelection.putExtra("type", type);
                startActivity(MenuSelection);
                finish();
                break;
            }
        }
    }

    @Override
    public void callback(String cas) {}

    @Override
    public void change(int type, int id_client){
        Intent MenuSelection = new Intent(this, DataListActivity.class);
        MenuSelection.putExtra("option", 6);
        MenuSelection.putExtra("type", type);
        MenuSelection.putExtra("id_client", id_client);
        startActivity(MenuSelection);
        finish();
    }

}
