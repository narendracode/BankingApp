package dto;

import java.math.BigInteger;

public class BranchDetails {
	private int id;
	private String branchName,address,email;
	private BigInteger phone;
	public int getId() {
		return id;
	}
		
	public BranchDetails(int id, String branchName, String address,
			String email, BigInteger phone) {
		super();
		this.id = id;
		this.branchName = branchName;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigInteger getPhone() {
		return phone;
	}
	public void setPhone(BigInteger phone) {
		this.phone = phone;
	}
	
}
