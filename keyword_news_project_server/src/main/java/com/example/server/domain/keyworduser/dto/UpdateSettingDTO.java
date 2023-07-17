package com.example.server.domain.keyworduser.dto;

import com.example.server.domain.keyworduser.UpdateType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSettingDTO {
    @NotNull
    private String userToken;
    @NotNull
    private UpdateType updateType;
    @NotNull
    private String updateValue;
}
