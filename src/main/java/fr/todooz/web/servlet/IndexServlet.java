package fr.todooz.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.todooz.DummyData;

public class IndexServlet extends HttpServlet {
	  private static final long serialVersionUID = 1L;

	  @Override
	  protected void doGet(HttpServletRequest request,
	      HttpServletResponse response) throws ServletException, IOException {
		  request.setAttribute("tasks", DummyData.tasks());
	    request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
	  }
	}