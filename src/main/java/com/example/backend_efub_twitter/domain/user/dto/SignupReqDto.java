package com.example.backend_efub_twitter.domain.user.dto;

import com.example.backend_efub_twitter.global.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Getter
public class SignupReqDto {
	private String email;
	private String password;
	private String fullName;

	@Builder
	public SignupReqDto(String email, String password, String fullName){
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	public void encryptPassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(password);
	}

	public User toEntity(SignupReqDto signupReqDto){
		return User.builder()
			.email(signupReqDto.getEmail())
			.password(signupReqDto.getPassword())
			.fullName(signupReqDto.getFullName())
			.build();
	}
}