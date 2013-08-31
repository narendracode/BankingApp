package dto;

import java.util.Date;

public class Customer {
	private int id,accountCreatedBy,accountModifiedBy;
	private String firstName,lastName,passport,nationality,gender,email,address,customerId,password;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private Date dob,accountCreatedOn,accountModifiedOn;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountCreatedBy() {
		return accountCreatedBy;
	}
	public void setAccountCreatedBy(int accountCreatedBy) {
		this.accountCreatedBy = accountCreatedBy;
	}
	public int getAccountModifiedBy() {
		return accountModifiedBy;
	}
	public void setAccountModifiedBy(int accountModifiedBy) {
		this.accountModifiedBy = accountModifiedBy;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getAccountCreatedOn() {
		return accountCreatedOn;
	}
	public void setAccountCreatedOn(Date accountCreatedOn) {
		this.accountCreatedOn = accountCreatedOn;
	}
	public Date getAccountModifiedOn() {
		return accountModifiedOn;
	}
	public void setAccountModifiedOn(Date accountModifiedOn) {
		this.accountModifiedOn = accountModifiedOn;
	}
}
