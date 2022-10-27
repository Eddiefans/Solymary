package com.example.solymary;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class rents_screen extends Fragment {


    private rent rent;
    private EditText priceField;
    private TextView clients, products;
    private Button dateGiveButton, dateReturnButton;
    private static final String DATE_GIVE = "date_give";
    private static final String DATE_RETURN = "date_return";
    private static final String ID_TAG = "id";
    private static final int REQUEST_GIVE = 2;
    private static final int REQUEST_RETURN = 1;
    private DataContainer datacenter;
    private prop prop;
    private costume costume;
    private List<costume> costumes = new ArrayList<>();
    private List<prop> props = new ArrayList<>();
    private client client;
    private int type=0;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = (int) getArguments().getSerializable(ID_TAG);
        datacenter = DataContainer.get(getActivity());
        rent=datacenter.getRent(id);
        costumes = datacenter.getCostumes();
        props = datacenter.getProps();
        client = datacenter.getClient(rent.getId_client());
        for(int i=0; i<props.size(); i++){
            if(props.get(i).getId_product()==rent.getId_product()){
                prop = props.get(i);
                type = 1;
                break;
            }
        }
        if(type==0){
            for(int i=0; i<costumes.size(); i++){
                if(costumes.get(i).getId_product()==rent.getId_product()){
                    costume = costumes.get(i);
                    type = 0;
                    break;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rents_view= inflater.inflate(R.layout.fragment_rents_screen, container, false);
        products = rents_view.findViewById(R.id.product_rent_txt);
        clients = rents_view.findViewById(R.id.client_rent_txt);
        priceField = rents_view.findViewById(R.id.income_rent_txt);
        dateGiveButton=rents_view.findViewById(R.id.dategive_rent_bt);
        dateReturnButton=rents_view.findViewById(R.id.datereturn_rent_bt);
        priceField.setText(rent.getPrice()+"");
        clients.setText(client.getNames());
        if(type==0){
            products.setText(costume.getName());
        }else{
            products.setText(prop.getName());
        }
        DataContainer.get(getActivity()).setAux_r(rent);
        setDates();


        priceField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    rent.setPrice(Integer.parseInt(s.toString().trim()));
                }
                DataContainer.get(getActivity()).setAux_r(rent);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });


        dateGiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dateDialog = DatePickerFragment.newInstance(rent.getDay_give(), rent.getMonth_give(), rent.getYear_give(), manager);
                dateDialog.setTargetFragment(rents_screen.this, REQUEST_GIVE);
                dateDialog.show(manager, DATE_GIVE);
            }
        });


        dateReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dateDialog = DatePickerFragment.newInstance(rent.getDay_return(), rent.getMonth_return(), rent.getYear_return(), manager);
                dateDialog.setTargetFragment(rents_screen.this, REQUEST_RETURN);
                dateDialog.show(manager, DATE_RETURN);
            }
        });

        return rents_view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_GIVE){
            int day = (int) data.getSerializableExtra(DatePickerFragment.ARG_DAY);
            int month = (int) data.getSerializableExtra(DatePickerFragment.ARG_MONTH);
            int year = (int) data.getSerializableExtra(DatePickerFragment.ARG_YEAR);
            rent.setDay_give(day);
            rent.setMonth_give(month+1);
            rent.setYear_give(year);
            DataContainer.get(getActivity()).setAux_r(rent);
            setDates();
            return;
        }else if(requestCode==REQUEST_RETURN){
            int day = (int) data.getSerializableExtra(DatePickerFragment.ARG_DAY);
            int month = (int) data.getSerializableExtra(DatePickerFragment.ARG_MONTH);
            int year = (int) data.getSerializableExtra(DatePickerFragment.ARG_YEAR);
            rent.setDay_return(day);
            rent.setMonth_return(month+1);
            rent.setYear_return(year);
            DataContainer.get(getActivity()).setAux_r(rent);
            setDates();
            return;
        }
    }

    public static rents_screen newInstance (int id){
        Bundle args = new Bundle();
        args.putSerializable(ID_TAG, id);
        rents_screen fragment = new rents_screen();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        DataContainer dataContainer = DataContainer.get(getActivity());
        dataContainer.setAux_r(rent);
    }

    public void setDates(){
        dateGiveButton.setText(rent.getDay_give()+" / "+rent.getMonth_give()+" / "+rent.getYear_give());
        dateReturnButton.setText(rent.getDay_return()+" / "+rent.getMonth_return()+" / "+rent.getYear_return());
        Date givedate = new GregorianCalendar(rent.getYear_give(), rent.getMonth_give(), rent.getDay_give()).getTime();
        Date returndate = new GregorianCalendar(rent.getYear_return(), rent.getMonth_return(), rent.getDay_return()).getTime();
        long difference = Math.abs(returndate.getTime()-givedate.getTime());
        difference = difference / (60*60*1000*24);
        rent.setDays((int)difference);
        DataContainer.get(getActivity()).setAux_r(rent);
    }

}
