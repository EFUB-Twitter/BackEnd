package com.example.backend_efub_twitter.domain.user.repository;

import com.example.backend_efub_twitter.global.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
}