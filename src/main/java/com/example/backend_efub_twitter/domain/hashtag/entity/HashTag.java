package com.example.backend_efub_twitter.domain.hashtag.entity;

import com.example.backend_efub_twitter.domain.board.entity.BoardHashTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "keyword") })
public class HashTag {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    @Size(max=50)
    @NotEmpty
    private String keyword;

    @OneToMany(mappedBy = "hashTag", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardHashTag> boardHashTags = new HashSet<>();

    @Builder
    public HashTag(String keyword){
        this.keyword = keyword;
    }
}
