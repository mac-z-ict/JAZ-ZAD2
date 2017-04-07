package web.filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.menu;
import repositories.ConferenceApplicationRepository;
import repositories.DummyConferenceApplicationRepository;
import repositories.HsqlRepo;

@WebFilter({ "/" })
public class MainPageFilter implements Filter {
	
//		private ConferenceApplicationRepository repository;
	
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession session = httpRequest.getSession();
            response.setContentType("text/html");

            
//            HsqlRepo hsql = new HsqlRepo();
//            try {
//				hsql.RepositoryBase();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            
//            if(new DummyConferenceApplicationRepository().count()>2){
//                response.getWriter().print("Limit ilosci uzytkownikow.");
//                return;
//            }
//            response.getWriter().println("admin="+session.getAttribute("admin"));
//            response.getWriter().println("p="+session.getAttribute("premium"));
//            response.getWriter().println("l="+session.getAttribute("logged"));
//            return;

            if (session.getAttribute("logged")==null || (boolean) session.getAttribute("logged") == false) {
            	httpResponse.sendRedirect("/login.jsp");
            }
            else if (session.getAttribute("admin")!=null && (boolean) session.getAttribute("admin")) {
            	response.getWriter().print("<br><b>Jesteś adminem!</b><br>");
            	response.getWriter().print("Lista użytkowników:<br>");
        		ConferenceApplicationRepository respository = new DummyConferenceApplicationRepository();
        		response.getWriter().print(respository.list());
            	response.getWriter().print("<hr>Formularz nadawania/usuwania premium:<br>");
            	response.getWriter().print("<form action=\"setpremium\">"
            			+ "Podaj login uzytkownika: <input name=\"username\"> &nbsp;"
            			+ "<input type=\"submit\" value=\"zmień status premium\"/>"
            			+ "</form>");
            }
            else if (session.getAttribute("premium")!=null && (boolean) session.getAttribute("premium")) {
            	response.getWriter().print("<b>Jesteś userem premium</b><br>");
            }
            else {
            	response.getWriter().print("<b>Jesteś zwykłym userem</b><br>");
            }
            menu.printMenu(response);
//            chain.doFilter(request, response);
        }
        
		@Override
		public void destroy() {
			// TODO Auto-generated method stub
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
		}
}
