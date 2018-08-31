package com.lazytomatostudios.svceinterrupt.events;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.bridge.AppConfig;
import com.lazytomatostudios.svceinterrupt.bridge.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RegisterActivity extends AppCompatActivity {

    Button button;
    StringBuffer sb1;
    Map<String, Integer> eventsMap = new HashMap<String, Integer>();
    String mail;
    String events;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ScrollView scrollView;

    String TAG1 = "mapping : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bundle userData = getIntent().getExtras();
        if (userData != null) {
            mail = userData.getString("mail");
            Log.d("TAG", mail);
        } else {
            Toast.makeText(RegisterActivity.this, "Please login to register.", Toast.LENGTH_SHORT).show();
            finish();
        }
        button = (Button) findViewById(R.id.QR);
        recyclerView = (RecyclerView) findViewById(R.id.myRecyclerView2);
        final ArrayList<Events> myEvents = new ArrayList<Events>();

        myEvents.add(new Events(1, "Battle Code"));
        myEvents.add(new Events(2, "Flip a Table!"));
        myEvents.add(new Events(3, "First Strike"));
        myEvents.add(new Events(4, "Logician's Code"));
        myEvents.add(new Events(5, "Presentation Park"));
        myEvents.add(new Events(6, "Quiz Wiz"));
        myEvents.add(new Events(7, "Mind your Business v4.0"));
        myEvents.add(new Events(8, "Coder's Bay"));
        myEvents.add(new Events(9, "Connect 4"));
        myEvents.add(new Events(10, "Picturesque"));
        myEvents.add(new Events(11, "Surprise Event"));


        adapter = new EventsAdapter(myEvents, new EventsAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(Events item, int position) {
                switch (position) {
                    case 0:
                        inserToMap("Battle Code");
                        break;
                    case 1:
                        inserToMap("Flip a Table!");
                        break;
                    case 2:
                        inserToMap("First Strike");
                        break;
                    case 3:
                        inserToMap("Logician's Code");
                        break;
                    case 4:
                        inserToMap("Presentation Park");
                        break;
                    case 5:
                        inserToMap("Quiz Wiz");
                        break;
                    case 6:
                        inserToMap("Mind Your Business v4.0");
                        break;
                    case 7:
                        inserToMap("Coder's Bay");
                        break;
                    case 8:
                        inserToMap("Connect 4");
                        break;
                    case 9:
                        inserToMap("Picturesque");
                        break;
                    case 10:
                        inserToMap("Surprise Event");
                    default:
                        break;
                }
            }

            @Override
            public void onItemUncheck(Events item, int position) {
                switch (position) {
                    case 0:
                        removeFromMap("Battle Code");
                        break;
                    case 1:
                        removeFromMap("Flip a Table!");
                        break;
                    case 2:
                        removeFromMap("First Strike");
                        break;
                    case 3:
                        removeFromMap("Logician's Code");
                        break;
                    case 4:
                        removeFromMap("Presentation Park");
                        break;
                    case 5:
                        removeFromMap("Quiz Wiz");
                        break;
                    case 6:
                        removeFromMap("Mind Your Business v4.0");
                        break;
                    case 7:
                        removeFromMap("Coder's Bay");
                        break;
                    case 8:
                        removeFromMap("Connect 4");
                        break;
                    case 9:
                        removeFromMap("Picturesque");
                        break;
                    case 10:
                        removeFromMap("Surprise Event");
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
                    sb1.deleteCharAt(sb1.length() - 1);
                    events = sb1.toString();
                    Log.d(TAG1, events);
                    sendEvents();
                } else if ((sb1.length()) == 0) {
                    Toast.makeText(RegisterActivity.this, "Please select the events.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void sendEvents() {

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_EVENTS_UPDATE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        displaySuccess();
                        Log.d("TAG", "No error");
                    } else {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TRY", "Response Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("email", mail);
                param.put("eventslist", events);
                Log.d("TRIAL", mail + " " + events);
                return param;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq);

    }

    private void inserToMap(String event) {
        Log.d("MAP", event);
        if (!eventsMap.containsKey(event)) {
            eventsMap.put(event, 1);
        } else {
            eventsMap.put(event, eventsMap.get(event) + 1);
        }
    }

    private void removeFromMap(String event) {
        Log.d("MAP", event);
        if (eventsMap.containsKey(event)) {
            eventsMap.remove(event);
        }
    }

    private void filterList() {
        Set<Map.Entry<String, Integer>> entires = eventsMap.entrySet();
        String singleEvent;
        int singleEventCount;

        sb1 = new StringBuffer("");

        for (Map.Entry<String, Integer> ent : entires) {
            singleEvent = ent.getKey();
            singleEventCount = ent.getValue();
            System.out.println(singleEvent + singleEventCount);

            if ((singleEventCount % 2) != 0) {
                sb1.append(singleEvent + ",");
            }
        }
    }

    public void displaySuccess() {
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("You have successfully registered.")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RegisterActivity.super.onBackPressed();
                    }

                })
                .show();
    }
}
