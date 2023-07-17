package com.example.server.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Builder
public class KeywordUser {
    @Id
    private String userToken;
    @Column(nullable = false)
    private String fcmToken;
    @ColumnDefault("false")
    private boolean isVip;
    @ColumnDefault("true")
    private boolean isAlarmAllowed;
    @ColumnDefault("'08001800'")
    private String fetchTime;
    @ColumnDefault("'5'")
    private String fetchInterval;
}
