package com.example.viktornyblom.sqlitetest;

public class DbItem {
    private int id;
    private String feedNumber;
    private String title;
    private String desc;
    private String image;

    public DbItem(int pId, String pTitle, String pDesc, String pImage, String pFeedNumber) {
        id = pId;
        title = pTitle;
        desc = pDesc;
        image = pImage;
        feedNumber = pFeedNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        id = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String pDesc) {
        desc = pDesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String pImage) {
        image = pImage;
    }

    public String getFeedNumber() {
        return feedNumber;
    }

    public void setFeedNumber(String pFeedNumber) {
        feedNumber = pFeedNumber;
    }
}
