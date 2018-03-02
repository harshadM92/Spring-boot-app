package com.facebook.DAO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_detail")
public class UserDetail {

	@Id
	@Column(name="user_detail_id")
	private String userDetailId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="role_id")
	private UserRole role;
	
	/**
	 * @return the userDetailId
	 */
	public String getUserDetailId() {
		return userDetailId;
	}

	/**
	 * @param userDetailId the userDetailId to set
	 */
	public void setUserDetailId(String userDetailId) {
		this.userDetailId = userDetailId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}
	
}
