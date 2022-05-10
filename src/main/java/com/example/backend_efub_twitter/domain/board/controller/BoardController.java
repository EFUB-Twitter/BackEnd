package com.example.backend_efub_twitter.domain.board.controller;

import com.example.backend_efub_twitter.domain.board.dto.BoardDto;
import com.example.backend_efub_twitter.domain.board.dto.BoardMapper;
import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.board.service.BoardService;
import com.example.backend_efub_twitter.domain.board.specification.BoardSearchCriteria;
import com.example.backend_efub_twitter.domain.board.specification.BoardSpecification;
import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagMapper;
import com.example.backend_efub_twitter.domain.hashtag.service.HashTagService;
import com.example.backend_efub_twitter.domain.user.exception.UserNotFoundException;
import com.example.backend_efub_twitter.global.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final HashTagService hashTagService;
    private final HashTagMapper hashTagMapper;

    @PostMapping
    public ResponseEntity<BoardDto.Response> createBoard(
            @Valid @RequestBody BoardDto.CreateRequest requestDto
    ){
        Board entity = boardService.save(
                boardMapper.toEntity(requestDto)
        );
        return ResponseEntity
                .created(
                        WebMvcLinkBuilder
                                .linkTo(BoardController.class)
                                .slash(entity.getId())
                                .toUri())
                .body(boardMapper.toResponseDto(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BoardDto.DeleteRequest> deleteBoard(
            @AuthenticationPrincipal User user, @PathVariable UUID id){
        Board board = boardService.findById(id);
        if (!user.getId().equals(board.getUser().getId())){
            throw new UserNotFoundException("유저가 일치하지 않습니다");
        }
        boardService.delete(id);
        return ResponseEntity
                .ok()
                .body(new BoardDto.DeleteRequest(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto.Response> getBoard(
            @AuthenticationPrincipal User user,
            @PathVariable UUID id
    ){
        Board entity = boardService.findById(id);
        return ResponseEntity
                .ok()
                .body(boardMapper.toResponseDto(entity));
    }

    @GetMapping
    public ResponseEntity<Page<BoardDto.Response>> getBoardList(
            @RequestParam(required = false) UUID userID,
            @RequestParam(required = false) List<String> hashTagKeyword,
            @PageableDefault(sort={"createdOn"}, direction = Sort.Direction.DESC) final Pageable pageable
            ){

        BoardSpecification spec = new BoardSpecification(
                BoardSearchCriteria.builder()
                        .userId(userID)
                        .hashTagKeyword(hashTagKeyword)
                        .build());
        Page<Board> result = boardService.findAll(spec, pageable);
        return ResponseEntity
                .ok()
                .body(result.map(boardMapper::toResponseDto));
    }

}
