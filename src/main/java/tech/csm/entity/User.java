package tech.csm.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "password")
	private String password;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_phone")
	private String userPhone;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "photo_path")
	private String photoPath;

	@Column(name = "user_role")
	private String userRole;

	@Column(name = "permit")
	private Boolean permit;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "created_on", insertable = false, updatable = false)
	private LocalDateTime createdOn;

	@Column(name = "updated_on", insertable = false, updatable = false)
	private LocalDateTime updatedOn;

	@Column(name = "deleted_flag", insertable = false)
	private Boolean deletedFlag;
}
