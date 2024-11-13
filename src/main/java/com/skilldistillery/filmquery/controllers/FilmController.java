package com.skilldistillery.filmquery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.entities.Film;

@Controller
public class FilmController {
	private final DatabaseAccessor dao;
	
	public FilmController( DatabaseAccessor dao) {
		this.dao = dao;
	}

// Create some web pages	
	
	@RequestMapping(path= { "/", "index.do"})
	 public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/index.jsp");
		return mv;
		
		
	} //end RequestMapping
	
	@RequestMapping(path= {"findById.do"})
	  public String findFilmById( @RequestParam("id")int id, Model m ) {
		Film film = null;
		try {
			 film = dao.findFilmById(id);
		m.addAttribute("film", film);	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "WEB-INF/film.jsp";
		
	}
}
