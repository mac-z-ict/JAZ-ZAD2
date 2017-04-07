
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.mockito.Mockito;
import web.AddServlet;
import web.filters.AddPageFilter;
import web.filters.MainPageFilter;
import web.filters.PremiumPageFilter;
import web.filters.SetPremiumPageFilter;
import domain.ConferenceApplication;
import domain.menu;


public class TestZad2 extends Mockito {

		@Test
		public void servlet_should_response_when_you_are_not_logged() throws IOException, ServletException {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);
	
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(null);
			when(response.getWriter()).thenReturn(writer);
	
			MainPageFilter servlet = new MainPageFilter();
			FilterChain chain = null;
			servlet.doFilter(request, response, chain);
			verify(response).sendRedirect("/login.jsp");
		}

		
		@Test
		public void servlet_should_response_when_you_are_logged_as_user() throws IOException, ServletException {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			MainPageFilter servlet = new MainPageFilter();

			FilterChain chain = null;
			servlet.doFilter(request, response, chain);
			verify(writer).print("<b>Jesteś zwykłym userem</b><br>");
		}

		
		@Test
		public void servlet_should_response_when_you_are_logged_as_premiumuser() throws IOException, ServletException {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(true);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			MainPageFilter servlet = new MainPageFilter();

			FilterChain chain = null;
			servlet.doFilter(request, response, chain);
			verify(writer).print("<b>Jesteś userem premium</b><br>");
		}

		
		@Test
		public void servlet_should_response_when_you_are_logged_as_adminuser() throws IOException, ServletException {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(true);
			when(response.getWriter()).thenReturn(writer);

			MainPageFilter servlet = new MainPageFilter();

			FilterChain chain = null;
			servlet.doFilter(request, response, chain);
			verify(writer).print("<br><b>Jesteś adminem!</b><br>");
		}
		

		@Test
		public void application_username() {
			ConferenceApplication ca = new ConferenceApplication();
			String test = "TestStringa1";
			ca.setUsername(test);
			assertEquals(test,ca.getUsername());
		}

		
		@Test
		public void application_premium() {
			ConferenceApplication ca = new ConferenceApplication();
			String test = "TestStringa2";
			ca.setPremium(test);
			assertEquals(test,ca.getPremium());
		}

		
		@Test
		public void application_password() {
			ConferenceApplication ca = new ConferenceApplication();
			String test = "TestStringa3";
			ca.setPassword(test);
			assertEquals(test,ca.getPassword());
		}		
		
		
		@Test
		public void application_email() {
			ConferenceApplication ca = new ConferenceApplication();
			String test = "TestStringa4";
			ca.setEmail(test);
			assertEquals(test,ca.getEmail());
		}
		
		
		@Test
		public void application_admin() {
			ConferenceApplication ca = new ConferenceApplication();
			String test = "TestStringa5";
			ca.setAdmin(test);
			assertEquals(test,ca.getAdmin());
		}

		
		@Test
		public void application_menu() throws IOException {
			HttpServletResponse response = mock(HttpServletResponse.class);
			PrintWriter writer = mock(PrintWriter.class);
			when(response.getWriter()).thenReturn(writer);
			menu.printMenu(response);
			
			verify(writer).println("<hr>");
			verify(writer).println("<b>MENU:</b>");
			verify(writer).println("<hr> ");
			verify(writer).println("<a href=\"/\">STRONA GLOWNA</a> | ");
			verify(writer).println("<a href=\"/add\">REJESTRACJA</a> | ");
			verify(writer).println("<a href=\"/login\">LOGIN</a> | ");
			verify(writer).println("<a href=\"/premium\">PREMIUM</a> | ");
			verify(writer).println("<a href=\"/profil\">PROFIL UZYTKOWNIKA</a> | ");
			verify(writer).println("<a href=\"/list\">LISTA USEROW (DEBUG)</a>");
			verify(writer).println(" <hr>");
		}

		
		@Test
		public void servlet_should_redirect_when_you_mainpage_or_add() throws IOException, ServletException {

			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			AddPageFilter servlet = new AddPageFilter();

			FilterChain chain = null;
			servlet.doFilter(request, response, chain);
			verify(response).sendRedirect("/profil");
		}

		
		@Test
		public void servlet_premium_page_filter_no_premium_access() throws IOException, ServletException {

			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);
			FilterChain chain = mock(FilterChain.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			PremiumPageFilter servlet = new PremiumPageFilter();
			servlet.doFilter(request, response, chain);

			verify(response).setContentType("text/html");
			verify(writer).print("<b>Brak uprawnien do strony PREMIUM</b><br>");
		}
		

		@Test
		public void servlet_premium_page_filter_with_premium_access() throws IOException, ServletException {

			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(null);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			PremiumPageFilter servlet = new PremiumPageFilter();

			FilterChain chain = null;
			servlet.doFilter(request, response, chain);

			verify(response).setContentType("text/html");
			verify(writer).print("<b>Brak uprawnien do strony PREMIUM</b><br>");
		}
		
		
		@Test
		public void filter_set_premium_page_no_access_admin_false() throws IOException, ServletException {

			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);
			
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			PremiumPageFilter servlet = new PremiumPageFilter();

			FilterChain chain = null;
			servlet.doFilter(request, response, chain);

			verify(response).setContentType("text/html");
			verify(writer).print("<b>Brak uprawnien do strony PREMIUM</b><br>");
		}
		
		
		@Test
		public void filter_set_premium_page_no_access_admin_null() throws IOException, ServletException {

			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(null);
			when(session.getAttribute("admin")).thenReturn(true);
			when(response.getWriter()).thenReturn(writer);

			PremiumPageFilter servlet = new PremiumPageFilter();

			FilterChain chain = null;
			servlet.doFilter(request, response, chain);

			verify(response).setContentType("text/html");
			verify(writer).print("<b>Brak uprawnien do strony PREMIUM</b><br>");
		}

		
		@Test
		public void set_premium_page_filter_no_access_admin_null() throws IOException, ServletException {

			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(null);
			when(session.getAttribute("admin")).thenReturn(null);
			when(response.getWriter()).thenReturn(writer);

			SetPremiumPageFilter servlet = new SetPremiumPageFilter();

			FilterChain chain = null;
			servlet.doFilter(request, response, chain);

			verify(response).setContentType("text/html");
			verify(writer).print("<b>Brak uprawnień</b>");
		}
		
		
		@Test
		public void set_premium_page_filter_no_access_admin_false() throws IOException, ServletException {

			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(true);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			SetPremiumPageFilter servlet = new SetPremiumPageFilter();

			FilterChain chain = null;
			servlet.doFilter(request, response, chain);

			verify(response).setContentType("text/html");
			verify(writer).print("<b>Brak uprawnień</b>");
		}


		@Test
		public void servlet_add_should_response_ok_when_you_register() throws IOException, ServletException {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getParameter("username")).thenReturn("login");
			when(request.getParameter("password")).thenReturn("haslo");
			when(request.getParameter("email")).thenReturn("adres");
			when(request.getParameter("premium")).thenReturn("false");
			when(request.getParameter("admin")).thenReturn("false");
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(false);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			AddServlet servlet = new AddServlet();

			servlet.doGet(request, response);
			verify(writer).print("<b>Dziekujemy za rejestracje!</b><br>");
		}

		
		@Test
		public void servlet_add_should_redirect_when_you_register_and_get_no_login_and_password() throws IOException, ServletException {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getParameter("username")).thenReturn(null);
			when(request.getParameter("password")).thenReturn(null);
			when(request.getParameter("email")).thenReturn("adres");
			when(request.getParameter("premium")).thenReturn("false");
			when(request.getParameter("admin")).thenReturn("false");
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(false);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			AddServlet servlet = new AddServlet();

			servlet.doGet(request, response);
			verify(response).sendRedirect("/");
		}


		@Test
		public void servlet_add_should_redirect_when_you_register_and_get_no_password() throws IOException, ServletException {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getParameter("username")).thenReturn("login");
			when(request.getParameter("password")).thenReturn(null);
			when(request.getParameter("email")).thenReturn("adres");
			when(request.getParameter("premium")).thenReturn("false");
			when(request.getParameter("admin")).thenReturn("false");
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(false);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			AddServlet servlet = new AddServlet();

			servlet.doGet(request, response);
			verify(response).sendRedirect("/");
		}

		
		@Test
		public void servlet_add_should_redirect_when_you_register_and_get_no_login() throws IOException, ServletException {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getParameter("username")).thenReturn(null);
			when(request.getParameter("password")).thenReturn("haslo");
			when(request.getParameter("email")).thenReturn("adres");
			when(request.getParameter("premium")).thenReturn("false");
			when(request.getParameter("admin")).thenReturn("false");
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(false);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			AddServlet servlet = new AddServlet();

			servlet.doGet(request, response);
			verify(response).sendRedirect("/");
		}

		
		@Test
		public void servlet_add_should_response_notok_when_you_register_and_logged() throws IOException, ServletException {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			HttpSession session = mock(HttpSession.class);
			PrintWriter writer = mock(PrintWriter.class);

			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("logged")).thenReturn(true);
			when(session.getAttribute("premium")).thenReturn(false);
			when(session.getAttribute("admin")).thenReturn(false);
			when(response.getWriter()).thenReturn(writer);

			AddServlet servlet = new AddServlet();

			servlet.doGet(request, response);
			verify(writer).print("Jestes zalogowany - brak mozliwosci rejestracji!<br>");
		}

}
