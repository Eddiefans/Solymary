package com.example.solymary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    public static final String ARG_DAY = "day";
    public static final String ARG_MONTH = "month";
    public static final String ARG_YEAR = "year";
    //creates a new Fragment Manager for creating the TimePicker
    private static FragmentManager manager;
    private DatePicker mDatePicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int day = (int) getArguments().getSerializable(ARG_DAY);
        int month = (int) getArguments().getSerializable(ARG_MONTH);
        int year = (int) getArguments().getSerializable(ARG_YEAR);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        //gets the current date of the crime

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        //Wiring up Calendar
        mDatePicker = v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //getting the current time selected
                                int year = mDatePicker.getYear();
                                int month = mDatePicker.getMonth();
                                int day = mDatePicker.getDayOfMonth();
                                Date date = new GregorianCalendar(year, month, day).getTime();

                                //Creates a new dialog of timePicker, send the date selected and start it
                                sendResult(Activity.RESULT_OK, day, month, year);

                            }
                        }
                )
                .create();
    }

    //Creates a new fragment which is gonna have the parameters set on the new bundle
    public static DatePickerFragment newInstance (int day, int month, int year, FragmentManager mana){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DAY, day);
        args.putSerializable(ARG_MONTH, month);
        args.putSerializable(ARG_YEAR, year);
        //The fragment manager has to be the same for three fragments
        manager = mana;
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void sendResult(int resultCode, int day, int month, int year){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(ARG_DAY, day);
        intent.putExtra(ARG_MONTH, month);
        intent.putExtra(ARG_YEAR, year);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


}
