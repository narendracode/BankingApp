package handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businesslogic.CustomerBL;

import dao.CustomerDAOImpl;
import dao.DAOFactory;
import dto.CurrentUser;
import dto.Customer;
import exception.InvalidLoginException;

public class LoginHandler implements Handler{

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String result = "fail";	
		String loginType = request.getParameter("loginType");
			String userName = request.getParameter("username");
			String password = new CustomerBL().encryptorPassowrd((String)request.getParameter("password").trim());			
			CurrentUser cUser = new CurrentUser();
			cUser.setUserType(request.getParameter("loginType"));
			cUser.setUsername(userName);
			cUser.setPassword(password);			
			CurrentUser c  = new CustomerBL().authenticate(cUser,loginType);
			if(c!=null){
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", c);		
				DAOFactory.getCustomerDAO().getAllAccountInfo(c.getId());
				session.setAttribute("accounts", new CustomerBL().getAllAccountInfo(c.getId()));				
				result = c.getUserType();
			}
			if(c==null){				
				result = "fail";
			}
			
		return result;
	}

}
