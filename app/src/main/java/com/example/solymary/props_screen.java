package com.example.solymary;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class props_screen extends Fragment {
    private prop prop;
    private size size = new size();
    private product product = new product();
    private EditText nameField, colorField, unitField, dimensionField, quantityField;
    private TextView stock1, stock2, stock3;
    private static final String TAG = "props_screen";
    private static final String ID_TAG = "id";
    private DataContainer datacenter;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = (int) getArguments().getSerializable(ID_TAG);
        datacenter = DataContainer.get(getActivity());
        prop=datacenter.getProp(id);
        product=datacenter.getProduct(prop.getId_product());
        size = datacenter.getSize(product.getId_size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View props_view = inflater.inflate(R.layout.fragment_props_screen, container, false);

        nameField = props_view.findViewById(R.id.name_prop_txt);
        colorField= props_view.findViewById(R.id.color_prop_txt);
        unitField= props_view.findViewById(R.id.size_prop_txt);
        dimensionField= props_view.findViewById(R.id.size_prop_num);
        quantityField=props_view.findViewById(R.id.quantity_prop);
        stock1 = props_view.findViewById(R.id.stock1_props);
        stock2 = props_view.findViewById(R.id.stock2_props);
        stock3 = props_view.findViewById(R.id.stock3_props);
        if(prop.getQuantity_available()==0 && prop.getQuantity_rented()==0){
            stock1.setVisibility(View.INVISIBLE);
            stock1.setText("");
            stock2.setVisibility(View.INVISIBLE);
            stock2.setText("");
            stock3.setVisibility(View.VISIBLE);
            stock3.setText(getActivity().getString(R.string.stock3));
        }else if(prop.getQuantity_rented()!=0 && prop.getQuantity_available()==0){
            stock1.setVisibility(View.INVISIBLE);
            stock1.setText("");
            stock2.setVisibility(View.VISIBLE);
            stock2.setText(getActivity().getString(R.string.stock2_1));
            stock3.setVisibility(View.INVISIBLE);
            stock3.setText("");
        }else{
            stock1.setVisibility(View.VISIBLE);
            stock1.setText(getActivity().getString(R.string.stock1_1));
            stock2.setVisibility(View.INVISIBLE);
            stock2.setText("");
            stock3.setVisibility(View.INVISIBLE);
            stock3.setText("");
        }
        nameField.setText(prop.getName());
        unitField.setText(size.getUnit());
        dimensionField.setText(size.getDimension()+"");
        colorField.setText(prop.getColor());
        quantityField.setText(prop.getQuantity_available()+"");
        DataContainer.get(getActivity()).setAux_p(prop);
        DataContainer.get(getActivity()).setAux_pro(product);
        DataContainer.get(getActivity()).setAux_si(size);

        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                prop.setName(s.toString());
                DataContainer.get(getActivity()).setAux_p(prop);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        colorField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                prop.setColor(s.toString());
                DataContainer.get(getActivity()).setAux_p(prop);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        unitField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                size.setUnit(s.toString());
                DataContainer.get(getActivity()).setAux_si(size);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        dimensionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    size.setDimension(Integer.parseInt(s.toString().trim()));
                }
                DataContainer.get(getActivity()).setAux_si(size);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        quantityField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count>0){
                    prop.setQuantity_available(Integer.parseInt(s.toString().trim()));
                }
                DataContainer.get(getActivity()).setAux_p(prop);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });


        return props_view;
    }



    public static props_screen newInstance (int id){
        Bundle args = new Bundle();
        args.putSerializable(ID_TAG, id);
        props_screen fragment = new props_screen();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        DataContainer dataContainer = DataContainer.get(getActivity());
        dataContainer.setAux_p(prop);
        dataContainer.setAux_si(size);
        dataContainer.setAux_pro(product);
    }


}
