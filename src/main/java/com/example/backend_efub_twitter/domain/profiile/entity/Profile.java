package com.example.backend_efub_twitter.domain.profiile.entity;

import com.example.backend_efub_twitter.domain.UploadedFile.entity.UploadedFile;
import com.example.backend_efub_twitter.global.user.entity.User;
import com.example.backend_efub_twitter.global.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Profile extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long profileId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "profile_file_id", nullable = true)
	private UploadedFile uploadedFile;

	@NotNull
	@Column(unique = true, nullable = false)
	private String nickname;

	@Builder
	public Profile(String nickname){
		this.nickname = nickname;
	}

}
