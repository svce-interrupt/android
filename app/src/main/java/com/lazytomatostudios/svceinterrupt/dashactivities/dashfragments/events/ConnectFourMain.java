package com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.lazytomatostudios.svceinterrupt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectFourMain extends Fragment {


    public ConnectFourMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_connect_four_main, container, false);

        CheckBox checkBox1 = view.findViewById(R.id.check1);
        checkBox1.setClickable(false);
        CheckBox checkBox2 = view.findViewById(R.id.check2);
        checkBox2.setClickable(false);
        CheckBox checkBox3 = view.findViewById(R.id.check3);
        checkBox3.setClickable(false);

        return view;
    }

}
