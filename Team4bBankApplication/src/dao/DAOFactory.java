package dao;

public class DAOFactory {
	public static CustomerDAO getCustomerDAO(){
		return new CustomerDAOImpl(); 
	}
	public static AccountTypeDAO getAccountTypeDAO(){
		return new AccountTypeDAOImpl();
	}
	public static BankerDAO getBankerDAO(){
		return new BankerDAOImpl();
	}
	public static BranchDetailDAO geBranchDetailDAO(){
		return new BranchDetailDAOimpl();
	}
	public static CustomerBankAccountDAO getCustomerBankAccountDAO(){
		return new CustomerBankAccountDAOImpl();
	}
	public static TransactionDAO getTransactionDAO(){
		return new TransactionDAOImpl();
	}
}
