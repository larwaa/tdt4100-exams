package exam2015;

public class Trip {

	private Path expectedPath, recordedPath;

	public Trip(Path expectedPath){
		this.expectedPath = expectedPath;
		recordedPath = new Path();
	}

	public void registerSegment(double distance, double duration){
		if (recordedPath.getSegmentCount() == expectedPath.getSegmentCount()){
			throw new IllegalStateException("Traveled all segments already.");
		}

		Segment expectedSegment = expectedPath.getSegmentAt(distance, false);
		distance = distance - recordedPath.getDistance(null, null);
		duration = duration - recordedPath.getDuration();

		if (distance != expectedSegment.getDistance()){
			throw new IllegalStateException("The distance of segments do not match.");
		}

		recordedPath.addSegment(new Segment(distance, duration));
	}



	public double estimateTime(double distance, double duration) {
		double remainingTime = 0.0;
		for (Segment segment : this.expectedPath) {
			distance -= segment.getDistance();
			if (distance < 0) {
				// gjenværende tid for segmented en er kommet til
				remainingTime += -distance / segment.getSpeed(); //

			} else if (remainingTime > 0.0) {
				// legg til gjenværende tid for etterfølgende segment
				remainingTime += segment.getDuration();
			}
		}
		return remainingTime;
	}
}
