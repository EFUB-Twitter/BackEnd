package com.example.backend_efub_twitter.domain.profiile.controller;

import com.example.backend_efub_twitter.domain.profiile.domain.ProfileModifyReqDto;
import com.example.backend_efub_twitter.domain.profiile.domain.ProfileModifyResDto;
import com.example.backend_efub_twitter.domain.profiile.domain.ProfileResDto;
import com.example.backend_efub_twitter.domain.profiile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class UserProfileController {

	private final ProfileService profileService;

	@GetMapping("/{nickname}")
	public ResponseEntity<ProfileResDto> getProfile(
		@PathVariable String nickname){

		ProfileResDto profileResDto = profileService.getProfile(nickname);
		return ResponseEntity
			.ok()
			.body(profileResDto);
	}

	@PostMapping("/modify/{nickname}")
	public ResponseEntity<ProfileModifyResDto> modifyProfile(
		@PathVariable String nickname,
		@RequestBody ProfileModifyReqDto profileModifyReqDto) {

		ProfileModifyResDto profileModifyResDto = profileService.modifyProfile(nickname, profileModifyReqDto);
		return ResponseEntity
			.ok()
			.body(profileModifyResDto);

	}
}
