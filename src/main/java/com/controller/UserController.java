package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.RestClient;
import com.model.User;
import com.sessionmanagement.UserSession;


@Controller
public class UserController {
	
	@Autowired
	private RestClient restClient;
	
	// -------------------------- LOGIN MANAGEMENT----------------------------------------------------
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest req, HttpServletResponse res) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		
		if(UserSession.getUser(req.getSession())!=null) {
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView loginPost(@ModelAttribute("userForm") User user, HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:login");
		
		// if any field is null then render login page
		if(user.getUsername() == null || user.getPassword()==null) {
			return mv;
		}
		
		
		// check from REST API's that user exist or not
		if(restClient.login(user)) {
			System.out.println(user+" is saved in session !");
			
			UserSession.saveUserInSession(req.getSession(), user);
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	
	// -------------------------- LOGOUT MANAGEMENT----------------------------------------------------
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView logoutGet(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:login");
		
		UserSession.removeUserFromSession(req.getSession());
		
		return mv;
	}
	
	

}
