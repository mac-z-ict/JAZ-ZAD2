package servlets;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repositories.ConferenceApplicationRepository;
import repositories.DummyConferenceApplicationRepository;
import domain.ConferenceApplication;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConferenceApplicationRepository repository;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ConferenceApplication application = retrieveApplicationFromRequest(request);
		repository.checkLogin(session, application.getUsername(), application.getPassword());

//		if ((boolean) session.getAttribute("logged")) {
//			response.getWriter().println("Logged");
//		}
//		else {
//			response.sendRedirect("/login.jsp");
//		}

		response.sendRedirect("/");

//		response.getWriter().println("Logged");
//		}
//		else {
//			session.setAttribute("logged", false);
//			response.getWriter().println("NOT Logged!");
//		}
//		respository.add(application);
//		response.sendRedirect("success.jsp");
	}
	
	private ConferenceApplication retrieveApplicationFromRequest(HttpServletRequest request){
		ConferenceApplication result = new ConferenceApplication();
		result.setUsername(request.getParameter("username"));
		result.setPassword(request.getParameter("password"));
		return result;
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
        repository = new DummyConferenceApplicationRepository();
	}
	
}
