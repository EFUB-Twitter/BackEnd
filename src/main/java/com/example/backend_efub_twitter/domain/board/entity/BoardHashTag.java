package com.example.backend_efub_twitter.domain.board.entity;

import com.example.backend_efub_twitter.domain.hashtag.entity.HashTag;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class BoardHashTag {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    @Setter
    @ManyToOne
    @JoinColumn
    private Board board;

    @ManyToOne
    @JoinColumn
    private HashTag hashTag;

    @Builder
    public BoardHashTag(Board board, HashTag hashTag){
        this.board = board;
        this.hashTag = hashTag;
    }

}
