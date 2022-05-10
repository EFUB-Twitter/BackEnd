package com.example.backend_efub_twitter.domain.profiile.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileModifyResDto {
	private String fullname;
	private String nickname;
	private String bio;

	@Builder
	public ProfileModifyResDto(String fullname, String nickname, String bio){
		this.fullname = fullname;
		this.nickname = nickname;
		this.bio = bio;
	}
}
