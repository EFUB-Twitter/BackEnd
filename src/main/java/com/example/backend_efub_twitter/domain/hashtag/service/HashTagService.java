package com.example.backend_efub_twitter.domain.hashtag.service;

import com.example.backend_efub_twitter.domain.hashtag.entity.HashTag;
import com.example.backend_efub_twitter.domain.hashtag.exception.HashTagAlreadyExistsException;
import com.example.backend_efub_twitter.domain.hashtag.exception.HashTagNotFoundException;
import com.example.backend_efub_twitter.domain.hashtag.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HashTagService {

    private final HashTagRepository hashTagRepository;

    @Transactional
    public HashTag save(HashTag hashTag){
        hashTagRepository.findByKeyword(hashTag.getKeyword())
                .ifPresent((existingHashTag)->{
                    throw new HashTagAlreadyExistsException("Hash Tag already exists with specified keyword");
                });
        return hashTagRepository.save(hashTag);
    }

    @Transactional(readOnly = true)
    public HashTag findById(UUID id){
        return (HashTag) hashTagRepository.findById(id)
                .orElseThrow(() -> new HashTagNotFoundException("HashTag not found with id = "+id));
    }

    @Transactional(readOnly = true)
    public HashTag findByKeyword(String keyword){
        return hashTagRepository.findByKeyword(keyword)
                .orElseThrow(() -> new HashTagNotFoundException("HashTag not found with keyword =  "+keyword));
    }

    @Transactional(readOnly = true)
    public Page<HashTag> findAll(Pageable pageable) {
        return hashTagRepository.findAll(pageable);
    }
}
