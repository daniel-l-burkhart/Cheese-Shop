
package edu.westga.cheeseshop.model;

/**
 * The Class TicketingSystem.
 *
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class TicketingSystem implements Runnable {

	private static final int NUMBER_OF_TICKETS = 10;
	private boolean keepWorking;
	private int waiting;
	private int currentCustomerTicket;
	private int nextTicket;
	private int ticketNumber;
	private int numberOfTicketsInPlay;

	/**
	 * Ticketing system.
	 */
	public TicketingSystem() {

		this.keepWorking = true;
		this.waiting = 0;
		this.currentCustomerTicket = 0;
		this.nextTicket = 0;
		this.numberOfTicketsInPlay = 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		while (this.keepWorking) {

			if (this.nextTicket >= NUMBER_OF_TICKETS) {
				this.nextTicket = 0;
			}

		}

	}

	/**
	 * Customer enters waiting room.
	 */
	public synchronized void enterWaitingRoom() {

		int ticketNumber = nextTicket++;

		this.waiting++;

		while (this.currentCustomerTicket < ticketNumber) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.waiting--;
		this.leaveShop();

	}

	/**
	 * Gets the waiting customers.
	 *
	 * @return the waiting
	 */
	public int getWaiting() {

		if (this.waiting < 0) {
			throw new IllegalStateException("Negative waiting customers");
		}

		return this.waiting;
	}

	/**
	 * Customer leaves shop.
	 */
	public synchronized void leaveShop() {
		this.numberOfTicketsInPlay--;

		this.currentCustomerTicket++;
		this.notifyAll();
	}

	/**
	 * Stops the thread.
	 */
	public void stop() {
		this.keepWorking = false;
	}

}
