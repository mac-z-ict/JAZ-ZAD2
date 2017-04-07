package repositories;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import domain.ConferenceApplication;

public class DummyConferenceApplicationRepository implements ConferenceApplicationRepository {

//	private static List<ConferenceApplication> db = new ArrayList<ConferenceApplication>(String email);
	private static List<ConferenceApplication> db = new ArrayList<ConferenceApplication>();

	@Override
	public ConferenceApplication getApplicationByEmailAddress(String email){
		for(ConferenceApplication application: db){
			if(application.getEmail().equalsIgnoreCase(email)) return application;
		}
		return null;
	}
	
	public boolean checkLogin(HttpSession session, String login, String password){

		for(ConferenceApplication application: db){
			if(application.getUsername().equalsIgnoreCase(login) && application.getPassword().equals(password)) {
				session.setAttribute("logged", true);
				session.setAttribute("login", login);

				if (application.getPremium().equals("1")) session.setAttribute("premium", true);
				else session.setAttribute("premium", false);

				if (application.getAdmin().equals("1")) {
					session.setAttribute("admin", true);
					session.setAttribute("premium", true);
				}
				else session.setAttribute("admin", false);
				
				return true;
			}
		}
		session.setAttribute("logged", false);
		session.setAttribute("premium", false);
		session.setAttribute("admin", false);
		return false;
	}

	@Override
	public void add(ConferenceApplication application) {
		db.add(application);
	}

	@Override
	public int count() {
		return db.size();
	}
//TEST
	public String list() {
		String ret = "";
		if (!db.isEmpty()) {
			ret += "<table border=\"1\">"
				+ "<tr>"
				+ "<td>Login</td>"
				+ "<td>Hasło</td>"
				+ "<td>E-mail</td>"
				+ "<td>Status Premium</td>"
				+ "<td>Status Admin</td>"
				+ "</tr>";
			for (ConferenceApplication ca : db) {
				ret += ""
						+ "<tr>"
						+ "<td>"+ca.getUsername()+"</td>"
						+ "<td>"+ca.getPassword()+"</td>"
						+ "<td>"+ca.getEmail()+"</td>"
						+ "<td>"+ca.getPremium()+"</td>"
						+ "<td>"+ca.getAdmin()+"</td>"
						+ "</tr>";
			}
			ret += "</table>";
		}
		return ret;
	}
	
	@Override
	public boolean setPremium(String login) {
		if (login.isEmpty()) return false;
		for(ConferenceApplication application: db){
			if(application.getUsername().equalsIgnoreCase(login)) {
				application.setPremium("1");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removePremium(String login) {
		if (login.isEmpty()) return false;
		for(ConferenceApplication application: db){
			if(application.getUsername().equalsIgnoreCase(login)) {
				application.setPremium("0");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean getPremium(String login) {
		if (login.isEmpty()) return false;
		for(ConferenceApplication application: db){
			if(application.getUsername().equalsIgnoreCase(login)) {
				return application.getPremium().equals("1");
			}
		}
		return false;
	}

	
	@Override
	public String getprofil(String login) {
		String ret="";
		for (ConferenceApplication ca : db) {
			if (ca.getUsername().equals(login)) {
				ret = "<table border=\"1\">"
						+ "<tr><td>Login:</td><td>"+ca.getUsername()+"</td></tr>"
						+ "<tr><td>Haslo:</td><td>"+ca.getPassword()+"</td></tr>"
						+ "<tr><td>E-mail:</td><td>"+ca.getEmail()+"</td></tr>"
						+ "<tr><td>Premium:</td><td>"+ca.getPremium()+"</td></tr>"
						+ "<tr><td>Admin:</td><td>"+ca.getAdmin()+"</td></tr>"
						+ "</table>";
				break;
			}
		}
		return ret;
	}

}
