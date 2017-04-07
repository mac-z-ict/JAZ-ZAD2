package web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repositories.ConferenceApplicationRepository;
import repositories.DummyConferenceApplicationRepository;
import domain.ConferenceApplication;
import domain.menu;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private ConferenceApplicationRepository repository;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        if (session.getAttribute("logged")==null || (boolean) session.getAttribute("logged").equals(false)) {
			ConferenceApplication application = retrieveApplicationFromRequest(request);
			ConferenceApplicationRepository respository = new DummyConferenceApplicationRepository();
			response.setContentType("text/html");
			if (application.getUsername()!=null && application.getPassword()!=null) {
				session.setAttribute("conf", application);
				respository.add(application);
				response.getWriter().print("<b>Dziekujemy za rejestracje!</b><br>");
			}
			else {
//				response.getWriter().print("Niepoprawne dane!<br>");
				response.sendRedirect("/");
			}
        }
        else {
			response.getWriter().print("Jestes zalogowany - brak mozliwosci rejestracji!<br>");
        }
//		response.sendRedirect("success.jsp");
		menu.printMenu(response);
	}
	
	private ConferenceApplication retrieveApplicationFromRequest(HttpServletRequest request){
		ConferenceApplication result = new ConferenceApplication();
		result.setUsername(request.getParameter("username"));
		result.setPassword(request.getParameter("password"));
		result.setEmail(request.getParameter("email"));
		result.setPremium(request.getParameter("premium"));
		result.setAdmin(request.getParameter("admin"));
		return result;
	}
	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//        repository = new DummyConferenceApplicationRepository();
//	}
	
}
