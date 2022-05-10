package com.example.backend_efub_twitter.domain.profiile.repository;

import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Boolean existsByNickname(String nickname);
}
