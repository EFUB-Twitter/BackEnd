package com.example.backend_efub_twitter.domain.UploadedFile.entity;

import com.example.backend_efub_twitter.domain.user.entity.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class UploadedFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long uploadedFileId;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id", nullable = false)
	private User user;

	@NotNull
	private String filename;

	@NotNull
	private String filetype;

	@NotNull
	private String url;

	@NotNull
	private String size;

	@ColumnDefault("0")
	private Boolean isPrivate;

}
