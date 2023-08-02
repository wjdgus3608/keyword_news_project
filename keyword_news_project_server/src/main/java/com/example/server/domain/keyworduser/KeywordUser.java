package com.example.server.domain.keyworduser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KeywordUser {
    @Id
    private String userToken;
    @Column
    private String fcmToken;
    @Column
    private boolean isVip;
    @Column
    private boolean isAlarmAllowed = true;
    @Column
    private String fetchTime;
    @Column
    private String fetchInterval;
    @Column
    private boolean isActive;

    @PrePersist
    public void prePersist() {
        this.fetchTime = this.fetchTime == null ? "08001800" : this.fetchTime;
        this.fetchInterval = this.fetchInterval == null ? "5" : this.fetchInterval;
    }

    public static KeywordUser generateUser(){
        return KeywordUser.builder()
                .userToken(UUID.randomUUID().toString())
                .build();
    }

}
