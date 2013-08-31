package businesslogic;

import java.sql.SQLException;

import dao.AccountTypeDAOImpl;
import dao.CustomerBankAccountDAOImpl;
import dao.DAOFactory;
import dto.TransactionBankAccountDetails;
import exception.NotEnoughCashInBankException;
import exception.ProblemConnectingWIthDatabase;

public class CustomerBankAccountBL {
	public String withdrawAmount(
			TransactionBankAccountDetails transactionBankAccountDetails)  {
		String result = "fail";
try{
		int accountTypeId = DAOFactory.getCustomerBankAccountDAO()
				.getAccountTypeIdByAccountNumber(
						transactionBankAccountDetails.getFromAccountNumber());
				
		double minBalAllowed = DAOFactory.getAccountTypeDAO()
				.getMinimumBalanceByAccountTypeId(accountTypeId);
				
		double balance = DAOFactory.getCustomerBankAccountDAO()
				.getBalanceByAccountNumber(
						transactionBankAccountDetails.getFromAccountNumber());		
		
		System.out.println(minBalAllowed +" < "+ balance+" - "+transactionBankAccountDetails.getAmount());
		if (minBalAllowed < balance - transactionBankAccountDetails.getAmount()) {
			result = DAOFactory.getCustomerBankAccountDAO().withdraw(
					transactionBankAccountDetails);
			
		} else
			result = "amountExceeded";
	
	}catch(Exception ex){
		ex.printStackTrace();
	}
	System.out.println("result in CBAL:"+result);
		return result;
	}

	public String depositAmount(
			TransactionBankAccountDetails transactionBankAccountDetails) {
		String result = DAOFactory.getCustomerBankAccountDAO().deposit(
				transactionBankAccountDetails);
		return result;
	}

	public String fundTransfer(
			TransactionBankAccountDetails transactionBankAccountDetails)  {
		String result = "fail";
		try{ 
		int accountTypeId = DAOFactory.getCustomerBankAccountDAO()
				.getAccountTypeIdByAccountNumber(
						transactionBankAccountDetails.getFromAccountNumber());
		double minBalAllowed = DAOFactory.getAccountTypeDAO()
				.getMinimumBalanceByAccountTypeId(accountTypeId);

		double balance = DAOFactory.getCustomerBankAccountDAO()
				.getBalanceByAccountNumber(
						transactionBankAccountDetails.getFromAccountNumber());
		

		if (0.0 < balance - transactionBankAccountDetails.getAmount()) {
			result = DAOFactory.getCustomerBankAccountDAO().withdraw(transactionBankAccountDetails);
		} else
			result = "amountExceeded";
		}
		catch(Exception e){			
		}
		return result;
	}
	
	public int getBranchIdByAccountId(int accountId) {
		return DAOFactory.getCustomerBankAccountDAO().getBranchIdByAccountId(
				accountId);
	}

	public double getBalanceByAccountId(int accountId) {
		return DAOFactory.getCustomerBankAccountDAO().getBalanceByAccountId(
				accountId);
	}

	public java.sql.Date getAccountCreationDateByAccountId(int accountId) {
		return DAOFactory.getCustomerBankAccountDAO()
				.getAccountCreationDateByAccountId(accountId);
	}
	
	 public int getAccountIdByAccountNumber(String accountNumber){
		 return DAOFactory.getCustomerBankAccountDAO().getAccountIdByAccountNumber(accountNumber);
	 }
	
}
