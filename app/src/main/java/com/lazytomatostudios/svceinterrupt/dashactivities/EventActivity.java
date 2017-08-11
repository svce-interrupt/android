package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CodeNinja;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CodeSprint;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CognitionQuest;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.DataDeQueue;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.DonOfLogic;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.GameOfArchives;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.MYB;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.Picturesque;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.Quizzler;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    ResideMenu resideMenu;
    String titles[] = { "Code Ninja", "Code Sprint", "Cognition Quest", "Data De-Queue", "Don of Logic", "Game of Archives", "MYB", "Picturesque", "Quizzler" };
    int icon[] = { R.drawable.ic_account_circle_black_24dp };
    ResideMenuItem cdnj, cdsp, cgqt, data, dlgc, garc, myb, pctq, quiz;
    Fragment initFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Bundle bundle = getIntent().getExtras();
        String string;

        initMenu();

        if(bundle != null) {
            string = bundle.getString("event");

            switch (string) {
                case "code sprint":
                    initFrag = new CodeSprint();
                    break;
                case "code ninja":
                    initFrag = new CodeNinja();
                    break;
                case "cognition quest":
                    initFrag = new CognitionQuest();
                    break;
                case "data de-queue":
                    initFrag = new DataDeQueue();
                    break;
                case "don of logic":
                    initFrag = new DonOfLogic();
                    break;
                case "game of archives":
                    initFrag = new GameOfArchives();
                    break;
                case "myb":
                    initFrag = new MYB();
                    break;
                case "picturesque":
                    initFrag = new Picturesque();
                    break;
                case "quizzler":
                    initFrag = new Quizzler();
                    break;
                default:
                    initFrag = new CodeNinja();
                    break;
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_layout, initFrag)
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();

        } else {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_layout, new CodeNinja())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                }
            }, 250);

        }
    }

    public void initMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.profile_gradient);
        resideMenu.attachToActivity(this);

        cdnj = new ResideMenuItem(this, icon[0], titles[0]);
        cdsp = new ResideMenuItem(this, icon[0], titles[1]);
        cgqt = new ResideMenuItem(this, icon[0], titles[2]);
        data = new ResideMenuItem(this, icon[0], titles[3]);
        dlgc = new ResideMenuItem(this, icon[0], titles[4]);
        garc = new ResideMenuItem(this, icon[0], titles[5]);
        myb = new ResideMenuItem(this, icon[0], titles[6]);
        pctq = new ResideMenuItem(this, icon[0], titles[7]);
        quiz = new ResideMenuItem(this, icon[0], titles[8]);

        cdnj.setOnClickListener(this);
        cdsp.setOnClickListener(this);
        cgqt.setOnClickListener(this);
        data.setOnClickListener(this);
        dlgc.setOnClickListener(this);
        garc.setOnClickListener(this);
        myb.setOnClickListener(this);
        pctq.setOnClickListener(this);
        quiz.setOnClickListener(this);

        resideMenu.addMenuItem(cdnj, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(cdsp, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(cgqt, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(data, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(dlgc, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(garc, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(myb, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(pctq, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(quiz, ResideMenu.DIRECTION_LEFT);

        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
    }

    public void openMenu(View view) {
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }

    @Override
    public void onClick(View view) {

        if (view == cdnj) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new CodeNinja())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == cdsp) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new CodeSprint())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == cgqt) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new CognitionQuest())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == data) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new DataDeQueue())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == dlgc) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new DonOfLogic())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == garc) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new GameOfArchives())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == myb) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new MYB())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == pctq) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new Picturesque())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == quiz) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new Quizzler())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        }

    }
}
