package com.lazytomatostudios.svceinterrupt.navbarfragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lazytomatostudios.svceinterrupt.interfaces.MyInterface;
import com.lazytomatostudios.svceinterrupt.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements MyInterface {

    ImageView con1, con2, con3, dev1, dev2, dev3;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) v.findViewById(R.id.shimmer);

        return v;
    }

    @Override
    public void fragmentNowVisible() {
        Log.d("Debug", "Profile visible");
    }

}
