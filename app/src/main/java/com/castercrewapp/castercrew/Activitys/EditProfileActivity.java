package com.castercrewapp.castercrew.Activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.CityModel;
import com.castercrewapp.castercrew.model.CountryModel;
import com.castercrewapp.castercrew.model.StateModel;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    RadioGroup radioGroup_button;
    RadioButton male,female;
    Button save;
    EditText address,family_information,hobbies_interest,basic_requirement,professional_expectation,preferred_location,not_specified,facebook_url,twitter_url,instagram,linkedIn_url;
    EditText my_word,name,age,height,weight,marital_status,mother_tounge,physical_status,body_types,complexion,profile_created,eating_habits,drink,smoking,religion,caste,sub_caste;
    EditText dob, gothra,zodiac,star,rassi,education_category,collage_institute,occupation,organization,employed_in,annual_income,citizenship;
    SharedPreferenceUtils preferances;
    Toolbar toolbar;
    private int mYear, mMonth, mDay;
    TextView preview;
    ViewGroup viewGroup;
    LinearLayout add_video,add_image,review;
    ArrayList<StateModel> StateModelArrayList;
    ArrayList<StateModel> StateModelArrayListTmp;
    ArrayList<CityModel> CityModelArrayList;
    ArrayList<CityModel> CityModelArrayListTmp ;
    ArrayList<CountryModel> CountryModelList;
    String str_gender="";
    ArrayList<CountryModel> CountryModelArrayListTmp;
    ArrayList<String> selectCity;
    ArrayList<String> selectState;
    ArrayList<String> selectCountry;
    String SelectedCityId="",SelectedStateId="",SelectedCountry="";
    String str_address="",str_gothra="",str_zodiac="",str_star="",str_rassi="",str_education_category="",str_collage_institute="",str_occupation="",str_organization="",str_employed_in="",str_annual_income="",str_citizenship="";
    String str_Dob="",str_myword="",str_name="",str_age="",str_height="",str_weight="",str_marital_status="",strmother_tongue="",str_physical_status="",str_body_types="",str_complexion="",str_profile_created="",str_eating_habits="",str_drink="",str_smoking="",str_religion="",str_caste="",ste_subcaste="";
    String User_id,str_familyinformation="",strhobbies="",strbasic_requirement="",str_professional_expectation="",str_preferred_location="",str_not_specified="",strfacebook_url="",str_tweeter_url="",str_instagram="",str_linkedIn_url="";
    Spinner city,state,country;
    FrameLayout progressBarHolder;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        radioGroup_button=findViewById(R.id.radioGroup_button);
        progressBarHolder=findViewById(R.id.progressBarHolder);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        dob=findViewById(R.id.dob);
        address=findViewById(R.id.address);
        annual_income=findViewById(R.id.annual_income);
        citizenship=findViewById(R.id.citizenship);
        employed_in=findViewById(R.id.employed_in);
        occupation=findViewById(R.id.occupation);
        organization=findViewById(R.id.organization);
        collage_institute=findViewById(R.id.collage_institute);
        education_category=findViewById(R.id.education_category);
        complexion=findViewById(R.id.complexion);
        profile_created=findViewById(R.id.profile_created);
        eating_habits=findViewById(R.id.eating_habits);
        drink=findViewById(R.id.drink);
        smoking=findViewById(R.id.smoking);
        religion=findViewById(R.id.religion);
        caste=findViewById(R.id.caste);
        sub_caste=findViewById(R.id.sub_caste);
        gothra=findViewById(R.id.gothra);
        zodiac=findViewById(R.id.zodiac);
        star=findViewById(R.id.star);
        rassi=findViewById(R.id.rassi);

        body_types=findViewById(R.id.body_types);
        physical_status=findViewById(R.id.physical_status);
        mother_tounge=findViewById(R.id.mother_tounge);
        my_word=findViewById(R.id.my_word);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        marital_status=findViewById(R.id.marital_status);
        hobbies_interest=findViewById(R.id.hobbies_interest);
        not_specified=findViewById(R.id.not_specified);
        facebook_url=findViewById(R.id.facebook_url);
        twitter_url=findViewById(R.id.twitter_url);
        instagram=findViewById(R.id.instagram);
        linkedIn_url=findViewById(R.id.linkedIn_url);
        country=findViewById(R.id.country);
        preferred_location=findViewById(R.id.table_layout9);
        professional_expectation=findViewById(R.id.table_layout8);
        save=findViewById(R.id.save);
        family_information=findViewById(R.id.table_layout5);
        basic_requirement=findViewById(R.id.table_layout6);
        preferances= SharedPreferenceUtils.getInstance(EditProfileActivity.this);
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        add_image=findViewById(R.id.add_image);
        add_video=findViewById(R.id.add_video);
        review=findViewById(R.id.review);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                str_Dob=dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                                dob.setText(str_Dob);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });




        CountryModelList=new ArrayList<>();
        CountryModelArrayListTmp=new ArrayList<>();
        StateModelArrayList=new ArrayList<>();
        StateModelArrayListTmp=new ArrayList<>();
        CityModelArrayList=new ArrayList<>();
        CityModelArrayListTmp=new ArrayList<>();
        selectCity=new ArrayList<>();
        selectCity.add("Select City");
        selectState=new ArrayList<>();
        selectState.add("Select State");
        selectCountry=new ArrayList<>();
        selectCountry.add("Select Country");
        getCountry();
        getState();

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(EditProfileActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(EditProfileActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        add_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(EditProfileActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
            }
        });

        preview=findViewById(R.id.preview);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditProfileActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ArrayAdapter<String> StateAdapter = new ArrayAdapter<String>(EditProfileActivity.this, R.layout.support_simple_spinner_dropdown_item, selectState);
        StateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(StateAdapter);
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                SelectedCategory = ;
                for(int i = 0; i<StateModelArrayList.size();i++) {
                    if (StateModelArrayList.get(i).getStateName().contains(parent.getItemAtPosition(position).toString())) {
                        StateModelArrayListTmp.add(StateModelArrayList.get(i));
                        SelectedStateId = StateModelArrayList.get(position - 1).getStateId();
                        getCities();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter<String> CityAdapter = new ArrayAdapter<String>(EditProfileActivity.this, R.layout.support_simple_spinner_dropdown_item, selectCity);
        CityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(CityAdapter);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                SelectedCategory = parent.getItemAtPosition(position).toString();
                for(int i = 0; i<CityModelArrayList.size();i++) {
                    if (CityModelArrayList.get(i).getCityName().contains(parent.getItemAtPosition(position).toString())) {
                        CityModelArrayListTmp.add(CityModelArrayList.get(i));
                        SelectedCityId = CityModelArrayList.get(position - 1).getCityId();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<String> CountryAdapter = new ArrayAdapter<String>(EditProfileActivity.this, R.layout.support_simple_spinner_dropdown_item, selectCountry);
        CountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(CountryAdapter);
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                SelectedCategory = parent.getItemAtPosition(position).toString();
                for(int i = 0; i<CountryModelList.size();i++) {
                    if (CountryModelList.get(i).getCountryName().contains(parent.getItemAtPosition(position).toString())) {
                        CountryModelArrayListTmp.add(CountryModelList.get(i));
                        SelectedCountry = CountryModelList.get(position - 1).getCountryId();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_address=address.getText().toString().trim();
                str_gothra= gothra.getText().toString().trim();
                str_zodiac= zodiac.getText().toString().trim();
                str_star= star.getText().toString().trim();
                str_rassi= rassi.getText().toString().trim();
                str_education_category= education_category.getText().toString().trim();
                str_collage_institute= collage_institute.getText().toString().trim();
                str_occupation= occupation.getText().toString().trim();
                str_organization= organization.getText().toString().trim();
                str_employed_in= employed_in.getText().toString().trim();
                str_annual_income= annual_income.getText().toString().trim();
                str_citizenship= citizenship.getText().toString().trim();
                str_myword= my_word.getText().toString().trim();
                str_name= name.getText().toString().trim();
                str_age= age.getText().toString().trim();
                str_height= height.getText().toString().trim();
                str_weight= weight.getText().toString().trim();
                str_marital_status= marital_status.getText().toString().trim();
                strmother_tongue= mother_tounge.getText().toString().trim();
                str_physical_status= physical_status.getText().toString().trim();
                str_body_types= body_types.getText().toString().trim();
                str_complexion= complexion.getText().toString().trim();
                str_profile_created= profile_created.getText().toString().trim();
                str_eating_habits= eating_habits.getText().toString().trim();
                str_drink= drink.getText().toString().trim();
                str_smoking= smoking.getText().toString().trim();
                str_religion= religion.getText().toString().trim();
                str_caste= caste.getText().toString().trim();
                ste_subcaste= sub_caste.getText().toString().trim();
                str_familyinformation= family_information.getText().toString().trim();
                strhobbies= hobbies_interest.getText().toString().trim();
                strbasic_requirement= basic_requirement.getText().toString().trim();
                str_professional_expectation= professional_expectation.getText().toString().trim();
                str_preferred_location= preferred_location.getText().toString().trim();
                str_not_specified= not_specified.getText().toString().trim();
                strfacebook_url= facebook_url.getText().toString().trim();
                str_tweeter_url= twitter_url.getText().toString().trim();
                str_instagram= instagram.getText().toString().trim();
                str_linkedIn_url= linkedIn_url.getText().toString().trim();
                if (male.isChecked() == true) {
                    str_gender = "male";
                } else {
                    str_gender = "female";
                }
           if(str_name.equalsIgnoreCase("")){
               Toast.makeText(EditProfileActivity.this,"Please enter name",Toast.LENGTH_LONG).show();
           }else if(str_age.equalsIgnoreCase("")){
               Toast.makeText(EditProfileActivity.this,"Please enter age",Toast.LENGTH_LONG).show();
           }else if(str_address.equalsIgnoreCase("")){
               Toast.makeText(EditProfileActivity.this,"Please enter Address",Toast.LENGTH_LONG).show();
           }else if(str_Dob.equalsIgnoreCase("")){
               Toast.makeText(EditProfileActivity.this,"Please select DOB",Toast.LENGTH_LONG).show();
           }else if(country.getSelectedItem().toString().trim().equalsIgnoreCase("Select Country")){
               Toast.makeText(EditProfileActivity.this,"Please select Country",Toast.LENGTH_LONG).show();
           }else if(state.getSelectedItem().toString().trim().equalsIgnoreCase("Select State")){
               Toast.makeText(EditProfileActivity.this,"Please select State",Toast.LENGTH_LONG).show();
           }else if(city.getSelectedItem().toString().trim().equalsIgnoreCase("Select City")){
               Toast.makeText(EditProfileActivity.this,"Please select City",Toast.LENGTH_LONG).show();
           }else if(str_gender.equalsIgnoreCase("")){
               Toast.makeText(EditProfileActivity.this,"Please select Gender",Toast.LENGTH_LONG).show();
           } else{
               EditProfileData();
               progressBarHolder.setVisibility(View.VISIBLE);
           }
            }
        });



    }
    public void EditProfileData(){
        String url = "https://castercrew.com/mobile_app/edit_profile";


        //Again creating the string request
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBarHolder.setVisibility(View.GONE);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("res");
                            if (result.equalsIgnoreCase("1")) {
                                Toast.makeText(EditProfileActivity.this,"Profile Updated Successfully",Toast.LENGTH_LONG).show();
                             Intent intent= new Intent(EditProfileActivity.this,EditProfileActivity.class);
                            startActivity(intent);
                            } else {
                                Toast.makeText(EditProfileActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBarHolder.setVisibility(View.GONE);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                //Adding the parameters to the request
                params.put("uid",User_id);
                params.put("full_name",str_name);
                params.put("sex", str_gender);
                params.put("dob",str_Dob);
                params.put("country",SelectedCountry);
                params.put("state",SelectedStateId);
                params.put("city",SelectedCityId);
                params.put("age", str_age);
                params.put("height",str_height);
                params.put("weight",str_weight);
                params.put("marital_status",str_marital_status);
                params.put("mother_tongue",strmother_tongue);
                params.put("physical_status", str_physical_status);
                params.put("body_types",str_body_types);
                params.put("complexion",str_complexion);
                params.put("profile_created_by",str_profile_created);
                params.put("eating_habits",str_eating_habits);
                params.put("drinking_habits", str_drink);
                params.put("smoking_habits",str_smoking);
                params.put("religion",str_religion);
                params.put("caste",str_caste);
                params.put("sub_caste",ste_subcaste);
                params.put("gothra", str_gothra);
                params.put("zodiac",str_zodiac);
                params.put("star",str_star);
                params.put("raasi",str_rassi);
                params.put("education_category",str_education_category);
                params.put("collage", str_collage_institute);
                params.put("occupation",str_occupation);
                params.put("organization",str_organization);
                params.put("employed_in",str_employed_in);
                params.put("annual_income",str_annual_income);
                params.put("citizenship", str_citizenship);
                params.put("family_info",str_familyinformation);
                params.put("hobbies",strhobbies);
                params.put("basic_requirements",strbasic_requirement);
                params.put("professional_expectations",str_professional_expectation);
                params.put("location_pref", str_preferred_location);
                params.put("looking_for",str_not_specified);
                params.put("fb",strfacebook_url);
                params.put("tw",str_tweeter_url);
                params.put("ln",str_linkedIn_url);
                params.put("in", str_instagram);
                params.put("aboutme",str_myword);
                params.put("address",str_address);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(jsonRequest);
    }
    private void getCountry() {
        String url = "https://castercrew.com/mobileapp/get_country";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                final String myResponse = responseBody.string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONArray city_json = new JSONArray(myResponse);
                            for (int j = 0; j < city_json.length(); j++) {
                                JSONObject e = city_json.getJSONObject(j);
                                CountryModel model = new CountryModel();
                                model.setCountryId(e.getString("id"));
                                model.setCountryName(e.getString("name"));
//                                model.setStateStatus(e.getString("status"));
                                CountryModelList.add(model);
                            }
                            for (int j = 0; j < CountryModelList.size(); j++) {
                                final CountryModel Items = CountryModelList.get(j);
                                selectCountry.add(Items.getCountryName());
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void getCities(){
        String url = "https://castercrew.com/mobileapp/get_cities";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("state_id", SelectedStateId)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                final String myResponse = responseBody.string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONArray city_json = new JSONArray(myResponse);
                            for (int j = 0; j < city_json.length(); j++) {
                                JSONObject e = city_json.getJSONObject(j);
                                CityModel model = new CityModel();
                                model.setCityId(e.getString("id"));
                                model.setCityName(e.getString("name"));
//                                model.setStateStatus(e.getString("status"));
                                CityModelArrayList.add(model);
                            }
                            for (int j = 0; j < CityModelArrayList.size(); j++) {
                                final CityModel Items = CityModelArrayList.get(j);
                                selectCity.add(Items.getCityName());
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }
    public void getState() {
        String url = "https://castercrew.com/mobileapp/get_states";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                final String myResponse = responseBody.string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        JSONObject json = null;
                        try {
                            JSONArray json = new JSONArray(myResponse);
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject e = json.getJSONObject(i);
                                StateModel model = new StateModel();
                                model.setStateId(e.getString("id"));
                                model.setStateName(e.getString("name"));
                                StateModelArrayList.add(model);
                            }
                            for (int i = 0; i < StateModelArrayList.size(); i++) {
                                final StateModel Items = StateModelArrayList.get(i);
                                selectState.add(Items.getStateName());
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });


    }
}
