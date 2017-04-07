package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.menu;
import repositories.ConferenceApplicationRepository;
import repositories.DummyConferenceApplicationRepository;

@WebServlet("/profil")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private ConferenceApplicationRepository repository;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ConferenceApplicationRepository respository = new DummyConferenceApplicationRepository();
		
//		if (session.getAttribute("logged") != null && ) {
//			
//		}
		response.setContentType("text/html");
		if (session.getAttribute("logged")!=null && session.getAttribute("logged").equals(true)) {
			response.getWriter().print("<b>Profil uzytkownika:</b><br>");
			response.getWriter().print(respository.getprofil((String)session.getAttribute("login")));
		}
		else {
			response.getWriter().print("Uzytkownik jest niezalogowany!");
		}
		menu.printMenu(response);
//		response.sendRedirect("success.jsp");
	}
}
