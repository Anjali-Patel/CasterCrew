package com.castercrewapp.castercrew.Fragments.ReviewSubCategoryFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.castercrewapp.castercrew.Activitys.ReviewsAllVideolActivity;
import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.ReviewSmallCardAdapter;
import com.castercrewapp.castercrew.adapter.ReviewsAdapter;
import com.castercrewapp.castercrew.connection.NetworkConnection;
import com.castercrewapp.castercrew.model.recent_featured_videos_list;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.ApiClient;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReviewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReviewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewsFragment extends Fragment {
    SharedPreferenceUtils preferances;
    String User_id;
    RecyclerView recyclerView_video,all_videos,cc_spatial_videos,popular_videos,continue_watching;
    ReviewSmallCardAdapter adapter_videos;
    private ArrayList<recent_featured_videos_list> itemlist_videos;
    ReviewsAdapter artistProfileAdapter;
    View linear_continue_watching;
    View linear_popularaudios;
    View linear_spatialaudios;
    View linear_allvideos;





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewsFragment newInstance(String param1, String param2) {
        ReviewsFragment fragment = new ReviewsFragment();
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
        View view= inflater.inflate(R.layout.fragment_reviews2, container, false);
        preferances= SharedPreferenceUtils.getInstance(getContext());
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        recyclerView_video = view.findViewById(R.id.rv_list);
        all_videos = view.findViewById(R.id.all_videos);
        cc_spatial_videos = view.findViewById(R.id.cc_spatial_videos);
        popular_videos = view.findViewById(R.id.popular_videos);
        continue_watching = view.findViewById(R.id.continue_watching);

        linear_continue_watching= view.findViewById(R.id.linear_continue_watching);
        linear_popularaudios= view.findViewById(R.id.linear_popularaudios);
        linear_spatialaudios= view.findViewById(R.id.linear_spatialaudios);
        linear_allvideos= view.findViewById(R.id.linear_allvideos);
        linear_allvideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ReviewsAllVideolActivity.class);
                intent.putExtra("all_review_tittle",true);
//                intent.putExtra("AllVideos","All Videos");

                getActivity().startActivity(intent);
            }
        });


        linear_spatialaudios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ReviewsAllVideolActivity.class);
                intent.putExtra("cc_special_review_tittle",true);
//                intent.putExtra("CCSpecial ","CC Special Videos");

                getActivity().startActivity(intent);
            }
        });
        linear_popularaudios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ReviewsAllVideolActivity.class);
                intent.putExtra("recommended_review_tittle",true);
//                intent.putExtra("Recommended","Recommended Videos");

                getActivity().startActivity(intent);
            }
        });
        linear_continue_watching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ReviewsAllVideolActivity.class);
                intent.putExtra("continue_watching_tittle",true);
//                intent.putExtra("Continue","Continue watching");

                getActivity().startActivity(intent);
            }
        });




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        cc_spatial_videos.setLayoutManager(linearLayoutManager3);
        cc_spatial_videos.setHasFixedSize(true);
        all_videos.setLayoutManager(linearLayoutManager4);
        all_videos.setHasFixedSize(true);
        recyclerView_video.setLayoutManager(linearLayoutManager);
        recyclerView_video.setHasFixedSize(true);
        continue_watching.setLayoutManager(linearLayoutManager1);
        continue_watching.setHasFixedSize(true);
        popular_videos.setLayoutManager(linearLayoutManager2);
        popular_videos.setHasFixedSize(true);
//        recyclerView_video.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView_video.setItemAnimator(new DefaultItemAnimator());
        continue_watching.setItemAnimator(new DefaultItemAnimator());
        popular_videos.setItemAnimator(new DefaultItemAnimator());
        all_videos.setItemAnimator(new DefaultItemAnimator());
        cc_spatial_videos.setItemAnimator(new DefaultItemAnimator());
        itemlist_videos = new ArrayList<>();
        artistProfileAdapter = new ReviewsAdapter(getContext(), itemlist_videos,User_id);

        adapter_videos = new ReviewSmallCardAdapter(getContext(), itemlist_videos,User_id);
        recyclerView_video.setAdapter(artistProfileAdapter);
        continue_watching.setAdapter(adapter_videos);
        popular_videos.setAdapter(adapter_videos);
        all_videos.setAdapter(adapter_videos);
        cc_spatial_videos.setAdapter(adapter_videos);




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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        getvideoslist();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public void getvideoslist(){
        if (!NetworkConnection.isConnected(getContext())) {
            Toast.makeText(getContext(), "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait.....");

        dialog.show();

        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_videos_list>> call = apiService.getrecent_feature_videos("reviews");
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
                        artistProfileAdapter.notifyDataSetChanged();

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
