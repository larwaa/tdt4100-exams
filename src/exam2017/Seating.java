package exam2017;

public class Seating {

	private final Table table;
	private final Group group;

	public Seating(Table table, Group group){
		checkSeating(table, group);
		this.table = table;
		this.group = group;
	}

	private void checkSeating(Table table, Group group){
		if (group.getGuestCount() > table.getSeats()){
			throw new IllegalArgumentException("Too many people, too few chairs. " + group.getGuestCount() + " > " + table.getSeats());
		}
	}

	public Table getTable(){
		return table;
	}

	public Group getGroup(){
		return group;
	}

}
