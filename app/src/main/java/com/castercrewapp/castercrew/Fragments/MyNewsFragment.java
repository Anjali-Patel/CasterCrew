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

import com.castercrewapp.castercrew.Fragments.NewsFragment.CoronavirusFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.CricketFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.EducationFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.EnterTainmentFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.FreeMovieFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.JobsFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.PoliticsFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.RecommendedFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.ScienceFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.SocietyFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.SportFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.TechFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.TravelFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.TrendingFragment;
import com.castercrewapp.castercrew.Fragments.NewsFragment.VideoFragment;
import com.castercrewapp.castercrew.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyNewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyNewsFragment extends Fragment {
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

    public MyNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyNewsFragment newInstance(String param1, String param2) {
        MyNewsFragment fragment = new MyNewsFragment();
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
        View view= inflater.inflate(R.layout.fragment_my_news, container, false);
        tabLayout=view.findViewById(R.id.mTabLayout);
        frameLayout=view.findViewById(R.id.fragment_container);

        fragment = new RecommendedFragment();
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
                        fragment = new RecommendedFragment();
                        break;
                    case 1:
                        fragment = new CoronavirusFragment();
                        break;
                    case 2:
                        fragment = new VideoFragment();
                        break;
                    case 3:
                        fragment = new CricketFragment();
                        break;
                    case 4:
                        fragment = new EnterTainmentFragment();
                        break;
                    case 5:
                        fragment = new FreeMovieFragment();
                        break;
                    case 6:
                        fragment = new PoliticsFragment();
                        break;
                    case 7:
                        fragment = new EducationFragment();
                        break;
                    case 8:
                        fragment = new TrendingFragment();
                        break;
                    case 9:
                        fragment = new SocietyFragment();
                        break;
                    case 10:
                        fragment = new TechFragment();
                        break;
                    case 11:
                        fragment = new JobsFragment();
                        break;
                    case 12:
                        fragment = new SportFragment();
                        break;
                    case 13:
                        fragment = new TravelFragment();
                        break;
                    case 14:
                        fragment = new ScienceFragment();
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










        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("News");
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
}
