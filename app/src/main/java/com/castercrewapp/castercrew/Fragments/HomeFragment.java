package com.castercrewapp.castercrew.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.castercrewapp.castercrew.Activitys.ActivistActivity;
import com.castercrewapp.castercrew.Activitys.DetailspageActivity;
import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Activitys.PaymentActivity;
import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.horzointal_rvlist_adapter;
import com.castercrewapp.castercrew.adapter.recent_featured_profile_adapter;
import com.castercrewapp.castercrew.connection.NetworkConnection;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.ApiClient;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment implements OnItemClickListener {
    private Activity activity;
    ViewGroup viewGroup;

    String user_id;
    SharedPreferenceUtils preferances;
    private RecyclerView recyclerView_artist,recyclerView_hr_list;
    recent_featured_profile_adapter adapter_artist;
    horzointal_rvlist_adapter adapter_hr_list;
    private ArrayList<recent_featured_profile_list> itemList_artist,itemlist_ht_list;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_recycler_view, container, false);
        preferances= SharedPreferenceUtils.getInstance(getContext());
        user_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
       initlizereartistlist(view);
       initilizehrview(view);
       FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_id.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.skip_poppup_layout, viewGroup, false);
                    TextView no=dialogView.findViewById(R.id.no);
                    TextView yes=dialogView.findViewById(R.id.yes);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                            alertDialog.dismiss();
                        }});
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }});
                }else{
                    Intent intent = new Intent(activity, ActivistActivity.class);
                    startActivity(intent);
                }

            }
        });

        return view;
    }

    public void initilizehrview(View view){
        recyclerView_hr_list = view.findViewById(R.id.h_r_list);
        recyclerView_hr_list.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL
                , false);
        recyclerView_hr_list.setLayoutManager(manager);

        itemlist_ht_list = new ArrayList<>();
        adapter_hr_list = new horzointal_rvlist_adapter(this, itemlist_ht_list,user_id);
        recyclerView_hr_list.setAdapter(adapter_hr_list);
    }



    public void initlizereartistlist(View view){
        recyclerView_artist = view.findViewById(R.id.rv_artist_list);
        recyclerView_artist.setHasFixedSize(true);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager manager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL
                , false);
        recyclerView_artist.setLayoutManager(manager);
        itemList_artist = new ArrayList<>();
        adapter_artist = new recent_featured_profile_adapter(this, itemList_artist,user_id);
        recyclerView_artist.setAdapter(adapter_artist);
    }




    @Override
    public void onStart() {
        super.onStart();
        getartists();
        gethrlist();
    }

    public void gethrlist(){
        if (!NetworkConnection.isConnected(activity)) {
            Toast.makeText(activity, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(activity);
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
                        adapter_hr_list.notifyDataSetChanged();
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


    public void getartists(){

        if (!NetworkConnection.isConnected(activity)) {
            Toast.makeText(activity, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(activity);
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

    @Override
    public void onItemClick(View view, int position) {

//        switch (view.getId()){
//
//            case R.id.card_mail:
//              //  open();
//                ToastMessage.onToast(activity,"cliekfas",ToastMessage.SUCCESS);
//                break;
//
//            case R.id.card_call:
//                open();
//                break;
//
//            case R.id.card_download:
//                open();
//                break;
//
//            case R.id.card_share:
//                open();
//                break;
//        }

        recent_featured_profile_list list = itemList_artist.get(position);

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

    public void open(){

        Intent intent = new Intent(activity,PaymentActivity.class);
        startActivity(intent);
    }
}
