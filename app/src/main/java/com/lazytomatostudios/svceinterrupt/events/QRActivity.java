package com.lazytomatostudios.svceinterrupt.events;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lazytomatostudios.svceinterrupt.MainActivity;
import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.bridge.AppConfig;
import com.lazytomatostudios.svceinterrupt.bridge.AppController;
import com.lazytomatostudios.svceinterrupt.userinfo.UserDBHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class QRActivity extends AppCompatActivity {

    private static final String TAG = QRActivity.class.getSimpleName();
    String eventsList, userName, userMail, userNum;
    ImageView imageView;
    private UserDBHelper mUserDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle list = getIntent().getExtras();
        if (list == null) {
        } else {
            eventsList = list.getString("eventslist");
            userName = list.getString("name");
            userMail = list.getString("usermail");
            userNum = list.getString("usernum");
        }
        setContentView(R.layout.activity_qr);
        System.out.println(eventsList);
        mUserDbHelper = new UserDBHelper(getApplicationContext());

        imageView = (ImageView) findViewById(R.id.qr);

        updateEvents(eventsList, userMail);

        /*MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(userName + ", " + userMail + ", " + userNum + ", " + eventsList, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        Toast.makeText(QrActivity.this, "Please take a screenshot of this.", Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QRActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void updateEvents(final String eventsList, final String email) {
        String tag_string_req = "update_events";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_EVENTS_UPDATE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        Toast.makeText(QRActivity.this, "Events registered.", Toast.LENGTH_SHORT).show();
                        mUserDbHelper.updateEvents(eventsList, email);
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
                Log.e(TAG, "Events update Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("eventslist", eventsList);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
