package com.example.backend_efub_twitter.global.config;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.board.entity.BoardHashTag;
import com.example.backend_efub_twitter.domain.board.repository.BoardHashTagRepository;
import com.example.backend_efub_twitter.domain.board.repository.BoardRepository;
import com.example.backend_efub_twitter.domain.hashtag.entity.HashTag;
import com.example.backend_efub_twitter.domain.hashtag.repository.HashTagRepository;
import com.example.backend_efub_twitter.domain.user.entity.User;
import com.example.backend_efub_twitter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardHashTagRepository boardHashTagRepository;
    private final HashTagRepository hashTagRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createInitialContents() {
        String[] hashTagKeywords = {"spring", "java", "python", "react", "vue.js", "javascript", "알고리즘", "일상", "취업"};

        for (String keyword : hashTagKeywords){
            HashTag hashTag = new HashTag(keyword);
            hashTagRepository.save(hashTag);
        }
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        createInitialContents();

        for (int i =1; i<10;i++){
            User testUser = User.builder()
                    .email("testUser"+i+"@ewhain.net")
                    .fullName("김이화"+i)
                    .password(passwordEncoder.encode("efub1886"))
                    .build();
            testUser = userRepository.save(testUser);

            Board board = Board.builder()
                    .user(testUser)
                    .description("이펍 트위터 게시글"+i)
                    .build();

            List<String> hashTagKeywords = Arrays.asList("spring", "알고리즘");
            for (String hashTagKeyword : hashTagKeywords){
                HashTag hashTag = hashTagRepository.findByKeyword(hashTagKeyword).get();
                BoardHashTag boardHashTag = new BoardHashTag(board, hashTag);
                board.getBoardHashTags().add(boardHashTag);
            }

            boardRepository.save(board);
        }

        alreadySetup = true;
    }
}
