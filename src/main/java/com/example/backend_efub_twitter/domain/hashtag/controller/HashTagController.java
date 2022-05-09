package com.example.backend_efub_twitter.domain.hashtag.controller;

import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagDto;
import com.example.backend_efub_twitter.domain.hashtag.dto.HashTagMapper;
import com.example.backend_efub_twitter.domain.hashtag.entity.HashTag;
import com.example.backend_efub_twitter.domain.hashtag.service.HashTagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hashtags")
public class HashTagController {

    final private HashTagService hashTagService;
    final private HashTagMapper hashTagMapper;

    public HashTagController (HashTagService hashTagService, HashTagMapper hashTagMapper){
        this.hashTagService = hashTagService;
        this.hashTagMapper = hashTagMapper;
    }

    @GetMapping
    public ResponseEntity<Page<HashTagDto.HashTagResponseDto>> getList(
            @PageableDefault(
                    sort = {"keyword"},
                    direction = Sort.Direction.ASC
            ) final Pageable pageable){
        Page<HashTag> result = hashTagService.findAll(pageable);

        return ResponseEntity
                .ok()
                .body(result.map(hashTagMapper::fromEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashTagDto.HashTagResponseDto> getDetail(
            @PathVariable UUID id) {
        HashTag entity = hashTagService.findById(id);

        return ResponseEntity
                .ok()
                .body(hashTagMapper.fromEntity(entity));
    }
}
