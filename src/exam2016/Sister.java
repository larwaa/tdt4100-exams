package exam2016;

import java.util.ArrayList;
import java.util.Collection;

public class Sister implements Relation {

	@Override
	public Collection<Person> getRelativesOf(Person person){
		Collection<Person> result = new ArrayList<>();
		for (Person child : person.getFather()){
			if (child.getGender() == Gender.FEMALE && person.getMother().hasChild(child)){
				result.add(person);
			}
		}
		result.remove(person);
		return result;
	}
}
