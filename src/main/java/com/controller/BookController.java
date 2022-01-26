package com.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Book;
import com.sessionmanagement.UserSession;

@Controller
public class BookController {

	@Autowired
	private RestClient restClient;
	
	// See all Books
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView searchPost(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("allbooks");

		if (!isUserLoggedIn(req)) {
			mv.setViewName("redirect:login");
		}else {
			// Fetch Data and add to ModelAndView object
			List<Book> bookList = restClient.getAllBook();
			System.out.println(bookList);
			Book[] bookArr = listToArray(bookList);
			System.out.println(Arrays.toString(bookArr));
			mv.addObject("bookArr",bookArr);
		}

		return mv;
	}

	// Add a book GET
	@RequestMapping(value = "addbook", method = RequestMethod.GET)
	public ModelAndView addBookGet(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addbook");

		if (!isUserLoggedIn(req)) {
			mv.setViewName("redirect:login");
		}else {
			// fetch all the auther from Application 2 
			// and add to mv object
			List<String> authorsList = restClient.getAllAuthors();
			String[] authorsArr = authorsListToAuthorsArr(authorsList);
			mv.addObject("authorsArr",authorsArr);
		}
		return mv;
	}

	// Add a book POST
	@RequestMapping(value = "addbook", method = RequestMethod.POST)
	public ModelAndView addBookPost(@ModelAttribute("bookForm") Book book, HttpServletRequest req,
			HttpServletResponse res) throws JsonProcessingException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");

		if (!isUserLoggedIn(req)) {
			mv.setViewName("redirect:login");
		} else {
			// Fetch Data and send to Application 2 to add to DB
			restClient.addBook(book);
		}
		System.out.println(book);
		return mv;
	}

	// Update a book GET
	@RequestMapping(value = "updatebook/{id}", method = RequestMethod.GET)
	public ModelAndView updateBookGet(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/updatebook");

		if (!isUserLoggedIn(req)) {
			mv.setViewName("redirect:login");
		}else {
			// fetch the book by id
			// put the book object into mv
			// if book not found redirect:/
			Book book = restClient.getBookById(id);
			String[] authorsArr = authorsListToAuthorsArr(restClient.getAllAuthors());
			
			mv.addObject("book",book);
			mv.addObject("authorsArr",authorsArr);
		}

		return mv;
	}

	// Update a book POST
	@RequestMapping(value = "updatebook", method = RequestMethod.POST)
	public ModelAndView updateBookPost(@ModelAttribute("updateBookForm") Book book, HttpServletRequest req,
			HttpServletResponse res) throws JsonProcessingException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");

		if (!isUserLoggedIn(req)) {
			mv.setViewName("redirect:login");
		} else {
			// Fetch Data and send to Application 2 to add to DB
			System.out.println("Data received from updatebook.jsp : "+book);
			restClient.updateBook(book);
		}
		return mv;
	}

	// Delete a book GET
		@RequestMapping(value = "deletebook/{id}", method = RequestMethod.GET)
		public ModelAndView deleteBookGet(@PathVariable("id") int id , HttpServletRequest req, HttpServletResponse res) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/");

			if (!isUserLoggedIn(req)) {
				mv.setViewName("redirect:/login");
			}else {
				// send id to Application 2 and delete book
				restClient.deleteBook(id);
			}
			return mv;
		}	
		
		
		
		// Utility methods
		
		public static boolean isUserLoggedIn(HttpServletRequest req) {
			return UserSession.getUser(req.getSession()) != null;
		}

		public static Book[] listToArray(List<Book> bookList) {
			Book[] bookArr = new Book[bookList.size()];
			for(int i=0;i<bookList.size();i++) {
				bookArr[i] = bookList.get(i);
			}
			return bookArr;
		}
		
		public static String[] authorsListToAuthorsArr(List<String> authorsList) {
			String[] authorsArr = new String[authorsList.size()];
			for(int i=0;i<authorsList.size();i++) {
				authorsArr[i] = authorsList.get(i);
			}
			return authorsArr;
		}

}
