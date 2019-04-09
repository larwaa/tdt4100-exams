package exam2017;

public class Table {

	private static int tableID = 1;
	private final int seats;
	private final int num;

	public Table(int seats){
		this.seats = seats;
		this.num = tableID++;
	}

	public int getSeats(){
		return seats;
	}

	public int getTableID(){
		return tableID;
	}

}
