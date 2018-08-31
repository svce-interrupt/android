package com.lazytomatostudios.svceinterrupt.profileactivities;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.lazytomatostudios.svceinterrupt.MainActivity;
import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.bridge.AppConfig;
import com.lazytomatostudios.svceinterrupt.bridge.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private EditText nameText;
    private EditText emailText;
    private EditText mobNumText;
    private EditText passText;
    private EditText rePasText;
    private TextView loginLink;
    private EditText collegeNameText;
    private Button createAccount;
    private int flag = 0;
    private String name;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameText = (EditText) findViewById(R.id.input_name);
        emailText = (EditText) findViewById(R.id.input_email);
        mobNumText = (EditText) findViewById(R.id.input_mobile);
        passText = (EditText) findViewById(R.id.input_password);
        rePasText = (EditText) findViewById(R.id.input_reEnterPassword);
        collegeNameText = (EditText) findViewById(R.id.input_collegeName);
        loginLink = (TextView) findViewById(R.id.link_login);
        createAccount = (Button) findViewById(R.id.btn_signup);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmerAnimation();

        try {
            if (!isConnected()) {
                Toast.makeText(SignUpActivity.this, "Please connect to the Internet.", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException f) {

        } catch (IOException e) {

        }

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.animator.push_left_in, R.animator.push_left_out);
            }
        });
    }

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec(command).waitFor() == 0);
    }


    public void signup() {
        if (!validate()) {
            onSignupFailed();
            return;
        }


        name = nameText.getText().toString().trim();
        final String email = emailText.getText().toString().trim();
        final String mobile = mobNumText.getText().toString().trim();
        String password = passText.getText().toString().trim();
        String collegeName = collegeNameText.getText().toString().trim();

        registerUser(name, email, password, mobile, collegeName);

    }


    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();
        createAccount.setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void registerUser(final String name, final String email,
                              final String password, final String mobile, final String collegeName) {
        String tag_string_req = "req_register";

        progressDialog.setMessage("Creating Account ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String password = user.getString("password");
                        String mobile = user.getString("phoneNumber");
                        String collegeName = user.getString("collegeName");

                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                        i.putExtra("username", name);
                        i.putExtra("usermail", email);
                        i.putExtra("usernum", mobile);
                        startActivity(i);
                        finish();
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
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("phone", mobile);
                params.put("collegename", collegeName);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = mobNumText.getText().toString();
        String password = passText.getText().toString();
        String reEnterPassword = rePasText.getText().toString();


        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            mobNumText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            mobNumText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            rePasText.setError("Password Do not match");
            valid = false;
        } else {
            rePasText.setError(null);
        }

        return valid;
    }
}
