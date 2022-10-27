package com.example.solymary;

import android.app.Fragment;
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
import java.util.List;

public class PropListFragment extends Fragment implements Interface_screens{
    private RecyclerView dataList;
    private RecyclerAdapter listAdapter;
    private TextView mEmptyList, mEmptyList1;
    private DataContainer datacenter;
    private List<prop> props_i;
    private int aux, aux1;



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
        List<prop> aux = datacenter.getProps();
        props_i = new ArrayList<prop>();
        for(int i=0; i<aux.size(); i++){
            if(aux.get(i).getName().compareTo("Desconocido")!=0){
                props_i.add(aux.get(i));
            }
        }
        if(props_i.size()==0){
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
            listAdapter = new PropListFragment.RecyclerAdapter(props_i);
            dataList.setAdapter(listAdapter);
        }else{
            listAdapter.setProps(props_i);
            listAdapter.notifyDataSetChanged();
        }
    }




    private class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name, stock;
        private prop prop;

        @Override
        public void onClick(View v){

            Intent intent = Container_props.newIntent(getActivity(), prop.getId_prop());
            startActivity(intent);

        }
        public ListHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.item_list, parent, false));
            itemView.setOnClickListener(this);
            name=itemView.findViewById(R.id.element1);
            stock = itemView.findViewById(R.id.element2);
        }
        public void bind(prop pro){
            prop = pro;
            name.setText(pro.getName());
            product product = DataContainer.get(getActivity()).getProduct(pro.getId_product());
            stock.setText("Stock: "+product.getStock_status());
        }
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<PropListFragment.ListHolder>{
        private List<prop> props;

        public RecyclerAdapter(List<prop> props_i){props = props_i;}


        @NonNull
        @Override
        public PropListFragment.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PropListFragment.ListHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PropListFragment.ListHolder holder, int position) {
            prop pr = props.get(position);
            holder.bind(pr);
        }

        @Override
        public int getItemCount() { return props.size(); }

        public void setProps(List<prop> props_i){props= props_i;}
    }


    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_menu_1, menu);
        menu.findItem(R.id.add_product).setIcon(R.drawable.add_prop);
        menu.findItem(R.id.switcher).setIcon(R.drawable.switch_icon);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_product) {
            size size = new size();
            size.setDimension(0);
            size.setUnit(" ");
            aux = DataContainer.get(getActivity()).addSize(size,  this, "addingSize");
            size.setId_size(aux);


            return true;
        }else if (item.getItemId()==R.id.switcher){
            ((Interface_screens)getActivity()).nav(5, 0);
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }



    }

    @Override
    public void callback(String cas) {
        switch (cas) {
            case "addingSize":{
                product product = new product();
                product.setId_size(aux);
                product.setStock_status(getActivity().getString(R.string.stock3));
                aux = DataContainer.get(getActivity()).addUpdateProduct(product,0,  this, "addingProduct");
                break;
            }
            case "addingProp": {
                Intent intent = Container_props.newIntent(getActivity(), aux1);
                startActivity(intent);
                break;
            }
            case "addingProduct":{
                prop propa = new prop();
                propa.setId_product(aux);
                propa.setRented(0);
                propa.setQuantity_rented(0);
                propa.setQuantity_available(0);
                propa.setColor(" ");
                propa.setName(" ");
                aux1 = DataContainer.get(getActivity()).addUpdateProp(propa,0, this, "addingProp");
                propa.setId_prop(aux1);
                props_i.add(propa);
                break;
            }
        }
    }

    @Override
    public void change(int type, int id_client) {

    }

    @Override
    public void nav(int option, int type) {}





}
