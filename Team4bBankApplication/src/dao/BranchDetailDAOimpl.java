package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.ConnectionManager;

public class BranchDetailDAOimpl implements BranchDetailDAO{

	@Override
	public String getBranchNameByBranchId(int branchId) {
		Connection con = ConnectionManager.getConnection();
		String branchName = null;
		try{
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement("select BranchName from branch_details where branchdetail_id = ?");
			ps.setInt(1, branchId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				branchName = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return branchName;
	}

}
