package com.example.backend_efub_twitter.domain.profiile.service;

import com.example.backend_efub_twitter.domain.profiile.domain.ProfileModifyReqDto;
import com.example.backend_efub_twitter.domain.profiile.domain.ProfileResDto;
import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import com.example.backend_efub_twitter.domain.profiile.exception.DuplicateNicknameException;
import com.example.backend_efub_twitter.domain.profiile.repository.ProfileRepository;
import com.example.backend_efub_twitter.domain.user.entity.User;
import com.example.backend_efub_twitter.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

	private final ProfileRepository profileRepository;

	@Transactional(readOnly = true)
	public ProfileResDto getProfile(String nickname) {

		Profile profile = profileRepository.findByNickname(nickname)
			.orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));

		User user = profile.getUser();

		return ProfileResDto.builder()
			.profile(profile)
			.build();
	}

	public ProfileResDto modifyProfile(String nickname, ProfileModifyReqDto profileModifyReqDto) {

		if (profileRepository.existsByNickname(profileModifyReqDto.getNickname()))
			throw new DuplicateNicknameException("이미 존재하는 닉네임입니다.");

		Profile profile = profileRepository.findByNickname(nickname)
			.orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));

		User user = profile.getUser();

		profile.updateProfile(profileModifyReqDto);
		user.setProfile(profile);

		profileRepository.save(profile);

		return ProfileResDto.builder()
			.profile(profile)
			.build();
	}
}
