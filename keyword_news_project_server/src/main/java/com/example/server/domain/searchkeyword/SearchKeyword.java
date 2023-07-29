package com.example.server.domain.searchkeyword;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SearchKeyword {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String keyword;
    @Column(nullable = false)
    private String ownerId;
    @Column
    private List<String> containKeywords = new ArrayList<>();
    @Column
    private List<String> excludeKeywords = new ArrayList<>();
}
