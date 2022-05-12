package com.example.backend_efub_twitter.domain.user.controller;

import com.example.backend_efub_twitter.domain.user.dto.LoginReqDto;
import com.example.backend_efub_twitter.domain.user.dto.LoginResDto;
import com.example.backend_efub_twitter.domain.user.dto.SignupReqDto;
import com.example.backend_efub_twitter.domain.user.exception.DuplicateUserException;
import com.example.backend_efub_twitter.domain.user.exception.UserNotFoundException;
import com.example.backend_efub_twitter.domain.user.service.UserService;
import com.example.backend_efub_twitter.global.config.JwtTokenProvider;
import com.example.backend_efub_twitter.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody SignupReqDto signupReqDto){
		if (userService.checkDuplicateUsers(signupReqDto))
			throw new DuplicateUserException("이미 존재하는 이메일입니다.");
		signupReqDto.encryptPassword(passwordEncoder);
		return userService.joinUser(signupReqDto);
	}

	@PostMapping("/login")
	public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws UserNotFoundException {
		User user = userService.findUser(loginReqDto);
		if (!passwordEncoder.matches(loginReqDto.getPassword(), user.getPassword())){
			throw new UserNotFoundException("잘못된 비밀번호입니다.");
		}
		String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
		LoginResDto loginResDto = LoginResDto.builder()
			.user(user)
			.token(token)
			.build();
		return loginResDto;
	}

}
