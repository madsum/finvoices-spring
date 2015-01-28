package com.finvoices.serviceImpl;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.finvoices.model.Finvoices;
import com.finvoices.service.XmlPaserService;


@Service
public class XmlPaserServiceImpl implements XmlPaserService {

	private static final Logger logger = LoggerFactory.getLogger(XmlPaserServiceImpl.class);


	public XmlPaserServiceImpl(){
	}


	public Finvoices parseXmlFile(String xmlFile){
		Finvoices fininvoices = null;
		try {
			File fh = new File(xmlFile);
			JAXBContext jaxbContext = JAXBContext.newInstance(Finvoices.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			fininvoices = (Finvoices) jaxbUnmarshaller.unmarshal(fh);
			logger.info("Print: "+fininvoices);
		} catch (JAXBException e) {
			logger.info(e.getMessage());
		} catch (NullPointerException npe) {
			logger.info(npe.getMessage());
		}
		return fininvoices;
	}


	public String getJsonData(Finvoices finvoices){
		ObjectMapper mapper = new ObjectMapper();

		try {

			// convert user object to json string,
			// mapper.writeValue(new File("test.json"), finvoices);
			System.out.println(mapper.writeValueAsString(finvoices));
			return mapper.writeValueAsString(finvoices);

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		catch(Exception ex){

		}
		return "";
	}	
}
