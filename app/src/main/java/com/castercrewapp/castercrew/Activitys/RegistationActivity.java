package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistationActivity extends AppCompatActivity  implements View.OnClickListener {

    String[] role = { "Select Role","Individual", "Recruiter", "Vendor", "Institute", "Activist"};

    EditText etemail,etmobile,etfpassword,etcpassword,referral_code;
    Button btregistation;
    private ProgressBar pbLoading;

    String url = "https://api.myjson.com/bins/j1hys";

    SharedPreferenceUtils preferances;
    String email,mobile,fpassword,cpassword;
    String ROLE;
    Spinner spin;
    String rolee;
    String roleids;
    ArrayList<String> rolelist;
    String idss,str_refcode,fcmTocken="";
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);
        preferances= SharedPreferenceUtils.getInstance(this);
        referral_code=findViewById(R.id.referral_code);
        fcmTocken = preferances.getStringValue(AccountUtils.FCM_TOKEN,"");


//        setspinerdata();
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

                    idss = String.valueOf(spin.getSelectedItemPosition());

//                    int idsss = Integer.parseInt(country);

                    Toast.makeText(RegistationActivity.this, idss, Toast.LENGTH_SHORT).show();

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

        etemail = findViewById(R.id.email);
        etmobile = findViewById(R.id.mobile_number);
        etfpassword = findViewById(R.id.first_password);
        etcpassword = findViewById(R.id.confirm_password);
        btregistation = findViewById(R.id.btn_register_free);
        pbLoading = findViewById(R.id.progressBar1);
        btregistation.setOnClickListener(this);
    }

//    public void setspinerdata(){
//
//         spin = (Spinner) findViewById(R.id.role);
//        spin.setOnItemSelectedListener(this);
//
//        //Creating the ArrayAdapter instance having the country list
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,role);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        spin.setAdapter(aa);
//    }

//    @Override
//    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//      //  Toast.makeText(getApplicationContext(),role[position] , Toast.LENGTH_LONG).show();
//
//         rolee = role[position];
//
//        if (rolee.equals("Select Role")){
//            return;
//        }
//
//        if (role[position].equals("Individul")){
//             roleids = "1";
//        }else if (rolee.equals("Vendor")){
//            roleids = "3";
//        }else if (rolee.equals("Institute")){
//            roleids = "4";
//        }else if (rolee.equals("Activist")){
//            roleids = "5";
//        }else if (rolee.equals("Recruiter")){
//            roleids = "2";
//        }
//    }
//    @Override
//    public void onNothingSelected(AdapterView<?> arg0) {
//        // TODO Auto-generated method stub
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_register_free:
                validation();
                return;
        }
    }

    private void loadSpinnerData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("ROLE");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String country = jsonObject1.getString("role");
                         //idss = jsonObject1.getInt("id");
                        rolelist.add(country);

                    }
                    spin.setAdapter(new ArrayAdapter<String>(RegistationActivity.this, android.R.layout.simple_spinner_dropdown_item, rolelist));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
    public void validation(){
        str_refcode =  referral_code.getText().toString().trim();
         email  =  etemail.getText().toString().trim();
         mobile = etmobile.getText().toString().trim();
         fpassword =  etfpassword.getText().toString().trim();
         cpassword = etcpassword.getText().toString().trim();


         if (email.isEmpty()){
             Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
         }else if (mobile.isEmpty()){
             Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
         }else if(!isValidEmail(email)){
             Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();

         }else if(spin.getSelectedItem().toString().trim().equalsIgnoreCase("Select Role")) {
             Toast.makeText(RegistationActivity.this, "Please Select Role", Toast.LENGTH_SHORT).show();
         } else if (fpassword.isEmpty()){
             Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
         }else if (cpassword.isEmpty()){
             Toast.makeText(this, "Please conform password", Toast.LENGTH_SHORT).show();
         }else if (validaterole()){
             return;
         }else if (!fpassword.equals(cpassword)){
             Toast.makeText(this, "Please enter correct password", Toast.LENGTH_SHORT).show();
         }
         else {

             senddata();
         }

    }

    private boolean validaterole(){
        boolean valid= false;

        ROLE = null;
        if (spin != null && spin.getSelectedItem() != null) {
            ROLE = (String) spin.getSelectedItem();
        } else {
            valid = true;
            if (rolee.equals("Select Role")){

            }else {
                Toast.makeText(this, "Please Select role", Toast.LENGTH_SHORT).show();
            }
            }

        return valid;

    }

    public void senddata(){

        String url ="https://castercrew.com/mobileapp/registration/add_info";

        isLoading(true);
        //Again creating the string request
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                       @Override
                    public void onResponse(String response) {
                        isLoading(false);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean result = jsonObject.getBoolean("error");
                            if (!result) {

                                 id = jsonObject.getInt("id");
//                                preferances.setValue(AccountUtils.PREF_USER_ID, String.valueOf(id));

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
                        isLoading(false);
                        onFailed(3);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request

                params.put("email",email);
                params.put("mobile",mobile);
                params.put("catg", idss);
                params.put("pwd",fpassword);
                params.put("refcode",str_refcode);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(jsonRequest);
    }

    public void onSuccess(){

        Toast.makeText(this, "Details Saved Sucessfully", Toast.LENGTH_SHORT).show();
//        preferances.setValue(AccountUtils.PREF_USER_ID, String.valueOf(id));

    /*    SharedPreferences sharedPreferences = this.getSharedPreferences("Castercrew", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", String.valueOf(id));
        editor.commit();*/

        Intent intent = new Intent(this,OTPVerfication.class);
        intent.putExtra("userid",String.valueOf(id));
        startActivity(intent);
    }

    private void isLoading(boolean loading) {
        if (loading) {
            btregistation.setVisibility(View.GONE);
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            btregistation.setVisibility(View.VISIBLE);
            pbLoading.setVisibility(View.GONE);
        }
    }


    private void onFailed(int index) {
        switch (index) {
            case 1:
                Toast.makeText(this, "Already Register", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                Toast.makeText(this, "We have some techinical issues", Toast.LENGTH_SHORT).show();
                break;

        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
