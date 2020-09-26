package com.castercrewapp.castercrew.model;

public class AuditionPoster_model {
    String audition_title;

    public String getAudition_title() {
        return audition_title;
    }

    public void setAudition_title(String audition_title) {
        this.audition_title = audition_title;
    }

    public String getAuditiondate() {
        return auditiondate;
    }

    public void setAuditiondate(String auditiondate) {
        this.auditiondate = auditiondate;
    }

    public String getAuditionDescription() {
        return AuditionDescription;
    }

    public void setAuditionDescription(String auditionDescription) {
        AuditionDescription = auditionDescription;
    }

    public String getAuditionImage() {
        return AuditionImage;
    }

    public void setAuditionImage(String auditionImage) {
        AuditionImage = auditionImage;
    }

    String auditiondate;
    String AuditionDescription;
    String AuditionImage;
}
