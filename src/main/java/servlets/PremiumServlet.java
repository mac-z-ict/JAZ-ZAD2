package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.menu;
import repositories.ConferenceApplicationRepository;
import repositories.DummyConferenceApplicationRepository;

@WebServlet("/premium")
public class PremiumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private ConferenceApplicationRepository repository;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ConferenceApplicationRepository respository = new DummyConferenceApplicationRepository();

//		session.setAttribute("conf", application);

		response.getWriter().print("<b>TO JEST STRONA PREMIUM</b>");
		menu.printMenu(response);
//		response.getWriter().print(respository.list());
//		response.sendRedirect("success.jsp");
	}
}
