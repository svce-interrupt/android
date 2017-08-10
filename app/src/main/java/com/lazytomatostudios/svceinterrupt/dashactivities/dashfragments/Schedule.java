package com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.lazytomatostudios.svceinterrupt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends Fragment {


    public Schedule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        WebView browser = (WebView) view.findViewById(R.id.webview);
        browser.loadUrl("https://www.svce.ac.in/departments/transport/schedule.php#nav");

        return view;
    }

}
