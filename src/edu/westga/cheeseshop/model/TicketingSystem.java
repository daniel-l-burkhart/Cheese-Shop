
package edu.westga.cheeseshop.model;

/**
 * The TicketingSystem that will ensure fairness and eliminate thread starvation.
 *
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class TicketingSystem {

	private static final int MAX_TICKET_NUMBER = 10;

	private int waiting;
	private long turn;
	private long next;

	/**
	 * Ticketing system.
	 */
	public TicketingSystem() {

		this.waiting = 0;
		this.turn = 0;
		this.next = 0;

	}

	/**
	 * Customer enters waiting room.
	 */
	public synchronized void enterWaitingRoom() {

		this.checkSystemToReset();

		long myturn = this.next;
		this.next++;

		this.waiting++;

		while (myturn != this.turn) {
			this.waitInLine();
		}

		this.waiting--;
	}

	private void checkSystemToReset() {

		if ((this.next) == MAX_TICKET_NUMBER) {
			this.next = 0;
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

		this.turn++;
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
