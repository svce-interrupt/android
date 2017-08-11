package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lazytomatostudios.svceinterrupt.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoidG9tYXRvc3plY2h1YW4iLCJhIjoiY2o2MmIwbHQwMTM2ZjJ3bW8zbjd0bnl2ZiJ9.H-PaElBPwZQl7CYDpJYXpg");
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
