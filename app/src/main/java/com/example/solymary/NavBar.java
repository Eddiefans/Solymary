package com.example.solymary;


import android.app.Activity;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavBar extends Fragment {

    private ImageButton home, products, clients, sales, rents;


    public NavBar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View nav_view =  inflater.inflate(R.layout.fragment_nav_bar, container, false);

        home = nav_view.findViewById(R.id.home_nav);
        rents = nav_view.findViewById(R.id.rents_nav);
        clients = nav_view.findViewById(R.id.clients_nav);
        products = nav_view.findViewById(R.id.products_nav);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity thisActivity = getActivity();
                ((Interface_screens)thisActivity).nav(1, 0);
            }
        });

        rents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity thisActivity = getActivity();
                ((Interface_screens)thisActivity).nav(2, 0);
            }
        });


        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity thisActivity = getActivity();
                ((Interface_screens)thisActivity).nav(4, 0);
            }
        });

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity thisActivity = getActivity();
                ((Interface_screens)thisActivity).nav(5, 0);
            }
        });


        return nav_view;
    }

}
