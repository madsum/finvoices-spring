package com.finvoices.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.finvoices.model.Finvoices;
import com.finvoices.service.XmlPaserService;
import com.finvoices.service.XmlPaserServiceImpl;


@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

	@Autowired
	private XmlPaserService xmlPaserService;	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadFileHandler() 
	{
		return "upload";
	}
	

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file) {
		ModelAndView mav = new ModelAndView("404");
		long time = System.currentTimeMillis();
		String name = Long.toString(time)+".xml";
	
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File xmlFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(xmlFile));
				stream.write(bytes);
				stream.close();
				Finvoices fininvoices = xmlPaserService.parseXmlFile(xmlFile);
				
				if(fininvoices != null){
					logger.info("parsing succesful! Check frist SellerOrganisationUnitNumbere:  "+fininvoices.getFinvoices().get(0).getSellerOrganisationUnitNumber());
					boolean success = xmlPaserService.writeInDatabase(fininvoices);
					logger.info("return afer write success = "+success);
					mav = new ModelAndView("redirect:/buyer/listBuyer");
					return mav;
				}
				
			} catch (Exception e) {
				logger.info("You failed to upload  => " + e.getMessage());
				mav = new ModelAndView("Error");
				return mav;
			}
		}
		return mav; 
	}
}
