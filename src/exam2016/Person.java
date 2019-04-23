package exam2016;

import java.util.Collection;

public class Person implements Iterable<Person> {

	private Person mother, father;
	private Gender gender;
	private String name;

	public Person(String name){
		this.name = name;
	}

	public Person getMother(){
		return this.mother;
	}

	public Person getFather(){
		return this.father;
	}

	public Gender getGender(){
		return this.gender;
	}

	public String getName(){
		return this.name;
	}

	public void setGender(String label){
		gender = Gender.valueOf(label);
	}


			... methods for name, gender, mother and father ...

			... field(s) for children ...

	/**
	 * @return the number of children of this Person
	 */
	public int getChildCount() {
      ...
	}

	/**
	 * @param child
	 * @return if this Person has the provided Person as a child
	 */
	public boolean hasChild(Person child) {
      ...
	}

	/**
	 * Returns all children of this Person with the provided Gender.
	 * If gender is null, all children are returned.
	 * Can be used to get all daughters or sons of a person.
	 * @param gender
	 */
	public Collection<Person> getChildren(Gender gender) {
      ...
	}

	/**
	 * Adds the provided Person as a child of this Person.
	 * Also sets the child's father or mother to this Person,
	 * depending on this Person's gender.
	 * To ensure consistency, if the provided Person already
	 * has a parent of that gender,
	 * it is removed as a child of that parent.
	 * @param child
	 */
	public void addChild(Person child) {
      ...
	}
}