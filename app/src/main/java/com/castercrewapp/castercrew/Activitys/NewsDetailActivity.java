package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewsDetailActivity extends AppCompatActivity {
    String UserId;
//    private YouTubePlayerView youTubeView;
//    YouTubePlayer youTubePlayer;
    Toolbar toolbar;
    EditText news_title,news_description;
    TextView name,designation,date;
    ImageView like,dislike,whatsapp,facebook,share,comment,news_image;
    Button edit,save,delete;
    RelativeLayout relative2,relative3,relative4;
    String  str_name;
    boolean Strnews_crab, india_today, nesTrackLive, continue_watching_tittle;
    LinearLayout title;
    ImageView profile_pic;
    SharedPreferenceUtils preferances;
    String str_Newsdate="",strNewsTitle="",strNewsDescription="";
String strDate="",strDescription="",strImage="",strTitle="",strTags="",strPosterUserId="",strIndustryId="",strNewsId="",strNewsSubject="",strNewsStatus="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News Details");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        preferances= SharedPreferenceUtils.getInstance(NewsDetailActivity.this);
        UserId=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            strDate = bundle.getString("date");
            strDescription = bundle.getString("description");
            strImage = bundle.getString("image");
            strTitle = bundle.getString("title");
            strTags = bundle.getString("tag");
            strPosterUserId = bundle.getString("poster_user_id");
            strIndustryId = bundle.getString("industry_id");
            strNewsId=bundle.getString("news_id");
            strNewsSubject=bundle.getString("subject");
            strNewsStatus=bundle.getString("status");
        }
//        youTubeView = findViewById(R.id.youtube_view);
        news_image=findViewById(R.id.news_image);
        news_title=findViewById(R.id.news_title);
        news_description=findViewById(R.id.news_description);
        edit=findViewById(R.id.edit);
        save=findViewById(R.id.save);
        delete=findViewById(R.id.delete);
        designation=findViewById(R.id.designation);
        date=findViewById(R.id.date);
        name=findViewById(R.id.name);
        title=findViewById(R.id.title);
        profile_pic=findViewById(R.id.profile_pic);
        news_title.setText(strTitle);
        news_description.setText(strDescription);
        Date datetime = null;
        try {
            datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newString = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(datetime);

        date.setText(newString);
        Picasso.with(NewsDetailActivity.this).load(strImage).error(R.drawable.viewpagerbackground).into(news_image);
        if(UserId.equalsIgnoreCase(strPosterUserId)){
            edit.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteNews();
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 date.setFocusable(true);
                    news_title.setFocusable(true);
                    news_description.setFocusable(true);

                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    strNewsTitle= news_title.getText().toString().trim();
                    strNewsDescription= news_description.getText().toString().trim();
                     EditNewsPostData();

                }
            });
        }else{
            edit.setVisibility(View.GONE);
            save.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);

        }
        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NewsDetailActivity.this,NewsPosterResultActivity.class);
                intent.putExtra("name","timesofindia.indiatimes.com");
                intent.putExtra("image",strImage);
                intent.putExtra("poser_id",strPosterUserId);
                startActivity(intent);
            }
        });
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NewsDetailActivity.this,NewsPosterResultActivity.class);
                intent.putExtra("name","timesofindia.indiatimes.com");
                intent.putExtra("poser_id",strPosterUserId);
                intent.putExtra("image",strImage);
                startActivity(intent);
            }
        });

      /*  Strnews_crab = getIntent().getExtras().getBoolean("News_Crab");
        india_today = getIntent().getExtras().getBoolean("India_Today");
        nesTrackLive = getIntent().getExtras().getBoolean("NewsTrack_Live");


        if (Strnews_crab) {
            name.setText("News Crab");
        } else if (india_today) {
            name.setText("India Today");
        } else if (nesTrackLive){
            name.setText("NewsTrack Live");
        }*/
        comment=findViewById(R.id.comment);
        relative2=findViewById(R.id.relative2);
        relative3=findViewById(R.id.relative3);
        relative4=findViewById(R.id.relative4);

        relative2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NewsDetailActivity.this,RecommendedActivity.class);
                startActivity(intent);
            }
        });
        relative3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NewsDetailActivity.this,RecommendedActivity.class);
                startActivity(intent);
            }
        });
        relative4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(NewsDetailActivity.this,RecommendedActivity.class);
                startActivity(intent);
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intent= new Intent(NewsDetailActivity.this,CommentActivity.class);
            startActivity(intent);
            }
             });

        like=findViewById(R.id.like);
        dislike=findViewById(R.id.dislike);
        whatsapp=findViewById(R.id.whatsapp);
        facebook=findViewById(R.id.facebook);
        share=findViewById(R.id.share);
//        view_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(NewsDetailActivity.this,SocialWebViewActivity.class);
//                myIntent.putExtra("url","https://www.instagram.com/samantharuthprabhuoffl/?utm_source=ig_embed");
//                startActivity(myIntent);
//            }
//        });
        like.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Toast.makeText(NewsDetailActivity.this,"We will recommended more articles you like",Toast.LENGTH_LONG).show();
            }
        });
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewsDetailActivity.this," Thanks for your feedback,we will deal it with soon",Toast.LENGTH_LONG).show();
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String  image_path = item.getFull_name();
//                String website_url=item.getArtist_name()+","+item.getTalent_title()+","+item.getReg_dt();
//                title=Items.getTitle();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/png");
//                i.putExtra(Intent.EXTRA_SUBJECT, title);
//                i.putExtra(Intent.EXTRA_TEXT, image_path+"\n \n"+website_url);
//                i.putExtra(Intent.EXTRA_TEXT,image_path);
                startActivity(Intent.createChooser(i, "Via "));

            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String  image_path = item.getFull_name();
//                String website_url=item.getArtist_name()+","+item.getTalent_title()+","+item.getReg_dt();
//                title=Items.getTitle();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/png");
//                i.putExtra(Intent.EXTRA_SUBJECT, title);
//                i.putExtra(Intent.EXTRA_TEXT, image_path+"\n \n"+website_url);
//                i.putExtra(Intent.EXTRA_TEXT,image_path);
                startActivity(Intent.createChooser(i, "Via "));
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String  image_path = item.getFull_name();
//                String website_url=item.getArtist_name()+","+item.getTalent_title()+","+item.getReg_dt();
//                title=Items.getTitle();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/png");
//                i.putExtra(Intent.EXTRA_SUBJECT, title);
//                i.putExtra(Intent.EXTRA_TEXT, image_path+"\n \n"+website_url);
//                i.putExtra(Intent.EXTRA_TEXT,image_path);
                startActivity(Intent.createChooser(i, "Via "));
            }
        });






    }

    private void deleteNews() {

            String url = "https://castercrew.com/mobileapp/delete_news";

            StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String result = jsonObject.getString("error");
                                if (result.equalsIgnoreCase("false")) {
                                    Toast.makeText(NewsDetailActivity.this,"News Posted  Successfully",Toast.LENGTH_LONG).show();
                                    Intent intent= new Intent(NewsDetailActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(NewsDetailActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                                }

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

                    params.put("uid",UserId );
                    params.put("id", strNewsId);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonRequest);
    }
        private void EditNewsPostData() {
        String url = "https://castercrew.com/mobileapp/editnews";
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("error");
                            if (result.equalsIgnoreCase("false")) {
                                Toast.makeText(NewsDetailActivity.this,"News Posted  Successfully",Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(NewsDetailActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(NewsDetailActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                            }
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
                params.put("industry_id",strIndustryId);
                if(!strNewsTitle.isEmpty()){
                    params.put("title",strNewsTitle);
                }else{
                    params.put("title",strTitle);
                }
                if(!strNewsDescription.isEmpty()){
                    params.put("description",strNewsDescription);

                }else{
                    params.put("description",strDescription);

                }
                params.put("subject", strNewsSubject);
                params.put("tags",strTags);
                params.put("status",strNewsStatus);
                params.put("photo",strImage);
                params.put("uid",UserId );
                params.put("news_id", strNewsId);
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
        }

        return super.onOptionsItemSelected(item);
    }
}
