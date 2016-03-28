package edu.westga.cheeseshop.controller;

/**
 * Class that contains main method.
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class Application {

	/**
	 * The entry point of the program
	 * 
	 * @param args
	 *            The args passed in.
	 */
	public static void main(String[] args) {

		CheeseShopController controller = new CheeseShopController();

		System.out.println("Begin" + System.lineSeparator());

		controller.openShop();
		controller.keepOpenForXSeconds(30);
		controller.closeShop();

		System.out.println("End");

	}

}
