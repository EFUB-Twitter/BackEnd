package com.example.backend_efub_twitter.domain.board.entity;

import com.example.backend_efub_twitter.global.user.entity.User;
import com.example.backend_efub_twitter.global.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    /**
     * 사용자
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 게시글 내용
     */
    @Size(max = 150)
    @NotNull
    private String description;

    /**
     * 해시태그
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardHashTag> boardHashTags = new HashSet<>();

    @Builder
    public Board(User user, String description) {
        this.user = user;
        this.description = description;
    }

    public void addBoardHashTag(BoardHashTag boardHashTag){
        boardHashTag.setBoard(this);
        this.boardHashTags.add(boardHashTag);
    }
}
