package com.castercrewapp.castercrew.Fragments.NewsFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.NewsAdapter;
import com.castercrewapp.castercrew.model.ExpandedMenuModel;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FreeMovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FreeMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FreeMovieFragment extends Fragment {
    SharedPreferenceUtils preferances;
    String User_id;
    RecyclerView FreeMovieNewsList;
    FrameLayout progressBarHolder;
    NewsAdapter newsAdapter;
    ArrayList<ExpandedMenuModel> newsArrayList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onStart() {
        super.onStart();
        getCoronavirusNewsList();
        progressBarHolder.setVisibility(View.VISIBLE);
    }
    private void getCoronavirusNewsList() {
        String url = "https://castercrew.com/mobileapp/all_news";

        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBarHolder.setVisibility(View.GONE);
                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray=json.getJSONArray("news_list");
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject e = jsonArray.getJSONObject(j);
                        ExpandedMenuModel model = new ExpandedMenuModel();
                        model.setNewsDate(e.getString("post_dt"));
                        model.setNewsDescription(e.getString("description"));
                        model.setNewsImage(e.getString("photo"));
                        model.setNewstags(e.getString("tags"));
                        model.setNewsTitle(e.getString("title"));
                        model.setIndustry_name(e.getString("industry_name"));
                        model.setCreate_by(e.getString("create_by"));
                        model.setSubject(e.getString("subject"));
                        model.setIndustry_id(e.getString("industry_id"));
                        model.setNewsId(e.getString("id"));
                        model.setNewsStatus(e.getString("status"));
                        newsArrayList.add(model);
                    }
                    newsAdapter = new NewsAdapter(getContext(),newsArrayList,User_id);
                    LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
                    FreeMovieNewsList.setLayoutManager(layoutmanager);
                    FreeMovieNewsList.setAdapter(newsAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBarHolder.setVisibility(View.GONE);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonRequest);

    }

    public FreeMovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FreeMovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FreeMovieFragment newInstance(String param1, String param2) {
        FreeMovieFragment fragment = new FreeMovieFragment();
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
        View view= inflater.inflate(R.layout.fragment_free_movie, container, false);
        preferances= SharedPreferenceUtils.getInstance(getContext());
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        progressBarHolder=view.findViewById(R.id.progressBarHolder);
        FreeMovieNewsList=view.findViewById(R.id.FreeMovieNewsList);
        newsArrayList=new ArrayList<>();
        LinearLayoutManager linearLayoutManagernews = new LinearLayoutManager(getContext());
        linearLayoutManagernews.setOrientation(LinearLayoutManager.VERTICAL);
        FreeMovieNewsList.setLayoutManager(linearLayoutManagernews);
        FreeMovieNewsList.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(getContext(), newsArrayList,User_id);
        FreeMovieNewsList.setAdapter(newsAdapter);


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
