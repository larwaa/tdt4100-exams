package exam2016;

import java.util.ArrayList;
import java.util.Collection;

public class Sibling implements Relation {

	private Gender gender;

	public Sibling(Gender gender){
		this.gender = gender;
	}

	public Collection<Person> getRelativesOf(Person person){
		Collection<Person> result = new ArrayList<>();

		for (Person child : person.getFather()){
			if (child.getGender() == gender && person.getMother().hasChild(child)){
				result.add(child);
			}
		}
		result.remove(person);
		return result;
	}

}
