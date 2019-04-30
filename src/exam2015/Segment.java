package exam2015;

public class Segment {

	private final double distance, duration;

	public Segment(double distance, double duration){
		this.distance = distance;
		this.duration = duration;
	}

	public double getDuration(){
		return duration;
	}

	public double getDistance(){
		return distance;
	}

	public double getSpeed(){
		return distance/duration;
	}

}
