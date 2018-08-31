package com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.dashactivities.ConnectFourActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectFourMain extends Fragment {

    String TAG = "PHP Request ";
    View view;


    public ConnectFourMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_connect_four_main, container, false);

        //Check for network state.

        ((ConnectFourActivity) this.getActivity()).getData("day");

        //Check for login state.

        ((ConnectFourActivity) this.getActivity()).getData("check_user");

        return view;
    }

    public void setDay() {

        String day = ((ConnectFourActivity) this.getActivity()).day;
        TextView dayText = view.findViewById(R.id.day);

        switch (day) {
            case "1":
                dayText.setText(getString(R.string.day1));
                break;
            case "2":
                dayText.setText(getString(R.string.day2));
                break;
            case "3":
                dayText.setText(getString(R.string.day3));
                break;
            case "4":
                dayText.setText(getString(R.string.day4));
                break;
            case "5":
                dayText.setText(getString(R.string.day5));
                break;
            case "6":
                dayText.setText(getString(R.string.day6));
            default:
                dayText.setText(getString(R.string.day0));
                break;
        }

    }

    public void setNos(int done, int left) {

        TextView doneText = view.findViewById(R.id.done);
        TextView leftText = view.findViewById(R.id.left);

        doneText.setText(String.valueOf(done));
        leftText.setText(String.valueOf(left));

    }

}
