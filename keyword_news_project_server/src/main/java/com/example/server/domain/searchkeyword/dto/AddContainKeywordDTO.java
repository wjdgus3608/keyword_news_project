package com.example.server.domain.searchkeyword.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddContainKeywordDTO {
    @NotNull
    private String keyword;
    @NotNull
    private String ownerId;
    @NotNull
    private String addContainKeyword;

    public FindKeywordDTO toFindDTO(){
        return FindKeywordDTO.builder()
                .keyword(this.keyword)
                .ownerId(this.ownerId)
                .build();
    }
}
