package com.example.backend_efub_twitter.global.user.entity;

import com.example.backend_efub_twitter.domain.profiile.entity.Profile;
import com.example.backend_efub_twitter.domain.UploadedFile.entity.UploadedFile;
import com.example.backend_efub_twitter.global.config.Account;
import com.example.backend_efub_twitter.global.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 16)
	private UUID id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String fullName;

	@JsonIgnore
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private UploadedFile uploadedFileId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Profile profile;

	@Column
	private String role;

	@Builder
	public User(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.role = "USER";
	}

	public Account toAccount() {
		return Account.builder()
			.email(email)
			.role(role)
			.password(password)
			.build();
	}

	public void encryptPassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(password);
	}

}
