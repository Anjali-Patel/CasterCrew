package com.castercrewapp.castercrew.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.VerticalArtistprofileAdapter;
import com.castercrewapp.castercrew.connection.NetworkConnection;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.ApiClient;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class AlsoVisitedActivity extends AppCompatActivity {
    String user_id;
    SharedPreferenceUtils preferances;

    RecyclerView member_also_visted;
    private ArrayList<recent_featured_profile_list> itemList_artist;
    VerticalArtistprofileAdapter adapter_artist;
    @Override
    protected void onStart() {
        super.onStart();
        getartists();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_also_visited);
        preferances= SharedPreferenceUtils.getInstance(AlsoVisitedActivity.this);
        user_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        member_also_visted=findViewById(R.id.member_also_visted);
        itemList_artist=new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        member_also_visted.setHasFixedSize(true);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager manager = new LinearLayoutManager(AlsoVisitedActivity.this,LinearLayoutManager.VERTICAL
                , false);
        member_also_visted.setLayoutManager(manager);
        itemList_artist = new ArrayList<>();
        adapter_artist = new VerticalArtistprofileAdapter(AlsoVisitedActivity.this, itemList_artist,user_id);
        member_also_visted.setAdapter(adapter_artist);

    }

    public void getartists(){

        if (!NetworkConnection.isConnected(AlsoVisitedActivity.this)) {
            Toast.makeText(AlsoVisitedActivity.this, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(AlsoVisitedActivity.this);
        dialog.setMessage("Please Wait.....");

        dialog.show();

        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_profile_list>> call = apiService.getrecent_feature_profile("Artists");
        call.enqueue(new Callback<List<recent_featured_profile_list>>() {
            @Override
            public void onResponse(@NonNull Call<List<recent_featured_profile_list>> call, @NonNull retrofit2.Response<List<recent_featured_profile_list>> response) {
                List<recent_featured_profile_list> subcategoryList = response.body();
                Log.d("EXIT", "EXIT: " + response.body());

                if (subcategoryList != null) {


                    if (!subcategoryList.isEmpty()){
                        itemList_artist.clear();
                        itemList_artist.addAll(subcategoryList);
//                        Collections.reverse(itemList);
                        adapter_artist.notifyDataSetChanged();
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









}
