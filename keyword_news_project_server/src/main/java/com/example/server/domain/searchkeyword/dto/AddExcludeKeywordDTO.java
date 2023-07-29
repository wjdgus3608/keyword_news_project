package com.example.server.domain.searchkeyword.dto;


import com.example.server.domain.searchkeyword.SearchKeyword;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddExcludeKeywordDTO {
    @NotNull
    private String keyword;
    @NotNull
    private String ownerId;
    @NotNull
    private String addExcludeKeyword;

    public FindKeywordDTO toFindDTO(){
        return FindKeywordDTO.builder()
                .keyword(this.keyword)
                .ownerId(this.ownerId)
                .build();
    }
}
