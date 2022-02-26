package com.mindtree.bookyourmeal.modules.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
	private int roleId;
	private String roleName;
	private LocalDateTime localDateTime= LocalDateTime.now();

	public Role() {
	}

	public Role(int roleId, String roleName, LocalDateTime localDateTime) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.localDateTime = LocalDateTime.now();
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", localDateTime=" + localDateTime + "]";
	}

}
