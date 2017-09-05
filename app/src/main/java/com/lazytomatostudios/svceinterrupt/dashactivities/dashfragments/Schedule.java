package com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lazytomatostudios.svceinterrupt.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends Fragment {


    public Schedule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        final AVLoadingIndicatorView progress = view.findViewById(R.id.avi);
        final WebView browser = view.findViewById(R.id.webview);

        progress.show();
        browser.setVisibility(View.GONE);

        browser.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.d("TEST", "testing");
                browser.setVisibility(View.VISIBLE);
                progress.hide();
            }

        });

        WebSettings webSettings = browser.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        browser.loadUrl("https://www.svce.ac.in/departments/transport/schedule.php#nav");

        return view;
    }

}
