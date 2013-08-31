package controller;

import handler.AccountSummaryHandler;
import handler.DepositHandler;
import handler.FundsTransferHandler;
import handler.LoginHandler;
import handler.LogoutHandler;
import handler.TransactionHandler;
import handler.WithdrawHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.NotEnoughCashInBankException;
import exception.ProblemConnectingWIthDatabase;

public class ApplicationController {
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String path = request.getServletPath();
		String message = null;
		Object o = map.get(path);
		RequestDispatcher requestDispatcher = null;		
		if (o instanceof LoginHandler) {
			LoginHandler loginHandler = (LoginHandler) o;			
			result = loginHandler.handleRequest(request, response);
			
			switch(result){			
			case "customer":{
							message = "Welcome to Bank";
							requestDispatcher = request.getRequestDispatcher("/customerHome.jsp");				
							}break;
			case "banker":{
							message = "Welcome to Bank";
							requestDispatcher = request.getRequestDispatcher("/bankerHome.jsp");
							}break;
			default:		{	
							message = "Invalid login";
							requestDispatcher = request.getRequestDispatcher("/login.jsp");
							}
			}			
			request.setAttribute("message",message);
			requestDispatcher.forward(request, response);
		}// LoginHandler
		
		if (o instanceof LogoutHandler) {
			LogoutHandler logoutHandler = (LogoutHandler) o;			
			result = logoutHandler.handleRequest(request, response);
			
			if (result.equals("success")) {
				message = "Thank you for using BANK. you are successfully logged out.";
				requestDispatcher = request.getRequestDispatcher("/login.jsp");
			}			
			request.setAttribute("message",message);
			requestDispatcher.forward(request, response);
		}// LoginHandler
		

		
		
		if (o instanceof WithdrawHandler) {
			WithdrawHandler withdrawHandler = (WithdrawHandler) o;			
			
				result = withdrawHandler.handleRequest(request, response);
			
			
			switch(result){
			case "success":{
							message = "Balance is successfully withdrawn.";
							}break;
			case "amountExceeded":{
							message = "You are exceeding the maximum amount of withdrawl";
							}break;
			default:		{message = "Some problems occured while withdrawing money. Please try again.";}
							}
			request.setAttribute("message",message);
			requestDispatcher = request.getRequestDispatcher("/customerWithdrawDeposit.jsp");
			requestDispatcher.forward(request, response);
			
		}// withdrawHandler
		
		if (o instanceof DepositHandler) {
			DepositHandler depositHandler = (DepositHandler) o;			
			result = depositHandler.handleRequest(request, response);
			
			if (result.equals("success")) {
				message = "Balance is successfully Deposited.";
				requestDispatcher = request.getRequestDispatcher("/customerWithdrawDeposit.jsp");
			}
			else{
				message = "Some problems occured while depositing money. Please try again.";
				requestDispatcher = request.getRequestDispatcher("/customerWithdrawDeposit.jsp");
			}
			request.setAttribute("message",message);
			requestDispatcher.forward(request, response);		
		}// depositHandler	
		
		
		
		if(o instanceof FundsTransferHandler){
			FundsTransferHandler fundsTransferHandler = (FundsTransferHandler)o;
			
				result = fundsTransferHandler.handleRequest(request, response);
			
			System.out.println("Result fT:"+result);
			
			if(result.equals("success")){
				message="Balance is successfully transfered";
				requestDispatcher = request.getRequestDispatcher("/customerFundsTransfer.jsp");
			}else if(result.equals("amountExceeded")){
				message="You don't have enough balance in your account, please check balance.";
				requestDispatcher = request.getRequestDispatcher("/customerFundsTransfer.jsp");
			}else if(result.equals("invalidAccount")){
				message="Account Number doesn't exists, please enter again.";
				requestDispatcher = request.getRequestDispatcher("/customerFundsTransfer.jsp");
			}
			else {
				message="Some problems occured while transfering the funds. Please try again.";
				requestDispatcher = request.getRequestDispatcher("/customerFundsTransfer.jsp");
			}
			request.setAttribute("message",message);
			requestDispatcher.include(request, response);	
			
		}//fundsTansferHandler()
			
		if(o instanceof TransactionHandler){
			TransactionHandler transactionHandler = (TransactionHandler)o;
			result = transactionHandler.handleRequest(request, response);
			if(result.equals("success")){
				//request.setAttribute("transactions", request.getAttribute("transactions"));
				requestDispatcher = request.getRequestDispatcher("/customerTransaction.jsp");
			}else{
			//	message="Some problems occured while fetching  the funds. Please try again.";
				requestDispatcher = request.getRequestDispatcher("/customerHome.jsp");
			}
			//request.setAttribute("message",message);
			requestDispatcher.forward(request, response);	
			
		}//fundsTansferHandler()
		
		
		if(o instanceof AccountSummaryHandler){
			AccountSummaryHandler accountSummaryHandler = (AccountSummaryHandler)o;
			result = accountSummaryHandler.handleRequest(request, response);
			if(result.equals("success")){				
				requestDispatcher = request.getRequestDispatcher("/customerAccountSummary.jsp");
			}else{			
				requestDispatcher = request.getRequestDispatcher("/customerHome.jsp");
			}		
			requestDispatcher.forward(request, response);				
		}//AccountSummaryHandler()
	}

	public void init() {
		map = new HashMap();	
		map.put("/login.iss", new LoginHandler());
		map.put("/logout.iss", new LogoutHandler());
        map.put("/withdraw.iss", new WithdrawHandler());
        map.put("/deposit.iss", new DepositHandler());
        map.put("/fundsTransfer.iss", new FundsTransferHandler());
        map.put("/transaction.iss", new TransactionHandler());
        map.put("/accountSummary.iss", new AccountSummaryHandler());
	}

	Map map;
	String result;
	RequestDispatcher requestDispatcher = null;

}
