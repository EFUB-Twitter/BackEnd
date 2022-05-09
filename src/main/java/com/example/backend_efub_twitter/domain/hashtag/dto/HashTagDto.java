package com.example.backend_efub_twitter.domain.hashtag.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class HashTagDto {
    @Getter
    @Setter
    public static class HashTagCreateDto{
        @Size(max = 50)
        @NotEmpty
        private String keyword;
    }

    @Getter
    @Setter
    public static class HashTagIdCreateDto{
        private UUID id;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HashTagResponseDto{
        private UUID id;
        private String keyword;
    }
}
