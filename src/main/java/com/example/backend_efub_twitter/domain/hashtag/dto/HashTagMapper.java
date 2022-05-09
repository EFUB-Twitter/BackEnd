package com.example.backend_efub_twitter.domain.hashtag.dto;

import com.example.backend_efub_twitter.domain.hashtag.entity.HashTag;
import com.example.backend_efub_twitter.domain.hashtag.repository.HashTagRepository;
import com.example.backend_efub_twitter.global.exception.ErrorCode;
import com.example.backend_efub_twitter.global.exception.GlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class HashTagMapper {
    private final HashTagRepository hashTagRepository;

    public HashTag createRequestDtoToEntity(HashTagDto.HashTagCreateDto requestDto){
        return HashTag.builder()
                .keyword(requestDto.getKeyword())
                .build();
    }

    public HashTagDto.HashTagResponseDto fromEntity(HashTag entity){
        return HashTagDto.HashTagResponseDto.builder()
                .id(entity.getId())
                .keyword(entity.getKeyword())
                .build();
    }

    public HashTag stringToEntity(String keyword){
        Optional<HashTag> hashTagOptional = hashTagRepository.findByKeyword(keyword);

        if (hashTagOptional.isEmpty()){
            return HashTag.builder()
                    .keyword(keyword)
                    .build();
        }

        return hashTagOptional.get();
    }

    //public String fromEntity(HashTag entity) {return entity.getKeyword();}
}
