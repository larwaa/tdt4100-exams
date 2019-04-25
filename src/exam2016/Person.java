package exam2016;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Person implements Iterable<Person> {

	private Person mother, father;
	private Gender gender = null;
	private final String name;
	private Collection<Person> children = new ArrayList<>();

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

	public void setGender(Gender gender){
		this.gender = gender;
	}

	public int getChildCount(){
		return children.size();
	}

	public boolean hasChild(Person child) {
      return children.contains(child);
	}

	public Collection<Person> getChildren(Gender gender) {
		if (gender == null){
			return children;
		}

  		return children.stream()
			  			.filter(person -> person.getGender() == gender)
			  			.collect(Collectors.toList());
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

		if (gender == Gender.MALE) {
			if (child.father != null){
				child.father.children.remove(child);
			}
			child.father = this;
		}
		else if (gender == Gender.FEMALE){
			if (child.mother != null){
				child.mother.children.remove(child);
			}
			child.mother = this;
		}
		else {
			throw new IllegalStateException("Parents must have a gender");
		}
		children.add(child);
	}

	//father2 har nå fått nytt kjønn, og da burde man sjekke at child.mother == father2, dog så vil fortsatt child ha father2 som far, så denne koblingen burde nok endres ved setGender.
	//Det siste burde utløse et unntak, for man kan ikke barn av sitt eget barn. Den nåværende koden tillater derimot dette.

	// Dette betyr at vi enkelt kan skrive ne for løkke for å iterere gjennom hvert barn til person, typ for (Person child : person) ...
	public Iterator<Person> iterator(){
		return children.iterator();
	}


	public static void main(String[] args){
		Relation daughter = parent -> parent.getChildren(Gender.FEMALE);
	}
}