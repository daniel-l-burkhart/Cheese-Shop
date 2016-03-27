
package edu.westga.cheeseshop.controller;

import edu.westga.cheeseshop.model.Clerk;
import edu.westga.cheeseshop.model.Customer;

/**
 * @author danielburkhart
 * @version Spring 2016
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		
		(new Thread(clerk)).start();

		for (int i = 0; i < 10; i++) {

			if (i % 2 == 0) {
				(new Customer(clerk, 1)).start();
			} else {
				(new Customer(clerk, 2)).start();
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		clerk.stop();
	}

}
