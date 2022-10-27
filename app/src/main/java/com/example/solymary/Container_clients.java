package com.example.solymary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Container_clients extends AppCompatActivity implements Interface_screens{
    private static final String ID_TAG = "id";
    private ViewPager screen;
    private List<client> clients;
    private Button mLastButton, mFirstButton;
    private DataContainer datacenter;
    private int size = 0, id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_cl);
        datacenter = DataContainer.get(this);
        List<client> aux = datacenter.getClients();
        clients = new ArrayList<client>();
        for(int i=0; i<aux.size(); i++){
            if(aux.get(i).getNames().compareTo("Desconocido")!=0){
                clients.add(aux.get(i));
            }
        }
        id = (int) getIntent().getSerializableExtra(ID_TAG);
        screen = findViewById(R.id.view_pager);
        mFirstButton = findViewById(R.id.first_item);
        mLastButton = findViewById(R.id.last_item);
        size=clients.size();
        FragmentManager fragmentManager = getSupportFragmentManager();
        screen.setAdapter(new FragmentPagerAdapter(fragmentManager) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                client client = clients.get(position);
                return clients_screen.newInstance(client.getId_client());
            }

            @Override
            public int getCount() {

                return size;
            }
        });

        screen.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(screen.getCurrentItem()==0){
                    mFirstButton.setEnabled(false);
                    mLastButton.setEnabled(true);
                }else if(screen.getCurrentItem() == size-1){
                    mFirstButton.setEnabled(true);
                    mLastButton.setEnabled(false);
                }else{
                    mFirstButton.setEnabled(true);
                    mLastButton.setEnabled(true);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i=0; i<size; i++){
            if(clients.get(i).getId_client()==id){
                if (clients.size() == 1){
                    mFirstButton.setEnabled(false);
                    mLastButton.setEnabled(false);
                    break;
                }else{
                    if(i==0){
                        mFirstButton.setEnabled(false);
                        mLastButton.setEnabled(true);
                    }else if(i == size-1){
                        mFirstButton.setEnabled(true);
                        mLastButton.setEnabled(false);
                    }else{
                        mFirstButton.setEnabled(true);
                        mLastButton.setEnabled(true);
                    }
                }
                screen.setCurrentItem(i);
                break;
            }
        }
        mFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setCurrentItem(0);
                mFirstButton.setEnabled(false);
                mLastButton.setEnabled(true);
            }
        });

        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setCurrentItem(size-1);
                mFirstButton.setEnabled(true);
                mLastButton.setEnabled(false);
            }
        });

    }




    public static Intent newIntent (Context packageContext, int id){
        Intent inten = new Intent(packageContext, Container_clients.class);
        inten.putExtra(ID_TAG, id);
        return inten;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.erase:{
                clients.get(screen.getCurrentItem()).setNames("Desconocido");
                clients.get(screen.getCurrentItem()).setAddress(" ");
                clients.get(screen.getCurrentItem()).setLastname(" ");
                clients.get(screen.getCurrentItem()).setPhone(" ");
                DataContainer.get(this).addUpdateClient(clients.get(screen.getCurrentItem()), 1, this, "unknownClient");

                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        client aux = DataContainer.get(this).getAux_c();
            DataContainer.get(this).addUpdateClient(aux, 1, this, "updateClient");


    }

    @Override
    public void callback(String cas) {
        switch (cas) {
            case "unknownClient": {
                List<client> aux = new ArrayList<client>();
                for (int i = 0; i < clients.size(); i++) {
                    if (clients.get(i).getNames() != "Desconocido") {
                        aux.add(clients.get(i));
                    }
                }
                finish();
                break;
            }
            case "updateClient": {
                super.onBackPressed();
                break;
            }
        }
    }

    @Override
    public void change(int type, int id_client) {

    }

    @Override
    public void nav(int option, int type) { }
}
