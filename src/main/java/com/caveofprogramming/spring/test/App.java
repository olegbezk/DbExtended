package com.caveofprogramming.spring.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/caveofprogramming/spring/test/beans/beans.xml");

		OffersDAO offersDao = (OffersDAO) context.getBean("offersDao");
		
		Offer offer4 = new Offer(6, "Karamel", "karamel@email.com", "Coding Java");
		
		if (offersDao.update(offer4)) {
			System.out.println("Offer updated");
		}
		
		try {
			
//			Offer offer2 = new Offer("Wreck", "some@email.com", "Wreck it up");
//			Offer offer3 = new Offer("Kara", "kara@email.com", "Coding Java");
//			
//			if(offersDao.create(offer2)) {
//				System.out.println("Creted offer object");
//			}
//			
//			if(offersDao.create(offer3)) {
//				System.out.println("Creted offer object");
//			}
			
			List<Offer> offersLs = new ArrayList<Offer>();
			
			offersLs.add(new Offer("Beyonce", "beyonce@email.com", "Manager"));
			offersLs.add(new Offer("Donna", "donna@email.com", "Back - end developer"));
			
			int[] updVals = offersDao.create(offersLs);
			
			for (int i : updVals) {
				System.out.println("Updated: " + i + " rows.");
			}
			
			offersDao.delete(44);
			
			List<Offer> offers = offersDao.getOffers();

			for (Offer offer : offers) {
				System.out.println(offer);
			}
			
			Offer offer = offersDao.getOffer(2);
			
			System.out.println("Should be Donna: " + offer);
		}
		catch(CannotGetJdbcConnectionException ex) {
			System.out.println("Unable to connect to database.");
		}
		catch (DataAccessException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
		}

		((ClassPathXmlApplicationContext) context).close();
	}

}
