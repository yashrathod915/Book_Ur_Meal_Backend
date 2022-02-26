package com.mindtree.bookyourmeal.modules.user.dto;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import com.mindtree.bookyourmeal.modules.core.entity.Address;

public class EditUserDto {
	private int userId;
	private String userPassword;
	private String userName;
	private String mobileNumber;
	private String mailId;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "EditUserDto [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName
				+ ", mobileNumber=" + mobileNumber + ", mailId=" + mailId + ", address=" + address + "]";
	}
	
}
