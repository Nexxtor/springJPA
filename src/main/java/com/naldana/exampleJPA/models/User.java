package com.naldana.exampleJPA.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 65)
	@Column(name = "frist_name")
	private String firtsName;

	@Size(max = 65)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Email
	@Size(max = 100)
	@Column(unique = true)
	private String email;

	@NotNull
	@Size(max = 128)
	private String password;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private UserProfile userProfile;

	public User() {
	}

	public User(String firtsName, String lastName, String email, String password) {
		super();
		this.firtsName = firtsName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirtsName() {
		return firtsName;
	}

	public void setFirtsName(String firtsName) {
		this.firtsName = firtsName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firtsName=" + firtsName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

}
