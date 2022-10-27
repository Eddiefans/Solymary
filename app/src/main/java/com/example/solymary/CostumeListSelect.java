package com.example.solymary;

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
import android.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CostumeListSelect extends Fragment implements Interface_screens{
    private RecyclerView dataList;
    private RecyclerAdapter listAdapter;
    private TextView mEmptyList, mEmptyList1;
    private DataContainer datacenter;
    private List<costume> costumes_i;
    private int aux, aux1;
    private int id_client;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        id_client = (int) getArguments().getSerializable("id");
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
        List<costume> aux = datacenter.getCostumes();
        List<costume> aux1 = new ArrayList<>();
        costumes_i = new ArrayList<costume>();
        for(int i=0; i<aux.size(); i++){
            if(aux.get(i).getName().compareTo("Desconocido")!=0){
                aux1.add(aux.get(i));
            }
        }
        for(int i=0; i<aux1.size(); i++){
            if(aux1.get(i).getRented()==0){
                costumes_i.add(aux1.get(i));
            }
        }
        if(costumes_i.size()==0){
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
            listAdapter = new CostumeListSelect.RecyclerAdapter(costumes_i);
            dataList.setAdapter(listAdapter);
        }else{
            listAdapter.setCostumes(costumes_i);
            listAdapter.notifyDataSetChanged();
        }
    }




    private class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name, stock;
        private costume costume;

        @Override
        public void onClick(View v){
            RentListFragment.get().create(id_client, costume, null, 0);
            getActivity().finish();
        }
        public ListHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.item_list, parent, false));
            itemView.setOnClickListener(this);
            name=itemView.findViewById(R.id.element1);
            stock = itemView.findViewById(R.id.element2);
        }
        public void bind(costume costum){
            costume = costum;
            name.setText(costum.getName());
            product product = DataContainer.get(getActivity()).getProduct(costum.getId_product());
            stock.setText("Stock: "+ product.getStock_status());
        }
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<CostumeListSelect.ListHolder>{
        private List<costume> costumes;

        public RecyclerAdapter(List<costume> costumes_i){costumes = costumes_i;}


        @NonNull
        @Override
        public CostumeListSelect.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CostumeListSelect.ListHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CostumeListSelect.ListHolder holder, int position) {
            costume cos = costumes.get(position);
            holder.bind(cos);
        }

        @Override
        public int getItemCount() { return costumes.size(); }

        public void setCostumes(List<costume> costumes_i){costumes = costumes_i;}
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
        menu.findItem(R.id.add_product).setIcon(R.drawable.add_costume);
        menu.findItem(R.id.add_product).setVisible(false);
        menu.findItem(R.id.switcher).setIcon(R.drawable.switch_icon);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.switcher){
            ((Interface_screens)getActivity()).change(1, id_client);
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void callback(String cas) { }

    public static CostumeListSelect newInstance (int id_client){
        Bundle args = new Bundle();
        args.putSerializable("id", id_client);
        CostumeListSelect fragment = new CostumeListSelect();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void change(int type, int id_client) {

    }

    @Override
    public void nav(int option, int type) {}
}
