package kexam2017;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Meal extends MenuItem implements Iterable<Course> {

	private List<Course> courses;

	public Meal(String name, String description, Course[] courses) {
		super(name, description);
		this.courses = Arrays.asList(courses);
	}

	public void addCourse(Course course){
		courses.add(course);
	}

	public void removeCourse(Course course){
		courses.remove(course);
	}

	public Iterator<Course> iterator(){
		return courses.iterator();
	}

	/**
	 * Finds a Course satisfying the provided Predicate.
	 * @param test
	 * @return the first Course satisfying the provided Predicate, otherwise null.
	 */
	public Course findCourse(Predicate<Course> test) {
        return courses.stream().filter(test).findFirst().orElse(null);
	}

}
