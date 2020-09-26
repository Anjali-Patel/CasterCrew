package com.castercrewapp.castercrew.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.ImageProfileAdapter;
import com.castercrewapp.castercrew.connection.NetworkConnection;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.castercrewapp.castercrew.utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class StarCastActivity extends AppCompatActivity implements View.OnClickListener{

//    private ArrayList<recent_featured_videos_list> itemlist_videos;
    ImageProfileAdapter imageProfileAdapter;
    RecyclerView rv_list;
    private ArrayList<recent_featured_profile_list> itemlist_ht_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_cast);
        rv_list=findViewById(R.id.rv_list);
        itemlist_ht_list = new ArrayList<>();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_list.setLayoutManager(linearLayoutManager);
        imageProfileAdapter = new ImageProfileAdapter(StarCastActivity.this, itemlist_ht_list);
        rv_list.setAdapter(imageProfileAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        gethrlist();
    }
    public void gethrlist(){
        if (!NetworkConnection.isConnected(StarCastActivity.this)) {
            Toast.makeText(StarCastActivity.this, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(StarCastActivity.this);
        dialog.setMessage("Please Wait.....");

        dialog.show();


        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_profile_list>> call = apiService.getrecent_feature_profile("Artists");
        call.enqueue(new Callback<List<recent_featured_profile_list>>() {
            @Override
            public void onResponse(@NonNull Call<List<recent_featured_profile_list>> call, @NonNull retrofit2.Response<List<recent_featured_profile_list>> response) {
                List<recent_featured_profile_list> subcategoryList = response.body();

                if (subcategoryList != null) {

                    if (!subcategoryList.isEmpty()){
                        itemlist_ht_list.clear();
                        itemlist_ht_list.addAll(subcategoryList);
//                        Collections.reverse(itemList);
                        imageProfileAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                        updateView(0);
                    }else
                        updateView(1);
                }
                else
                    updateView(2);
            }

            @Override
            public void onFailure(@NonNull Call<List<recent_featured_profile_list>>call, @NonNull Throwable t) {
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

    @Override
    public void onClick(View v) {

    }
}
