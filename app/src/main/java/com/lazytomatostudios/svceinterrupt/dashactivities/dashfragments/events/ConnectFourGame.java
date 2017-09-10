package com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectFourGame extends Fragment {

    String day = "";
    int question;
    int score;
    public View view;
    TextView dayView, questionView, scoreView;

    KonfettiView konfettiView;

    SubmitProcessButton submitButton;

    String imageBaseURL = "http://192.168.1.10/res/";
    String imageEndURL = ".png";

    String answer;

    ImageView imageView;

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
        scoreView = view.findViewById(R.id.score);
        submitButton = view.findViewById(R.id.submit_answer);
        konfettiView = view.findViewById(R.id.viewKonfetti);

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

        ((ConnectFourActivity)this.getActivity()).game_progress = true;
        submitButton.setText(String.valueOf("Submit"));

        ((ConnectFourActivity) this.getActivity()).attempted++;
        question = ((ConnectFourActivity) this.getActivity()).attempted;
        score = ((ConnectFourActivity) this.getActivity()).score;

        refreshScore();

        Log.d(TAG, String.valueOf(question));
        Log.d(TAG, String.valueOf(score));

        questionView.setText(String.valueOf(question));
        scoreView.setText((String.valueOf(score)));

        Log.d(TAG, imageBaseURL + String.valueOf(question) + imageEndURL);

        Glide
                .with(getContext())
                .load(imageBaseURL + String.valueOf(question) + imageEndURL)
                .into(imageView);

        ((ConnectFourActivity) this.getActivity()).getData("attempt");

    }

    public void clearQuestion() {

        konfettiView.build()
                .addColors(Color.RED, Color.BLUE)
                .setDirection(0.0, 359.0)
                .setSpeed(4f, 7f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .stream(300, 500L);

        ((ConnectFourActivity)this.getActivity()).game_progress = false;
        Glide.with(getContext()).clear(imageView);
        answer = ((EditText) view.findViewById(R.id.answer)).getText().toString();
        ((ConnectFourActivity)this.getActivity()).getData("submit");
        submitButton.setText(String.valueOf("Next"));
        ((EditText) view.findViewById(R.id.answer)).setText("");

        imageView.setVisibility(View.INVISIBLE);

        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(submitButton.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    public String getAnswer() {
        return answer;
    }

    public void refreshScore() {

        scoreView.setText((String.valueOf(score)));

    }

}
