package com.example.backend_efub_twitter.domain.profiile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileModifyReqDto {
	private String nickname;
	private String bio;
}
