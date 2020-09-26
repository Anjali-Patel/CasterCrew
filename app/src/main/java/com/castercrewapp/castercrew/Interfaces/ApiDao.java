package com.castercrewapp.castercrew.Interfaces;


import com.castercrewapp.castercrew.model.Banner;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.castercrewapp.castercrew.model.recent_featured_reviews_list;
import com.castercrewapp.castercrew.model.recent_featured_videos_list;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiDao {

    @POST("mobileapp/home?")
    Call<List<recent_featured_profile_list>> getrecent_feature_profile(
            @Query("title") String request);


    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_actress(
            @Query("title") String request,
            @Query("talent") String name);

    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_actor(
            @Query("title") String request,
            @Query("talent") String name);

    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_anchor(
            @Query("title") String request,
            @Query("talent") String name);

    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_childartist(
            @Query("title") String request,
            @Query("talent") String name);

    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_dancer(
            @Query("title") String request,
            @Query("talent") String name);

    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_model(
            @Query("title") String request,
            @Query("talent") String name);

    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_produ_assistant(
            @Query("title") String request,
            @Query("talent") String name);

    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_produ_coordinator(
            @Query("title") String request,
            @Query("talent") String name);

    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_singer(
            @Query("title") String request,
            @Query("talent") String name);

    @POST(" mobileapp/page?")
    Call<List<recent_featured_profile_list>> getartist_other(
            @Query("title") String request,
            @Query("talent") String name);


    @POST("mobileapp/home?")
    Call<List<recent_featured_videos_list>> getrecent_feature_videos(
            @Query("title") String request);



    @POST("mobileapp/home?")
    Call<List<recent_featured_reviews_list>> getrecent_feature_review(
            @Query("title") String request);

    @POST("mobileapp/home?")
    Call<List<recent_featured_profile_list>> getrecent_feature_professional(
            @Query("title") String request);

    @POST("mobileapp/home?")
    Call<List<Banner>> getBannerImages(
            @Query("title") String request);

}
