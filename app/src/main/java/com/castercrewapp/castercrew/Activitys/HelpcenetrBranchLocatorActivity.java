package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.CityModel;
import com.castercrewapp.castercrew.model.StateModel;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class HelpcenetrBranchLocatorActivity extends AppCompatActivity {
TextView  view_map,view_map1;
Spinner state,city;
ImageView img1,img2;
LinearLayout expandableButton1,expandableButton2;
    ExpandableRelativeLayout expandableLayout1,expandableLayout2;
    ArrayList<StateModel> StateModelArrayList;
    ArrayList<StateModel> StateModelArrayListTmp;
    ArrayList<CityModel> CityModelArrayList;
    ArrayList<CityModel> CityModelArrayListTmp ;
    ArrayList<String> selectCity;
    ArrayList<String> selectState;
    String SelectedCityId="",SelectedStateId="";
    double latitude = 40.714728;
    double longitude = -73.998672;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpcenetr_branch_locator);
        view_map=findViewById(R.id.view_map);
        view_map1=findViewById(R.id.view_map1);
        expandableButton1=findViewById(R.id.expandableButton1);
        state=findViewById(R.id.state);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        expandableButton2=findViewById(R.id.expandableButton2);
        expandableLayout2=findViewById(R.id.expandableLayout2);
        city=findViewById(R.id.city);
        StateModelArrayList=new ArrayList<>();
        StateModelArrayListTmp=new ArrayList<>();
        CityModelArrayList=new ArrayList<>();
        CityModelArrayListTmp=new ArrayList<>();
        selectCity=new ArrayList<>();
        selectCity.add("Select City");
        selectState=new ArrayList<>();
        selectState.add("Select State");
        getState();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Branch Locator");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        expandableLayout1 = findViewById(R.id.expandableLayout1);



        expandableButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout1.toggle();
                img1.setImageResource(R.drawable.ic_expand_less_black_24dp);
            }
        });
        expandableButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout2.toggle();
                img2.setImageResource(R.drawable.ic_expand_less_black_24dp);

            }
        });
        view_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = "I'm Here!";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(mapIntent);
            }
        });
        view_map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = "I'm Here!";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(mapIntent);
            }
        });
        ArrayAdapter<String> StateAdapter = new ArrayAdapter<String>(HelpcenetrBranchLocatorActivity.this, R.layout.support_simple_spinner_dropdown_item, selectState);
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
        ArrayAdapter<String> CityAdapter = new ArrayAdapter<String>(HelpcenetrBranchLocatorActivity.this, R.layout.support_simple_spinner_dropdown_item, selectCity);
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

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        }else if (id == R.id.action_artist){

            Intent intent = new Intent(this, TalentPostListActivity.class);
            // intent.putExtra("cat","Artist");
            startActivity(intent);
//            Bundle bundle1 = new Bundle();
//                bundle1.putString("cat", "Artist");
//                HomeFragment categoryFragment = new HomeFragment();
//                categoryFragment.setArguments(bundle1);
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,categoryFragment).commit();
        }else if (id == R.id.action_profissional){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","Professionals");
            startActivity(intent);
        }
        else if (id == R.id.action_vender){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","vendors");
            startActivity(intent);
        }

        else if (id == R.id.action_inistitute){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","institutes");
            startActivity(intent);
        }else if (id == R.id.action_activist){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","activists");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
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
//                                model.setStateStatus(e.getString("status"));

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
