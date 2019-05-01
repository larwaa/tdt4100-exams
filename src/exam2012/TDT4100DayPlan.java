package exam2012;

public class TDT4100DayPlan extends DayPlan {

	private TimeSlot tdt4100Lecture = new TDT4100Lecture();

	public TDT4100DayPlan(){
		super();
		addTimeSlot(tdt4100Lecture);
	}

	@Override
	public void addTimeSlot(TimeSlot timeSlot){
		if (timeSlot.overlaps(tdt4100Lecture)){
			throw new IllegalArgumentException("Overlaps with TDT4100 lecture!");
		}
		super.addTimeSlot(timeSlot);
	}

	@Override
	public void removeTimeSlot(TimeSlot timeSlot){
		if (timeSlot == tdt4100Lecture){
			throw new IllegalArgumentException("You cannot remove the TDT4100 lecture!");
		}
		super.removeTimeSlot(timeSlot);
	}
}
