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
import com.example.backend_efub_twitter.domain.user.entity.User;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/boards")
@Api(tags = {"게시글 API"})
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final HashTagService hashTagService;
    private final HashTagMapper hashTagMapper;

    @PostMapping
    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.")
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
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    public ResponseEntity<BoardDto.DeleteRequest> deleteBoard(
            @ApiIgnore @AuthenticationPrincipal User user,@ApiParam(value = "게시글 ID", required = true) @PathVariable UUID id){
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
    @ApiOperation(value = "게시글 상세 조회", notes = "게시글 하나에 대한 상세 정보를 조회한다.")
    public ResponseEntity<BoardDto.Response> getBoard(
            @ApiIgnore @AuthenticationPrincipal User user,
            @ApiParam(value = "게시글 ID", required = true) @PathVariable UUID id
    ){
        Board entity = boardService.findById(id);
        return ResponseEntity
                .ok()
                .body(boardMapper.toResponseDto(entity));
    }

    @GetMapping
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 조회한다.")
    public ResponseEntity<Page<BoardDto.Response>> getBoardList(
            @ApiParam(value = "게시글 등록 사용자", example = "0sk24do0-dsb2-d2e3-njt7-skt81bfse0") @RequestParam(required = false) UUID userID,
            @ApiParam(value = "해시태그 키워드 (배열, ',' 로 구분)", example = "java, 알고리즘") @RequestParam(required = false) List<String> hashTagKeyword,
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
