package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.BattleCode;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CodeOPolyOnline;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.MindYourBusiness;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.BreakingTheLogicianCode;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CoderBay;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.Connect4;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.PresentationPark;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.QuizWiz;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.DonOfLogic;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.FlipATable;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CodeOPoly;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.Picturesque;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    ResideMenu resideMenu;
    String titles[] = { "Battle Code", "Breaking the Logician\'s Code", "Coder\'s Bay", "Connect 4", "Don of Logic", "Flip a Table!", "Code-O-Poly", "Mind Your Business v3.0", "Picturesque", "Presentation Park", "Quiz Wiz"};
    int icon[] = { R.drawable.ic_account_circle_black_24dp };
    ResideMenuItem btc, fat, mybv, blc, pp, qw, dlgc, cop, cb, pctq, c4;
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
                case "battle code":
                    initFrag = new BattleCode();
                    break;
                case "breaking the logician code":
                    initFrag = new BreakingTheLogicianCode();
                    break;
                case "coder bay":
                    initFrag = new CoderBay();
                    break;
                case "connect4":
                    initFrag = new Connect4();
                    break;
                case "don of logic":
                    initFrag = new DonOfLogic();
                    break;
                case "flip a table":
                    initFrag = new FlipATable();
                    break;
                case "game of archives":
                    initFrag = new CodeOPoly();
                    break;
                case "Mind Your Business v3.0":
                    initFrag = new MindYourBusiness();
                    break;
                case "presentation park":
                    initFrag = new PresentationPark();
                    break;
                case "picturesque":
                    initFrag = new Picturesque();
                    break;
                case "quizwiz":
                    initFrag = new QuizWiz();
                    break;
                default:
                    initFrag = new BattleCode();
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
                    .add(R.id.frame_layout, new BattleCode())
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

        btc = new ResideMenuItem(this, icon[0], titles[0]);
        fat = new ResideMenuItem(this, icon[0], titles[5]);
        mybv = new ResideMenuItem(this, icon[0], titles[7]);
        blc = new ResideMenuItem(this, icon[0], titles[1]);
        pp = new ResideMenuItem(this, icon[0], titles[9]);
        qw = new ResideMenuItem(this, icon[0], titles[10]);
        dlgc = new ResideMenuItem(this, icon[0], titles[4]);
        cop = new ResideMenuItem(this, icon[0], titles[6]);
        cb = new ResideMenuItem(this, icon[0], titles[2]);
        pctq = new ResideMenuItem(this, icon[0], titles[8]);
        c4 = new ResideMenuItem(this, icon[0], titles[3]);

        btc.setOnClickListener(this);
        fat.setOnClickListener(this);
        mybv.setOnClickListener(this);
        blc.setOnClickListener(this);
        pp.setOnClickListener(this);
        qw.setOnClickListener(this);
        dlgc.setOnClickListener(this);
        cop.setOnClickListener(this);
        cb.setOnClickListener(this);
        pctq.setOnClickListener(this);
        c4.setOnClickListener(this);

        resideMenu.addMenuItem(btc, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(blc, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(cb, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(c4, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(dlgc, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(fat, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(cop, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(mybv, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(pctq, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(pp, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(qw, ResideMenu.DIRECTION_LEFT);

        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
    }

    public void openMenu(View view) {
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, CodeOPolyOnline.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        if (view == btc) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new BattleCode())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == fat) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new FlipATable())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == mybv) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new MindYourBusiness())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == blc) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new BreakingTheLogicianCode())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == pp) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new PresentationPark())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == qw) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new QuizWiz())
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
        } else if (view == cop) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new CodeOPoly())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        } else if (view == cb) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new CoderBay())
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
        } else if (view == c4) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new Connect4())
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
            resideMenu.closeMenu();
        }

    }
}
