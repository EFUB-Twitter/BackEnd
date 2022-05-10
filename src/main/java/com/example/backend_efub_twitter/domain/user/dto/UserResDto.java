package com.example.backend_efub_twitter.domain.user.dto;

import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import lombok.*;

import java.util.UUID;

public class UserResDto {
	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private UUID id;
		private String email;
		private String fullName;
		private Profile profile;
	}

}