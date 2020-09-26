package com.castercrewapp.castercrew.model;

import com.google.gson.annotations.SerializedName;

public class recent_featured_reviews_list {

    @SerializedName("id")
    private String id;

    @SerializedName("catg_id")
    private String catg_id;

    @SerializedName("industry_id")
    private String industry_id;

    @SerializedName("title")
    private String title;

    @SerializedName("youtube_url")
    private String image;

    @SerializedName("post_dt")
    private String post_dt;

    @SerializedName("views")
    private String views;

    @SerializedName("status")
    private String status;

    @SerializedName("create_by")
    private String create_by;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("starring")
    private String starring;

    @SerializedName("director")
    private String director;

    @SerializedName("producers")
    private String producers;

    @SerializedName("music_director")
    private String music_director;

    @SerializedName("story")
    private String story;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatg_id() {
        return catg_id;
    }

    public void setCatg_id(String catg_id) {
        this.catg_id = catg_id;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPost_dt() {
        return post_dt;
    }

    public void setPost_dt(String post_dt) {
        this.post_dt = post_dt;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getMusic_director() {
        return music_director;
    }

    public void setMusic_director(String music_director) {
        this.music_director = music_director;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
