package com.example.solymary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class Container_costumes extends AppCompatActivity implements Interface_screens{
    private static final String ID_TAG = "id";
    private ViewPager screen;
    private List<costume> costumes;
    private costume aux = new costume();
    private Button mLastButton, mFirstButton;
    private DataContainer datacenter;
    private product product = new product(), product_aux = new product();
    private size size_aux = new size();
    private season season_aux = new season();
    private int size = 0, id=0, id_size = 0, id_season= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_co);
        datacenter = DataContainer.get(this);
        List<costume> aux = datacenter.getCostumes();
        costumes = new ArrayList<costume>();
        for(int i=0; i<aux.size(); i++){
            if(aux.get(i).getName().compareTo("Desconocido")!=0){
                costumes.add(aux.get(i));
            }
        }
        id = (int) getIntent().getSerializableExtra(ID_TAG);
        screen = findViewById(R.id.view_pager_co);
        mFirstButton = findViewById(R.id.first_item_co);
        mLastButton = findViewById(R.id.last_item_co);
        size=costumes.size();

        FragmentManager fragmentManager = getSupportFragmentManager();
        screen.setAdapter(new FragmentPagerAdapter(fragmentManager) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                costume costume = costumes.get(position);
                return costumes_screen.newInstance(costume.getId_costume());
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
            if(costumes.get(i).getId_costume()==id){
                if (costumes.size() == 1){
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
        Intent inten = new Intent(packageContext, Container_costumes.class);
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
                costumes.get(screen.getCurrentItem()).setName("Desconocido");
                costumes.get(screen.getCurrentItem()).setRented(0);
                product = DataContainer.get(this).getProduct(costumes.get(screen.getCurrentItem()).getId_product());
                product.setStock_status(getApplicationContext().getString(R.string.stock3));
                DataContainer.get(this).addUpdateCostume(costumes.get(screen.getCurrentItem()), 1, this, "unknownCostume");
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        size our_size = new size();
        our_size.setId_size(0);
        our_size.setDimension(0);
        our_size.setUnit(" ");
        season our_season = new season();
        our_season.setId_season(0);
        our_season.setName(" ");
        size_aux = DataContainer.get(this).getAux_si();
        season_aux = DataContainer.get(this).getAux_se();
        aux = DataContainer.get(this).getAux_co();
        product_aux = DataContainer.get(this).getProduct(aux.getId_product());
        List<size> sizes = DataContainer.get(this).getSizes();
        List<season> seasons = DataContainer.get(this).getSeasons();
        for(int i=0; i<seasons.size(); i++){
            if(seasons.get(i).getId_season()==aux.getId_season()){
                our_season = seasons.get(i);
            }
        }
        for(int i=0; i<sizes.size(); i++){
            if(sizes.get(i).getId_size()==product_aux.getId_size()){
                our_size = sizes.get(i);
            }
        }
        if(size_aux.getDimension()!=our_size.getDimension() || size_aux.getUnit().compareTo(our_size.getUnit())!=0){
            if(season_aux.getName().compareTo(our_season.getName())!=0){
                id_season = DataContainer.get(this).addSeason(season_aux, this, "sesi");
                aux.setId_season(id_season);
            }else{
                id_size = DataContainer.get(this).addSize(size_aux, this, "si");
                product_aux.setId_size(id_size);
            }
        }else{
            if(season_aux.getName().compareTo(our_season.getName())!=0){
                id_season = DataContainer.get(this).addSeason(season_aux, this, "se");
                aux.setId_season(id_season);
            }else{
                DataContainer.get(this).addUpdateCostume(aux, 1, this, "updateCostume");
            }
        }
    }

    @Override
    public void callback(String cas) {
        switch (cas) {
            case "unknownCostume": {
                List<costume> aux = new ArrayList<costume>();
                for (int i = 0; i < costumes.size(); i++) {
                    if (costumes.get(i).getName().compareTo("Desconocido") != 0) {
                        aux.add(costumes.get(i));
                    }
                }
                DataContainer.get(this).addUpdateProduct(product, 1,this, "unknownProduct");
                break;
            }
            case "updateCostume": {
                super.onBackPressed();
                break;
            }
            case "unknownProduct":{
                finish();
                break;
            }
            case "sesi":{
                id_size = DataContainer.get(this).addSize(size_aux, this, "si");
                product_aux.setId_size(id_size);
                break;
            }
            case "si":{
                DataContainer.get(this).addUpdateProduct(product_aux, 1,this, "updateProduct");
            }
            case "updateProduct":{
                DataContainer.get(this).addUpdateCostume(aux, 1, this, "updateCostume");
            }
            case "se":{
                DataContainer.get(this).addUpdateCostume(aux, 1, this, "updateCostume");
            }
        }
    }

    @Override
    public void change(int type, int id_client) {

    }

    @Override
    public void nav(int option, int type) { }
}
