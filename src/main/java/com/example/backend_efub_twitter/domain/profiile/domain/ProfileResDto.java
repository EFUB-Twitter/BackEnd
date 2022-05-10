package com.example.backend_efub_twitter.domain.profiile.domain;

import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
