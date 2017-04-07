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

@WebFilter({ "/login", "/add"})
public class AddPageFilter implements Filter {

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpSession session = httpRequest.getSession();
    if (session.getAttribute("logged")!=null && session.getAttribute("logged").equals(true)) {
    	httpResponse.sendRedirect("/profil");
    	return;
    }
    chain.doFilter(request, response);
}

@Override
public void destroy() {
}

@Override
public void init(FilterConfig arg0) throws ServletException {
}

}