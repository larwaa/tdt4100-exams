package exam2010;

public class Date {

	private int day, month, year;

	public Date(int day, int month, int year){
		setDay(day);
		setMonth(month);
		setYear(year);
	}

	private void checkValidDay(int day){
		if (day < 1 || day > 31) throw new IllegalArgumentException();
	}

	private void checkValidMonth(int month){
		if (month < 1 || month > 12) throw new IllegalArgumentException();
	}

	private void checkValidYear(int year){
		//Velger å ikke sjekke negative årstall, siden det kan være før år 0.
	}

	public int getDay() {
		return day;
	}

	public int getMonth(){
		return month;
	}

	public int getYear() {
		return year;
	}

	public void setDay(int day) {
		checkValidDay(day);
		this.day = day;
	}

	public void setMonth(int month) {
		checkValidMonth(month);
		this.month = month;
	}

	public void setYear(int year) {
		checkValidYear(year);
		this.year = year;
	}
}
