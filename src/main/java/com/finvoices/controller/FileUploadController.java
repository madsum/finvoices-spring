package com.finvoices.controller;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.finvoices.model.Finvoices;
import com.finvoices.service.DatabaseRreaderWritter;
import com.finvoices.service.XmlPaserService;


@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

	@Autowired 
	private DatabaseRreaderWritter databaseRreaderWritter;
	
	@Autowired
	private XmlPaserService xmlPaserService;	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadFileHandler() 
	{
		return "upload";
	}
	

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file) {
		ModelAndView mav = null;
	
		if (!file.isEmpty()) {
			try {				
				String xmlFile = databaseRreaderWritter.uploadXmlFile(file.getOriginalFilename(), file.getBytes());
				logger.info("file name: "+xmlFile);
				Finvoices fininvoices = xmlPaserService.parseXmlFile(xmlFile);				
				if(fininvoices != null){
					logger.info("parsing succesful! Check frist SellerOrganisationUnitNumbere:  "+fininvoices.getFinvoices().get(0).getSellerOrganisationUnitNumber());
					boolean success = databaseRreaderWritter.writeInDatabase(fininvoices);
					logger.info("return afer write success = "+success);
					String fileName = file.getOriginalFilename();
					fileName = fileName.split(Pattern.quote("."))[0];
					if(success){
						mav = new ModelAndView("redirect:/buyer/listBuyer/"+fileName); 
					}
					else{
						return new ModelAndView("404");
					}
					
				} else {
					mav = new ModelAndView("404");
					mav.addObject("message", "Invalid finvoice xml file");
				}				
			} catch (Exception e) {
				logger.info("You failed to upload  => " + e.getMessage());
				mav = new ModelAndView("404");
				mav.addObject("message", "Invalid finvoice xml file");
			}
		}
		return mav; 
	}
}
