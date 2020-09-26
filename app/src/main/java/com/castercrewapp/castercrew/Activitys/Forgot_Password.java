package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Forgot_Password extends AppCompatActivity implements View.OnClickListener {

    EditText etmobile_email;
    String mobbile_email;
    Button btreset_password;
    private ProgressBar pbLoading;
    Spinner spin;
    ArrayList<String> rolelist;
    String rids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        setspinner();
        initlizeviews();
    }

    public void setspinner(){

        rolelist = new ArrayList<>();
        spin = findViewById(R.id.role);

//        loadSpinnerData(url);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (spin.equals("Select Role")){

                }else {

                    rids = String.valueOf(spin.getSelectedItemPosition());

//                    int idsss = Integer.parseInt(country);

//                Toast.makeText(RegistationActivity.this, idss, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }


    public void initlizeviews(){
        etmobile_email  = findViewById(R.id.mobile_email);
        pbLoading = findViewById(R.id.progressBar1);
        btreset_password = findViewById(R.id.btn_reset_password);
        btreset_password.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_reset_password:
                validation();
                return;
        }
    }

    public void validation(){
        mobbile_email = etmobile_email.getText().toString().trim();
     if (mobbile_email.isEmpty()){
            Toast.makeText(this, "Please enter mobile number or Email", Toast.LENGTH_SHORT).show();
        }else if(spin.getSelectedItem().toString().trim().equalsIgnoreCase("Select Role")) {
            Toast.makeText(Forgot_Password.this, "Please Select Role", Toast.LENGTH_SHORT).show();
       } else {
           sendhttp();
        }

    }
    public void sendhttp(){
        //        String url = "https://castercrew.com/mobileapp/login/do_login";
String url="https://castercrew.com/mobileapp/forgot_sub?send_sms_email="+mobbile_email+"&"+"userrole="+rids;
        isLoading(true);
        //Again creating the string request
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
                    public void onResponse(String response) {
                        isLoading(false);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean result = jsonObject.getBoolean("error");

                            if (!result) {

                                onsucess();


                            } else {
                                onFailed(1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            onFailed(2);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        isLoading(false);
                        onFailed(3);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the reques
             /*   params.put("send_sms_email" ,mobbile_email);
                params.put("userrole",rids);*/
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the string request to request queue
        requestQueue.add(jsonRequest);
    }
    private void onsucess() {
        Toast.makeText(Forgot_Password.this, "Link has been sent to your Email Id or registered2 phone number", Toast.LENGTH_SHORT).show();
    }
    private void onFailed(int index) {
        switch (index) {
            case 1:
                Toast.makeText(this, "Invailed Details", Toast.LENGTH_SHORT).show();
                break;
                case 2:
                Toast.makeText(this, "We have some techinical issues", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void isLoading(boolean loading) {
        if (loading) {
            btreset_password.setVisibility(View.GONE);
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            btreset_password.setVisibility(View.VISIBLE);
            pbLoading.setVisibility(View.GONE);
        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static final boolean isValidPhoneNumber(CharSequence target) {
        if (target.length()!=10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }
}
