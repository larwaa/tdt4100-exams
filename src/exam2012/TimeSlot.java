package exam2012;

public class TimeSlot implements Comparable<TimeSlot> {

	private final String description;
	private final int startTime, endTime;

	public TimeSlot(String description, int hours, int minutes, int duration){
		this.description = description;
		this.startTime = asMinutes(hours, minutes);
		this.endTime = asMinutes(hours, minutes) + duration;
	}

	static int asMinutes(int hours, int minutes){
		return hours * 60 + minutes;
	}

	static int asHours(int minutes){
		return minutes / 60;
	}

	public String toString(){
		String startTime = getStartTime().toString();
		String endTime = getEndTime().toString();

		startTime = (startTime.length() < 2 ? 0 : "") + startTime;
		endTime = (endTime.length() < 2 ? 0 : "") + endTime;

		return String.format("%s@%s-%s", description,
										startTime,
										endTime);
	}

	private String timeStringHelper(int hours, int minutes){

		return String.format("%s:%s", hours, minutes);
	}

	public int getDuration(){
		return endTime - startTime;
	}

	public DayTime getStartTime(){
		return new DayTime(asHours(startTime), startTime % 60);
	}

	public DayTime getEndTime(){
		return new DayTime(asHours(endTime), endTime % 60);
	}

	public boolean contains(int hours, int minutes){
		int time = asMinutes(hours, minutes);
		return (startTime <= time && endTime > time);
	}

	public boolean overlaps(TimeSlot timeSlot){
		return startTime < timeSlot.endTime && endTime > timeSlot.startTime;
	}

	public int compareTo(TimeSlot timeSlot){
		int diff = startTime - timeSlot.startTime;
		return diff != 0 ?
				diff : endTime - timeSlot.endTime;
	}
}
