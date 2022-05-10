package com.example.backend_efub_twitter.domain.profiile.domain;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProfileResDto {
	String nickname;
	String bio;
	List<Board> boardList;

	@Builder
	public ProfileResDto(Profile profile, List<Board> boardList){
		this.nickname = profile.getNickname();
		this.bio = profile.getBio();
		this.boardList = boardList;
	}
}
