package com.mindtree.bookyourmeal.modules.core.dto;

import java.time.LocalDateTime;

public class RoleDto {
	private int roleId;
	private String roleName;
	private LocalDateTime localDateTime= LocalDateTime.now();

	public RoleDto() {
		super();
	}

	

	public RoleDto(int roleId, String roleName, LocalDateTime localDateTime) {
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
		return "RoleDto [roleId=" + roleId + ", roleName=" + roleName + ", localDateTime=" + localDateTime + "]";
	}

}