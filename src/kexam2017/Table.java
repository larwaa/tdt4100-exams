package kexam2017;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Manages the set of ordered items for a table (set of guests).
 */
public class Table implements KitchenListener{

	private Menu menu;
	private Collection<MenuItem> menuItems = new ArrayList<>();
	private Kitchen kitchen;

	public Table(Menu menu) {
        this.menu = menu;
	}

	public double getPrice() throws IllegalStateException {
        try{
        	int total = 0;
        	total += menuItems.stream().mapToDouble(menuItem -> menu.getPrice(menuItem)).sum();
        	return total;
		}
        catch (IllegalArgumentException e){
        	throw new IllegalStateException(e);
		}
	}

	public void addItem(MenuItem item){
		menuItems.add(item);
		kitchen.menuItemAdded(this, item);
	}

	/**
	 * Sets the Kitchen that should be notified when items are added.
	 * Note that this method may be called several times with different Kitchen objects.
	 * @param kitchen
	 */
	public void setKitchen(Kitchen kitchen) {
        if (this.kitchen != null) {
			this.kitchen.removeKitchenListener(this);
		}
		this.kitchen = kitchen;
		if (kitchen != null){
			kitchen.addKitchenListener(this);
		}
	}

	public void courseReady(Table table, Course course){
	}
}