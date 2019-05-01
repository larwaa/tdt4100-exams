package kexam2016;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class Exam implements Comparable<Exam> {

	private char grade;
	private final Course course;

	public Exam(Course course, char grade){
		checkGrade(grade);
		this.grade = grade;
		this.course = course;
	}

	private void checkGrade(char grade){
		Collection<Character> legalGrades = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F');
		if (legalGrades.stream().map(Character::toUpperCase).noneMatch(lg -> lg == grade)) {
			throw new IllegalArgumentException();
		}
	}

	public int compareTo(Exam other){
		return course.compareTo(other.course);
	}

	public static Comparator<Exam> gradeComparator(){
		return (o1, o2) -> o1.grade - o2.grade;
	}

	public Course getCourse(){
		return course;
	}

	public char getGrade(){
		return grade;
	}

	/**
	 * Tells whether this Exam has a result that is a passing grade.
	 */
	public boolean isPass(){
		return grade < 'F';
	}

}
