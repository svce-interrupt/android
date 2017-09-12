package com.lazytomatostudios.svceinterrupt.navbarfragments;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazytomatostudios.svceinterrupt.MainActivity;
import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.interfaces.MyInterface;
import com.lazytomatostudios.svceinterrupt.profileactivities.UpdateActivity;

public class PostLogin extends Fragment implements MyInterface {

    private String userMail;
    private TextView userNameText;
    private TextView userMailText;
    private TextView mobileNumText;
    private TextView collegeText;
    private TextView eventsText;
    private FloatingActionButton floatingActionButton;

    public PostLogin(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String userMail = getArguments().getString("email");

        View v = inflater.inflate(R.layout.activity_post_login, container, false);

        userNameText = (TextView)v.findViewById(R.id.UsernameInput);
        userMailText = (TextView)v.findViewById(R.id.mailInput);
        mobileNumText = (TextView)v.findViewById(R.id.mobileInput);
        collegeText = (TextView)v.findViewById(R.id.collegeInput);
        eventsText = (TextView)v.findViewById(R.id.eventsInput);
        floatingActionButton = (FloatingActionButton)v.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), UpdateActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.dummy_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.SignOut:
                i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("username", "anonymous");
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fragmentNowVisible() {
        Log.d("Debug", "PostLogin visible");
    }
}
