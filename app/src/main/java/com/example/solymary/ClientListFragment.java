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

public class ClientListFragment extends Fragment implements Interface_screens{
    private RecyclerView dataList;
    private RecyclerAdapter listAdapter;
    private TextView mEmptyList, mEmptyList1;
    private DataContainer datacenter;
    private List<client> clients_i;
    private int aux;


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
        List<client> aux = datacenter.getClients();
        clients_i = new ArrayList<client>();
        for(int i=0; i<aux.size(); i++){
            if(aux.get(i).getNames().compareTo("Desconocido")!=0){
                clients_i.add(aux.get(i));
            }
        }
        if(clients_i.size()==0){
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
            listAdapter = new RecyclerAdapter(clients_i);
            dataList.setAdapter(listAdapter);
        }else{
            listAdapter.setClients(clients_i);
            listAdapter.notifyDataSetChanged();
        }
    }




    private class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name, phone;
        private client client;

        @Override
        public void onClick(View v){

            Intent intent = Container_clients.newIntent(getActivity(), client.getId_client());
            startActivity(intent);

        }
        public ListHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.item_list, parent, false));
            itemView.setOnClickListener(this);
            name=itemView.findViewById(R.id.element1);
            phone = itemView.findViewById(R.id.element2);
        }
        public void bind(client clien){
            client = clien;
            name.setText(clien.getNames()+" "+clien.getLastname());
            phone.setText(getActivity().getString(R.string.phone)+":  "+ clien.getPhone());
        }
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<ListHolder>{
        private List<client> clients;

        public RecyclerAdapter(List<client> clients_i){clients = clients_i;}


        @NonNull
        @Override
        public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ListHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ListHolder holder, int position) {
            client cli = clients.get(position);
            holder.bind(cli);
        }

        @Override
        public int getItemCount() { return clients.size(); }

        public void setClients(List<client> clients_i){clients = clients_i;}
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
        menu.findItem(R.id.add_item).setIcon(R.drawable.add_client);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_item) {
            client clien = new client();
            clien.setLastname(" ");
            clien.setPhone(" ");
            clien.setAddress(" ");
            clien.setNames(" ");
            aux = DataContainer.get(getActivity()).addUpdateClient(clien, 0, this, "addingClient");
            clien.setId_client(aux);
            clients_i.add(clien);

            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }



    }

    @Override
    public void callback(String cas) {
        switch (cas) {
            case "addingClient": {
                Intent intent = Container_clients.newIntent(getActivity(), aux);
                startActivity(intent);
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
