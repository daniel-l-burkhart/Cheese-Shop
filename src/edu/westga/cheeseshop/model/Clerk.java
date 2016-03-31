package edu.westga.cheeseshop.model;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Clerk of the shop. Serves customers one at a time.
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class Clerk implements Runnable {

	private CopyOnWriteArrayList<Customer> customers;
	private boolean keepWorking;

	/**
	 * Constructor of class that initializes
	 */
	public Clerk() {
		this.customers = new CopyOnWriteArrayList<Customer>();
		this.keepWorking = true;
	}

	/**
	 * Adds a customer to the queue
	 * 
	 * @param customer
	 *            The current customer
	 */
	public synchronized void addCustomer(Customer customer) {

		if (customer == null) {
			throw new IllegalArgumentException("Customer is null");
		}

		this.customers.add(customer);

		this.sortCustomers();
	}

	/**
	 * Sorts the customers based on their priority.
	 */
	private void sortCustomers() {

		Collections.sort(this.customers, new CustomerComparator());
		
	}

	/**
	 * Run method of clerk thread.
	 */
	@Override
	public void run() {

		while (this.keepWorking) {

			this.serveCustomers();

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private void serveCustomers() {

		while (this.customers.size() > 0) {

			Customer customer = this.customers.remove(0);
			
			System.out.println("Customer with priority " 
					+ customer.getCustomerPriority() 
					+ " is meeting with the clerk");

			customer.beNotifiedByClerk();
			this.serviceSingleCustomer(customer);
		}
	}

	private void serviceSingleCustomer(Customer customer) {
		
		try {
			customer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops the thread from running.
	 */
	public void stop() {
		this.keepWorking = false;
	}

}
