package com.example.keyword_project.domain;

import java.io.Serializable;

public class KeywordUser implements Serializable {
    private String userToken;
    private String fcmToken;
    private boolean isVip;
    private boolean isAlarmAllowed;
    private String fetchTime;
    private String fetchInterval;
    private boolean isActive;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public boolean isAlarmAllowed() {
        return isAlarmAllowed;
    }

    public void setAlarmAllowed(boolean alarmAllowed) {
        isAlarmAllowed = alarmAllowed;
    }

    public String getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(String fetchTime) {
        this.fetchTime = fetchTime;
    }

    public String getFetchInterval() {
        return fetchInterval;
    }

    public void setFetchInterval(String fetchInterval) {
        this.fetchInterval = fetchInterval;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
