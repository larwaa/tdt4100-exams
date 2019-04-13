package exam2017;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GuestManager implements CapacityListener {

	private Diner diner;
	private Collection<Group> queue = new ArrayList<>();

	public GuestManager(Diner diner){
		this.diner = diner;
		diner.addCapacityListener(this);
	}

	public void groupArrived(Group group){
		if (! diner.addSeating(group)){
			queue.add(group);
		}
	}

	public void groupDeparted(int tableNum){
		diner.removeSeating(tableNum);
	}

	public void capacityChanged(Diner diner){
		for (Group group : queue){
			if (diner.addSeating(group)){
				queue.remove(group);
			}
		}
	}



}
