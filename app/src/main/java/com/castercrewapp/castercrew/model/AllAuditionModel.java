package com.castercrewapp.castercrew.model;

public class AllAuditionModel {
    String auditionDate;

    public String getAuditionDate() {
        return auditionDate;
    }

    public void setAuditionDate(String auditionDate) {
        this.auditionDate = auditionDate;
    }

    public String getAuditionTitle() {
        return AuditionTitle;
    }

    public void setAuditionTitle(String auditionTitle) {
        AuditionTitle = auditionTitle;
    }

    public String getAuditionDescription() {
        return AuditionDescription;
    }

    public void setAuditionDescription(String auditionDescription) {
        AuditionDescription = auditionDescription;
    }

    public String getAudiotionImage() {
        return AudiotionImage;
    }

    public void setAudiotionImage(String audiotionImage) {
        AudiotionImage = audiotionImage;
    }

    String AuditionTitle,AuditionDescription,AudiotionImage;
}
