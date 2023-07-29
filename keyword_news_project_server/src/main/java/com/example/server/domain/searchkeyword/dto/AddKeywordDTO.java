package com.example.server.domain.searchkeyword.dto;


import com.example.server.domain.searchkeyword.SearchKeyword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddKeywordDTO {
    @NotNull
    private String keyword;
    @NotNull
    private String ownerId;

    public SearchKeyword toEntity(){
        return SearchKeyword.builder()
                .keyword(this.keyword)
                .ownerId(this.ownerId)
                .build();
    }
}
