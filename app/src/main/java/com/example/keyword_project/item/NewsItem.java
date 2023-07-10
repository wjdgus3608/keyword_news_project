package com.example.keyword_project.item;

public class NewsItem {
    private int image;
    private String time;
    private String title;
    private String text;



    private String urlString = "https://news.mtn.co.kr/news-detail/2023070309265394546";

    public NewsItem(int image, String time, String title, String text) {
        this.image = image;
        this.time = time;
        this.title = title;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }
}
