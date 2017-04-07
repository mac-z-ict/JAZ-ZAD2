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
import javax.servlet.http.HttpSession;

import domain.menu;

@WebFilter("/setpremium")
public class SetPremiumPageFilter implements Filter {

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        response.setContentType("text/html");
        if (session.getAttribute("admin")==null || session.getAttribute("admin").equals(false)) {
                response.getWriter().print("<b>Brak uprawnień</b>");
                menu.printMenu(response);
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