package com.castercrewapp.castercrew.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.castercrewapp.castercrew.Fragments.AudioFragment.AudioAdsFragment;
import com.castercrewapp.castercrew.Fragments.AudioFragment.AudioEventsFragment;
import com.castercrewapp.castercrew.Fragments.AudioFragment.AudioMoviewFragment;
import com.castercrewapp.castercrew.Fragments.AudioFragment.AudioOtherFragment;
import com.castercrewapp.castercrew.Fragments.AudioFragment.AudioShortFilmFragment;
import com.castercrewapp.castercrew.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AudiosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AudiosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudiosFragment extends Fragment {
    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AudiosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AudiosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AudiosFragment newInstance(String param1, String param2) {
        AudiosFragment fragment = new AudiosFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_audios, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Audios");
        tabLayout=view.findViewById(R.id.mTabLayout);
        frameLayout=view.findViewById(R.id.fragment_container);
        fragment = new AudioShortFilmFragment();
        fragmentManager = getChildFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new AudioMoviewFragment();
                        break;
                    case 1:
                        fragment = new AudioShortFilmFragment();
                        break;
                    case 2:
                        fragment = new AudioAdsFragment();
                        break;
                    case 3:
                        fragment = new AudioEventsFragment();
                        break;
                    case 4:
                        fragment = new AudioOtherFragment();
                        break;

                }
                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return  view;
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

    @Override
    public void onStart() {
        super.onStart();
//        getvideoslist();

    }

 /*   public void getvideoslist(){
        if (!NetworkConnection.isConnected(getContext())) {
            Toast.makeText(getContext(), "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait.....");

        dialog.show();


        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_videos_list>> call = apiService.getrecent_feature_videos("audios");
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
    }*/
}
