package com.lazytomatostudios.svceinterrupt.navbarfragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lazytomatostudios.svceinterrupt.dashactivities.EventActivity;
import com.lazytomatostudios.svceinterrupt.interfaces.MailInterface;
import com.lazytomatostudios.svceinterrupt.interfaces.MyInterface;
import com.lazytomatostudios.svceinterrupt.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment implements MyInterface {

    ImageButton button1, button2, button3, button4, button5;

    MailInterface mailInterface;

    public Dashboard() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mailInterface = (MailInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onFragmentChangeListener");
        }
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

            String mail = mailInterface.sendMail();

            switch (v.getId()) {
                case R.id.event1:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "battle code");
                    intent.putExtra("mail", mail);
                    startActivity(intent);
                    break;
                case R.id.event2:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "connect4");
                    intent.putExtra("mail", mail);
                    startActivity(intent);
                    break;
                case R.id.event3:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "Mind Your Business v4.0");
                    intent.putExtra("mail", mail);
                    startActivity(intent);
                    break;
                case R.id.event4:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "breaking the logician code");
                    intent.putExtra("mail", mail);
                    startActivity(intent);
                    break;
                case R.id.event5:
                    intent = new Intent(getActivity(), EventActivity.class);
                    intent.putExtra("event", "presentation park");
                    intent.putExtra("mail", mail);
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
