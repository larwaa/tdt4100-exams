package exam2017;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Diner {

	private Collection<Table> tables = new ArrayList<>();
	private List<Seating> seatings = new ArrayList<>();
	private List<CapacityListener> capacityListeners = new ArrayList<>();

	public boolean isOccupied(Table table){
		return seatings.stream()
				.anyMatch((t) -> t.getTable() == table);
	}

	public int getCapacity(boolean includeOccupied){
		if (includeOccupied){
			return tables.stream()
					.mapToInt(Table::getSeats)
					.sum();
		}
		return tables.stream()
				.filter(this::isOccupied)
				.mapToInt(Table::getSeats)
				.sum();
	}

	public void addTable(Table table){
		if (! tables.contains(table)){
			tables.add(table);
			capacityChanged();
		}
	}

	private void capacityChanged(){
		capacityListeners.forEach(capacityListener -> capacityListener.capacityChanged(this));
	}

	public void removeTable(Table table){
		if (isOccupied(table)){
			throw new IllegalStateException("Someone's sitting here...");
		}
		tables.remove(table);
		capacityChanged();
	}

	public void mergeTables(Table table1, Table table2, int lostCapacity){
		removeTable(table1);
		removeTable(table2);
		Table table = new CompositeTable(table1, table2, lostCapacity);
		tables.add(table);
		if (lostCapacity != 0){
			capacityChanged();
		}
	}

	public void splitTable(CompositeTable table){
		removeTable(table);
		tables.add(table.getTable1());
		tables.add(table.getTable2());
		capacityChanged();
	}

	private boolean hasCapacity(Table table, int capacity){
		return (!isOccupied(table) && table.getSeats() >= capacity);
	}

	public Collection<Table> findAvailableTables(int capacity){
		return tables.stream()
				.filter(t -> this.hasCapacity(t, capacity))
				.sorted(Comparator.comparingInt(Table::getSeats).reversed())
				.collect(toList());
	}

	private Seating createSeating(Group group){
		Collection<Table> availableTables = findAvailableTables(group.getGuestCount());
		if (availableTables.isEmpty()){
			return null;
		}
		return new Seating(availableTables.iterator().next(), group);
	}

	public boolean addSeating(Group group){
		Seating seating = createSeating(group);
		if (seating != null){
			seatings.add(seating);
			capacityChanged();
			return true;
		}
		return false;
	}

	public void removeSeating(int tableNum){
		for (Seating seating : seatings){
			if (seating.getTable().getTableID() == tableNum){
				seatings.remove(seating);
				capacityChanged();
			}
		}
	}

	public void addCapacityListener(CapacityListener capacityListener){
		if (! capacityListeners.contains(capacityListener)){
			capacityListeners.add(capacityListener);
		}
	}

	public void removeCapacityListener(CapacityListener capacityListener){
		capacityListeners.remove(capacityListener);
	}
}
