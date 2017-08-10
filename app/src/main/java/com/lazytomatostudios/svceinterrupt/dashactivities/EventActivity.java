package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
   
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CodeNinja;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.CodeSprint;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.eventfragments.EventDescription;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.eventfragments.EventFormat;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.eventfragments.EventInfo;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    ResideMenu resideMenu;
    int i;

    private ResideMenuItem codeNinja;
    private ResideMenuItem codeSprnt;
    private ResideMenuItem cogQuest;
    private ResideMenuItem dataDeque;
    private ResideMenuItem donLogic;
    private ResideMenuItem gameArch;
    private ResideMenuItem myb;
    private ResideMenuItem picturesque;
    private ResideMenuItem quizzler;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.profile_gradient);
        resideMenu.attachToActivity(this);

        // create menu items;
        String titles[] = { "Code Ninja", "Code Sprint", "Cognition Quest", "Data De-Queue", "Don of Logic", "Game of Archives", "MYB", "Picturesque", "Quizzler" };
        int icon[] = { R.drawable.ic_account_circle_black_24dp };

        codeNinja = new ResideMenuItem(this, icon[0], titles[0]);
        codeSprnt = new ResideMenuItem(this, icon[0], titles[1]);
        cogQuest = new ResideMenuItem(this, icon[0], titles[2]);
        dataDeque = new ResideMenuItem(this, icon[0], titles[3]);
        donLogic = new ResideMenuItem(this, icon[0], titles[4]);
        gameArch = new ResideMenuItem(this, icon[0], titles[5]);
        myb = new ResideMenuItem(this, icon[0], titles[6]);
        picturesque = new ResideMenuItem(this, icon[0], titles[7]);
        quizzler = new ResideMenuItem(this, icon[0], titles[8]);

        codeNinja.setOnClickListener(this);
        codeSprnt.setOnClickListener(this);
        cogQuest.setOnClickListener(this);
        dataDeque.setOnClickListener(this);
        donLogic.setOnClickListener(this);
        gameArch.setOnClickListener(this);
        myb.setOnClickListener(this);
        picturesque.setOnClickListener(this);
        quizzler.setOnClickListener(this);

        resideMenu.addMenuItem(codeNinja, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(codeSprnt, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(cogQuest, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(dataDeque, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(donLogic, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(gameArch, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(myb, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(picturesque, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(quizzler, ResideMenu.DIRECTION_LEFT);

        resideMenu.setScaleValue(0.6f);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);

        loadFrag(new CodeNinja());
      
    }

    @Override
    public void onClick(View view) {

        if (view == codeNinja){
            Log.d("HELLO", "0");
            loadFrag(new CodeNinja());
        }else if (view == codeSprnt){
            Log.d("HELLO", "1");
            loadFrag(new CodeSprint());
        }else if (view == cogQuest){
            Log.d("HELLO", "2");
        }else if (view == dataDeque){
            Log.d("HELLO", "3");
        }else if (view == donLogic){
            Log.d("HELLO", "4");
        }else if (view == gameArch){
            Log.d("HELLO", "5");
        }else if (view == myb){
            Log.d("HELLO", "6");
        }else if (view == picturesque){
            Log.d("HELLO", "7");
        }else if (view == quizzler){
            Log.d("HELLO", "8");
        }else {
            //
        }

        resideMenu.closeMenu();
    }

    public void openDrawer(View view) {
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }

    public void loadFrag(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_holder, fragment, "frag")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
