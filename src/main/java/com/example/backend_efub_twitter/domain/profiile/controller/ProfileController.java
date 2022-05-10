package com.example.backend_efub_twitter.domain.profiile.controller;

import com.example.backend_efub_twitter.domain.profiile.domain.ProfileModifyReqDto;
import com.example.backend_efub_twitter.domain.profiile.domain.ProfileModifyResDto;
import com.example.backend_efub_twitter.domain.profiile.domain.ProfileResDto;
import com.example.backend_efub_twitter.domain.profiile.service.ProfileService;
import com.example.backend_efub_twitter.global.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileService profileService;

	@GetMapping("/")
	public ResponseEntity<ProfileResDto> getProfile(
		@AuthenticationPrincipal User user){

		System.out.println(user.getFullName());
		ProfileResDto profileResDto = profileService.getProfile(user);
		return ResponseEntity
			.ok()
			.body(profileResDto);
	}

	@PostMapping("/modify")
	public ResponseEntity<ProfileModifyResDto> modifyProfile(
		@AuthenticationPrincipal User user,
		@RequestBody ProfileModifyReqDto profileModifyReqDto) {

		ProfileModifyResDto profileModifyResDto = profileService.modifyProfile(user, profileModifyReqDto);
		return ResponseEntity
			.ok()
			.body(profileModifyResDto);

	}
}
