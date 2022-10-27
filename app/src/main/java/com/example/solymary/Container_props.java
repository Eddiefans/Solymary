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

public class Container_props extends AppCompatActivity implements Interface_screens{
    private static final String ID_TAG = "id";
    private ViewPager screen;
    private List<prop> props;
    private prop aux = new prop();
    private Button mLastButton, mFirstButton;
    private DataContainer datacenter;
    private product product = new product(), product_aux = new product();
    private size size_aux = new size();
    private int size = 0, id=0, id_size = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_p);
        datacenter = DataContainer.get(this);
        List<prop> aux = datacenter.getProps();
        props = new ArrayList<prop>();
        for(int i=0; i<aux.size(); i++){
            if(aux.get(i).getName().compareTo("Desconocido")!=0){
                props.add(aux.get(i));
            }
        }
        id = (int) getIntent().getSerializableExtra(ID_TAG);
        screen = findViewById(R.id.view_pager_p);
        mFirstButton = findViewById(R.id.first_item_p);
        mLastButton = findViewById(R.id.last_item_p);
        size=props.size();

        FragmentManager fragmentManager = getSupportFragmentManager();
        screen.setAdapter(new FragmentPagerAdapter(fragmentManager) {

            @NonNull
            @Override
            public Fragment getItem(int position) {
                prop prop = props.get(position);
                return props_screen.newInstance(prop.getId_prop());
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
            if(props.get(i).getId_prop()==id){
                if (props.size() == 1){
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
        Intent inten = new Intent(packageContext, Container_props.class);
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
                props.get(screen.getCurrentItem()).setName("Desconocido");
                props.get(screen.getCurrentItem()).setRented(0);
                props.get(screen.getCurrentItem()).setQuantity_available(0);
                props.get(screen.getCurrentItem()).setQuantity_rented(0);
                props.get(screen.getCurrentItem()).setColor(" ");
                product = DataContainer.get(this).getProduct(props.get(screen.getCurrentItem()).getId_product());
                product.setStock_status(getApplicationContext().getString(R.string.stock3));
                DataContainer.get(this).addUpdateProp(props.get(screen.getCurrentItem()), 1, this, "unknownProp");
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
        size_aux = DataContainer.get(this).getAux_si();
        aux = DataContainer.get(this).getAux_p();
        product_aux = DataContainer.get(this).getProduct(aux.getId_product());
        if(aux.getQuantity_available()==0&&aux.getQuantity_rented()==0){
            product_aux.setStock_status(getApplicationContext().getString(R.string.stock3));
        }else if(aux.getQuantity_available()==0 && aux.getQuantity_rented()!=0){
            product_aux.setStock_status(getApplicationContext().getString(R.string.stock2_1));
        }else{
            product_aux.setStock_status(getApplicationContext().getString(R.string.stock1_1));
        }
        List<size> sizes = DataContainer.get(this).getSizes();
        for(int i=0; i<sizes.size(); i++){
            if(sizes.get(i).getId_size()==product_aux.getId_size()){
                our_size = sizes.get(i);
            }
        }
        if(size_aux.getDimension()!=our_size.getDimension() || size_aux.getUnit().compareTo(our_size.getUnit())!=0){
            id_size = DataContainer.get(this).addSize(size_aux, this, "si");
            product_aux.setId_size(id_size);
        }else{
            DataContainer.get(this).addUpdateProp(aux, 1, this, "updateProp");
        }
    }

    @Override
    public void callback(String cas) {
        switch (cas) {
            case "unknownProp": {
                List<prop> aux = new ArrayList<prop>();
                for (int i = 0; i < props.size(); i++) {
                    if (props.get(i).getName().compareTo("Desconocido") != 0) {
                        aux.add(props.get(i));
                    }
                }
                DataContainer.get(this).addUpdateProduct(product, 1,this, "unknownProduct");
                break;
            }
            case "updateProp": {
                super.onBackPressed();
                break;
            }
            case "unknownProduct":{
                finish();
                break;
            }
            case "si":{
                DataContainer.get(this).addUpdateProduct(product_aux, 1,this, "updateProduct");
            }
            case "updateProduct":{
                DataContainer.get(this).addUpdateProp(aux, 1, this, "updateProp");
            }
        }
    }

    @Override
    public void change(int type, int id_client) {

    }

    @Override
    public void nav(int option, int type) { }
}
