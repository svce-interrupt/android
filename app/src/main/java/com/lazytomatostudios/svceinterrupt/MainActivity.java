package com.lazytomatostudios.svceinterrupt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lazytomatostudios.svceinterrupt.dashactivities.EventActivity;
import com.lazytomatostudios.svceinterrupt.dashactivities.InstructionActivity;
import com.lazytomatostudios.svceinterrupt.dashactivities.MapActivity;
import com.lazytomatostudios.svceinterrupt.dashactivities.TransportActivity;
import com.lazytomatostudios.svceinterrupt.events.RegisterActivity;
import com.lazytomatostudios.svceinterrupt.homeactivities.AboutActivity;
import com.lazytomatostudios.svceinterrupt.homeactivities.ContactActivity;
import com.lazytomatostudios.svceinterrupt.interfaces.MyInterface;
import com.lazytomatostudios.svceinterrupt.navbarfragments.Chat;
import com.lazytomatostudios.svceinterrupt.navbarfragments.Dashboard;
import com.lazytomatostudios.svceinterrupt.navbarfragments.Home;
import com.lazytomatostudios.svceinterrupt.navbarfragments.PostLogin;
import com.lazytomatostudios.svceinterrupt.navbarfragments.Profile;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    NavigationTabBar navigationTabBar;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    static String name = "anonymous";
    static String mailId = "null";
    static String phoneNum = "null";

    ArrayList<NavigationTabBar.Model> barModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle userData = getIntent().getExtras();
        if (userData != null) {
            name = userData.getString("username");
            mailId = userData.getString("usermail");
            phoneNum = userData.getString("usernum");
        }

        initNavBar();
    }

    public void initNavBar() {

        viewPager = ( ViewPager ) findViewById(R.id.view_pager);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        navigationTabBar = (NavigationTabBar) findViewById(R.id.nav_tb);

        barModel = new ArrayList<>();

        barModel.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home_black_24dp),
                        R.color.primary_dark)
                        .title("Home")
                        .badgeTitle("NTB HOME")
                        .build()
        );

        barModel.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_dashboard_black_24dp),
                        R.color.primary_dark)
                        .title("Dashboard")
                        .badgeTitle("NTB DASH")
                        .build()
        );

        barModel.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_chat_black_24dp),
                        R.color.primary_dark).
                        title("Chat")
                        .badgeTitle("NTB CHAT")
                        .build()
        );

        barModel.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_person_black_24dp),
                        R.color.primary_dark).
                        title("Profile")
                        .badgeTitle("NTB PROF")
                        .build()
        );

        navigationTabBar.setModels(barModel);
        navigationTabBar.setViewPager(viewPager, 0);

        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                MyInterface fragment = (MyInterface) pagerAdapter.instantiateItem(viewPager, position);
                navigationTabBar.getModels().get(position).hideBadge();
                fragment.fragmentNowVisible();
                Log.d("Debug", String.valueOf(position));

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }

        });

    }

    public void openAbout(View view) {
        Log.d("Debug", "About visible");

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);

    }

    public void openContact(View view) {
        Log.d("Debug", "Contact visible");

        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);

    }

    public void openEvent(View view) {
        Log.d("Debug", "Events visible");

        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    public void openMap(View view) {
        Log.d("Debug", "Maps visible");

        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void openTransport(View view) {
        Log.d("Debug", "Transport visible");

        Intent intent = new Intent(this, TransportActivity.class);
        startActivity(intent);
    }

    public void openInstruction(View view) {
        Log.d("Debug", "Instruction visible");

        Intent intent = new Intent(this, InstructionActivity.class);
        startActivity(intent);

    }

    public void openRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        Bundle bundle = new Bundle();

        private MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Home home = new Home();
                    bundle.putString("uname", name);
                    home.setArguments(bundle);
                    return home;
                case 1:
                    Dashboard dashboard = new Dashboard();
                    bundle.putString("uname", name);
                    bundle.putString("umail", mailId);
                    bundle.putString("unum", phoneNum);
                    dashboard.setArguments(bundle);
                    return dashboard;
                case 2:
                    return new Chat();
                case 3:
                    try {
                        if (name.equals("anonymous")) {
                            return new Profile();
                        } else {
                            PostLogin postLogin = new PostLogin();
                            bundle.putString("umail", mailId);
                            postLogin.setArguments(bundle);
                            return postLogin;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

    }

}
