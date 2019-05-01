package exam2012;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DayPlan {

	private List<TimeSlot> timeSlots = new ArrayList<>();

	public void addTimeSlot(TimeSlot timeSlot){
		if (! timeSlots.contains(timeSlot)){
			timeSlots.add(timeSlot);
			Collections.sort(timeSlots);
		}
	}

	public void removeTimeSlot(TimeSlot timeSlot){
		timeSlots.remove(timeSlot);
	}

	public TimeSlot getTimeSlotAt(int hours, int minutes){
		return getAllTimeSlots().stream()
						.filter(timeSlot -> timeSlot.contains(hours, minutes))
						.findFirst()
						.orElse(null);
	}

	protected Collection<TimeSlot> getAllTimeSlots(){
		return timeSlots;
	}

	public boolean containsOverlapping(){
		List<TimeSlot> timeSlots = new ArrayList<>(getAllTimeSlots());
		for (int i = 0; i < timeSlots.size() - 1; i++){
			if (timeSlots.get(i).overlaps(timeSlots.get(i + 1))){
				return true;
			}
		}
		return false;
	}

	public Collection<TimeSlot> getFreeTime(){
		Collection<TimeSlot> results = new ArrayList<>();
		for (int i = 0; i < timeSlots.size() - 1; i++){
			TimeSlot ts1 = timeSlots.get(i), ts2 = timeSlots.get(i + 1);
			if (timeBetweenSlots(ts1, ts2) > 0) {
				results.add(new TimeSlot("Free time",
						ts1.getEndTime().hours,
						ts1.getEndTime().minutes,
						timeBetweenSlots(ts1, ts2)));
			}
		}
		return results;
	}

	private int timeBetweenSlots(TimeSlot ts1, TimeSlot ts2){
		return TimeSlot.asMinutes(ts1.getEndTime().hours, ts1.getEndTime().minutes)
				- TimeSlot.asMinutes(ts2.getStartTime().hours, ts2.getStartTime().minutes);
	}
}
