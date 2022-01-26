package com.sessionmanagement;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.model.User;

@Component
public class UserSession {
	public static void saveUserInSession(HttpSession httpSession, User user) {
		httpSession.setAttribute("username", user.getUsername());
		httpSession.setAttribute("password", user.getPassword());
	}
	
	public static void removeUserFromSession(HttpSession httpSession) {
		httpSession.invalidate();
	}
	
	public static User getUser(HttpSession httpSession) {
		String username = (String) httpSession.getAttribute("username");
		String password = (String) httpSession.getAttribute("password");
		
		if(username==null || password==null) {
			return null;
		}
		
		User user = new User(username, password);
		return user;
	}

}
