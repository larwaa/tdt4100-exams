package kexam2016;

public class Course implements Comparable<Course> {

	private String time; //<semester><year>
	private String code;
	private double credits;

	public Course(String code){
		this.code = code;
	}

	public void setCredits(double credits){
		this.credits = credits;
	}

	public double getCredits(){
		return credits;
	}

	public String getCode(){
		return code;
	}

	public int getYear() {
		return Integer.parseInt(time.substring(1));
	}

	public char getSemester() {
		return time.charAt(0);
	}

	/**
	 * Gets the time this Course is given, in the format <semester><year>
	 * E.g. if the semester is 'S' and the year is 2016,
	 * it should return S2016.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time that this Course is taught. The format is the semester
	 * (char) followed by the year. The year may be shortened to two digits;
	 * if it is below 50 then 2000 should be added, otherwise 1900.
	 * E.g. S16 means Spring 2016, while F86 means Fall 1986.
	 * @param time The time in the format <semester><year>
	 * @throws IllegalArgumentException if the format is incorrect
	 */
	public void setTime(String time) {
		checkTimeHelper(time);
		int year = Integer.parseInt(time.substring(1));
		this.time = time.toUpperCase().charAt(0) + Integer.toString((year < 50 ? 2000 : 1900) + year);
	}

	public static void checkTimeHelper(String time) throws IllegalArgumentException {
		if (time.length() != 3 && time.length() != 5){
			throw new IllegalArgumentException();
		}

		char c = time.toUpperCase().charAt(0);
		if (c != 'S' && c != 'F'){
			throw new IllegalArgumentException();
		}

		try{
			Integer.parseInt(time.substring(1));
		} catch (NumberFormatException e){
			throw new IllegalArgumentException(e);
		}
	}

	public int compareTo(Course other){
		int year = this.getYear() - other.getYear();
		return (year == 0 ? other.getSemester() - this.getSemester() : year);
	}
}