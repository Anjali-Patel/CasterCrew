package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.AuditionAllAdapter;
import com.castercrewapp.castercrew.model.AllAuditionModel;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AuditionAllActivity extends AppCompatActivity {
    SharedPreferenceUtils preferances;
    String User_id;

    ViewGroup viewGroup;
    RecyclerView all_videos;
    AuditionAllAdapter adapter_videos;
    String all_title, str_all_videos;
    TextView toolbar_title;
    String flag_activity = "";
    Boolean all_videos_tittle, cc_special_videos_tittle, recommended_videos_tittle, continue_watching_audition_tittle,recommened_audition,spetial_audition,all_audition;
    boolean view_all;
    boolean cc_special_audios,recommended_audios,all_audios;
    private ArrayList<AllAuditionModel> itemlist_videos;
    Button filter;

    @Override
    protected void onStart() {
        super.onStart();
        getAuditionList();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audition_all);
        preferances= SharedPreferenceUtils.getInstance(this);
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar_title = findViewById(R.id.toolbar_title);
        filter=findViewById(R.id.filter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Auditions");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        continue_watching_audition_tittle=getIntent().getExtras().getBoolean("continue_watching_audition");
        recommened_audition = getIntent().getExtras().getBoolean("recommended_auditions");
        spetial_audition = getIntent().getExtras().getBoolean("special_auditions");
        all_audition = getIntent().getExtras().getBoolean("all_audition_tittle");
        if (all_audition) {
            getSupportActionBar().setTitle("All Auditions");
            //            toolbar_title.setText("All Auditions");
        }else if (spetial_audition) {
            getSupportActionBar().setTitle("CC Spatial Auditions");
            //            toolbar_title.setText("CC Spatial Auditions");
        }else if (recommened_audition) {
            getSupportActionBar().setTitle("Recommended Auditions");
            //            toolbar_title.setText("Recommended Auditions");
        }else if (continue_watching_audition_tittle) {
            getSupportActionBar().setTitle("Continue watching");

//            toolbar_title.setText("Continue watching");
        }

     /*   else if (continue_watching_tittle) {
            toolbar_title.setText("Continue Watching");
        }*/
        all_videos = findViewById(R.id.all_videos);
        itemlist_videos = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        all_videos.setLayoutManager(gridLayoutManager);
        adapter_videos = new AuditionAllAdapter(getApplicationContext(), itemlist_videos,User_id);
        all_videos.setAdapter(adapter_videos);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AuditionAllActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.language_filter, viewGroup, false);
                TextView cancel=dialogView.findViewById(R.id.cancel);
                TextView ok=dialogView.findViewById(R.id.ok);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }
    public void getAuditionList() {
        itemlist_videos.clear();
        String url = "https://castercrew.com/mobileapp/all_auditions";
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray=json.getJSONArray("auditions");
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject e = jsonArray.getJSONObject(j);
                        AllAuditionModel model = new AllAuditionModel();
                        model.setAuditionDate(e.getString("reg_date"));
                        model.setAuditionDescription(e.getString("description"));
                        model.setAudiotionImage(e.getString("image"));
                        model.setAuditionTitle(e.getString("audition_title"));
                        itemlist_videos.add(model);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    all_videos.setLayoutManager(gridLayoutManager);
                    adapter_videos = new AuditionAllAdapter(getApplicationContext(), itemlist_videos,User_id);
                    all_videos.setAdapter(adapter_videos);
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
//                params.put("uid",uid);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);

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
        }else if (id == android.R.id.home){
            onBackPressed();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
