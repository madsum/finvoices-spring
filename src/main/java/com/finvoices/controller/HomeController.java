package com.finvoices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController{

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
	public ModelAndView index() {
		logger.info("Return to index.jsp");
		return new ModelAndView("index");
	}	
}
