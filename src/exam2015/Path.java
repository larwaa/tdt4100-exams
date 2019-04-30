package exam2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Path implements Iterable<Segment> {

	private List<Segment> segments = new ArrayList<>();

	public Path(Segment...segments){
		this.segments.addAll(Arrays.asList(segments));
	}

	public int getSegmentCount(){
		return segments.size();
	}

	public Segment getSegment(int index){
		if (! (index < segments.size()) || index < 0){
			throw new IllegalArgumentException();
		}
		return segments.get(index);
	}

	public void addSegment(Segment segment){
		segments.add(segment);
	}

	public double getDuration(){
		return getDurationHelper(this.segments);
	}

	private double getDurationHelper(List<Segment> segments){
		return segments.stream()
				.mapToDouble(Segment::getDuration)
				.sum();
	}

	private double getDistanceHelper(List<Segment> segments){
		return segments.stream()
				.mapToDouble(Segment::getDistance)
				.sum();
	}

	public double getDistance(Segment fromSegment, Segment upToSegment){
		int startIndex;
		int endIndex;

		if (fromSegment == null){
				startIndex = 0;
		}
		else {
			startIndex = segments.indexOf(fromSegment);
		}

		if (upToSegment == null){
			endIndex = getSegmentCount() - 1;
		}
		else {
			endIndex = segments.indexOf(upToSegment) - 1;
		}
		if (startIndex > endIndex){
			throw new IllegalArgumentException();
		}
		return getDistanceHelper(segments.subList(startIndex, endIndex));
	}


	public Segment getSegmentAt(double distance, boolean next){
		if (distance == 0 && ! next){
			return null;
		}

		for (Segment segment : segments){
			distance -= segment.getDistance();

			if (distance < 0){
				return segment;
			}
			else if (distance == 0){
				if (! next){
					return segment;
				}
				else if (segments.indexOf(segment) + 1 < getSegmentCount()){
					return segments.get((segments.indexOf(segment) + 1));
				}
				else {
					return null;
				}
			}
		}
		return null;
	}

	public Iterator<Segment> iterator(){
		return segments.iterator();
	}

	public static void main(String[] args){
		Segment s1 = new Segment(10, 5);
		Segment s2 = new Segment(20, 10);
		Segment s3 = new Segment(30, 15);
		Path p1 = new Path(s1, s2, s3);
		System.out.println(p1.getSegmentAt(0, false));
		System.out.println(p1.getSegmentAt(0, true));
		System.out.println(p1.getSegmentAt(10, true));
		System.out.println(p1.getSegmentAt(10, false));
		System.out.println(p1.getSegmentAt(60, false));
		System.out.println(p1.getSegmentAt(60, true));
	}

}
