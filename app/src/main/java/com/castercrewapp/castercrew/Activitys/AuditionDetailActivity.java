package com.castercrewapp.castercrew.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.AuditionAdapter;
import com.castercrewapp.castercrew.adapter.AuditionDetailAdapter;
import com.castercrewapp.castercrew.connection.NetworkConnection;
import com.castercrewapp.castercrew.model.recent_featured_videos_list;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.ApiClient;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditionDetailActivity extends AppCompatActivity {
    ViewGroup viewGroup;

    LinearLayout download_video;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.viewpagerbackground,R.drawable.chennel,R.drawable.media,R.drawable.samantha_profile,R.drawable.samantha_six};
    private static final String[]title={"Protect Your Child","Safe Your Parents","Track The L:ocation","Connect to Family"};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    RecyclerView rv_list;
    SharedPreferenceUtils preferances;
    String User_id;

    LinearLayout share,shortlist;
    String desriptionString="";
    TextView date,description,view_all;
    String url,video_title,date1;
ImageView download_audition;
    AuditionAdapter adapter_videos;
    private ArrayList<recent_featured_videos_list> itemlist_videos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audition_detail);
        preferances= SharedPreferenceUtils.getInstance(this);
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        shortlist=findViewById(R.id.shortlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Auditions Details");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            video_title = bundle.getString("video_title");
            date1= bundle.getString("date");
            desriptionString=bundle.getString("description");
            //description

        }
        date=findViewById(R.id.date);
        rv_list=findViewById(R.id.rv_list);
        download_video=findViewById(R.id.download_video);
        download_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User_id.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AuditionDetailActivity.this);
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
                            Intent intent= new Intent(AuditionDetailActivity.this, LoginActivity.class);
                            startActivity(intent);
                            alertDialog.dismiss();
                        }});
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }});
                }else{
                    Toast.makeText(AuditionDetailActivity.this,"Downloading Audition Details..........",Toast.LENGTH_LONG).show();

                }
            }
        });
        shortlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(User_id.equalsIgnoreCase("")){
                  AlertDialog.Builder builder = new AlertDialog.Builder(AuditionDetailActivity.this);
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
                          Intent intent= new Intent(AuditionDetailActivity.this, LoginActivity.class);
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
        itemlist_videos= new ArrayList<>();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_list.setLayoutManager(linearLayoutManager);
        adapter_videos = new AuditionAdapter(getApplicationContext(), itemlist_videos,User_id);
        rv_list.setAdapter(adapter_videos);
        view_all=findViewById(R.id.view_all);
        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AuditionDetailActivity.this,AuditionAllActivity.class);
                intent.putExtra("View_all_button",true);
                startActivity(intent);
            }
        });
        description=findViewById(R.id.description);
        share=findViewById(R.id.share);
        if(desriptionString==null){
            desriptionString="Bheesham is a frushtated man who is tired of being single all of his life.\nWhen an incident changes his lifeforever but helps him get close to the woman he loves,how does he deal with it?";
            description.setText(desriptionString);

        }else{
            description.setText(desriptionString);

        }


      /* if(desriptionString.equalsIgnoreCase("")){
           desriptionString="Bheesham is a frushtated man who is tired of being single all of his life.\nWhen an incident changes his lifeforever but helps him get close to the woman he loves,how does he deal with it?";
           description.setText(desriptionString);
       }else{
           description.setText(desriptionString);
       }*/
//        if(desriptionString.length()>100){
//            makeTextViewResizable(description, 5, "View More", true);
//        }



        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    if(User_id.equalsIgnoreCase("")){
    AlertDialog.Builder builder = new AlertDialog.Builder(AuditionDetailActivity.this);
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
            Intent intent= new Intent(AuditionDetailActivity.this, LoginActivity.class);
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
        assert datetime != null;
        String newString = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(datetime);
        date.setText(newString);

        init();
    }
    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new AuditionDetailAdapter(AuditionDetailActivity.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

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
        if (!NetworkConnection.isConnected(AuditionDetailActivity.this)) {
            Toast.makeText(AuditionDetailActivity.this, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(AuditionDetailActivity.this);
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




}
