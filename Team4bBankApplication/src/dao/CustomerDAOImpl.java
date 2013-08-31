package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectionManager;

import dto.AccountNumberIdPair;
import dto.CurrentUser;
import dto.Customer;

public class CustomerDAOImpl implements CustomerDAO{

	@Override
	public CurrentUser authenticate(CurrentUser cUser,String userType) throws SQLException{
		Connection con = ConnectionManager.getConnection();
	
		String query = null;
		if(userType.equals("customer")){
			query = "select * from customer where loginname =? and password =?";
		}
		else query = "select * from banker where username =? and password =?";		
		CurrentUser currentUser = null;
	PreparedStatement ps = con.prepareStatement(query);
	ps.setString(1, cUser.getUsername());
		ps.setString(2, cUser.getPassword());
		ResultSet rs = ps.executeQuery();	
		if(rs!=null){
		while(rs.next()){
			currentUser = new CurrentUser();
			currentUser.setId(rs.getInt(1));
			if(userType.equals("customer")){
			currentUser.setFirstName(rs.getString(2));
			currentUser.setLastName(rs.getString(3));
			currentUser.setUsername(rs.getString(11));
			}
			else{
				currentUser.setFirstName(rs.getString(2));
				currentUser.setLastName(rs.getString(3));
				currentUser.setUsername(rs.getString(6));
			}
			currentUser.setUserType(userType);
		}
		}
		
		
		return currentUser;
	}

	@Override
	public String saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCustomerAccountNumber(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deposit(double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String withdraw(double amount, String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AccountNumberIdPair> getAllAccountInfo(int customerId) {
		Connection con = ConnectionManager.getConnection();
		ArrayList<AccountNumberIdPair> accountIdPairs = new ArrayList<AccountNumberIdPair>();
		try{
		PreparedStatement ps = con.prepareStatement("select * from customer_bankaccount where customerid = ?");
		ps.setInt(1, customerId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			AccountNumberIdPair accountNumberIdPair = new AccountNumberIdPair();
			accountNumberIdPair.setId(rs.getInt(1));
			System.out.println("check DAO:"+rs.getString(6));
			accountNumberIdPair.setAccountNumber(rs.getString(6));
			accountIdPairs.add(accountNumberIdPair);
		}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return accountIdPairs;
	}//getAllAccountInfo

}
