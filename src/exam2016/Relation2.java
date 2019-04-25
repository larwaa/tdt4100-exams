package exam2016;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Relation2 implements Relation {

	private Relation rel1, rel2;

	public Relation2(Relation rel1, Relation rel2) {
      	this.rel1 = rel1;
  		this.rel2 = rel2;
	}

	@Override
	public Collection<Person> getRelativesOf(Person person) {
		Collection<Person> result = new ArrayList<>();

		Collection<Person> temp = rel1.getRelativesOf(person);

		for (Person p : temp){
			result.addAll(rel2.getRelativesOf(p));
		}
		return result.stream().distinct().collect(Collectors.toList());
	}
}