package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.lazytomatostudios.svceinterrupt.R;

import java.util.Arrays;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String string;

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

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker and move the camera
        LatLng svce_center = new LatLng(12.987079, 79.972057);
        LatLng svce_ne = new LatLng(12.990996,79.977368);
        LatLng svce_sw = new LatLng(12.981106,79.965373);
        LatLngBounds latLngBounds = new LatLngBounds(svce_sw, svce_ne);

        mMap.setMinZoomPreference(15.0f);
        mMap.setLatLngBoundsForCameraTarget(latLngBounds);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(svce_center, 16));

        final Marker clg=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.986644, 79.971972))
                .title("SVCE")
                .snippet("also called shitvce"));
        final Marker B5can=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988040,79.974906))
                .title("Block 5 Canteen")
                .snippet("shitty food"));
        final Marker cse=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987496, 79.972795))
                .title("CSE Department")
                .snippet("blahblah"));
        final Marker B5=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988513, 79.974230))
                .title("Block 5")
                .snippet("GGWP"));
        final Marker ec=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988636, 79.974021))
                .title("ECE Department")
                .snippet("GG"));
        final Marker BB2=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988745, 79.974587))
                .title("Bus Bay 2")
                .snippet("shitty bus"));
        final Marker BB1=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.986608, 79.970998))
                .title("Bus Bay 1")
                .snippet("shitty bus"));
        final Marker civ=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988745, 79.974163))
                .title("Civil Department")
                .snippet("blahblah"));
        final Marker B4=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987402, 79.973321))
                .title("Block 4")
                .snippet("GGWP"));
        final Marker B3=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988063, 79.972637))
                .title("Block 3")
                .snippet("GG"));
        final Marker mech=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987663, 79.972658))
                .title("Mech Department")
                .snippet("GG"));
        final Marker itd=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987243, 79.972999))
                .title("IT Department")
                .snippet("GG"));
        final Marker eee=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987420, 79.972395))
                .title("EEE Department")
                .snippet("GG"));
        final Marker mca=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987287, 79.972495))
                .title("MCA Department")
                .snippet("GG"));
        final Marker appsc=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987358, 79.972621))
                .title("Applied Science Department")
                .snippet("GG"));
        final Marker chem=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987872, 79.972060))
                .title("Chemical Department")
                .snippet("GG"));
        final Marker biotech=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987459, 79.971524))
                .title("BioTech Department")
                .snippet("GG"));
        final Marker marine=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988024, 79.971411))
                .title("Marine Department")
                .snippet("GG"));
        final Marker B2=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.986858, 79.972543))
                .title("Block 2")
                .snippet("GGWP"));
        final Marker B1=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.986686, 79.972309))
                .title("Block 1")
                .snippet("GG"));
        final Marker maincan=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.986547, 79.972199))
                .title("Main Canteen")
                .snippet("GG"));
        final Marker cenlib=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.986822, 79.971497))
                .title("Central Library")
                .snippet("GG"));
        final Marker ad=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987039, 79.971937))
                .title("Administration")
                .snippet("GG"));
        final Marker off=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.986979, 79.972071))
                .title("Office")
                .snippet("GG"));
        final Marker vidhal1=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.986877, 79.972149))
                .title("Video Hall 1")
                .snippet("GG"));
        final Marker vidhal2=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.986814, 79.972208))
                .title("Video Hall 2")
                .snippet("GG"));
        final Marker oat=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987227, 79.971027))
                .title("Open Air Theatre")
                .snippet("GG"));
        final Marker mechws=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988319, 79.972250))
                .title("Mechanical Workshop")
                .snippet("GG"));
        final Marker autows=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988377, 79.971593))
                .title("Automobile Workshop")
                .snippet("GG"));
        final Marker marinews=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988163, 79.971140))
                .title("Marine Workshop")
                .snippet("GG"));
        final Marker chemlb=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988016, 79.971786))
                .title("Chemical Lab")
                .snippet("GG"));
        final Marker rnd=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.989007, 79.971148))
                .title("Research and Development")
                .snippet("GG"));
        final Marker mulhal=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.989318, 79.971486))
                .title("Multipurpose Hall")
                .snippet("GG"));
        final Marker swim=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.989822, 79.971234))
                .title("Swimming Pool")
                .snippet("GG"));
        final Marker clinic=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988229, 79.969861))
                .title("SVCE Clinic")
                .snippet("GG"));
        final Marker hstmen=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.988463, 79.970555))
                .title("Mens Hostel")
                .snippet("GG"));
        final Marker hstwomen=mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.987287, 79.969558))
                .title("Ladies Hostel")
                .snippet("GG"));





        //marker Animation
        final ValueAnimator va = ValueAnimator.ofFloat(20, 1);
        va.setDuration(1500);
        va.setInterpolator(new BounceInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                clg.setAnchor(0f, (Float) animation.getAnimatedValue());
                B5can.setAnchor(0f, (Float) animation.getAnimatedValue());
                cse.setAnchor(0f, (Float) animation.getAnimatedValue());
                B5.setAnchor(0f, (Float) animation.getAnimatedValue());
                B4.setAnchor(0f, (Float) animation.getAnimatedValue());
                B3.setAnchor(0f, (Float) animation.getAnimatedValue());
                ec.setAnchor(0f, (Float) animation.getAnimatedValue());
                civ.setAnchor(0f, (Float) animation.getAnimatedValue());
                BB2.setAnchor(0f, (Float) animation.getAnimatedValue());
                mech.setAnchor(0f,(Float) animation.getAnimatedValue());
                itd.setAnchor(0f,(Float) animation.getAnimatedValue());
                eee.setAnchor(0f,(Float)animation.getAnimatedValue());
                mca.setAnchor(0f,(Float) animation.getAnimatedValue());
                appsc.setAnchor(0f,(Float) animation.getAnimatedValue());
                chem.setAnchor(0f,(Float) animation.getAnimatedValue());
                biotech.setAnchor(0f,(Float) animation.getAnimatedValue());
                marine.setAnchor(0f,(Float) animation.getAnimatedValue());
                B1.setAnchor(0f,(Float) animation.getAnimatedValue());
                B2.setAnchor(0f,(Float) animation.getAnimatedValue());
                maincan.setAnchor(0f,(Float) animation.getAnimatedValue());
                cenlib.setAnchor(0f,(Float) animation.getAnimatedValue());
                ad.setAnchor(0f,(Float) animation.getAnimatedValue());
                off.setAnchor(0f,(Float) animation.getAnimatedValue());
                vidhal1.setAnchor(0f,(Float) animation.getAnimatedValue());
                vidhal2.setAnchor(0f,(Float) animation.getAnimatedValue());
                oat.setAnchor(0f,(Float) animation.getAnimatedValue());
                mechws.setAnchor(0f,(Float) animation.getAnimatedValue());
                autows.setAnchor(0f,(Float) animation.getAnimatedValue());
                chemlb.setAnchor(0f,(Float) animation.getAnimatedValue());
                marinews.setAnchor(0f,(Float) animation.getAnimatedValue());
                rnd.setAnchor(0f,(Float) animation.getAnimatedValue());
                mulhal.setAnchor(0f,(Float) animation.getAnimatedValue());
                swim.setAnchor(0f,(Float) animation.getAnimatedValue());
                clinic.setAnchor(0f,(Float) animation.getAnimatedValue());
                hstmen.setAnchor(0f,(Float) animation.getAnimatedValue());
                hstwomen.setAnchor(0f,(Float) animation.getAnimatedValue());
            }
        });
        va.start();


        //pathways
        final Dot DOT = new Dot();
        final Dash DASH = new Dash(30);
        final Gap GAP = new Gap(20);
        final List<PatternItem> PATTERN = Arrays.asList(GAP, DASH, GAP, DASH);

        Polyline polyline = googleMap.addPolyline(new PolylineOptions()
                .width(20)
                .color(Color.WHITE)
                .add(
                        new LatLng(12.9832, 79.971062),
                        new LatLng(12.983461, 79.971052),
                        new LatLng(12.983785, 79.971186),
                        new LatLng(12.986318, 79.971392),
                        new LatLng(12.986386, 79.97136),
                        new LatLng(12.98716, 79.970745),
                        new LatLng(12.98762,79.970781),
                        new LatLng(12.988213,79.970805),//cli
                        new LatLng(12.988286,79.97081),//ws
                        new LatLng(12.98918,79.970925),//mul
                        new LatLng(12.989034,79.97283),//ct
                        new LatLng(12.988911,79.974692)));
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .width(20)
                .color(Color.WHITE)
                .add(new LatLng(12.983294,79.97122),
                        new LatLng(12.983785, 79.971186)));
        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
                .width(20)
                .color(Color.WHITE)
                .add(new LatLng(12.986318, 79.971392),
                        new LatLng(12.986258,79.971837),
                        new LatLng(12.986723,79.971837),
                        new LatLng(12.986386, 79.97136)));
        Polyline polyline3 = googleMap.addPolyline(new PolylineOptions()
                .width(20)
                .color(Color.WHITE)
                .add(new LatLng(12.98735, 79.969737),
                        new LatLng(12.987264,79.970756)));
        Polyline polyline4 = googleMap.addPolyline(new PolylineOptions()
                .width(20)
                .color(Color.WHITE)
                .add(new LatLng(12.98762,79.970781),
                        new LatLng(12.987557,79.971926),
                        new LatLng(12.988208,79.972025),
                        new LatLng(12.988286,79.97081)));
        Polyline polyline5 = googleMap.addPolyline(new PolylineOptions()
                .width(20)
                .color(Color.WHITE)
                .add(new LatLng(12.98832, 79.969702),
                        new LatLng(12.988285,79.97081)));
        Polyline polyline6 = googleMap.addPolyline(new PolylineOptions()
                .width(20)
                .color(Color.WHITE)
                .add(new LatLng(12.98841, 79.970649),
                        new LatLng(12.988285,79.97081)));
        Polyline polyline7 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.986258,79.971837),
                        new LatLng(12.986221,79.971966),
                        new LatLng(12.986261,79.972304),
                        new LatLng(12.986726,79.972908),
                        new LatLng(12.987154,79.973546),
                        new LatLng(12.987988,79.974533),
                        new LatLng(12.988171,79.974512)));
        Polyline polyline8 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.987988,79.974533),
                        new LatLng(12.988006,79.974611),
                        new LatLng(12.987949,79.974662)));
        Polyline polyline9 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.988182,79.974536),
                        new LatLng(12.988124,79.974608),
                        new LatLng(12.988106,79.974726),
                        new LatLng(12.988286,79.974914)));
        Polyline polyline10 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.988749,79.974509),
                        new LatLng(12.988801,79.974332),
                        new LatLng(12.988528,79.974007),
                        new LatLng(12.988438,79.973964),
                        new LatLng(12.987944,79.973288),
                        new LatLng(12.987698,79.972894)));
        Polyline polyline11 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.987944,79.973288),
                        new LatLng(12.988059,79.972822)));
        Polyline polyline12 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.988132,79.972613),
                        new LatLng(12.988208,79.972025)));
        Polyline polyline13 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.988205,79.972224),
                        new LatLng(12.987972,79.972216)));
        Polyline polyline14 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.988033,79.97224),
                        new LatLng(12.988039,79.9723),
                        new LatLng(12.987275,79.972819),
                        new LatLng(12.987162,79.972894),
                        new LatLng(12.986977,79.972739),
                        new LatLng(12.986971,79.972417),
                        new LatLng(12.986846,79.972265)));
        Polyline polyline15 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.986971,79.972417),
                        new LatLng(12.987263,79.972454),
                        new LatLng(12.987228,79.972065),
                        new LatLng(12.987557,79.971926)));
        Polyline polyline16 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.987228,79.972065),
                        new LatLng(12.987181,79.971982),
                        new LatLng(12.987181,79.971899),
                        new LatLng(12.987157,79.971899)));
        Polyline polyline17 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.986971,79.972417),
                        new LatLng(12.986773,79.972358)));
        Polyline polyline18 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.986815,79.972521),
                        new LatLng(12.986842,79.972375)));
        Polyline polyline19 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.987181,79.971982),
                        new LatLng(12.986864,79.972315)));
        Polyline polyline20 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.987528,79.97269),
                        new LatLng(12.987465,79.972621)));
        Polyline polyline21 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.987371,79.973342),
                        new LatLng(12.987149,79.973525)));
        Polyline polyline22 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.987275,79.972819),
                        new LatLng(12.987272,79.972929)));
        Polyline polyline23 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.987275,79.972819),
                        new LatLng(12.987366,79.972881)));
        Polyline polyline24 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.986977,79.972739),
                        new LatLng(12.98693,79.973224)));
        Polyline polyline25 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.986621,79.972221),
                        new LatLng(12.986723,79.971837),
                        new LatLng(12.986822,79.972159)));
        Polyline polyline26 = googleMap.addPolyline(new PolylineOptions()
                .width(12)
                .pattern(PATTERN)
                .color(Color.WHITE)
                .add(new LatLng(12.987018,79.971894),
                        new LatLng(12.986723,79.971837),
                        new LatLng(12.986799,79.971542)));




        //Set Buildings
        final PolygonOptions svcebound = new PolygonOptions()
                .add(new LatLng(12.982813, 79.970502),
                        new LatLng(12.98619,79.970915),
                        new LatLng(12.986577,79.968485),
                        new LatLng(12.987445,79.968463),
                        new LatLng(12.988124,79.968967),
                        new LatLng(12.989138,79.969579),
                        new LatLng(12.990884,79.970191),
                        new LatLng(12.988537,79.976751),
                        new LatLng(12.986859,79.975346),
                        new LatLng(12.986995,79.974976),
                        new LatLng(12.986321,79.974713),
                        new LatLng(12.984852, 79.973812),
                        new LatLng(12.984381, 79.974101),
                        new LatLng(12.982813, 79.970502))
                .strokeWidth(10)
                .strokeColor(Color.BLACK);
        final PolygonOptions eeedpt = new PolygonOptions()
                .add(new LatLng(12.987081, 79.972484),
                        new LatLng(12.987073,79.972731),
                        new LatLng(12.987311,79.972744),
                        new LatLng(12.987314,79.97268),
                        new LatLng(12.987405,79.972677),
                        new LatLng(12.987565,79.97254),
                        new LatLng(12.987578,79.972444),
                        new LatLng(12.987664,79.972441),
                        new LatLng(12.987675,79.972132),
                        new LatLng(12.987429, 79.972124),
                        new LatLng(12.987421, 79.972342),
                        new LatLng(12.987245, 79.972484),
                        new LatLng(12.987081, 79.972484))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions itdpt = new PolygonOptions()
                .add(new LatLng(12.987303, 79.972934),
                        new LatLng(12.987301,79.973117),
                        new LatLng(12.98722,79.973122),
                        new LatLng(12.987215,79.973254),
                        new LatLng(12.987063,79.973254),
                        new LatLng(12.987066,79.973042),
                        new LatLng(12.987214,79.97303),
                        new LatLng(12.987215,79.972934),
                        new LatLng(12.987303, 79.972934))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions csdpt = new PolygonOptions()
                .add(new LatLng(12.98735, 79.97306),
                        new LatLng(12.987609,79.97306),
                        new LatLng(12.987609,79.972945),
                        new LatLng(12.987769,79.972808),
                        new LatLng(12.987923,79.972808),
                        new LatLng(12.987923,79.972537),
                        new LatLng(12.987716,79.972537),
                        new LatLng(12.987716,79.97261),
                        new LatLng(12.987609,79.97261),
                        new LatLng(12.987465,79.972757),
                        new LatLng(12.987465,79.972838),
                        new LatLng(12.98735,79.972838),
                        new LatLng(12.98735, 79.97306))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions bk4 = new PolygonOptions()
                .add(new LatLng(12.98758,79.973224),
                        new LatLng(12.98758,79.973347),
                        new LatLng(12.987494,79.973347),
                        new LatLng(12.987494,79.973433),
                        new LatLng(12.987369,79.973433),
                        new LatLng(12.987369,79.973347),
                        new LatLng(12.987285,79.973347),
                        new LatLng(12.987285,79.973224),
                        new LatLng(12.987369,79.973224),
                        new LatLng(12.987369,79.973144),
                        new LatLng(12.987494,79.973144),
                        new LatLng(12.987494,79.973224),
                        new LatLng(12.98758,79.973224))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions bk3 = new PolygonOptions()
                .add(new LatLng(12.988192, 79.972559),
                        new LatLng(12.988263, 79.972645),
                        new LatLng(12.988196, 79.972704),
                        new LatLng(12.988254, 79.972773),
                        new LatLng(12.988152, 79.972859),
                        new LatLng(12.988105, 79.972798),
                        new LatLng(12.988058, 79.972835),
                        new LatLng(12.98799, 79.972742),
                        new LatLng(12.988032, 79.972698),
                        new LatLng(12.987982, 79.972637),
                        new LatLng(12.98808, 79.972551),
                        new LatLng(12.988134, 79.972613),
                        new LatLng(12.988192, 79.972559))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions bk5 = new PolygonOptions()
                .add(new LatLng(12.988537, 79.974184),
                        new LatLng(12.988581, 79.974233),
                        new LatLng(12.988706, 79.974244),
                        new LatLng(12.988709, 79.974326),
                        new LatLng(12.98861,79.974343),
                        new LatLng(12.988608,79.974396),
                        new LatLng(12.98838,79.974595),
                        new LatLng(12.988307,79.974608),
                        new LatLng(12.988297, 79.974675),
                        new LatLng(12.988202, 79.974672),
                        new LatLng(12.988208, 79.974565),
                        new LatLng(12.988155, 79.974501),
                        new LatLng(12.988043, 79.974485),
                        new LatLng(12.988046, 79.974396),
                        new LatLng(12.988142,79.974391),
                        new LatLng(12.988153,79.974316),
                        new LatLng(12.98837,79.974131),
                        new LatLng(12.988455,79.974123),
                        new LatLng(12.988461, 79.974037),
                        new LatLng(12.988553, 79.974045),
                        new LatLng(12.988537, 79.974184))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions ecdpt = new PolygonOptions()
                .add(new LatLng(12.988712, 79.974058),
                        new LatLng(12.988715, 79.974195),
                        new LatLng(12.98878, 79.974208),
                        new LatLng(12.988838, 79.974149),
                        new LatLng(12.988843, 79.974066),
                        new LatLng(12.988929, 79.974064),
                        new LatLng(12.988939, 79.973771),
                        new LatLng(12.988681, 79.973771),
                        new LatLng(12.988676, 79.973983),
                        new LatLng(12.988587, 79.97398),
                        new LatLng(12.988592, 79.97405),
                        new LatLng(12.988712, 79.974058))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions b5c = new PolygonOptions()
                .add(new LatLng(12.987784, 79.974474),
                        new LatLng(12.98814, 79.974903),
                        new LatLng(12.988038, 79.974992),
                        new LatLng(12.987685, 79.974573),
                        new LatLng(12.987784, 79.974474 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions busb2 = new PolygonOptions()
                .add(new LatLng(12.988994, 79.974825),
                        new LatLng(12.988537, 79.975204),
                        new LatLng(12.988281, 79.974903),
                        new LatLng(12.988741, 79.974495),
                        new LatLng(12.988994, 79.974825))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.DKGRAY);
        final PolygonOptions busb1 = new PolygonOptions()
                .add(new LatLng(12.986417, 79.971288),
                        new LatLng(12.987157, 79.970663),
                        new LatLng(12.987157, 79.970435),
                        new LatLng(12.986435, 79.970429),
                        new LatLng(12.986417, 79.971288))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.DKGRAY);
        final PolygonOptions wsmech = new PolygonOptions()
                .add(new LatLng(12.988691, 79.972503),
                        new LatLng(12.988297, 79.972503),
                        new LatLng(12.988297, 79.971996),
                        new LatLng(12.988691, 79.971996),
                        new LatLng(12.988691, 79.972503 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions wsauto = new PolygonOptions()
                .add(new LatLng(12.988297, 79.971829),
                        new LatLng(12.988297, 79.971352),
                        new LatLng(12.988652, 79.971352),
                        new LatLng(12.988652, 79.971829),
                        new LatLng(12.988297, 79.971829 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions rndd = new PolygonOptions()
                .add(new LatLng(12.988746, 79.971352),
                        new LatLng(12.989102, 79.971352),
                        new LatLng(12.989102, 79.971105),
                        new LatLng(12.988746, 79.971105),
                        new LatLng(12.988746, 79.971352 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions muhu = new PolygonOptions()
                .add(new LatLng(12.989253, 79.971738),
                        new LatLng(12.989716, 79.971738),
                        new LatLng(12.989716, 79.971183),
                        new LatLng(12.989253, 79.971183),
                        new LatLng(12.989253, 79.971738 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions pl = new PolygonOptions()
                .add(new LatLng(12.990061, 79.971188),
                        new LatLng(12.989755, 79.971188),
                        new LatLng(12.989755, 79.97144),
                        new LatLng(12.990061, 79.97144),
                        new LatLng(12.990061, 79.971188 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.CYAN);
        final PolygonOptions chelb = new PolygonOptions()
                .add(new LatLng(12.98808, 79.971711),
                        new LatLng(12.988059, 79.971904),
                        new LatLng(12.987705, 79.971878),
                        new LatLng(12.987719, 79.971676),
                        new LatLng(12.98808, 79.971711))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions wsmar = new PolygonOptions()
                .add(new LatLng(12.988226, 79.971001),
                        new LatLng(12.988216, 79.971186),
                        new LatLng(12.987685, 79.971145),
                        new LatLng(12.987706, 79.970952),
                        new LatLng(12.988226, 79.971001 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions oatt = new PolygonOptions()
                .add(new LatLng(12.987374, 79.971201),
                        new LatLng(12.987147, 79.971242),
                        new LatLng(12.987076, 79.970866),
                        new LatLng(12.987314, 79.970834),
                        new LatLng(12.987374, 79.971201 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions cl = new PolygonOptions()
                .add(new LatLng(12.98712, 79.971204),
                        new LatLng(12.98711, 79.97155),
                        new LatLng(12.98666, 79.971548),
                        new LatLng(12.986665, 79.971202),
                        new LatLng(12.98712, 79.971204 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions chemdpt = new PolygonOptions()
                .add(new LatLng(12.98779, 79.972022),
                        new LatLng(12.987923, 79.972028),
                        new LatLng(12.987918, 79.972189),
                        new LatLng(12.987978, 79.972192),
                        new LatLng(12.987978, 79.972248 ),
                        new LatLng(12.987735,79.972245),
                        new LatLng(12.987737,79.972162),
                        new LatLng(12.98779,79.972162),
                        new LatLng(12.98779, 79.972022))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions marb = new PolygonOptions()
                .add(new LatLng(12.988163, 79.971237),
                        new LatLng(12.988158, 79.971344),
                        new LatLng(12.988048, 79.971347),
                        new LatLng(12.988048, 79.971481),
                        new LatLng(12.988134, 79.971481),
                        new LatLng(12.988134,79.971599),
                        new LatLng(12.987643,79.971599),
                        new LatLng(12.987643,79.97128),
                        new LatLng(12.987915, 79.97128),
                        new LatLng(12.987915,79.971237),
                        new LatLng(12.988163, 79.971237))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions medbay = new PolygonOptions()
                .add(new LatLng(12.988276, 79.969928),
                        new LatLng(12.988119, 79.969928),
                        new LatLng(12.988119, 79.969791),
                        new LatLng(12.988276, 79.969791),
                        new LatLng(12.988276, 79.969928 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions hstm= new PolygonOptions()
                .add(new LatLng(12.99056, 79.97063),
                        new LatLng(12.988396, 79.970738),
                        new LatLng(12.988396, 79.969268),
                        new LatLng(12.99056, 79.97063))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions hstw= new PolygonOptions()
                .add(new LatLng(12.987821, 79.969751),
                        new LatLng(12.987821, 79.968737),
                        new LatLng(12.986765, 79.968737),
                        new LatLng(12.986765, 79.969751),
                        new LatLng(12.987821, 79.969751 ))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions btb= new PolygonOptions()
                .add(new LatLng(12.987238, 79.971333),
                        new LatLng(12.987238, 79.971636),
                        new LatLng(12.987413, 79.971636),
                        new LatLng(12.987413, 79.971564),
                        new LatLng(12.987541, 79.971564),
                        new LatLng(12.987541,79.971448),
                        new LatLng(12.987439,79.971448),
                        new LatLng(12.987439,79.971333),
                        new LatLng(12.987238, 79.971333))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions maioff= new PolygonOptions()
                .add(new LatLng(12.987, 79.971626),
                        new LatLng(12.987, 79.971859),
                        new LatLng(12.987008, 79.971859),
                        new LatLng(12.987008, 79.971972),
                        new LatLng(12.986689, 79.972275),
                        new LatLng(12.98653,79.972079),
                        new LatLng(12.98644,79.972079),
                        new LatLng(12.98644,79.972181),
                        new LatLng(12.986313, 79.972181),
                        new LatLng(12.986313,79.972301),
                        new LatLng(12.986477,79.972301),
                        new LatLng(12.986613,79.972492),
                        new LatLng(12.986684,79.972438),
                        new LatLng(12.986734,79.972485),
                        new LatLng(12.986817,79.972409),
                        new LatLng(12.986781,79.972366),
                        new LatLng(12.987157,79.97198),
                        new LatLng(12.987157,79.971859),
                        new LatLng(12.987191,79.971792),
                        new LatLng(12.987196,79.97169),
                        new LatLng(12.987162,79.971626),
                        new LatLng(12.987, 79.971626))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);
        final PolygonOptions bk2= new PolygonOptions()
                .add(new LatLng(12.98687,79.972465),
                        new LatLng(12.986953, 79.972551),
                        new LatLng(12.986903, 79.972613),
                        new LatLng(12.986953, 79.97268),
                        new LatLng(12.98687, 79.972757),
                        new LatLng(12.986812,79.972701),
                        new LatLng(12.986753,79.972759),
                        new LatLng(12.986668,79.972676),
                        new LatLng(12.986734, 79.97261),
                        new LatLng(12.986673,79.97254),
                        new LatLng(12.986747,79.972475),
                        new LatLng(12.986808,79.972535),
                        new LatLng(12.98687,79.972465))
                .strokeWidth(5)
                .strokeColor(Color.BLACK)
                .fillColor(Color.LTGRAY);

        Polygon polygon27=mMap.addPolygon(svcebound);
        Polygon polygon=mMap.addPolygon(eeedpt);
        Polygon polygon1=mMap.addPolygon(itdpt);
        Polygon polygon2=mMap.addPolygon(csdpt);
        Polygon polygon3=mMap.addPolygon(bk4);
        Polygon polygon4=mMap.addPolygon(bk3);
        Polygon polygon5=mMap.addPolygon(bk5);
        Polygon polygon6=mMap.addPolygon(ecdpt);
        Polygon polygon7=mMap.addPolygon(b5c);
        Polygon polygon8=mMap.addPolygon(busb2);
        Polygon polygon9=mMap.addPolygon(busb1);
        Polygon polygon10=mMap.addPolygon(wsmech);
        Polygon polygon11=mMap.addPolygon(wsauto);
        Polygon polygon12=mMap.addPolygon(rndd);
        Polygon polygon13=mMap.addPolygon(muhu);
        Polygon polygon14=mMap.addPolygon(pl);
        Polygon polygon15=mMap.addPolygon(wsmar);
        Polygon polygon16=mMap.addPolygon(chelb);
        Polygon polygon17=mMap.addPolygon(oatt);
        Polygon polygon18=mMap.addPolygon(cl);
        Polygon polygon19=mMap.addPolygon(chemdpt);
        Polygon polygon20=mMap.addPolygon(marb);
        Polygon polygon21=mMap.addPolygon(medbay);
        Polygon polygon22=mMap.addPolygon(hstm);
        Polygon polygon23=mMap.addPolygon(hstw);
        Polygon polygon24=mMap.addPolygon(btb);
        Polygon polygon25=mMap.addPolygon(maioff);
        Polygon polygon26=mMap.addPolygon(bk2);

    }
}