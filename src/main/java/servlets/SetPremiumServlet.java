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

@WebServlet("/setpremium")
public class SetPremiumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private ConferenceApplicationRepository repository;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ConferenceApplicationRepository repository = new DummyConferenceApplicationRepository();

//		response.getWriter().println("un="+request.getParameter("username"));
		
		if (request.getParameter("username")!=null) {
			if (repository.getPremium(request.getParameter("username"))) { //user ma premium
				if (repository.removePremium(request.getParameter("username"))) {
					response.getWriter().println("Usunieto status premium.");
				}
				else {
					response.getWriter().println("Nie zmieniono statusu premium!");
				}
			}
			else { // user nie ma premium
				if (repository.setPremium(request.getParameter("username"))) {
					response.getWriter().println("Ustawiono status premium.");
				}
				else {
					response.getWriter().println("Nie zmieniono statusu premium!");
				}
			}
		}
		else {
			response.getWriter().println("Nie ma takiego uzytkownika");
		}
		menu.printMenu(response);
//		session.setAttribute("conf", application);

//		response.getWriter().print(repository.list());
//		response.sendRedirect("success.jsp");
	}
}
