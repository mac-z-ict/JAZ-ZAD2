package domain;

import java.io.IOException;

import javax.servlet.ServletResponse;

public class menu {
	public static void printMenu(ServletResponse response) throws IOException {
		response.getWriter().println("<hr>");
		response.getWriter().println("<b>MENU:</b>");
		response.getWriter().println("<hr> ");
		response.getWriter().println("<a href=\"/\">STRONA GLOWNA</a> | ");
		response.getWriter().println("<a href=\"/add\">REJESTRACJA</a> | ");
		response.getWriter().println("<a href=\"/login\">LOGIN</a> | ");
		response.getWriter().println("<a href=\"/premium\">PREMIUM</a> | ");
		response.getWriter().println("<a href=\"/profil\">PROFIL UZYTKOWNIKA</a> | ");
		response.getWriter().println("<a href=\"/list\">LISTA USEROW (DEBUG)</a>");
		response.getWriter().println(" <hr>");
	}
}
