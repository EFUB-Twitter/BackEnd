package com.example.backend_efub_twitter.global.user.domain;

import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
public class UserResDto {

	private UUID id;
	private String email;
	private String fullName;
	private Profile profile;

}