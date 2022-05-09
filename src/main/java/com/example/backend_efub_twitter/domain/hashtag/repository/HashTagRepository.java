package com.example.backend_efub_twitter.domain.hashtag.repository;

import com.example.backend_efub_twitter.domain.hashtag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HashTagRepository extends JpaRepository<HashTag, UUID> {
    Optional<HashTag> findByKeyword(String keyword);
}
