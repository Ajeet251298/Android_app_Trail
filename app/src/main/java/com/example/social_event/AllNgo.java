package com.example.social_event;

public class AllNgo {
    private String name;
    private int imageID;
    private String des;
    private String url;

    public AllNgo(String name, int imageID,String des,String url) {
        this.name = name;
        this.imageID = imageID;
        this.des = des;
        this.url =url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
