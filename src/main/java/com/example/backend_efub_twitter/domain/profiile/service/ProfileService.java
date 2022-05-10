package com.example.backend_efub_twitter.domain.profiile.service;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.board.repository.BoardRepository;
import com.example.backend_efub_twitter.domain.profiile.domain.ProfileModifyReqDto;
import com.example.backend_efub_twitter.domain.profiile.domain.ProfileModifyResDto;
import com.example.backend_efub_twitter.domain.profiile.domain.ProfileResDto;
import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import com.example.backend_efub_twitter.domain.profiile.exception.DuplicateNicknameException;
import com.example.backend_efub_twitter.domain.profiile.repository.ProfileRepository;
import com.example.backend_efub_twitter.domain.user.entity.User;
import com.example.backend_efub_twitter.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

	private final ProfileRepository profileRepository;
	private final BoardRepository boardRepository;

	@Transactional(readOnly = true)
	public ProfileResDto getProfile(String nickname) {

		Profile profile = profileRepository.findByNickname(nickname)
			.orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));

		User user = profile.getUser();

		Optional<Board> optionalBoardList = boardRepository.findByUser_Id(user.getId());
		List<Board> boardList = optionalBoardList.stream().collect(Collectors.toList());

		return ProfileResDto.builder()
			.profile(profile)
			.boardList(boardList)
			.build();
	}

	public ProfileModifyResDto modifyProfile(String nickname, ProfileModifyReqDto profileModifyReqDto) {

		if (profileRepository.existsByNickname(profileModifyReqDto.getNickname()))
			throw new DuplicateNicknameException("이미 존재하는 닉네임입니다.");

		Profile profile = profileRepository.findByNickname(nickname)
			.orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));

		User user = profile.getUser();

		profile.updateProfile(profileModifyReqDto);
		user.setProfile(profile);

		profileRepository.save(profile);

		return ProfileModifyResDto.builder()
			.fullname(user.getFullName())
			.nickname(profile.getNickname())
			.bio(profile.getBio())
			.build();
	}
}
