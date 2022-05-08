package com.example.backend_efub_twitter.domain.user.dto;

import com.example.backend_efub_twitter.global.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResDto {
	String email;
	String fullName;

	public LoginResDto(User user){
		this.email = user.getEmail();
		this.fullName = user.getFullName();
	}
}
