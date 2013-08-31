package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionManager;

public class AccountTypeDAOImpl implements AccountTypeDAO{

	@Override
	public double getMinimumBalanceByAccountTypeId(int accountTypeId) {
		double minimumBalance = 0;
		try{
			Connection con = ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select minimum_balance from account_type where accountType_id = ?");
			ps.setInt(1, accountTypeId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				minimumBalance = rs.getDouble(1);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return minimumBalance;
	}

}
