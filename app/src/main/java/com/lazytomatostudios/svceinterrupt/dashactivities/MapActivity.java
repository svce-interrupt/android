package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.lazytomatostudios.svceinterrupt.R;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        LatLng svce_center = new LatLng(12.987079, 79.972057);
        LatLng svce_ne = new LatLng(12.991045, 79.977014);
        LatLng svce_sw = new LatLng(12.982682, 79.968302);

        LatLng svce_ne1 = new LatLng(12.987179, 79.972157);
        LatLng svce_sw1 = new LatLng(12.986979, 79.971957);

        LatLngBounds latLngBounds = new LatLngBounds(svce_sw, svce_ne);

        mMap.addMarker(new MarkerOptions().position(svce_center).title("SVCE"));

        Polyline polyline = mMap.addPolyline( new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(12.983270, 79.971102),
                        new LatLng(12.986417, 79.971403)));

        mMap.setMinZoomPreference(15.0f);
        mMap.setLatLngBoundsForCameraTarget(latLngBounds);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(svce_center, 16));
    }
}
