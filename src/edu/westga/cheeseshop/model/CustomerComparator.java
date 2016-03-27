/**
 * 
 */
package edu.westga.cheeseshop.model;

import java.util.Comparator;

/**
 * @author danielburkhart
 * @version Spring 2016
 */
public class CustomerComparator implements Comparator<Customer> {

	/**
	 * Compare method to sort customers in descending order.
	 */
	@Override
	public int compare(Customer o1, Customer o2) {

		return Integer.compare(o2.getCustomerPriority(), o1.getCustomerPriority());
	}

}
