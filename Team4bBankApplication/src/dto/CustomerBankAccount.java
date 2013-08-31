package dto;

import java.math.BigDecimal;

public class CustomerBankAccount {
	private int id;
	private BigDecimal balance;
	private int  branchDetailsId,accountTypeId;
	private String customerId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public int getBranchDetailsId() {
		return branchDetailsId;
	}
	public void setBranchDetailsId(int branchDetailsId) {
		this.branchDetailsId = branchDetailsId;
	}
	public int getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public CustomerBankAccount(int id, BigDecimal balance, int branchDetailsId,
			int accountTypeId, String customerId) {
		super();
		this.id = id;
		this.balance = balance;
		this.branchDetailsId = branchDetailsId;
		this.accountTypeId = accountTypeId;
		this.customerId = customerId;
	}
	
	
}
