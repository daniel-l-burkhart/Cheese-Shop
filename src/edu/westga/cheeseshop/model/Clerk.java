package edu.westga.cheeseshop.model;

import java.util.ArrayList;
import java.util.Collections;

public class Clerk implements Runnable {

	private ArrayList<Customer> customers;
	private boolean keepWorking;

	/**
	 * Constructor of class that initializes
	 */
	public Clerk() {
		this.customers = new ArrayList<Customer>();
		this.keepWorking = true;
	}

	public synchronized void addCustomer(Customer customer) {
		
		if (customer == null) {
			throw new IllegalArgumentException("Customer is null");
		}

		this.customers.add(customer);

		this.sortCustomers();
	}

	private void sortCustomers() {

		/*
		 * Collections.sort(this.customers, new Comparator<Customer>() {
		 * 
		 * @Override public int compare(Customer customer1, Customer customer2)
		 * {
		 * 
		 * return Integer.compare(customer2.getCustomerPriority(),
		 * customer1.getCustomerPriority()); // return
		 * customer2.getCustomerPriority() - // customer1.getCustomerPriority();
		 * }
		 * 
		 * });
		 */

		Collections.sort(this.customers, new CustomerComparator());

	}

	/**
	 * Stops the thread from running.
	 */
	public void stop() {
		this.keepWorking = false;
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

	/**
	 * Serves the customers.
	 */
	private void serveCustomers() {
		while (this.customers.size() > 0) {

			Customer customer = this.customers.remove(0);

			System.out
					.println("Customer with priority " + customer.getCustomerPriority() + " is meeting with the clerk");

			customer.beNotifiedByClerk();

			this.serviceSingleCustomer(customer);
		}
	}

	/**
	 * @param customer
	 */
	private void serviceSingleCustomer(Customer customer) {
		try {
			customer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
