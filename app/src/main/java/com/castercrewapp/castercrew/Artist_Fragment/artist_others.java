package com.castercrewapp.castercrew.Artist_Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.castercrewapp.castercrew.Activitys.DetailspageActivity;
import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.recent_featured_profile_adapter;
import com.castercrewapp.castercrew.connection.NetworkConnection;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.ApiClient;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class artist_others extends Fragment implements OnItemClickListener {
    String user_id;
    SharedPreferenceUtils preferances;
    private Activity activity;
    recent_featured_profile_adapter adapter_profissional;
    RecyclerView recyclerView_profissional;
    private ArrayList<recent_featured_profile_list> itemList_profissional;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profissional, container, false);
        preferances= SharedPreferenceUtils.getInstance(getContext());
        user_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        initlizelist(view);

        return view;
    }

    public void initlizelist(View view){

        recyclerView_profissional = view.findViewById(R.id.rv_professionals_list);
        recyclerView_profissional.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,
                false);
        recyclerView_profissional.setLayoutManager(manager);

        itemList_profissional = new ArrayList<>();
        adapter_profissional = new recent_featured_profile_adapter(this, itemList_profissional,user_id);
        recyclerView_profissional.setAdapter(adapter_profissional);
    }

    @Override
    public void onStart() {
        super.onStart();
        getlist();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                activity.onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }



    public void getlist(){
        if (!NetworkConnection.isConnected(activity)) {
            Toast.makeText(activity, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage("Please Wait.....");

        dialog.show();

        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_profile_list>> call = apiService.getartist_other("Artists","Others");
        call.enqueue(new Callback<List<recent_featured_profile_list>>() {
            @Override
            public void onResponse(@NonNull Call<List<recent_featured_profile_list>> call, @NonNull retrofit2.Response<List<recent_featured_profile_list>> response) {
                List<recent_featured_profile_list> subcategoryList = response.body();

                if (subcategoryList != null) {

                    if (!subcategoryList.isEmpty()){
                        itemList_profissional.clear();
                        itemList_profissional.addAll(subcategoryList);
//                        Collections.reverse(itemList);
                        adapter_profissional.notifyDataSetChanged();
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
    public void onItemClick(View view, int position) {
        recent_featured_profile_list list = itemList_profissional.get(position);

        Intent intent = new Intent(activity, DetailspageActivity.class);
        intent.putExtra("name",list.getFull_name()+list.getUid());
        intent.putExtra("description",list.getAbout_info());
        intent.putExtra("category",list.getType());
        intent.putExtra("subcategory",list.getArtist_name());
        intent.putExtra("talenttitle",list.getTalent_title());
        intent.putExtra("experience",list.getExperience());
        intent.putExtra("languageknows",list.getLang_known());
        intent.putExtra("website",list.getWebsite());
        intent.putExtra("talentstatus",list.getTalent_status());
        intent.putExtra("remarks",list.getRemarks());
        intent.putExtra("industryname",list.getIndustry_name());
        intent.putExtra("views",list.getViews());
        intent.putExtra("uid",list.getUid());
        intent.putExtra("image",list.getTalent_pic());

        startActivity(intent);

    }
}
