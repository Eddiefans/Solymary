package com.example.solymary;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentListFragment extends Fragment implements Interface_screens{
    private RecyclerView dataList;
    private RecyclerAdapter listAdapter;
    private TextView mEmptyList, mEmptyList1;
    private DataContainer datacenter;
    private List<rent> rents_i;
    private int aux, aux1;
    private boolean selected=false, selected1=false;
    private client client = new client();
    private costume costume = new costume();
    private prop prop = new prop();
    private product product= new product();
    private static RentListFragment mFragment;


    public static RentListFragment get() {
        if (mFragment==null) {
            mFragment = new RentListFragment();
        }
        return mFragment;
    }

    public RentListFragment(){
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View listview= inflater.inflate(R.layout.fragment_data_list, container, false);

        dataList = listview.findViewById(R.id.recycler_view);
        dataList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mEmptyList = listview.findViewById(R.id.empty_list);
        mEmptyList1 = listview.findViewById(R.id.empty_list1);
        updateList();
        return listview;
    }





    public void updateList(){
        datacenter = DataContainer.get(getActivity());
        rents_i = datacenter.getRents();
        if(rents_i.size()==0){
            mEmptyList.setText(R.string.empty_list);
            mEmptyList1.setText(R.string.empty_list1);
            mEmptyList.setEnabled(true);
            mEmptyList1.setEnabled(true);
            mEmptyList.setPadding(0,850,0,60);
            mEmptyList1.setPadding(0,0,0,0);
        }else{
            mEmptyList.setText("");
            mEmptyList1.setText("");
            mEmptyList.setEnabled(false);
            mEmptyList1.setEnabled(false);
            mEmptyList.setPadding(0,-100,0,0);
            mEmptyList1.setPadding(0,-100,0,0);
        }
        if(listAdapter == null){
            listAdapter = new RentListFragment.RecyclerAdapter(rents_i);
            dataList.setAdapter(listAdapter);
        }else{
            listAdapter.setRents(rents_i);
            listAdapter.notifyDataSetChanged();
        }
    }




    private class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView price, dateGive;
        private rent rent;

        @Override
        public void onClick(View v){

            Intent intent = Container_rents.newIntent(getActivity(), rent.getId_rent());
            startActivity(intent);

        }
        public ListHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.item_list, parent, false));
            itemView.setOnClickListener(this);
            price=itemView.findViewById(R.id.element1);
            dateGive = itemView.findViewById(R.id.element2);
        }
        public void bind(rent ren){
            rent = ren;
            price.setText("$ "+ren.getPrice());
            dateGive.setText(ren.getDay_give()+" / "+ren.getMonth_give()+" / "+ ren.getYear_give());
        }
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RentListFragment.ListHolder>{
        private List<rent> rents;

        public RecyclerAdapter(List<rent> rents_i){rents = rents_i;}


        @NonNull
        @Override
        public RentListFragment.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RentListFragment.ListHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RentListFragment.ListHolder holder, int position) {
            rent re= rents.get(position);
            holder.bind(re);
        }

        @Override
        public int getItemCount() { return rents.size(); }

        public void setRents(List<rent> rents_i){rents= rents_i;}
    }


    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_menu, menu);
        menu.findItem(R.id.add_item).setIcon(R.drawable.add_rent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_item) {
            Intent MenuSelection = new Intent(getActivity(), DataListActivity.class);
            MenuSelection.putExtra("option", 7);
            MenuSelection.putExtra("type", 0);
            startActivity(MenuSelection);
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }



    }

    @Override
    public void callback(String cas) {
        switch (cas) {
            case "updateCostume":{
                aux1 = DataContainer.get(getActivity()).addUpdateProduct(product, 1, this, "updateProduct");
                break;
            }
            case "updateProduct":{
                rent rent = new rent();
                Date date = new Date();
                rent.setYear_return(date.getYear());
                rent.setMonth_return(date.getMonth());
                rent.setDay_return(date.getDay());
                rent.setYear_give(date.getYear());
                rent.setMonth_give(date.getMonth());
                rent.setDay_give(date.getDay());
                rent.setPrice(0);
                rent.setQuantity(1);
                rent.setDays(0);
                rent.setId_client(client.getId_client());
                rent.setId_product(aux1);
                aux = DataContainer.get(getActivity()).addUpdateRent(rent, 0, this, "addingRent");
                break;
            }
            case "addingRent": {
                break;
            }
        }
    }

    @Override
    public void change(int type, int id_client) {

    }


    @Override
    public void nav(int option, int type) {}

    public void create(int id_client, costume costum, prop pro, int typ){
        client = DataContainer.get(getActivity()).getClient(id_client);
        if(typ==0){
            costume = costum;
            costume.setRented(1);
            product = DataContainer.get(getActivity()).getProduct(costume.getId_product());
            product.setStock_status("actualmente rentados");
            aux = DataContainer.get(getActivity()).addUpdateCostume(costume, 1, this, "updateCostume");
        }else{
            prop = pro;
            prop.setRented(1);
            prop.setQuantity_available(prop.getQuantity_available()-1);
            prop.setQuantity_rented(prop.getQuantity_rented()+1);
            product = DataContainer.get(getActivity()).getProduct(prop.getId_product());
            if(prop.getQuantity_available()==0){
                product.setStock_status("actualmente rentados");
            }
            aux = DataContainer.get(getActivity()).addUpdateProp(prop, 1, this, "updateCostume");
        }
    }


    public boolean isSelected() {
        return selected;
    }


}
