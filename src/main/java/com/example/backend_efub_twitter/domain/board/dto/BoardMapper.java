package com.example.backend_efub_twitter.domain.board.dto;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.board.entity.BoardHashTag;
import com.example.backend_efub_twitter.domain.board.repository.BoardRepository;
import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagDto;
import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagMapper;
import com.example.backend_efub_twitter.domain.hashtag.entity.HashTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoardMapper {

    private final BoardRepository boardRepository;
    private final HashTagMapper hashTagMapper;

    public Board toEntity(BoardDto.CreateRequest requestDto){
        Board board = Board.builder()
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
                .map(BoardHashTag::getBoard)
                .map(hashTagMapper::fromEntity)
                .collect(Collectors.toSet());

        return BoardDto.Response.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .hashTags(hashTags)
                .boardCreateOn(entity.getCreatedOn())
                .build();
    }
}
