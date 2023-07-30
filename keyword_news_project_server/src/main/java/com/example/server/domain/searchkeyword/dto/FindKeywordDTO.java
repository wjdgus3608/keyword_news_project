package com.example.server.domain.searchkeyword.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindKeywordDTO {
    @NotNull
    private String keyword;
    @NotNull
    private String ownerId;
}
