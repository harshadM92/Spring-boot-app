package com.facebook.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {

	@Id
	@Column(name="role_id")
	private String roleId;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="role_enabled")
	private Integer roleEnabled;

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleEnabled
	 */
	public Integer getRoleEnabled() {
		return roleEnabled;
	}

	/**
	 * @param roleEnabled the roleEnabled to set
	 */
	public void setRoleEnabled(Integer roleEnabled) {
		this.roleEnabled = roleEnabled;
	}
	
}

