package com.example.backend_efub_twitter.domain.board.service;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.board.exception.BoardNotFoundException;
import com.example.backend_efub_twitter.domain.board.repository.BoardRepository;
import com.example.backend_efub_twitter.domain.hashtag.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final HashTagRepository hashTagRepository;

    @Transactional
    public Board save(Board board){
        board.getBoardHashTags().forEach((boardHashTag)->{
            this.hashTagRepository.save(boardHashTag.getHashTag());
            board.addBoardHashTag(boardHashTag);
        });
        return boardRepository.save(board);
    }

    @Transactional
    public void delete(UUID id){
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> new BoardNotFoundException("Board not found with id"+ id));
        boardRepository.delete(board);
    }

    @Transactional(readOnly = true)
    public Board findById(UUID id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("Board not found with id=" + id));
    }

    @Transactional(readOnly = true)
    public Page<Board> findAll(Specification<Board> spec, Pageable pageable){
        return boardRepository.findAll(spec, pageable);
    }
}
