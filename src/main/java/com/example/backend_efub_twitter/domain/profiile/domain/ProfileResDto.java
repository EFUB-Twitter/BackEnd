package com.example.backend_efub_twitter.domain.profiile.domain;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ProfileResDto {
	String nickname;
	String bio;

	@Builder
	public ProfileResDto(Profile profile){
		this.nickname = profile.getNickname();
		this.bio = profile.getBio();
	}

	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private String nickname;
		private String bio;
	}
}
