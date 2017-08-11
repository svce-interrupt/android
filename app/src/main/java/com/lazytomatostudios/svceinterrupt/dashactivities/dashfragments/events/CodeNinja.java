package com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazytomatostudios.svceinterrupt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeNinja extends Fragment {


    public CodeNinja() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_code_ninja, container, false);
    }

}
