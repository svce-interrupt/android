package com.lazytomatostudios.svceinterrupt.navbarfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lazytomatostudios.svceinterrupt.dashactivities.EventActivity;
import com.lazytomatostudios.svceinterrupt.interfaces.MyInterface;
import com.lazytomatostudios.svceinterrupt.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment implements MyInterface {

    ImageButton button1, button2, button3, button4, button5;


    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        button1 = view.findViewById(R.id.event1);
        button2 = view.findViewById(R.id.event2);
        button3 = view.findViewById(R.id.event3);
        button4 = view.findViewById(R.id.event4);
        button5 = view.findViewById(R.id.event5);

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);

        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            Intent intent;

            switch (v.getId()) {
                case R.id.event1:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "code ninja");
                    startActivity(intent);
                    break;
                case R.id.event2:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "data de-queue");
                    startActivity(intent);
                    break;
                case R.id.event3:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "quizzler");
                    startActivity(intent);
                    break;
                case R.id.event4:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "cognition quest");
                    startActivity(intent);
                    break;
                case R.id.event5:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "don of logic");
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void fragmentNowVisible() {
        Log.d("Debug", "Dashboard visible");
    }

}
