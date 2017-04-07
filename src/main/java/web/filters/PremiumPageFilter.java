package web.filters;

import java.io.IOException;

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

@WebFilter({ "/premium" })
public class PremiumPageFilter implements Filter {
	
//		private ConferenceApplicationRepository repository;
	
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession session = httpRequest.getSession();
            response.setContentType("text/html");
//            if(new DummyConferenceApplicationRepository().count()>2){
//                response.getWriter().print("Limit ilosci uzytkownikow.");
//                return;
//            }
//            response.getWriter().println("admin="+session.getAttribute("admin"));
//            response.getWriter().println("p="+session.getAttribute("premium"));
//            response.getWriter().println("l="+session.getAttribute("logged"));
//            return;

            if (session.getAttribute("premium")==null || (boolean) session.getAttribute("premium") == false) {
            	response.getWriter().print("<b>Brak uprawnien do strony PREMIUM</b><br>");
                menu.printMenu(response);
                return;
            }
            chain.doFilter(request, response);
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
