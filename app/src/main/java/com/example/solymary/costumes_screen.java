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


public class costumes_screen extends Fragment {
    private costume costume;
    private season season =new season();
    private size size = new size();
    private product product = new product();
    private EditText nameField, seasonField, unitField, dimensionField;
    private TextView stock1, stock2, stock3;
    private static final String TAG = "costumes_screen";
    private static final String ID_TAG = "id";
    private DataContainer datacenter;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = (int) getArguments().getSerializable(ID_TAG);
        datacenter = DataContainer.get(getActivity());
        costume=datacenter.getCostume(id);
        product=datacenter.getProduct(costume.getId_product());
        season = datacenter.getSeason(costume.getId_season());
        size = datacenter.getSize(product.getId_size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View costumes_view = inflater.inflate(R.layout.fragment_costumes_screen, container, false);

        nameField = costumes_view.findViewById(R.id.name_costume_txt);
        seasonField= costumes_view.findViewById(R.id.season_costume_txt);
        unitField= costumes_view.findViewById(R.id.size_costume_txt);
        dimensionField= costumes_view.findViewById(R.id.size_costume_num);
        stock1 = costumes_view.findViewById(R.id.stock1_costumes);
        stock2 = costumes_view.findViewById(R.id.stock2_costumes);
        stock3 = costumes_view.findViewById(R.id.stock3_costumes);
        if(product.getStock_status().compareTo(getActivity().getString(R.string.stock1_1))==0){
            stock1.setVisibility(View.VISIBLE);
            stock1.setText(getActivity().getString(R.string.stock1_1));
            stock2.setVisibility(View.INVISIBLE);
            stock2.setText("");
            stock3.setVisibility(View.INVISIBLE);
            stock3.setText("");
        }else if(product.getStock_status().compareTo(getActivity().getString(R.string.stock2_1))==0){
            stock1.setVisibility(View.INVISIBLE);
            stock1.setText("");
            stock2.setVisibility(View.VISIBLE);
            stock2.setText(getActivity().getString(R.string.stock2_1));
            stock3.setVisibility(View.INVISIBLE);
            stock3.setText("");
        }else{

            stock1.setVisibility(View.INVISIBLE);
            stock1.setText("");
            stock2.setVisibility(View.INVISIBLE);
            stock2.setText("");
            stock3.setVisibility(View.VISIBLE);
            stock3.setText(getActivity().getString(R.string.stock3));
        }

        nameField.setText(costume.getName());
        seasonField.setText(season.getName());
        unitField.setText(size.getUnit());
        dimensionField.setText(size.getDimension()+"");
        DataContainer.get(getActivity()).setAux_co(costume);
        DataContainer.get(getActivity()).setAux_pro(product);
        DataContainer.get(getActivity()).setAux_se(season);
        DataContainer.get(getActivity()).setAux_si(size);

        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                costume.setName(s.toString());
                DataContainer.get(getActivity()).setAux_co(costume);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        seasonField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                season.setName(s.toString());
                DataContainer.get(getActivity()).setAux_se(season);
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


        return costumes_view;
    }



    public static costumes_screen newInstance (int id){
        Bundle args = new Bundle();
        args.putSerializable(ID_TAG, id);
        costumes_screen fragment = new costumes_screen();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        DataContainer dataContainer = DataContainer.get(getActivity());
        dataContainer.setAux_co(costume);
        dataContainer.setAux_si(size);
        dataContainer.setAux_se(season);
        dataContainer.setAux_pro(product);
    }


}
