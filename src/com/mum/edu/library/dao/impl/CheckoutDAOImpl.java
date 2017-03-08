package com.mum.edu.library.dao.impl;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mum.edu.library.dao.ICheckoutDAO;
import com.mum.edu.library.model.Books;
import com.mum.edu.library.model.CheckoutEntry;
import com.mum.edu.library.model.CheckoutRecord;
import com.mum.edu.library.model.CheckoutRecords;

public class CheckoutDAOImpl implements ICheckoutDAO {
	private final String FILE_NAME = "D:\\MUM\\MPP\\LibraryProblem\\CheckoutRecords.xml";

	@Override
	public void save(CheckoutRecord saveCheckoutRecord) {
		// TODO Auto-generated method stub
		JAXBContext jaxbContext = null;
		CheckoutRecords checkoutRecords = new CheckoutRecords();
		File file = new File(FILE_NAME);
		
		try {

			jaxbContext = JAXBContext.newInstance(CheckoutRecords.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			checkoutRecords = (CheckoutRecords) jaxbUnmarshaller.unmarshal(file);
			checkoutRecords.getCheckoutRecords().add(saveCheckoutRecord);
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(checkoutRecords, file);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CheckoutRecord> read() {
		// TODO Auto-generated method stub
				JAXBContext jaxbContext = null;
				CheckoutRecords checkoutRecords = new CheckoutRecords();
				File file = new File(FILE_NAME);
				
				try {

					jaxbContext = JAXBContext.newInstance(CheckoutRecords.class);
					
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					
					checkoutRecords = (CheckoutRecords) jaxbUnmarshaller.unmarshal(file);
					
					
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				
		return checkoutRecords.getCheckoutRecords();
	}
}