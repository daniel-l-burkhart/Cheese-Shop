
package edu.westga.cheeseshop.model;

/**
 * The Class TicketingSystem.
 *
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class TicketingSystem {

	private static final int MAX_TICKET_NUMBER = 9;

	private int waiting;
	private int currentTurn;
	private int nextTicket;

	/**
	 * Ticketing system.
	 */
	public TicketingSystem() {

		this.waiting = 0;
		this.currentTurn = 0;
		this.nextTicket = 0;

	}

	/**
	 * Customer enters waiting room.
	 */
	public synchronized void enterWaitingRoom() {

		this.checkSystemToReset();

		int ticketNumber = this.nextTicket++;

		this.waiting++;

		while (this.currentTurn < ticketNumber) {
			this.waitInLine();
		}

		this.waiting--;

		this.leaveShop();

	}

	private void checkSystemToReset() {

		if ((this.nextTicket + 1) == MAX_TICKET_NUMBER) {
			this.nextTicket = 0;
		}
	}

	private void waitInLine() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Customer leaves shop.
	 */
	public synchronized void leaveShop() {

		this.currentTurn++;
		this.notifyAll();
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

}
