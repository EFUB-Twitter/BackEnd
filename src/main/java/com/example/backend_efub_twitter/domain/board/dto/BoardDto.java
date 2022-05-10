package com.example.backend_efub_twitter.domain.board.dto;

import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagDto;
import com.example.backend_efub_twitter.domain.user.dto.UserResDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class BoardDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull
        private UUID userId;
        @NotEmpty
        private String description;
        @NotNull
        private Set<String> hashTags;
    }

    @Getter
    @AllArgsConstructor
    public static class DeleteRequest {
        @NotBlank
        private UUID id;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private UUID id;
        private UserResDto userResDto;
        private String description;
        private Set<HashTagDto.HashTagResponseDto> hashTags;
        private LocalDateTime boardCreateOn;
    }
}
