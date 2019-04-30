package exam2015;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Person {

	private char gender;
	private int age;

	public Person(char gender, int age){
		this.gender = gender;
		this.age = age;
	}

	public int getAge(){
		return age;
	}

	public char getGender(){
		return gender;
	}

	public static Collection<Person> getMatchingPersons(Collection<Person> people, Predicate<Person> predicate){
		return people.stream().filter(predicate).collect(Collectors.toList());
	}

	public static void main(String[] args){
		Person p1 = new Person('M', 18);
		Person p2 = new Person('F', 24);
		Collection<Person> people = Arrays.asList(p1, p2);
		getMatchingPersons(people, (person) -> person.getAge() >= 18 && person.getGender() == 'M');
	}

}
