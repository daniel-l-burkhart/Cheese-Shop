
package edu.westga.cheeseshop.model;

import java.util.ArrayList;

/**
 * @author danielburkhart
 *
 */
public class TicketingSystem implements Runnable {

	private ArrayList<Integer> tickets;
	private int numberOfTickets;
	private boolean keepWorking;
	private int waiting;
	private int currentCustomerTicket;
	private int nextTicketToBePulled;

	private TicketingSystem() {
		this.tickets = new ArrayList<Integer>();
		this.keepWorking = true;
		this.waiting = 0;
		this.currentCustomerTicket = 0;
		this.nextTicketToBePulled = 0;
	}

	/**
	 * Ticketing system.
	 * 
	 * @param numberOfTickets
	 *            Number of tickets to be used.
	 */
	public TicketingSystem(int numberOfTickets) {

		this();

		if (numberOfTickets < 0) {
			throw new IllegalArgumentException("Tickets cannot be negative");
		}

		this.numberOfTickets = numberOfTickets;

	}

	public int dispenseTicket() {

		if (this.tickets.size() == 0) {
			throw new IllegalStateException("No more tickets. Customers are angry");
		}

		int ticket = this.tickets.get(0);
		this.tickets.remove(0);
		return ticket;
	}

	@Override
	public void run() {

		while (this.keepWorking) {

			if (this.tickets.size() == 0) {
				this.stockTickets();
			}

		}

	}

	private void stockTickets() {
		for (int i = 0; i < this.numberOfTickets; i++) {
			this.tickets.add(i);
		}
	}

	public synchronized void enterWaitingRoom() {
		this.waiting++;
		
		

	}

	public void stop() {
		this.keepWorking = false;
	}

}
