package exam2017;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Diner {

	private Collection<Table> tables = new ArrayList<>();
	private List<Seating> seatings = new ArrayList<>();

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
		}
	}

	public void removeTable(Table table){
		if (isOccupied(table)){
			throw new IllegalStateException("Someone's sitting here...");
		}
		tables.remove(table);
	}

	public void mergeTables(Table table1, Table table2, int lostCapacity){
		removeTable(table1);
		removeTable(table2);
		Table table = new Table(table1.getSeats() + table2.getSeats() - lostCapacity);
		tables.add(table);
	}

	public void splitTable(Table table, int capacity1, int capacity2){
		removeTable(table);
		tables.add(new Table(capacity1));
		tables.add(new Table(capacity2));
	}

	public boolean hasCapacity(Table table, int capacity){
		return (!isOccupied(table) && table.getSeats() >= capacity);
	}

	public Collection<Table> findAvailableTables(int capacity){
		return tables.stream()
				.filter(t -> this.hasCapacity(t, capacity))
				.sorted(Comparator.comparingInt(Table::getSeats).reversed())
				.collect(toList());
	}

	public Seating createSeating(Group group){
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
			return true;
		}
		return false;
	}

	public void removeSeating(int tableNum){
		for (Seating seating : seatings){
			if (seating.getTable().getTableID() == tableNum){
				seatings.remove(seating);
			}
		}
	}
}
