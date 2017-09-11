package com.lazytomatostudios.svceinterrupt.profileactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lazytomatostudios.svceinterrupt.MainActivity;
import com.lazytomatostudios.svceinterrupt.R;
import com.lazytomatostudios.svceinterrupt.bridge.AppConfig;
import com.lazytomatostudios.svceinterrupt.bridge.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    private static final String TAG = UpdateActivity.class.getSimpleName();
    String name, email, mobile;
    private int flag;
    private String userName;
    private String userMail;
    private String userNum;
    private EditText nameText;
    private EditText emailText;
    private EditText mobNumText;
    private EditText passText;
    private EditText rePasText;
    private EditText collegeNameText;
    private Button updateAccount;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Bundle userInfo = getIntent().getExtras();
        if (userInfo == null)
            return;
        else {
            userName = userInfo.getString("name");
            userMail = userInfo.getString("email");
            userNum = userInfo.getString("usernum");
        }

        Toast.makeText(UpdateActivity.this, "Update the details wherever necessary.", Toast.LENGTH_SHORT).show();

        nameText = (EditText) findViewById(R.id.input_name);
        emailText = (EditText) findViewById(R.id.input_email);
        mobNumText = (EditText) findViewById(R.id.input_mobile);
        passText = (EditText) findViewById(R.id.input_password);
        rePasText = (EditText) findViewById(R.id.input_reEnterPassword);
        collegeNameText = (EditText) findViewById(R.id.input_collegeName);
        updateAccount = (Button) findViewById(R.id.btn_update);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        try {
            if (!isConnected()) {
                Toast.makeText(UpdateActivity.this, "Please connect to the Internet.", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException f) {

        } catch (IOException e) {

        }

        nameText.setText(userName);
        emailText.setText(userMail);
        emailText.setEnabled(false);

        updateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

    private void update() {
        if (!validate()) {
            onUpdateFailed();
            return;
        }

        name = nameText.getText().toString();
        email = emailText.getText().toString();
        mobile = mobNumText.getText().toString();
        String password = passText.getText().toString();
        String collegeName = collegeNameText.getText().toString();

        updateUser(name, email, password, mobile, collegeName);

    }


    private void onUpdateFailed() {
        Toast.makeText(UpdateActivity.this, "Update failed.Please enter valid credentials.", Toast.LENGTH_SHORT).show();
        updateAccount.setEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dummy_menu, menu);
        return true;
    }

    private void updateUser(final String name, final String email,
                            final String password, final String mobile, final String collegeName) {
        String tag_string_req = "req_update";

        progressDialog.setMessage("Updating Account ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_UPDATE, new Response.Listener<String>() {

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
                Log.e(TAG, "Update Error: " + error.getMessage());
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

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec(command).waitFor() == 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SignOut:
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                i.putExtra("username", "anonymous");
                finish();
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        attachIntent(1);
    }

    private void attachIntent(int flag) {
        Intent i = new Intent(UpdateActivity.this, MainActivity.class);
        if (flag == 0) {
            i.putExtra("username", name);
            i.putExtra("usermail", email);
            i.putExtra("usernum", mobile);
        } else if (flag == 1) {
            i.putExtra("username", userName);
            i.putExtra("usermail", userMail);
            i.putExtra("usernum", userNum);
            finish();
        }
        startActivity(i);
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

        name = nameText.getText().toString();
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
