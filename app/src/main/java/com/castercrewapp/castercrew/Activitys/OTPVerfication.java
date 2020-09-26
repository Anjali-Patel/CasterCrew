package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class OTPVerfication extends AppCompatActivity implements View.OnClickListener {
    EditText et_otp;
    Button bt_submit;
    String otp;
String userId ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverfication);
        userId=getIntent().getStringExtra("userid");
        InitializeViews();
    }
    public void InitializeViews(){
        et_otp = findViewById(R.id.otp_verfication);
        bt_submit = findViewById(R.id.btn_submit);
        bt_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btn_submit:
                validation();
        }

    }
    public void validation(){

        otp = et_otp.getText().toString().trim();

        if(otp.isEmpty()){
            Toast.makeText(this, "Please enter your OTP", Toast.LENGTH_SHORT).show();
        }else{
            sendHTTP();
        }
    }

    public void sendHTTP(){

        String url = "https://castercrew.com/mobileapp/auth_motp?otp="+otp+"&"+"id="+userId;

        //Again creating the string request
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean result = jsonObject.getBoolean("error");

                            if (!result) {
                                onSuccess();
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

                        onFailed(3);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
              /*  //Adding the parameters to the request
                SharedPreferences sharedPreferences = OTPVerfication.this.getSharedPreferences("Castercrew", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("id", "");*/
//                params.put("otp",otp);
//                params.put("id",userId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(jsonRequest);
    }

    public void onSuccess(){
        Toast.makeText(this, "Verfication Sucessfull", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(OTPVerfication.this,LoginActivity.class);
        startActivity(intent);
    }

    private void onFailed(int index) {
        switch (index) {
            case 1:
                Toast.makeText(this, "Invalied OTP", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                Toast.makeText(this, "We have some techinical issues", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
