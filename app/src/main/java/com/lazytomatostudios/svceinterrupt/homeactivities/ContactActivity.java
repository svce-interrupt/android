package com.lazytomatostudios.svceinterrupt.homeactivities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lazytomatostudios.svceinterrupt.R;

public class ContactActivity extends Activity {

    public static String FACEBOOK_URL = "https://www.facebook.com/svceinterrupt";
    public static String FACEBOOK_PAGE_ID = "svceinterrupt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
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

}
