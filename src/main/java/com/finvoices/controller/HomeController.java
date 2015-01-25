package com.finvoices.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.finvoices.model.Finvoices;
import com.finvoices.service.XmlPaserService;


@Controller
public class HomeController implements ServletContextAware{

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private ServletContext servletContext;

	@Autowired
	private XmlPaserService xmlPaserService;	


	public void setServletContext(ServletContext pServletContext) {
		servletContext = pServletContext;
	}

	@RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}
/*
	@RequestMapping(value="/parse", method=RequestMethod.GET)
	public ModelAndView parseAllXml() {

		//ModelAndView mav = new ModelAndView("test");

		resourceReaderService.ReadResources();
		ModelAndView mav = new ModelAndView("test");

		return new ModelAndView("test");
	}
*/

}
