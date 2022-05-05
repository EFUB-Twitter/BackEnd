package com.example.backend_efub_twitter.domain.user.entity;

import com.example.backend_efub_twitter.global.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 16)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @JsonIgnore
    private String password;

    /* 프로필 객체 생성 후 연결해주세요
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(nullable = true)
    private Profile profile;

    @Builder
    public User(UUID id, String fullName, String email, Boolean emailVerified, String password,
                String providerId, Profile profile) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.emailVerified = emailVerified;
        this.password = password;
        this.profile = profile;
    }
*/


}
