package com.castercrewapp.castercrew.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShortlistedMeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShortlistedMeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShortlistedMeFragment extends Fragment implements OnItemClickListener {
    String user_id;
    SharedPreferenceUtils preferances;
    private Activity activity;
    recent_featured_profile_adapter adapter_profissional;
    RecyclerView recyclerView_profissional;
    private ArrayList<recent_featured_profile_list> itemList_profissional;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShortlistedMeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShortlistedMeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShortlistedMeFragment newInstance(String param1, String param2) {
        ShortlistedMeFragment fragment = new ShortlistedMeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_shortlisted_me, container, false);
        preferances= SharedPreferenceUtils.getInstance(getContext());
        user_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        recyclerView_profissional = view.findViewById(R.id.rv_professionals_list);
        recyclerView_profissional.setHasFixedSize(true);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager manager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,
                false);
        recyclerView_profissional.setLayoutManager(manager);

        itemList_profissional = new ArrayList<>();
        adapter_profissional = new recent_featured_profile_adapter(this, itemList_profissional,user_id);
        recyclerView_profissional.setAdapter(adapter_profissional);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Shortlisted Me");
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            try {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            activity = (Activity) context;


//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getlist();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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

        Call<List<recent_featured_profile_list>> call = apiService.getartist_actor("Artists","Actor");
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



}
