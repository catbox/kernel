package com.wh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wh.dao.SemesterDAO;
import com.wh.factory.ConnectionFactory;
import com.wh.transfer.Semester;

public class SemesterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SemesterServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 4;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
       
        if(page >= 0) {
	        SemesterDAO dao = null;
	        List<Semester> list = new ArrayList<Semester>();
			try {
				ConnectionFactory.createConnection();
				dao = new SemesterDAO();
				list = dao.getSemesters((page-1)*recordsPerPage,recordsPerPage);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	       
	        int noOfRecords = dao.getNumOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        request.setAttribute("semesterList", list);
	        request.setAttribute("numOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
	        view.forward(request, response);
        }
	}

}
