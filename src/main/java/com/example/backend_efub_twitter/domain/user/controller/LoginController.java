package com.example.backend_efub_twitter.domain.user.controller;

import com.example.backend_efub_twitter.domain.user.dto.LoginReqDto;
import com.example.backend_efub_twitter.domain.user.dto.SignupReqDto;
import com.example.backend_efub_twitter.global.user.entity.User;
import com.example.backend_efub_twitter.domain.user.exception.UserNotFoundException;
import com.example.backend_efub_twitter.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody SignupReqDto signupReqDto){
		//	String encoded = passwordEncoder.encode(signupReqDto.getPassword());
		//	signupReqDto.setPassword(encoded);
		return userService.joinUser(signupReqDto);
	}

	@GetMapping("/login")
	public User login(@RequestBody LoginReqDto loginReqDto) throws UserNotFoundException {
		/* TODO : passwordEncoder로 확인
		if (!passwordEncoder.matches(loginReqDto.getPassword(), user.getPassword())){
			throw new NoSuchUserException("잘못된 비밀번호입니다.");
		}
		return jwtTokenProvider.createToken();
		*/
		return userService.findUser(loginReqDto);
	}

}
