package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.NewsAdapter;
import com.castercrewapp.castercrew.model.ExpandedMenuModel;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsPosterResultActivity extends AppCompatActivity {
    String user_id;
    SharedPreferenceUtils preferances;
String str_img,str_name,newsPoster_id;
    NewsAdapter newsAdapter;
    ArrayList<ExpandedMenuModel> newsArrayList;
    private RecyclerView news_list;
    TextView profile_name;
    ImageView profile_pic;
    FrameLayout progressBarHolder;
    @Override
    protected void onStart() {
        super.onStart();

    }
    private void getUserNewsList() {
        String url = "https://castercrew.com/mobileapp/user_news?uid"+"="+newsPoster_id;
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBarHolder.setVisibility(View.GONE);

                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray=json.getJSONArray("usernews_list");
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject e = jsonArray.getJSONObject(j);
                        ExpandedMenuModel model = new ExpandedMenuModel();
                        model.setNewsDate(e.getString("post_dt"));
                        model.setNewsDescription(e.getString("description"));
                        model.setNewsImage(e.getString("photo"));
                        model.setNewstags(e.getString("tags"));
                        model.setNewsTitle(e.getString("title"));
                        model.setIndustry_name(e.getString("industry_name"));
                        model.setCreate_by(e.getString("create_by"));
                        model.setSubject(e.getString("subject"));
                        model.setIndustry_id(e.getString("industry_id"));
                        model.setNewsId(e.getString("id"));
                        model.setNewsStatus(e.getString("status"));
                        newsArrayList.add(model);
                    }
                    newsAdapter = new NewsAdapter(NewsPosterResultActivity.this,newsArrayList,user_id);
                    LinearLayoutManager layoutmanager = new LinearLayoutManager(NewsPosterResultActivity.this);
                    news_list.setLayoutManager(layoutmanager);
                    news_list.setAdapter(newsAdapter);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_poster_result);
        preferances= SharedPreferenceUtils.getInstance(getApplicationContext());
        user_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        profile_name=findViewById(R.id.profile_name);
        news_list=findViewById(R.id.news_list);
        profile_pic=findViewById(R.id.profile_pic);
        progressBarHolder=findViewById(R.id.progressBarHolder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News Poster Result");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        str_name=getIntent().getStringExtra("name");
        str_img=getIntent().getStringExtra("image");
        newsPoster_id=getIntent().getStringExtra("poser_id");
        profile_name.setText(str_name);
        Picasso.with(NewsPosterResultActivity.this).load(str_img).placeholder(NewsPosterResultActivity.this.getResources().getDrawable(R.drawable.samantha_eleven)).error(NewsPosterResultActivity.this.getResources().getDrawable(R.drawable.samantha_eleven)).into(profile_pic);
        getUserNewsList();
        progressBarHolder.setVisibility(View.VISIBLE);
        newsArrayList= new ArrayList<>();

        LinearLayoutManager linearLayoutManagernews = new LinearLayoutManager(NewsPosterResultActivity.this);
        linearLayoutManagernews.setOrientation(LinearLayoutManager.VERTICAL);
        news_list.setLayoutManager(linearLayoutManagernews);
        news_list.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(NewsPosterResultActivity.this, newsArrayList,user_id);
        news_list.setAdapter(newsAdapter);
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
}
