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

public class Container_rents extends AppCompatActivity implements Interface_screens{
    private static final String ID_TAG = "id";
    private ViewPager screen;
    private List<rent> rents;
    private Button mLastButton, mFirstButton;
    private DataContainer datacenter;
    private int size = 0, id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_r);
        datacenter = DataContainer.get(this);
        rents = datacenter.getRents();
        id = (int) getIntent().getSerializableExtra(ID_TAG);
        screen = findViewById(R.id.view_pager_r);
        mFirstButton = findViewById(R.id.first_item_r);
        mLastButton = findViewById(R.id.last_item_r);
        size=rents.size();
        FragmentManager fragmentManager = getSupportFragmentManager();
        screen.setAdapter(new FragmentPagerAdapter(fragmentManager) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                rent rent= rents.get(position);
                return rents_screen.newInstance(rent.getId_rent());
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
            if(rents.get(i).getId_rent()==id){
                if (rents.size() == 1){
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
        Intent inten = new Intent(packageContext, Container_rents.class);
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
                DataContainer.get(this).deleteData(rents.get(screen.getCurrentItem()).getId_rent(), getApplicationContext().getString(R.string.rent), this, "deleteRent");
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        rent aux = DataContainer.get(this).getAux_r();
        DataContainer.get(this).addUpdateRent(aux, 1, this, "updateRent");
    }

    @Override
    public void callback(String cas) {
        switch (cas) {
            case "deleteRent": {
                finish();
                break;
            }
            case "updateRent": {
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
