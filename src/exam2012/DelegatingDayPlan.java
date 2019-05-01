package exam2012;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DelegatingDayPlan extends DayPlan {

	private DayPlan delegate;

	public DelegatingDayPlan(DayPlan delegate){
		super();
		this.delegate = delegate;
	}

	@Override
	protected Collection<TimeSlot> getAllTimeSlots(){
		Collection<TimeSlot> allTimeSlots = super.getAllTimeSlots();

		if (delegate != null){
			List<TimeSlot> timeSlotList = new ArrayList<>(allTimeSlots);
			timeSlotList.addAll(delegate.getAllTimeSlots());
			allTimeSlots = timeSlotList;
		}
		return allTimeSlots;
	}

}
