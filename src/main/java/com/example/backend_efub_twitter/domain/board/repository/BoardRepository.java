package com.example.backend_efub_twitter.domain.board.repository;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {
    Optional<Board> findByUser_Id(UUID uuid);
}
