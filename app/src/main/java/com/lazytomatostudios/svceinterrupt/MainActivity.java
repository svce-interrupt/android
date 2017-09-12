package com.lazytomatostudios.svceinterrupt;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lazytomatostudios.svceinterrupt.dashactivities.EventActivity;
import com.lazytomatostudios.svceinterrupt.dashactivities.InstructionActivity;
import com.lazytomatostudios.svceinterrupt.dashactivities.MapActivity;
import com.lazytomatostudios.svceinterrupt.dashactivities.TransportActivity;
import com.lazytomatostudios.svceinterrupt.events.RegisterActivity;
import com.lazytomatostudios.svceinterrupt.interfaces.MailInterface;
import com.lazytomatostudios.svceinterrupt.interfaces.MyInterface;
import com.lazytomatostudios.svceinterrupt.navbarfragments.Chat;
import com.lazytomatostudios.svceinterrupt.navbarfragments.Dashboard;
import com.lazytomatostudios.svceinterrupt.navbarfragments.Home;
import com.lazytomatostudios.svceinterrupt.navbarfragments.Login;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity implements MailInterface{

    public NavigationTabBar navigationTabBar;
    public ViewPager viewPager;
    PagerAdapter pagerAdapter;

    String mail = "null", event = "null", pass = "null";
    static String FACEBOOK_URL = "https://www.facebook.com/svceinterrupt";
    static String FACEBOOK_PAGE_ID = "svceinterrupt";

    String TAG = "Hello";

    ArrayList<NavigationTabBar.Model> barModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void openEvent(View view) {
        Log.d("Debug", "Events visible");

        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("mail", mail);
        intent.putExtra("event", event);

        Log.d("TAG", event+mail);

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

        if(mail.equals("null")) {

            Toast.makeText(getApplicationContext(),
                    "Please sign in to register", Toast.LENGTH_LONG).show();

        } else {

            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra("mail", mail);
            startActivity(intent);

        }
    }

    public void openFacebook(View view) {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(this);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    public void openInstagram(View view) {
        Intent instagramIntent = new Intent(Intent.ACTION_VIEW);
        instagramIntent.setPackage("com.instagram.android");
        String instagramUrl = "http://instagram.com/_u/interrupt_svce";
        instagramIntent.setData(Uri.parse(instagramUrl));
        try {
            startActivity(instagramIntent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/_u/interrupt_svce")));
        }
    }

    public void openMail(View view) {
        String email[] = { "interrupt2k17@gmail.com" };
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        startActivity(emailIntent);
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        private MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Home home = new Home();
                    return home;
                case 1:
                    Dashboard dashboard = new Dashboard();
                    return dashboard;
                case 2:
                    return new Chat();
                case 3:
                    return new Login();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

    }

    @Override
    public void getMail(String string) {
        mail = string;
    }

    @Override
    public String sendMail() {
        return mail;
    }

    @Override
    public void storePass(String string) {
        pass = string;
    }

    @Override
    public String getPass() {
        return pass;
    }

}
