package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerDAOImpl;
import dao.DAOFactory;
import dto.AccountNumberIdPair;
import dto.CurrentUser;
import util.Encryptor;

public class CustomerBL {
	public CurrentUser authenticate(CurrentUser cUser,String loginType) {
		CurrentUser cu = null;
		try {
			cu=  new CustomerDAOImpl().authenticate(cUser,loginType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cu;
	}
	
	public String encryptorPassowrd(String password){
		String encryptedPassword=null;
		try{
			encryptedPassword = new Encryptor().encryptMyPssword(password);
		}catch(Exception e){
			
		}
		return encryptedPassword;
	}
	public ArrayList<AccountNumberIdPair> getAllAccountInfo(int customerId){
		return DAOFactory.getCustomerDAO().getAllAccountInfo(customerId);
	}

}
