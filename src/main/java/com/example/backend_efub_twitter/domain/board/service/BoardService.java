package com.example.backend_efub_twitter.domain.board.service;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.board.exception.BoardNotFoundException;
import com.example.backend_efub_twitter.domain.board.repository.BoardRepository;
import com.example.backend_efub_twitter.domain.hashtag.repository.HashTagRepository;
import com.example.backend_efub_twitter.domain.user.exception.UserNotFoundException;
import com.example.backend_efub_twitter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final HashTagRepository hashTagRepository;

    @Transactional
    public Board save(Board board) {
        userRepository.findById(board.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id" + board.getUser().getId()));
        board.getBoardHashTags().forEach((boardHashTag) -> {
            this.hashTagRepository.save(boardHashTag.getHashTag());
            board.addBoardHashTag(boardHashTag);
        });
        return boardRepository.save(board);
    }

    @Transactional
    public void delete(UUID id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(board);
    }

    @Transactional(readOnly = true)
    public Board findById(UUID id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<Board> findAll(Specification<Board> spec) {
        return boardRepository.findAll(spec);
    }
}
