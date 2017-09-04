package com.lazytomatostudios.svceinterrupt.homeactivities;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lazytomatostudios.svceinterrupt.R;

public class ContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmerAnimation();
    }

}
