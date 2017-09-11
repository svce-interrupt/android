package com.lazytomatostudios.svceinterrupt.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lazytomatostudios.svceinterrupt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RegisterActivity extends AppCompatActivity {

    Button button;
    StringBuffer sb1 = new StringBuffer("");
    Map<String, Integer> eventsMap = new HashMap<String, Integer>();
    String name;
    String mailId;
    String phoneNum;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bundle userData = getIntent().getExtras();
        if (userData == null) {
        } else {
            name = userData.getString("name");
            mailId = userData.getString("usermail");
            phoneNum = userData.getString("usernum");
        }
        button = (Button) findViewById(R.id.QR);
        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView2);
        final ArrayList<Events> myEvents = new ArrayList<Events>();

        myEvents.add(new Events(1, "Code Ninja"));
        myEvents.add(new Events(2, "Code Sprint"));
        myEvents.add(new Events(3, "Cognition Quest"));
        myEvents.add(new Events(4, "Data De-Queue"));
        myEvents.add(new Events(5, "Don of Logic"));
        myEvents.add(new Events(6, "Game of Archives"));
        myEvents.add(new Events(7, "MYB"));
        myEvents.add(new Events(8, "Picturesque"));


        adapter = new EventsAdapter(myEvents, new EventsAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(Events item, int position) {
                switch (position) {
                    case 0:
                        inserToMap("Code Ninja");
                        break;
                    case 1:
                        inserToMap("Code Sprint");
                        break;
                    case 2:
                        inserToMap("Cognition Quest");
                        break;
                    case 3:
                        inserToMap("Data De-queue");
                        break;
                    case 4:
                        inserToMap("Don of Logic");
                        break;
                    case 5:
                        inserToMap("Game of Archives");
                        break;
                    case 6:
                        inserToMap("MYB");
                        break;
                    case 7:
                        inserToMap("Picturesque");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemUncheck(Events item, int position) {
                switch (position) {
                    case 0:
                        removeFromMap("Code Ninja");
                        break;
                    case 1:
                        removeFromMap("Code Sprint");
                        break;
                    case 2:
                        removeFromMap("Cognition Quest");
                        break;
                    case 3:
                        removeFromMap("Data De-queue");
                        break;
                    case 4:
                        removeFromMap("Don of Logic");
                        break;
                    case 5:
                        removeFromMap("Game of Archives");
                        break;
                    case 6:
                        removeFromMap("MYB");
                        break;
                    case 7:
                        removeFromMap("Picturesque");
                        break;
                    default:
                        break;
                }

            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterList();
                if (sb1.length() >= 5) {
                    sb1.deleteCharAt(sb1.length() - 2);
                } else if ((sb1.length()) == 0) {
                    Toast.makeText(RegisterActivity.this, "Please select the events.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inserToMap(String event) {
        if (!eventsMap.containsKey(event)) {
            eventsMap.put(event, 1);
        } else {
            eventsMap.put(event, eventsMap.get(event) + 1);
        }
    }

    private void removeFromMap(String event) {
        if (eventsMap.containsKey(event)) {
            eventsMap.remove(event);
        }
    }

    private void filterList() {
        Set<Map.Entry<String, Integer>> entires = eventsMap.entrySet();
        String singleEvent;
        int singleEventCount;

        for (Map.Entry<String, Integer> ent : entires) {
            singleEvent = ent.getKey();
            singleEventCount = ent.getValue();
            System.out.println(singleEvent + singleEventCount);

            if ((singleEventCount % 2) != 0) {
                sb1.append(singleEvent + ", ");
            }
        }
    }
}
