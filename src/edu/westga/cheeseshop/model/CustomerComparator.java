package edu.westga.cheeseshop.model;

import java.util.Comparator;

/**
 * Class to compare customers based on priority
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class CustomerComparator implements Comparator<Customer> {

	/**
	 * Compare method to sort customers by priority to prove
	 * ticketing system functionality.
	 */
	@Override
	public int compare(Customer o1, Customer o2) {
		
		return o2.getCustomerPriority().compareTo(o1.getCustomerPriority());
	}

}
