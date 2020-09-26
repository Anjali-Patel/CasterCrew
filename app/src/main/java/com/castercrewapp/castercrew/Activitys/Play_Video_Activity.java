package com.castercrewapp.castercrew.Activitys;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.ContinueWatchingAdapter;
import com.castercrewapp.castercrew.connection.NetworkConnection;
import com.castercrewapp.castercrew.model.recent_featured_videos_list;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.ApiClient;
import com.castercrewapp.castercrew.utils.Config;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Play_Video_Activity extends YouTubeBaseActivity  {
    ViewGroup viewGroup;
    String category_id="",industry_id="",video_id="",uid="";
    Button delete,save,edit;
    private String url;
ImageView image_view;
LinearLayout download_video;
RecyclerView rv_list;
    private YouTubePlayerView youTubeView;
    YouTubePlayer youTubePlayer;
LinearLayout share,shortlist;

String desriptionString= "";
    YouTubePlayer.OnInitializedListener onInitializedListener;
TextView date,description,view_all;
    Boolean all_videos_tittle, cc_special_videos_tittle, recommended_videos_tittle, Review_image,VideoSection,Artist_section,Continue_watching;

    String video_title,date1;
    RelativeLayout relative_startcast;
    ContinueWatchingAdapter adapter_videos;
    private ArrayList<recent_featured_videos_list> itemlist_videos;
    SharedPreferenceUtils preferances;
    String User_id;

//       description.setText(Event.getString("description").replace("null",""));
//                                if(Event.getString("description").length() > 100){
//        makeTextViewResizable(description, 5, "View More", true);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__video_);
        preferances= SharedPreferenceUtils.getInstance(this);
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        delete=findViewById(R.id.delete);
        save=findViewById(R.id.save);
        edit=findViewById(R.id.edit);
        shortlist=findViewById(R.id.shortlist);

        youTubeView = findViewById(R.id.youtube_view);
        image_view=findViewById(R.id.image_view);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            url = bundle.getString("youtube_url");
            video_title = bundle.getString("video_title");
            date1= bundle.getString("date");
            Review_image=getIntent().getExtras().getBoolean("review_image");
            VideoSection = getIntent().getExtras().getBoolean("video_section");
            Artist_section = getIntent().getExtras().getBoolean("artist_section");
            Continue_watching = getIntent().getExtras().getBoolean("continue_watching");
            category_id=getIntent().getStringExtra("category_id");
            industry_id=getIntent().getStringExtra("industry_id");
            video_id=getIntent().getStringExtra("video_id");
            uid=getIntent().getStringExtra("uid");
        }

        download_video=findViewById(R.id.download_video);

        date=findViewById(R.id.date);
        rv_list=findViewById(R.id.rv_list);
        itemlist_videos= new ArrayList<>();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_list.setLayoutManager(linearLayoutManager);
        adapter_videos = new ContinueWatchingAdapter(getApplicationContext(), itemlist_videos);
        rv_list.setAdapter(adapter_videos);
        view_all=findViewById(R.id.view_all);
if(User_id.equalsIgnoreCase(uid)){
    delete.setVisibility(View.VISIBLE);
    edit.setVisibility(View.VISIBLE);
    save.setVisibility(View.VISIBLE);
edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    edit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
}else{
    delete.setVisibility(View.GONE);
    edit.setVisibility(View.GONE);
    save.setVisibility(View.GONE);
}
        download_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User_id.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Play_Video_Activity.this);
                    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.skip_poppup_layout, viewGroup, false);
                    TextView no=dialogView.findViewById(R.id.no);
                    TextView yes=dialogView.findViewById(R.id.yes);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(Play_Video_Activity.this, LoginActivity.class);
                            startActivity(intent);
                            alertDialog.dismiss();
                        }});
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }});
                }else{
                    Toast.makeText(Play_Video_Activity.this,"Downloading Video",Toast.LENGTH_LONG).show();

                }
            }
        });
        shortlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User_id.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Play_Video_Activity.this);
                    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.skip_poppup_layout, viewGroup, false);
                    TextView no=dialogView.findViewById(R.id.no);
                    TextView yes=dialogView.findViewById(R.id.yes);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(Play_Video_Activity.this, LoginActivity.class);
                            startActivity(intent);
                            alertDialog.dismiss();
                        }});
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }});
                }else{

                }
            }
        });
        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Play_Video_Activity.this,ContinueWatchingActivity.class);
                intent.putExtra("View_all_button",true);
                startActivity(intent);
            }
        });
        description=findViewById(R.id.description);
        desriptionString=getIntent().getStringExtra("story");

        share=findViewById(R.id.share);
        if(desriptionString==null){
            desriptionString="Bheesham is a frushtated man who is tired of being single all of his life.\nWhen an incident changes his lifeforever but helps him get close to the woman he loves,how does he deal with it?";
            description.setText(desriptionString);

        }else{
            description.setText(desriptionString);

        }
//        if(desriptionString.length()>100){
//            makeTextViewResizable(description, 5, "View More", true);
//        }
        relative_startcast=findViewById(R.id.relative_startcast);
        relative_startcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Play_Video_Activity.this,StarCastActivity.class);
                startActivity(intent);
            }
        });


        if (Review_image) {
            image_view.setVisibility(View.VISIBLE);
            Picasso.with(Play_Video_Activity.this).load(url).error(R.drawable.viewpagerbackground).into(image_view);

        }else if (VideoSection) {
            youTubeView.setVisibility(View.VISIBLE);
            onInitializedListener = new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
                    youTubePlayer = player;
                    youTubePlayer.loadVideo(url);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };
            youTubeView.initialize(Config.YOUTUBE_API_KEY,onInitializedListener);
        }else if (Artist_section) {
            youTubeView.setVisibility(View.VISIBLE);
            onInitializedListener = new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
                    youTubePlayer = player;
                    youTubePlayer.loadVideo(url);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };
            youTubeView.initialize(Config.YOUTUBE_API_KEY,onInitializedListener);
        }else if (Continue_watching) {
            youTubeView.setVisibility(View.VISIBLE);
            onInitializedListener = new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
                    youTubePlayer = player;
                    youTubePlayer.loadVideo(url);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };
            youTubeView.initialize(Config.YOUTUBE_API_KEY,onInitializedListener);
        }
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    if(User_id.equalsIgnoreCase("")){
    AlertDialog.Builder builder = new AlertDialog.Builder(Play_Video_Activity.this);
    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.skip_poppup_layout, viewGroup, false);
    TextView no=dialogView.findViewById(R.id.no);
    TextView yes=dialogView.findViewById(R.id.yes);
    builder.setView(dialogView);
    final AlertDialog alertDialog = builder.create();
    alertDialog.show();
    yes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(Play_Video_Activity.this, LoginActivity.class);
            startActivity(intent);
            alertDialog.dismiss();
        }});
     no.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            alertDialog.dismiss();
        }});
      }else{
      Intent i = new Intent(Intent.ACTION_SEND);
     i.setType("image/png");
     i.putExtra(Intent.EXTRA_TEXT, url+"\n \n"+video_title+"\n \n"+date1);
     startActivity(Intent.createChooser(i, "Share "));
      }
            }
        });

        TextView title = findViewById(R.id.video_title);
        title.setText(video_title);
        Date datetime = null;
        try {
            datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newString = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(datetime);

        date.setText(newString);




    }
    @Override
    protected void onStart() {
        super.onStart();
        getvideoslist();
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

    public void getvideoslist(){
        if (!NetworkConnection.isConnected(Play_Video_Activity.this)) {
            Toast.makeText(Play_Video_Activity.this, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(Play_Video_Activity.this);
        dialog.setMessage("Please Wait.....");

        dialog.show();

        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_videos_list>> call = apiService.getrecent_feature_videos("videos");
        call.enqueue(new Callback<List<recent_featured_videos_list>>() {
            @Override
            public void onResponse(@NonNull Call<List<recent_featured_videos_list>> call, @NonNull retrofit2.Response<List<recent_featured_videos_list>> response) {
                List<recent_featured_videos_list> subcategoryList = response.body();

                if (subcategoryList != null) {

                    if (!subcategoryList.isEmpty()){
                        itemlist_videos.clear();
                        itemlist_videos.addAll(subcategoryList);
//                        Collections.reverse(itemList);
                        adapter_videos.notifyDataSetChanged();
//                        artistProfileAdapter.notifyDataSetChanged();

                        dialog.dismiss();
                        updateView(0);
                    }else
                        updateView(1);
                }
                else
                    updateView(2);
            }

            @Override
            public void onFailure(@NonNull Call<List<recent_featured_videos_list>>call, @NonNull Throwable t) {
                // Log error here since request failed
                updateView(3);
            }
        });
    }


    private void updateView(int index){

        switch (index){
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

        }
    }
    private void deleteAudio() {

        String url = "https://castercrew.com/mobileapp/delete_audio";

        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("error");
                            if (result.equalsIgnoreCase("false")) {
                                Toast.makeText(Play_Video_Activity.this,"News Posted  Successfully",Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(Play_Video_Activity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Play_Video_Activity.this,"Something went wrong",Toast.LENGTH_LONG).show();
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

                params.put("uid",User_id );
                params.put("id", video_id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Play_Video_Activity.this);
        requestQueue.add(jsonRequest);

    }



    private void EditAudio() {
        String url = "https://castercrew.com/mobileapp/update_audio";
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("error");
                            if (result.equalsIgnoreCase("false")) {
                                Toast.makeText(Play_Video_Activity.this,"News Posted  Successfully",Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(Play_Video_Activity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Play_Video_Activity.this,"Something went wrong",Toast.LENGTH_LONG).show();
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

             /*   params.put("industry_id1",industry_id);
                if(!strNewsTitle.isEmpty()){
                    params.put("title1",strNewsTitle);
                }else{
                    params.put("title1",strTitle);
                }
                if(!strNewsDescription.isEmpty()){
                    params.put("description2",strNewsDescription);
                }else{
                    params.put("description2",strDescription);

                }
                params.put("youtube_url1", strNewsSubject);
                params.put("audio_id", video_id);
                params.put("rowid", strNewsSubject);

                params.put("name",strTags);
                params.put("designation",strNewsStatus);
                params.put("image",strImage);
                params.put("uid",User_id );
                params.put("catg_id1", category_id);*/
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Play_Video_Activity.this);
        requestQueue.add(jsonRequest);

    }
    private void DeleteVideo() {

        String url = "https://castercrew.com/mobileapp/delete_video";

        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("error");
                            if (result.equalsIgnoreCase("false")) {
                                Toast.makeText(Play_Video_Activity.this,"News Posted  Successfully",Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(Play_Video_Activity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Play_Video_Activity.this,"Something went wrong",Toast.LENGTH_LONG).show();
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

                params.put("uid",User_id );
                params.put("id", video_id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Play_Video_Activity.this);
        requestQueue.add(jsonRequest);

    }



    private void EditVideo() {
        String url = "https://castercrew.com/mobileapp/update_video";

        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("error");
                            if (result.equalsIgnoreCase("false")) {
                                Toast.makeText(Play_Video_Activity.this,"News Posted  Successfully",Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(Play_Video_Activity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Play_Video_Activity.this,"Something went wrong",Toast.LENGTH_LONG).show();
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

                params.put("industry_id1",industry_id);
             /*   if(!strNewsTitle.isEmpty()){
                    params.put("title1",strNewsTitle);

                }else{
                    params.put("title1",strTitle);

                }
                if(!strNewsDescription.isEmpty()){
                    params.put("description2",strNewsDescription);

                }else{
                    params.put("description2",strDescription);

                }
                params.put("youtube_url1", strNewsSubject);
                params.put("video_id",video_id);
                params.put("name",strNewsStatus);
                params.put("image",strImage);
                params.put("uid",User_id );
                params.put("designation",UserId);
                params.put("rowid",UserId );
                params.put("catg_id1", category_id);*/
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Play_Video_Activity.this);
        requestQueue.add(jsonRequest);

    }




}
