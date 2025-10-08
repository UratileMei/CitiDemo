package com.example.citidemo;

public class Announcement {
    private String id;
    private String title;
    private String description;
    private String category;
    private long timestamp;
    private String imageUrl;
    private boolean isUrgent;

    // Required empty constructor for Firestore
    public Announcement() {}

    public Announcement(String title, String description, String category, long timestamp) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.timestamp = timestamp;
        this.isUrgent = false;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }
}