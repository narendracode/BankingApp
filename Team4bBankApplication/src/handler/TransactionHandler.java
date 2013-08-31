package handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businesslogic.TransactionBL;

import dao.DAOFactory;
import dao.TransactionDAOImpl;
import dto.CurrentUser;
import dto.Transaction;
import dto.TransactionBankAccountDetails;

public class TransactionHandler implements Handler {
	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Transaction> transactions = null;
		CurrentUser cu = (CurrentUser)request.getSession().getAttribute("currentUser");
		int customerId = cu.getId();
		transactions = new TransactionBL().getAllTransactionsByCustomerId(customerId);
		
		if(request.getParameter("showTransaction")!=null){
		if(request.getParameter("showTransaction").equals("showTransactionByAccountId")){
			transactions = new TransactionBL().getAllTransactionsByAccountId(Integer.parseInt(request.getParameter("accountId")));
		}
		}	
		request.setAttribute("transactions",transactions);
		if(transactions!=null)
			return "success";
		else
			return "fail";
	}

}
