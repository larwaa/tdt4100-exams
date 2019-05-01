package kexam2017;

import java.util.HashMap;
import java.util.Map;

public class Menu {

	 private Map<MenuItem, Double> menuItems = new HashMap<>();

	/**
	 * Gets the price for a Course.
	 * @return the price
	 * @throws IllegalArgumentException if this Menu doesn't include the provided Course
	 */
	public double getPrice(MenuItem menuItem) throws IllegalArgumentException {
        if (! menuItems.containsKey(menuItem)){
        	throw new IllegalArgumentException();
		}
        if (menuItem instanceof Meal){
			double price = menuItems.get(menuItem);
			if (price == 0){
				for (Course course : (Meal) menuItem){
					price += getPrice(course);
				}
			}
			return price;
		}
		return menuItems.get(menuItem);
	}

	public void updatePrice(MenuItem menuItem, double price) {
        menuItems.put(menuItem, price);
	}
}
