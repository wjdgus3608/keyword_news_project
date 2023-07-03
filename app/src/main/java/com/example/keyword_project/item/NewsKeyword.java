package com.example.keyword_project.item;

public class NewsKeyword {
    private String keyword;
    private boolean isClicked;

    public NewsKeyword(String keyword, boolean isClicked) {
        this.keyword = keyword;
        this.isClicked = isClicked;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
