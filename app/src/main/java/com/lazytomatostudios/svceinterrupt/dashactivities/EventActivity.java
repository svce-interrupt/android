package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.BattleCode;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CodeOPoly;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.MindYourBusiness;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.BreakingTheLogicianCode;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CoderBay;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.Connect4;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.PresentationPark;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.QuizWiz;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.SurpriseEvent;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.FlipATable;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.Picturesque;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    ResideMenu resideMenu;
    String titles[] = { "Battle Code", "Logician\'s Code", "Coder\'s Bay", "Connect 4", "Surprise Event", "Flip a Table!", "First Strike", "Mind Your Business v4.0", "Picturesque", "Presentation Park", "Quiz Wiz"};
    int icon[] = { R.drawable.ic_account_circle_black_24dp };
    ResideMenuItem btc, fat, mybv, blc, pp, qw, dlgc, cop, cb, pctq, c4;
    Fragment initFrag;

    String string, mail;

    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Bundle bundle = getIntent().getExtras();

        string = bundle.getString("event");
        mail = bundle.getString("mail");

        Log.d("TAG-", string+mail);

        if(mail.equals("null")) {
            Log.d("TAG--", mail);
        } else {
            Log.d("TAG***", mail);
        }

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);

        initMenu();

        if(!string.equals("null")) {

            Log.d("TAG------", string+mail);

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
                    initFrag = new SurpriseEvent();
                    break;
                case "flip a table":
                    initFrag = new FlipATable();
                    break;
                case "game of archives":
                    initFrag = new CodeOPoly();
                    break;
                case "Mind Your Business v4.0":
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

        Snackbar.make(coordinatorLayout,
                "Check out this fun workshop on web dev!", Snackbar.LENGTH_LONG)
                .setAction("Okay!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://svce.acm.org/webbed/"));
                        startActivity(intent);

                    }
                }).show();


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
        resideMenu.addMenuItem(cop, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(cb, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(c4, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(dlgc, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(fat, ResideMenu.DIRECTION_LEFT);
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

        if (mail.equals("null")) {
            new AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage("Please sign in to play the online game event.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //
                        }

                    })
                    .show();
        } else {
            Intent intent = new Intent(this, ConnectFourActivity.class);
            intent.putExtra("mail", mail);
            startActivity(intent);
        }

        /*new AlertDialog.Builder(this)
                .setTitle("Alert")
                    .setMessage("Game in alpha testing. Come back later.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //
                        }

                    })
                .show();*/
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
                    .replace(R.id.frame_layout, new SurpriseEvent())
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
