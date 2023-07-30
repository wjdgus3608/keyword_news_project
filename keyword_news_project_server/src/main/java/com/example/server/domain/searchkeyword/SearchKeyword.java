package com.example.server.domain.searchkeyword;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

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

    public void addContainKeyword(String keyword){
        if(!containKeywords.contains(keyword))
            containKeywords.add(keyword);
    }
    public void addExcludeKeyword(String keyword){
        if(!excludeKeywords.contains(keyword))
            excludeKeywords.add(keyword);
    }
    public void removeContainKeyword(String keyword){
        containKeywords.remove(keyword);
    }
    public void removeExcludeKeyword(String keyword){
            excludeKeywords.remove(keyword);
    }
}
