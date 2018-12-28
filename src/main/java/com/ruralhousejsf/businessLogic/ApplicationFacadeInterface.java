package com.ruralhousejsf.businessLogic;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.ruralhousejsf.dataAccess.HibernateDataAccessInterface;
import com.ruralhousejsf.domain.Client;
import com.ruralhousejsf.domain.Offer;
import com.ruralhousejsf.domain.RuralHouse;
import com.ruralhousejsf.exceptions.BadDatesException;

public interface ApplicationFacadeInterface {

	/**
	 * Initialize the data base.
	 */
	public void initializeDB();

	/**
	 * Set the DataAccess object for the business logic
	 * 
	 * @param dataAccess
	 */
	public void setDataAccess(HibernateDataAccessInterface dataAccess);

	/**
	 * Creates a RuralHouse in the database with his description and his city.
	 * 
	 * @param description
	 * @param city
	 * @return RuralHouse created
	 */
	public RuralHouse createRuralHouse(String description, String city);

	/**
	 * Creates a Offer of a RuralHouse with his startDate, his endDate and his price.
	 * 
	 * @param ruralHouse
	 * @param startDate
	 * @param endDate
	 * @param price
	 * @return Offer of the RuralHouse created
	 * @throws BadDatesException 
	 */
	public Offer createOffer(RuralHouse ruralHouse, LocalDate startDate, LocalDate endDate, double price) throws BadDatesException;

	/**
	 * Creates a Offer of a RuralHouse with his startDate, his endDate and his price.
	 * 
	 * @param ruralHouse
	 * @param startDate
	 * @param endDate
	 * @param price
	 * @return Offer of the RuralHouse created
	 * @throws BadDatesException 
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date startDate, Date endDate, double price) throws BadDatesException;

	/**
	 * Creates a Client in the database and returns the created client
	 * 
	 * @param username
	 * @param password
	 * @return Client created
	 */
	public Client createClient(String username, String password);

	/**
	 * Obtain all the rural houses.
	 * 
	 * @return list of rural houses
	 */
	public List<RuralHouse> getAllRuralHouses();

	/**
	 * Obtain all the offers of a RuralHouse between the given range of dates.
	 * 
	 * @param ruralHouse
	 * @param startDate
	 * @param endDate
	 * @return vector of offers
	 * @throws BadDatesException 
	 */
	public List<Offer> getOffers(RuralHouse ruralHouse, LocalDate startDate, LocalDate endDate) throws BadDatesException;

	/**
	 * Obtain all the offers of a RuralHouse between the given range of dates.
	 * 
	 * @param ruralHouse
	 * @param startDate
	 * @param endDate
	 * @return vector of offers
	 * @throws BadDatesException 
	 */
	public List<Offer> getOffers(RuralHouse ruralHouse, Date startDate, Date endDate) throws BadDatesException;

	/**
	 * Verify that the login is correct with for the given username and a password.
	 * 
	 * @param username
	 * @param password
	 * @return <code>true</code> if the operation is successful, <code>false</code> otherwise
	 */
	public boolean login(String username, String password);

	/**
	 * Delete the persistent instance with the given id
	 * 
	 * @param <T> the key type
	 * @param cls the instance class
	 * @param key the instance key
	 */
	public <T extends Serializable> void delete(Class<?> cls, T key);

}