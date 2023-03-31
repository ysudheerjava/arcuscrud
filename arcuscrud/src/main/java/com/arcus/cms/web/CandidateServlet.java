package com.arcus.cms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class CandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidateDAO candidateDAO;

	public void init() {
		candidateDAO = new CandidateDAO();
	}

	
	  protected void doPost(HttpServletRequest request, HttpServletResponse
	  response) throws ServletException, IOException { 
		  doGet(request, response);
		  }
	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		System.out.println("action::::::::::::::::::"+action);

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertCandidate(request, response);
				break;
			case "/delete":
				deleteCandidate(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCandidate(request, response);
				break;
			default:
				listCandidate(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCandidate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Candidate> listCandidates = candidateDAO.selectAllCandidates();
		request.setAttribute("listCandidate", listCandidates);
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidateslist.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidateform.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Candidate existingCandidate = candidateDAO.selectCandidate(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidateform.jsp");
		request.setAttribute("candidate", existingCandidate);
		dispatcher.forward(request, response);

	}

	private void insertCandidate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String qualification = request.getParameter("qualification");
		String state = request.getParameter("state");
		Candidate candidate = new Candidate(name, gender, email, qualification, state);
		candidateDAO.insertCandidate(candidate);
		response.sendRedirect("list");
	}

	private void updateCandidate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String qualification = request.getParameter("qualification");
		String state = request.getParameter("state");

		Candidate candidate = new Candidate(id, name, gender, email, qualification, state);
		candidateDAO.updateCandidate(candidate);
		response.sendRedirect("list");
	}

	private void deleteCandidate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		candidateDAO.deleteCandidate(id);
		response.sendRedirect("list");

	}
}