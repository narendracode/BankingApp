package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.CustomerBankAccount;
import dto.TransactionBankAccountDetails;

public interface CustomerBankAccountDAO {
	 
	
	 public String withdraw(TransactionBankAccountDetails transactionBankAccountDetails) ;
	 public String deposit(TransactionBankAccountDetails transactionBankAccountDetails);
	 
	 public int getAccountIdByAccountNumber(String accountNumber);
	 
	 public int getBranchIdByAccountId(int accountId);
	public String saveCustomerBankAccount(CustomerBankAccount customerBankAccount);
	 public String updateCustomerBankAccount(CustomerBankAccount customerBankAccount);
	 public String deleteCustomerBankAccount(CustomerBankAccount customerBankAccount);	 
	 public ArrayList<CustomerBankAccount> getAllCustomerBankAccounts();
	 public CustomerBankAccount getCustomerBankAccountByAccountNumber(String accountNumber);
	 public CustomerBankAccount getCustomerBankAccountByCustomerId(String customerId);
	 public java.sql.Date getAccountCreationDateByAccountId(int accountId);
	 public double getBalanceByAccountId(int accountId);
	 public double getBalanceByAccountNumber(String accountNumber);
	 
	 public int getAccountTypeIdByAccountId(int accountId);
	 public int getAccountTypeIdByAccountNumber(String accountNumber); 
	 public boolean isAccountNumber(String accountNumber);
}
