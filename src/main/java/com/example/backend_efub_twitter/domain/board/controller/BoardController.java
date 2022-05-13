package com.example.backend_efub_twitter.domain.board.controller;

import com.example.backend_efub_twitter.domain.board.dto.BoardDto;
import com.example.backend_efub_twitter.domain.board.dto.BoardMapper;
import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.board.service.BoardService;
import com.example.backend_efub_twitter.domain.board.specification.BoardSearchCriteria;
import com.example.backend_efub_twitter.domain.board.specification.BoardSpecification;
import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagMapper;
import com.example.backend_efub_twitter.domain.hashtag.service.HashTagService;
import com.example.backend_efub_twitter.domain.user.dto.UserResDto;
import com.example.backend_efub_twitter.domain.user.exception.UserNotFoundException;
import com.example.backend_efub_twitter.domain.user.entity.User;
import com.example.backend_efub_twitter.domain.user.service.UserService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final HashTagService hashTagService;
    private final HashTagMapper hashTagMapper;
    private final UserService userService;

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
            @RequestParam(required = false) String nickname, @PathVariable UUID id){

        UserResDto.Response user = userService.getUserInfoByProfile(nickname);
        Board board = boardService.findById(id);

        if (!user.getId().equals(board.getUser().getId())){
            throw new UserNotFoundException("유저가 일치하지 않습니다");
        }
        boardService.delete(id);

        BoardDto.DeleteRequest deleteRequest = BoardDto.DeleteRequest.builder()
                .id(id)
                .message("게시물이 삭제되었습니다")
                .build();

        return ResponseEntity
                .ok()
                .body(deleteRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto.Response> getBoard(
            @RequestParam(required = false) String nickname,
            @PathVariable UUID id
    ){
        UserResDto.Response user = userService.getUserInfoByProfile(nickname);
        Board entity = boardService.findById(id);
        return ResponseEntity
                .ok()
                .body(boardMapper.toResponseDto(entity));
    }

    @GetMapping
    public ResponseEntity<List<BoardDto.Response>> getBoardList(
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) List<String> hashTagKeyword
            ){

        UserResDto.Response user = new UserResDto.Response();

        if (nickname != null){
            user = userService.getUserInfoByProfile(nickname);
        }

        BoardSpecification spec = new BoardSpecification(
                BoardSearchCriteria.builder()
                        .userId(user.getId())
                        .hashTagKeyword(hashTagKeyword)
                        .build());
        List<Board> result = boardService.findAll(spec);

        return ResponseEntity
                .ok()
                .body(result.stream().map(boardMapper::toResponseDto).collect(Collectors.toList()));
    }

}
