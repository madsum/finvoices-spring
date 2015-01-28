package com.finvoices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.finvoices.model.UserInput;


@Controller
@RequestMapping(value="/input")
public class UserInputController{
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserInputController.class);
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("userInput") UserInput userInput, SessionStatus status) {
		
		status.setComplete();
		logger.info("Got search user name: "+userInput.getUserName());
		String buyerName = userInput.getUserName(); 
		return"redirect:/singleBuyer/"+buyerName;

	}

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model){
		UserInput userInput = new UserInput();
		//command object
		model.addAttribute("UserInput", userInput);
		return "InputForm";
	}
}