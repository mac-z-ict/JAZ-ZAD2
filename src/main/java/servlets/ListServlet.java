package servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import repositories.ConferenceApplicationRepository;
import repositories.DummyConferenceApplicationRepository;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private ConferenceApplicationRepository repository;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ConferenceApplicationRepository respository = new DummyConferenceApplicationRepository();

//		session.setAttribute("conf", application);
		response.setContentType("text/html");
		response.getWriter().println("<b>Repo list:</b><br>");
		response.getWriter().print(respository.list());
		
		response.getWriter().println("<hR><b>Session list:</b><br>");
		response.getWriter().print(session.toString());
//		response.sendRedirect("success.jsp");
	}
	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//        repository = new DummyConferenceApplicationRepository();
//	}
	
}
