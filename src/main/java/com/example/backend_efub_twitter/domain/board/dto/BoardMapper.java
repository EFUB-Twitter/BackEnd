package com.example.backend_efub_twitter.domain.board.dto;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.board.entity.BoardHashTag;
import com.example.backend_efub_twitter.domain.board.repository.BoardRepository;
import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagDto;
import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagMapper;
import com.example.backend_efub_twitter.domain.hashtag.entity.HashTag;
import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import com.example.backend_efub_twitter.domain.profiile.repository.ProfileRepository;
import com.example.backend_efub_twitter.domain.user.dto.UserMapper;
import com.example.backend_efub_twitter.domain.user.dto.UserResDto;
import com.example.backend_efub_twitter.domain.user.entity.User;
import com.example.backend_efub_twitter.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoardMapper {

    private final BoardRepository boardRepository;
    private final ProfileRepository profileRepository;
    private final HashTagMapper hashTagMapper;
    private final UserMapper userMapper;

    public Board toEntity(BoardDto.CreateRequest requestDto){
        Profile profile = profileRepository.findByNickname(requestDto.getNickname())
                .orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));

        User user = profile.getUser();

        Board board = Board.builder()
                .user(user)
                .description(requestDto.getDescription())
                .build();
        for (String hashTagKeyword: requestDto.getHashTags()){
            board.addBoardHashTag(
                    new BoardHashTag(
                            board,
                            hashTagMapper.stringToEntity(hashTagKeyword)));
        }

        return board;
    }

    public BoardDto.Response toResponseDto(Board entity){
        if (entity == null){
            return null;
        }

        Set<HashTagDto.HashTagResponseDto> hashTags = entity.getBoardHashTags()
                .stream()
                .map(BoardHashTag::getHashTag)
                .map(hashTagMapper::fromEntity)
                .collect(Collectors.toSet());

        UserResDto.Response userResDto = userMapper.toResponseDto(entity.getUser());

        return BoardDto.Response.builder()
                .id(entity.getId())
                .userResDto(userResDto)
                .description(entity.getDescription())
                .hashTags(hashTags)
                .boardCreateOn(entity.getCreatedOn())
                .build();
    }
}
