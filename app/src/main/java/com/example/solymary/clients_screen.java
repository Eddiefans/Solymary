package com.example.solymary;


import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class clients_screen extends Fragment{

    private client client;
    private EditText nameField, phoneField, addressField, lastnameField;
    private static final String TAG = "clients_screen";
    private static final String ID_TAG = "id";
    private DataContainer datacenter;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = (int) getArguments().getSerializable(ID_TAG);
        datacenter = DataContainer.get(getActivity());
        client=datacenter.getClient(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View clients_view = inflater.inflate(R.layout.fragment_clients_screen, container, false);

        nameField = clients_view.findViewById(R.id.names_client_txt);
        phoneField = clients_view.findViewById(R.id.phone_client_txt);
        addressField = clients_view.findViewById(R.id.address_client_txt);
        lastnameField = clients_view.findViewById(R.id.last_client_txt);
        nameField.setText(client.getNames());
        lastnameField.setText(client.getLastname());
        addressField.setText(client.getAddress());
        phoneField.setText(client.getPhone());
        DataContainer.get(getActivity()).setAux_c(client);

        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                client.setNames(s.toString());
                DataContainer.get(getActivity()).setAux_c(client);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        lastnameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                client.setLastname(s.toString());
                DataContainer.get(getActivity()).setAux_c(client);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        phoneField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                client.setPhone(s.toString());
                DataContainer.get(getActivity()).setAux_c(client);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        addressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                client.setAddress(s.toString());
                DataContainer.get(getActivity()).setAux_c(client);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });


        return clients_view;
    }



    public static clients_screen newInstance (int id){
        Bundle args = new Bundle();
        args.putSerializable(ID_TAG, id);
        clients_screen fragment = new clients_screen();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        DataContainer dataContainer = DataContainer.get(getActivity());
        DataContainer.get(getActivity()).setAux_c(client);
    }


}
