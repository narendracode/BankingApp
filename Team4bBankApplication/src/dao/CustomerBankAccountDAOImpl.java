package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connection.ConnectionManager;
import dto.CustomerBankAccount;
import dto.TransactionBankAccountDetails;

public class CustomerBankAccountDAOImpl implements CustomerBankAccountDAO{	
	@Override
	public String withdraw(TransactionBankAccountDetails transaction) {
		String result = "fail";
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try{
			ps1 = con.prepareStatement("UPDATE customer_bankaccount SET Balance = Balance-? WHERE accountnumber = ?");
			ps1.setDouble(1, transaction.getAmount());
			ps1.setString(2,transaction.getFromAccountNumber());			
			
			
			//getAccountIdByAccountNumber //for fromAccountNumber
			//getAccountIdByAccountNumber   //for toAccountNumber
			
			
			transaction.setFrom_id(getAccountIdByAccountNumber(transaction.getFromAccountNumber()));
			transaction.setTo_id(getAccountIdByAccountNumber(transaction.getToAccountNumber()));
			
			
			System.out.println(" WD from bank act id:"+transaction.getFrom_id()+ " cust id:"+transaction.getCustomerId());					
			int result1 = ps1.executeUpdate();			
			ps2 = con.prepareStatement("INSERT INTO transaction (Amount,Transaction_Time,From_CustomerAccount_ID,To_Customer_Account_ID,Status,Customer_AccountID)VALUES(?,?,?,?,?,?)");
			ps2.setDouble(1, transaction.getAmount());
			ps2.setDate(2, transaction.getTransactionDate());
			ps2.setInt(3, transaction.getFrom_id());
			ps2.setInt(4, transaction.getTo_id());
			ps2.setString(5, transaction.getStatus());
			ps2.setInt(6, transaction.getCustomerId());			
			int result2 = ps2.executeUpdate();		
			
			System.out.println("Result 1:"+result1+" result2:"+result2);
			if(result1>0 && result2==1){
				result = "success";
			}
		}catch(Exception e){
			
		}
		return result;
	}
	
	@Override
	public String saveCustomerBankAccount(
			CustomerBankAccount customerBankAccount) {		
		return null;
	}

	@Override
	public String updateCustomerBankAccount(
			CustomerBankAccount customerBankAccount) {		
		return null;
	}

	@Override
	public String deleteCustomerBankAccount(
			CustomerBankAccount customerBankAccount) {		
		return null;
	}

	@Override
	public ArrayList<CustomerBankAccount> getAllCustomerBankAccounts() {		
		return null;
	}

	@Override
	public CustomerBankAccount getCustomerBankAccountByAccountNumber(
			String accountNumber) {
		
		return null;
	}

	@Override
	public CustomerBankAccount getCustomerBankAccountByCustomerId(
			String customerId) {
		
		return null;
	}

	@Override
	public String deposit(
			TransactionBankAccountDetails transaction) {
		String result = "fail";
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try{
			ps1 = con.prepareStatement("UPDATE customer_bankaccount SET Balance = Balance + ? WHERE accountnumber = ?");
			ps1.setDouble(1, transaction.getAmount());
			ps1.setString(2,transaction.getFromAccountNumber());			
				
			int fromAccountId = getAccountIdByAccountNumber(transaction.getFromAccountNumber());
			int toAccountId = getAccountIdByAccountNumber(transaction.getToAccountNumber());
			transaction.setFrom_id(fromAccountId);
			transaction.setTo_id(toAccountId);
			
			int result1 = ps1.executeUpdate();			
			ps2 = con.prepareStatement("INSERT INTO transaction (Amount,Transaction_Time,From_CustomerAccount_ID,To_Customer_Account_ID,Status,Customer_AccountID)VALUES(?,?,?,?,?,?)");
			ps2.setDouble(1, transaction.getAmount());
			ps2.setDate(2, transaction.getTransactionDate());
			ps2.setInt(3, transaction.getFrom_id());
			
			ps2.setInt(4, transaction.getTo_id());
			ps2.setString(5, transaction.getStatus());
			ps2.setInt(6, transaction.getCustomerId());			
			int result2 = ps2.executeUpdate();			
			if(result1>0&&result2==1){
				result = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getAccountIdByAccountNumber(String accountNumber) {
		Connection con = ConnectionManager.getConnection();
		int accountId = -1;
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select accountid from customer_bankaccount where accountnumber ='"+accountNumber+"'");
			if(rs.next()){
				accountId = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return accountId;
	}
	
	
	public String getAccountNumberByAccountId(int accountId){
		Connection con = ConnectionManager.getConnection();
		String accountNumber= null;
		try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select accountnumber from customer_bankaccount where accountid ="+accountId+"");
			if(rs.next()){
				accountNumber = rs.getString(1);
			}
			
		}catch(Exception ex){
			
		}
		return accountNumber;
	}

	public String fundTransfer(TransactionBankAccountDetails transaction) {
		String result = "fail";
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		try{
			//from -decrease the  balance //from_accountId
			ps1 = con.prepareStatement("UPDATE customer_bankaccount SET Balance = Balance - ? WHERE accountid = ?");
			ps1.setDouble(1, transaction.getAmount());
			ps1.setInt(2,transaction.getAccountId());			
			
			int result1 = ps1.executeUpdate();	
			//to + increase the balance //to_accountId
			ps3 = con.prepareStatement("UPDATE customer_bankaccount SET Balance = Balance + ? WHERE accountid = ?");
			ps3.setDouble(1, transaction.getAmount());
			ps3.setInt(2,transaction.getTo_id());
			int result3 = ps3.executeUpdate();	
			
			
			ps2 = con.prepareStatement("INSERT INTO transaction (Amount,Transaction_Time,From_CustomerAccount_ID,To_Customer_Account_ID,Status,Customer_AccountID)VALUES(?,?,?,?,?,?)");
			ps2.setDouble(1, transaction.getAmount());
			ps2.setDate(2, transaction.getTransactionDate());
			ps2.setInt(3, transaction.getFrom_id());
			
			ps2.setInt(4, transaction.getTo_id());
			ps2.setString(5, transaction.getStatus());
			ps2.setInt(6, transaction.getAccountId());			
			int result2 = ps2.executeUpdate();			
			if(result1==1&&result2==1&&result3==1){
				result = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getBranchIdByAccountId(int accountId) {
		int branchId = -1;
	Connection con = ConnectionManager.getConnection();
	try{
		PreparedStatement ps = con.prepareStatement("select BranchDetail_id from customer_bankaccount where accountId = ?");
		ps.setInt(1, accountId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			branchId = rs.getInt(1);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
		return branchId;
	}

	@Override
	public double getBalanceByAccountId(int accountId) {
		double balance=0.0;
		Connection con = ConnectionManager.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("select balance from customer_bankaccount where accountId = ?");
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				balance = rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return balance;
	}//getBalanceByAccountId()

	@Override
	public Date getAccountCreationDateByAccountId(int accountId) {
		java.sql.Date accountCreatedOn = null;
		Connection con = ConnectionManager.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("select created_on from customer_bankaccount where accountId = ?");
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				accountCreatedOn = rs.getDate(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return accountCreatedOn;
	}

	@Override
	public int getAccountTypeIdByAccountId(int accountId) {
		int accountTypeId =0;
	try{
		Connection con = ConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement("select accounttype_id from customer_bankaccount where accountid = ?");
		ps.setInt(1, accountId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			accountTypeId = rs.getInt(1);
		}
	}catch(Exception e){
		
	}
		return accountTypeId;
	}//getAccountTypeIdByAccountId()

	@Override
	public int getAccountTypeIdByAccountNumber(String accountNumber) {
		int accountTypeId=0;
		try{
			Connection con = ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select accounttype_id from customer_bankaccount where accountnumber = ?");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				accountTypeId = rs.getInt(1);
			}
		}catch(Exception e){
			
		}
			return accountTypeId;
	}

	@Override
	public double getBalanceByAccountNumber(String accountNumber) {
		double balance=0.0;
		Connection con = ConnectionManager.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("select balance from customer_bankaccount where accountnumber = ?");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				balance = rs.getDouble(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	public boolean isAccountNumber(String accountNumber) {
		//System.out.println("Account number in DAO:"+accountNumber);
		boolean result = false;
		Connection con = ConnectionManager.getConnection();		
		try{
			PreparedStatement ps = con.prepareStatement("select * from customer_bankaccount where accountNumber = ?");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			//	System.out.println("accnt check:"+rs.getInt(1));
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}//isAccountNumber()

}
