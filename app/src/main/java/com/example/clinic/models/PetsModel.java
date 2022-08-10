package com.example.clinic.models;

public class PetsModel {

    public String imageUrl;
    public String title;
    public String contentUrl;
    public String dateAdded;

    public PetsModel(String imageUrl, String title, String contentUrl, String dateAdded) {
        this.contentUrl = contentUrl;
        this.dateAdded = dateAdded;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
