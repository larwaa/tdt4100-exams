package kexam2016;

import java.util.*;

public class Person {

	private final String name;
	private Collection<Course> courses = new ArrayList<>();
	private List<Exam> exams = new ArrayList<>();

	public Person(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	/**
	 * Adds a Course to this Person, if no Course is registered
	 * for the same code, year and semester.
	 * @param course
	 * @return true if the course was added, false otherwise
	 */
	public boolean addCourse(Course course){
		if (courses.stream()
				.noneMatch(c1 -> c1.getTime().equals(course.getTime())
							&& c1.getCode().equals(course.getCode()))) {
			courses.add(course);
			return true;
		}
		return false;
	}

	/**
	 * Returns whether this Person has a Course with the given code.
	 * @param code
	 */
	public boolean hasCourse(Course course){
		return courses.stream()
						.anyMatch(c1 -> c1.getCode().equals(course.getCode()));
	}

	/**
	 * Creates and adds an exam to this Person for the provided Course and
	 * with the provided grade, but only if this Person has this Course and
	 * no passing Exam is registered for that Course.
	 * @param course
	 * @param grade
	 * @return the newly created and added Exam, or null
	 */
	public Exam addExam(Course course, char grade) {
		Exam exam;
		exam = new Exam(course, grade);
		if (hasCourse(course)
				&& (exams.stream()
						.noneMatch(e -> e.getCourse() == course && e.isPass()))){
			exams.add(exam);
			return exam;
		}
		return null;
	}

	/**
	 * Gets the exam that was registered last for the provided course code.
	 * This is the exam that counts for that course!
	 * @param course
	 */
	public Exam getLastExam(String code) {
		return exams.stream()
					.filter(e -> e.getCourse().getCode().equals(code))
					.findFirst()
					.orElse(null);
	}

	/**
	 * Returns true of this Person has passed the Course for the provided code.
	 * @param code
	 */
	public boolean hasPassed(String code) {
		return exams.stream()
				.map(e -> getLastExam(e.getCourse().getCode()))
				.distinct()
				.anyMatch(Exam::isPass);
	}

	/**
	 * Counts the credits this Person has earned.
	 */
	public double countCredits() {
		return exams.stream()
				.filter(e -> hasPassed(e.getCourse().getCode()))
				.distinct()
				.mapToDouble(e -> e.getCourse().getCredits())
				.sum();
	}

}
