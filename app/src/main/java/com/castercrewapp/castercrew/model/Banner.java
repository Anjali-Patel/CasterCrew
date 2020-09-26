package com.castercrewapp.castercrew.model;

import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("id")
    private String id;

    @SerializedName("category")
    private String category;

    @SerializedName("img")
    private String img;

    @SerializedName("description")
    private String description;

    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    @SerializedName("social_name")
    private String social_name;

    @SerializedName("social_icon")
    private String social_icon;

    @SerializedName("post_dt")
    private String post_dt;

    @SerializedName("status")
    private String status;

    @SerializedName("create_by")
    private String create_by;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSocial_name() {
        return social_name;
    }

    public void setSocial_name(String social_name) {
        this.social_name = social_name;
    }

    public String getSocial_icon() {
        return social_icon;
    }

    public void setSocial_icon(String social_icon) {
        this.social_icon = social_icon;
    }

    public String getPost_dt() {
        return post_dt;
    }

    public void setPost_dt(String post_dt) {
        this.post_dt = post_dt;
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
}
