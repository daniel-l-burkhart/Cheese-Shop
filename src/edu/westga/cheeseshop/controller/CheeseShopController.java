package edu.westga.cheeseshop.controller;

import edu.westga.cheeseshop.model.Clerk;
import edu.westga.cheeseshop.model.Customer;
import edu.westga.cheeseshop.model.Priority;
import edu.westga.cheeseshop.model.TicketingSystem;

/**
 * Controller class that ties in model and main
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class CheeseShopController {

	private Clerk clerk;
	private TicketingSystem ticketingSystem;
	private Thread clerkThread;

	private static final int NUMBER_OF_CUSTOMERS = 10;

	/**
	 * Initializes all variables.
	 */
	public CheeseShopController() {

		this.clerk = new Clerk();
		this.ticketingSystem = new TicketingSystem();
		this.clerkThread = new Thread(this.clerk);

	}

	/**
	 * Opens the cheese shop for a specific amount of time.
	 */
	public void openShop() {

		this.clerkThread.start();
		this.startCustomers();
	}

	private void startCustomers() {

		for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
			this.alternateCustomers(i);
		}

	}

	private void alternateCustomers(int i) {

		if (i % 2 == 0) {
			(new Customer(clerk, Priority.BOLD, ticketingSystem)).start();
		} else {
			(new Customer(clerk, Priority.MEEK, ticketingSystem)).start();
		}

	}

	/**
	 * Keeps shop open for x seconds.
	 *
	 * @param numberOfSeconds
	 *            the number of seconds
	 */
	public void keepOpenForXSeconds(int numberOfSeconds) {

		try {
			Thread.sleep(numberOfSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Closes the cheese shop after all customers waiting have been served.
	 */
	public void closeShop() {

		this.clerk.stop();

		System.out.println(System.lineSeparator() + "Numbers of customers waiting: " + this.ticketingSystem.getWaiting()
				+ System.lineSeparator());
	}

}
