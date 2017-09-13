package com.lazytomatostudios.svceinterrupt.dashactivities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.bridge.AppConfig;
import com.lazytomatostudios.svceinterrupt.bridge.AppController;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.ConnectFourGame;
import com.lazytomatostudios.svceinterrupt.dashactivities.dashfragments.events.ConnectFourMain;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConnectFourActivity extends AppCompatActivity {

    public boolean game_progress = false;
    public int attempted = 0;
    public int score = 0;
    public String day = "0";
    public String email = "hello@gmail.com";
    String answer;
    public String data_copy = "";

    public boolean right = false;

    int done = 0, left = -1;

    ConnectFourGame connectFourGame;

    public String mail;

    String TAG = "PHP Request ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_four);

        Bundle bundle = getIntent().getExtras();

        mail = bundle.getString("mail");
        Log.d(TAG, mail);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_layout_game, new ConnectFourMain())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

        Toast.makeText(getApplicationContext(),
                "Do not exit game screen while in a question as you are being timed!", Toast.LENGTH_SHORT).show();

    }

    public void startGame(View view) {
        try {
            if(isConnected()) {
                if(isOpen()) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_layout_game, new ConnectFourGame())
                            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("Alert")
                            .setMessage("Game is closed for today as yu have attempted all possible questions for the day.")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //
                                }

                            })
                            .show();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isOpen() {
        switch (day) {
            case "1":
                if(attempted == 20) return false;
                else return true;
            case "2":
                if(attempted == 40) return false;
                else return true;
            case "3":
                if(attempted == 60) return false;
                else return true;
            case "4":
                if(attempted == 80) return false;
                else return true;
            case "5":
                if(attempted == 100) return false;
                else return true;
            default:
                return false;
        }
    }

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec(command).waitFor() == 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //Ask the user if they want to quit
            new AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage("Quit the online game?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            game_progress = false;
                            ConnectFourActivity.super.onBackPressed();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void getData(final String data) {

        data_copy = data;
        Log.d(TAG, "----" + data);

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_GAME, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        switch (data) {
                            case "day":
                                calculateDay(jObj);
                                break;
                            case "check_user":
                                calculateAttempts(jObj);
                                break;
                            case "submit":
                                break;
                            case "attempt":
                                break;
                            default:
                                break;
                        }
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
                Log.e(TAG, "Response Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                

                Map<String, String> param = new HashMap<String, String>();
                param.put("data", data);

                switch (data_copy) {
                    case "day":
                        break;
                    case "check_user":
                        param.put("email", mail);
                        break;
                    case "submit":
                        Log.d(TAG, answer + " " + attempted + " " + mail);
                        param.put("answer", answer);
                        param.put("qno", String.valueOf(attempted));
                        param.put("email", mail);
                        break;
                    case "attempt":
                        param.put("attempt", String.valueOf(attempted));
                        param.put("email", mail);
                        break;
                    case "score":
                        param.put("email", mail);
                        param.put("score", String.valueOf(score));
                    default:
                        break;
                }

                return param;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq);

    }

    public void calculateDay(JSONObject jsonObject) {

        String mday;

        try {
            mday = jsonObject.getString("CURRENT_DATE()");
            day = mday;
            Log.d(TAG, "Day : " + day);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ConnectFourMain connectFourMain = (ConnectFourMain) getSupportFragmentManager().findFragmentById(R.id.frame_layout_game);
        connectFourMain.setDay();

    }

    public void calculateAttempts(JSONObject jsonObject) {

        done = 0;
        left = 0;

        try {
            Log.d(TAG, "Attempts :" + String.valueOf(jsonObject));
            attempted = Integer.parseInt(jsonObject.getString("attempted"));
            score = Integer.parseInt(jsonObject.getString("score"));
            switch (day) {
                case "1":
                    left = 20 - attempted;
                    break;
                case "2":
                    left = 40 - attempted;
                    break;
                case "3":
                    left = 60 - attempted;
                    break;
                case "4":
                    left = 80 - attempted;
                    break;
                case "5":
                    left = 100 - attempted;
                    break;
                default:
                    left = 0;
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ConnectFourMain connectFourMain = (ConnectFourMain) getSupportFragmentManager().findFragmentById(R.id.frame_layout_game);
        connectFourMain.setNos(attempted, left);

    }

    public void submitAnswer(View view) {

        connectFourGame = (ConnectFourGame) getSupportFragmentManager().findFragmentById(R.id.frame_layout_game);

        if(game_progress) {

            if(connectFourGame.clearQuestion()) {
                answer = connectFourGame.getAnswer();
                getData("submit");
            }

        } else {

            connectFourGame.setQuestion();

        }

    }
}
