package com.example.backend_efub_twitter.domain.user.service;


import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import com.example.backend_efub_twitter.domain.user.dto.LoginReqDto;
import com.example.backend_efub_twitter.domain.user.dto.SignupReqDto;
import com.example.backend_efub_twitter.global.user.entity.User;
import com.example.backend_efub_twitter.domain.user.exception.UserNotFoundException;
import com.example.backend_efub_twitter.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public ResponseEntity<Object> joinUser(SignupReqDto signupReqDto){
		User user = signupReqDto.toEntity(signupReqDto);
		userRepository.save(user);
		return ResponseEntity.ok(201);
	}

	@Transactional(readOnly = true)
	public User findUser(LoginReqDto loginReqDto) throws UserNotFoundException {
		return userRepository.findByEmail(loginReqDto.getEmail())
			.orElseThrow(() -> new UserNotFoundException("해당하는 유저가 없습니다."));
	}

}