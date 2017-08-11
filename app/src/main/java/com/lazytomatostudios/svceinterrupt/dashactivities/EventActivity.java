package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lazytomatostudios.svceinterrupt.R;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class EventActivity extends Activity implements View.OnClickListener {

    ResideMenu resideMenu;
    String titles[] = { "Code Ninja", "Code Sprint", "Cognition Quest", "Data De-Queue", "Don of Logic", "Game of Archives", "MYB", "Picturesque", "Quizzler" };
    int icon[] = { R.drawable.ic_account_circle_black_24dp };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        initMenu();
    }

    public void initMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.profile_gradient);
        resideMenu.attachToActivity(this);

        ResideMenuItem cdnj = new ResideMenuItem(this, icon[0], titles[0]);
        ResideMenuItem cdsp = new ResideMenuItem(this, icon[0], titles[1]);
        ResideMenuItem cgqt = new ResideMenuItem(this, icon[0], titles[2]);
        ResideMenuItem data = new ResideMenuItem(this, icon[0], titles[3]);
        ResideMenuItem dlgc = new ResideMenuItem(this, icon[0], titles[4]);
        ResideMenuItem garc = new ResideMenuItem(this, icon[0], titles[5]);
        ResideMenuItem myb = new ResideMenuItem(this, icon[0], titles[6]);
        ResideMenuItem pctq = new ResideMenuItem(this, icon[0], titles[7]);
        ResideMenuItem quiz = new ResideMenuItem(this, icon[0], titles[8]);

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

    @Override
    public void onClick(View view) {

    }
}
