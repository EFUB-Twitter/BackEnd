package com.example.backend_efub_twitter.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginReqDto {
	String Email;
	String password;
}