package com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dd.processbutton.iml.SubmitProcessButton;
import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.dashactivities.ConnectFourActivity;
import com.wang.avi.AVLoadingIndicatorView;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectFourGame extends Fragment {

    String day = "";
    int question;
    int score;
    public View view;
    TextView dayView, questionView, scoreView, loadingView;

    SubmitProcessButton submitButton;

    String imageBaseURL = "https://api.interrupt2k17.com//res/";
    String imageEndURL = ".jpg";

    String answer;

    ImageView imageView;

    AVLoadingIndicatorView progress;

    String TAG = "PHP";

    public ConnectFourGame() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_connect_four_game, container, false);

        imageView = view.findViewById(R.id.image);
        dayView = view.findViewById(R.id.day);
        questionView = view.findViewById(R.id.question);
        submitButton = view.findViewById(R.id.submit_answer);
        loadingView = view.findViewById(R.id.loading);

        ((ConnectFourActivity)this.getActivity()).game_progress = true;

        day = ((ConnectFourActivity)this.getActivity()).day;

        setDay();

        setQuestion();

        return view;
    }

    public void setDay() {

        String day = ((ConnectFourActivity) this.getActivity()).day;

        switch (day) {
            case "1":
                dayView.setText(getString(R.string.day1s));
                break;
            case "2":
                dayView.setText(getString(R.string.day2s));
                break;
            case "3":
                dayView.setText(getString(R.string.day3s));
                break;
            case "4":
                dayView.setText(getString(R.string.day4s));
                break;
            case "5":
                dayView.setText(getString(R.string.day5s));
                break;
            default:
                dayView.setText(getString(R.string.daynulls));
                break;
        }

    }

    public void setQuestion() {

        if (((ConnectFourActivity) this.getActivity()).isOpen()) {

            question = ((ConnectFourActivity) this.getActivity()).attempted + 1;

            Log.d(TAG, String.valueOf(question));
            Log.d(TAG, String.valueOf(score));

            questionView.setText(String.valueOf(question));

            Log.d(TAG, imageBaseURL + String.valueOf(question) + imageEndURL);

            Glide
                    .with(getContext())
                    .load(imageBaseURL + String.valueOf(question) + imageEndURL)
                    .into(imageView);

            loadingView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);

        } else {
            new AlertDialog.Builder(getContext())
                    .setTitle("Alert")
                    .setMessage("Game is closed for today as yu have attempted all possible questions for the day.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frame_layout_game, new ConnectFourMain())
                                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                    .commit();
                        }

                    })
                    .show();
        }

    }

    public boolean clearQuestion() {

        answer = ((EditText) view.findViewById(R.id.answer)).getText().toString();

        if(!answer.matches(".*[a-z].*")) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Alert")
                    .setMessage("Please enter some answer.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    })
                    .show();

            return false;
        } else {
            loadingView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);

            Glide.with(getContext()).clear(imageView);
            ((EditText) view.findViewById(R.id.answer)).setText("");

            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(submitButton.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            return true;
        }

    }

    public String getAnswer() {
        return answer;
    }

}
