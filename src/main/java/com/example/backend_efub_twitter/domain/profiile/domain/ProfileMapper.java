package com.example.backend_efub_twitter.domain.profiile.domain;

import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import com.example.backend_efub_twitter.domain.profiile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileMapper {
    private final ProfileRepository profileRepository;

    public ProfileResDto.Response toResponseDto(Profile entity){
        if (entity == null){
            return null;
        }

        return ProfileResDto.Response.builder()
                .nickname(entity.getNickname())
                .bio(entity.getBio())
                .build();
    }
}
