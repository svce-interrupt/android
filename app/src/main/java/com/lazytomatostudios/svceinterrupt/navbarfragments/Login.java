package com.lazytomatostudios.svceinterrupt.navbarfragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dd.processbutton.iml.SubmitProcessButton;
import com.lazytomatostudios.svceinterrupt.MainActivity;
import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.bridge.AppConfig;
import com.lazytomatostudios.svceinterrupt.bridge.AppController;
import com.lazytomatostudios.svceinterrupt.dashactivities.ConnectFourActivity;
import com.lazytomatostudios.svceinterrupt.interfaces.MailInterface;
import com.lazytomatostudios.svceinterrupt.interfaces.MyInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements MyInterface {

    MailInterface mailInterface;

    private EditText emailText;
    private EditText passText;
    private Button loginButton, signoutButton;
    private Button signupLink, backButton;

    SubmitProcessButton signupButton;

    private static Login instance;

    ScrollView scrollView;

    String name, email = "null", phone, college, events;

    public LinearLayout loginPage, signupPage;
    public android.support.constraint.ConstraintLayout profilePage;

    TextView nameView, mobileView, mailView, collegeView, eventView;

    TextView signupName, signupMail, signupPhone, signupCollege, signupPass, signupRepass;

    String TAG = "TRY";

    public Login() {
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
        View rootView =  inflater.inflate(R.layout.fragment_login, container, false);

        emailText = (EditText) rootView.findViewById(R.id.input_email);
        passText = (EditText) rootView.findViewById(R.id.input_password);

        nameView = rootView.findViewById(R.id.nameInput);
        mailView = rootView.findViewById(R.id.mailInput);
        mobileView = rootView.findViewById(R.id.mobileInput);
        collegeView = rootView.findViewById(R.id.collegeInput);
        eventView = rootView.findViewById(R.id.eventsInput);

        signupName = rootView.findViewById(R.id.signupname);
        signupMail = rootView.findViewById(R.id.signupmail);
        signupPhone = rootView.findViewById(R.id.signupphone);
        signupCollege = rootView.findViewById(R.id.signupcoll);
        signupPass = rootView.findViewById(R.id.signuppass);
        signupRepass = rootView.findViewById(R.id.signuprepass);

        scrollView = rootView.findViewById(R.id.scroll);

        loginPage = rootView.findViewById(R.id.login_layout);
        profilePage = rootView.findViewById(R.id.profile_layout);
        signupPage =  rootView.findViewById(R.id.signupLayout);

        emailText.setText("");
        passText.setText("");

        loginButton = rootView.findViewById(R.id.btn_login);
        signoutButton = rootView.findViewById(R.id.signoutButton);
        signupLink = rootView.findViewById(R.id.signup_login);
        signupButton = rootView.findViewById(R.id.btn_signup);
        backButton =rootView.findViewById(R.id.gotologin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submitLogin(String.valueOf(emailText.getText()), String.valueOf(passText.getText()));
                emailText.setText("");
                passText.setText("");
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register();
            }
        });

        signoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logout();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signup();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gotologin();
            }
        });

        return rootView;

    }

    public static synchronized Login getInstance()
    {
        return instance;
    }

    public void submitLogin(final String user, final String pass) {

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        fetchDetails(jObj);
                        loginPage.setVisibility(View.GONE);
                        profilePage.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.GONE);
                        Log.d(TAG, "No error");
                    } else {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getContext(),
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
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("email", user);
                param.put("password", pass);
                Log.d(TAG, user + pass);
                return param;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq);
    }

    @Override
    public void fragmentNowVisible() {
        Log.d("Debug", "Login isible");
    }

    public void fetchDetails(JSONObject jsonObject) {

        try {
            name = jsonObject.getJSONObject("user").getString("name");
            email = jsonObject.getJSONObject("user").getString("email");
            phone = jsonObject.getJSONObject("user").getString("phoneNumber");
            college = jsonObject.getJSONObject("user").getString("collegeName");
            events = jsonObject.getJSONObject("eventslist").toString();

            Log.d(TAG, name + " " + email + " " + phone + " " + college + " " + events);

            mailInterface.getMail(email);

            refreshProfile();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void refreshProfile() {
        nameView.setText(name);
        mailView.setText(email);
        mobileView.setText(phone);
        collegeView.setText(college);
        eventView.setText(events);
    }

    public void logout() {
        nameView.setText("");
        mailView.setText("");
        mobileView.setText("");
        collegeView.setText("");
        eventView.setText("");
        emailText.setText("");
        passText.setText("");
        loginPage.setVisibility(View.VISIBLE);
        profilePage.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);

    }

    public void register() {

        loginPage.setVisibility(View.GONE);
        profilePage.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

        signupName.setText("");
        signupMail.setText("");
        signupPhone.setText("");
        signupCollege.setText("");
        signupPass.setText("");
        signupRepass.setText("");

    }

    public void signup() {

        if (signupPass.getText().toString().equals(signupRepass.getText().toString())) {

            StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response: " + response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        boolean error = jObj.getBoolean("error");
                        if (!error) {
                            submitLogin(signupMail.getText().toString(), signupPass.getText().toString());
                            /*loginPage.setVisibility(View.GONE);
                            profilePage.setVisibility(View.VISIBLE);
                            signupPage.setVisibility(View.GONE);*/
                            Log.d(TAG, "No error");
                        } else {
                            String errorMsg = jObj.getString("error_msg");
                            Toast.makeText(getContext(),
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
                    Toast.makeText(getContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("name", signupName.getText().toString());
                    param.put("email", signupMail.getText().toString());
                    param.put("password", signupPass.getText().toString());
                    param.put("phone", signupPhone.getText().toString());
                    param.put("collegename", signupCollege.getText().toString());
                    return param;
                }

            };

            AppController.getInstance().addToRequestQueue(strReq);

        } else {
            Toast.makeText(getContext(),
                    "Passwords don't match.", Toast.LENGTH_LONG).show();
        }

    }

    public void gotologin() {
        loginPage.setVisibility(View.VISIBLE);
        profilePage.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);
    }

    public String getMail() {
        return email;
    }

}
