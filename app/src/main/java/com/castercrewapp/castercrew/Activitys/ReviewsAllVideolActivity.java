package com.castercrewapp.castercrew.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.ReviewAllVideiAdapter;
import com.castercrewapp.castercrew.connection.NetworkConnection;
import com.castercrewapp.castercrew.model.recent_featured_videos_list;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.ApiClient;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAllVideolActivity extends AppCompatActivity {
    SharedPreferenceUtils preferances;
    String User_id;

    ViewGroup viewGroup;
    RecyclerView all_videos;
    ReviewAllVideiAdapter adapter_videos;
    String all_title, str_all_videos;
    TextView toolbar_title;
    String flag_activity = "";
    Boolean all_videos_tittle, cc_special_videos_tittle, recommended_videos_tittle, continue_watching_review_tittle,recommened_review,spetial_reviews,all_reviews;
    boolean view_all;
    boolean cc_special_audios,recommended_audios,all_audios;
    private ArrayList<recent_featured_videos_list> itemlist_videos;
    Button filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_all_videol);
        preferances= SharedPreferenceUtils.getInstance(this);
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        filter=findViewById(R.id.filter);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        continue_watching_review_tittle=getIntent().getExtras().getBoolean("continue_watching_tittle");
        recommened_review = getIntent().getExtras().getBoolean("recommended_review_tittle");
        spetial_reviews = getIntent().getExtras().getBoolean("cc_special_review_tittle");
        all_reviews = getIntent().getExtras().getBoolean("all_review_tittle");
        if (all_reviews) {
            toolbar_title.setText("All Reviews");
        }else if (spetial_reviews) {
            toolbar_title.setText("CC Spatial Reviews");
        }else if (recommened_review) {
            toolbar_title.setText("Recommended Reviews");
        }else if (continue_watching_review_tittle) {
            toolbar_title.setText("Continue watching");
        }

     /*   else if (continue_watching_tittle) {
            toolbar_title.setText("Continue Watching");
        }*/
        all_videos = findViewById(R.id.all_videos);
        itemlist_videos = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        all_videos.setLayoutManager(gridLayoutManager);
        adapter_videos = new ReviewAllVideiAdapter(getApplicationContext(), itemlist_videos,User_id);
        all_videos.setAdapter(adapter_videos);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewsAllVideolActivity.this);
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
//                        Intent intent= new Intent(ContinueWatchingActivity.this, MembershipPackage.class);
//                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent= new Intent(ContinueWatchingActivity.this, MembershipPackage.class);
//                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getvideoslist();
    }

    public void getvideoslist() {
        if (!NetworkConnection.isConnected(ReviewsAllVideolActivity.this)) {
            Toast.makeText(getApplicationContext(), "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(ReviewsAllVideolActivity.this);
        dialog.setMessage("Please Wait.....");

        dialog.show();

        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_videos_list>> call = apiService.getrecent_feature_videos("reviews");
        call.enqueue(new Callback<List<recent_featured_videos_list>>() {
            @Override
            public void onResponse(@NonNull Call<List<recent_featured_videos_list>> call, @NonNull retrofit2.Response<List<recent_featured_videos_list>> response) {
                List<recent_featured_videos_list> subcategoryList = response.body();
                if (subcategoryList != null) {
                    if (!subcategoryList.isEmpty()) {
                        itemlist_videos.clear();
                        itemlist_videos.addAll(subcategoryList);
//                        Collections.reverse(itemList);
                        adapter_videos.notifyDataSetChanged();

                        dialog.dismiss();
                        updateView(0);
                    } else
                        updateView(1);
                } else
                    updateView(2);
            }

            @Override
            public void onFailure(@NonNull Call<List<recent_featured_videos_list>> call, @NonNull Throwable t) {
                // Log error here since request failed
                updateView(3);
            }
        });
    }


    private void updateView(int index) {

        switch (index) {
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
