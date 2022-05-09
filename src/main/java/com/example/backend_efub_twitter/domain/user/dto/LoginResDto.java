package com.example.backend_efub_twitter.domain.user.dto;

import com.example.backend_efub_twitter.global.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDto {
	String email;
	String fullName;
	String token;

	@Builder
	public LoginResDto(User user, String token){
		this.email = user.getEmail();
		this.fullName = user.getFullName();
		this.token = token;
	}
}
