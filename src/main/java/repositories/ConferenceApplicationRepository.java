package repositories;
import javax.servlet.http.HttpSession;

import domain.ConferenceApplication;

public interface ConferenceApplicationRepository {
	ConferenceApplication getApplicationByEmailAddress(String email);
	void add(ConferenceApplication application);
	int count();
	String list();
	boolean checkLogin(HttpSession session, String username, String password);
	boolean setPremium(String username);
	String getprofil(String parameter);
	boolean getPremium(String login);
	boolean removePremium(String login);
}
