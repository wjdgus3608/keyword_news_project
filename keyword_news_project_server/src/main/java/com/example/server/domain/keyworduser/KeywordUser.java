package com.example.server.domain.keyworduser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.Id;
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

    public static KeywordUser generateUser(){
        return KeywordUser.builder()
                .userToken(UUID.randomUUID().toString())
                .build();
    }

}
