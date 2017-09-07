package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.ConnectFourGame;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.ConnectFourMain;

public class ConnectFourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_four);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_layout_game, new ConnectFourMain())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }

    public void startGame(View view) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_game, new ConnectFourGame())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }
}
