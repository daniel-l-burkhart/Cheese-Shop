package edu.westga.cheeseshop.model;

/**
 * The customer class.
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class Customer extends Thread {

	private Priority priority;
	private Clerk clerk;
	private boolean clerkIsReady;
	private TicketingSystem ticketingSystem;

	/**
	 * Sets up a customer object with clerk and priority
	 * 
	 * @precondition: clerk != null, priority must be 1 or 2
	 * @postcondition: a customer object is made.
	 * 
	 * @param clerk
	 *            The clerk of our shop
	 * @param priority
	 *            Priority 1 = meek, 2 =bold
	 */
	public Customer(Clerk clerk, Priority priority, TicketingSystem ticketingSystem) {

		if (clerk == null) {
			throw new IllegalArgumentException("Clerk is null");
		} else if (priority == null) {
			throw new IllegalArgumentException("Priority is out of range.");
		} else if (ticketingSystem == null) {
			throw new IllegalArgumentException("Ticketing System is null");
		}

		this.clerk = clerk;
		this.priority = priority;
		this.clerkIsReady = false;
		this.ticketingSystem = ticketingSystem;
	}

	/**
	 * The priority of the customer
	 * 
	 * @return the priority 1 if meek, 2 if bold
	 */
	public Priority getCustomerPriority() {
		return this.priority;
	}

	/**
	 * Notifies customers that clerk is ready to serve.
	 */
	public void beNotifiedByClerk() {
		this.clerkIsReady = true;
	}

	/**
	 * Run method for this thread.
	 */
	public void run() {

		this.ticketingSystem.enterWaitingRoom();

		this.clerk.addCustomer(this);

		while (!this.clerkIsReady) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Customer " + this.priority + " is being served");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.ticketingSystem.leaveShop();

	}

}
