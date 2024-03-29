package handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.NotEnoughCashInBankException;
import exception.ProblemConnectingWIthDatabase;

public interface Handler {
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException;
}
