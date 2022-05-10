package com.example.backend_efub_twitter.domain.board.dto;

import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagDto;
import com.example.backend_efub_twitter.domain.user.dto.UserResDto;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class BoardDto {
    @ApiModel(value = "게시글 생성 DTO", description = "게시글 생성 정보")
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull
        @ApiModelProperty(value = "사용자 ID")
        private UUID userId;

        @NotEmpty
        @ApiModelProperty(value = "게시글 내용", example = "이펍 게시글 내용")
        private String description;
        @NotNull
        @ApiModelProperty(value = "게시글 키워드", example = "[\"java\",\"spring\",\"일상\",\"알고리즘\"]")
        private Set<String> hashTags;
    }

    @Getter
    @AllArgsConstructor
    public static class DeleteRequest {
        @NotBlank
        @ApiModelProperty(value = "게시글 ID")
        private UUID id;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        @ApiModelProperty(value = "게시글 ID")
        private UUID id;
        @ApiModelProperty(value = "사용자 정보")
        private UserResDto.Response userResDto;
        @ApiModelProperty(value = "게시글 내용")
        private String description;
        private Set<HashTagDto.HashTagResponseDto> hashTags;
        private LocalDateTime boardCreateOn;
    }
}
