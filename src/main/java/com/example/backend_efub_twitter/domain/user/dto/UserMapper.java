package com.example.backend_efub_twitter.domain.user.dto;

import com.example.backend_efub_twitter.domain.profiile.domain.ProfileMapper;
import com.example.backend_efub_twitter.domain.profiile.domain.ProfileResDto;
import com.example.backend_efub_twitter.domain.user.entity.User;
import com.example.backend_efub_twitter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    public UserResDto.Response toResponseDto(User entity){
        if (entity == null){
            return null;
        }

        ProfileResDto.Response profileResDto = profileMapper.toResponseDto(entity.getProfile());

        return UserResDto.Response.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .fullName(entity.getFullName())
                .profile(profileResDto)
                .build();
    }
}
