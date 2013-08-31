package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.AccountNumberIdPair;
import dto.CurrentUser;
import dto.Customer;

public interface CustomerDAO {
	public CurrentUser authenticate(CurrentUser currentUser,String userType) throws SQLException;
	public String saveCustomer(Customer customer);
	public String updateCustomer(Customer customer);
	public String deleteCustomer(Customer customer);
	public ArrayList<Customer> getAllCustomers();
	public Customer getCustomerByAccountNumber(String accountNumber);
	public String getCustomerAccountNumber(int id);	
	public String deposit(double amount);
	public String withdraw(double amount,String accountNumber);
	public ArrayList<AccountNumberIdPair> getAllAccountInfo(int customerId);
}
