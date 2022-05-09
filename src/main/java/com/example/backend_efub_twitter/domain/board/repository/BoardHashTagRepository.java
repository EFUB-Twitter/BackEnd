package com.example.backend_efub_twitter.domain.board.repository;

import com.example.backend_efub_twitter.domain.board.entity.BoardHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BoardHashTagRepository extends JpaRepository<BoardHashTag, UUID> {
    @Query("SELECT p FROM BoardHashTag p order by p.id DESC")
    List<BoardHashTag> findAllDesc();
}
