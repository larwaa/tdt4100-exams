package exam2016;

import java.util.ArrayList;
import java.util.Collection;

public class Parent implements Relation {

	@Override
	public Collection<Person> getRelativesOf(Person person){
		Collection<Person> result = new ArrayList<>();

		if (person.getFather() != null){
			result.add(person.getFather());
		}

		if (person.getMother() != null){
			result.add(person.getMother());
		}

		return result;
	}

}
