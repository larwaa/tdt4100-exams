package exam2016;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Family implements Iterable<Person> {

	private Collection<Person> family = new ArrayList<>();

	/**
	 * Adds a Person as a new family member
	 * @param person the Person to add
	 */
	public void addMember(Person person) {
      family.add(person);
	}

	/**
	 * Finds a member with the given name
	 * @param name
	 * @return the Person in this Family with the provided name
	 */

	public Person findMember(String name) {
      for (Person person : family){
      	if (person.getName().equals(name)){
      		return person;
		}
	  }
      return null;
	}

	//

	/**
	 * Writes the contents of this Family to the OutputStream,
	 * so it can be reconstructed using load.
	 * @param out
	 */
	public void save(OutputStream out) throws IOException {
		PrintWriter printWriter = new PrintWriter(out);
		String s = "";
		List<String> firstLineList = family.stream()
									.map(person -> person.getGender() + " \"" + person.getName() + "\"\n")
									.collect(Collectors.toList());

		for (String pers : firstLineList){
			s += pers;
		}

		for (Person person : family){
			if (person.getChildCount() > 0){
				s += "\"" + person.getName() + "\" ";
				for (Person child : person){
					s += "\"" + child.getName() + "\" ";
				}
				s += "\n";
			}
		}
		printWriter.print(s);
		printWriter.flush();
		printWriter.close();
	}

	/**
	 * Helper method that splits a line into a list of tokens,
	 * either words or quoted names (quotes are removed).
	 * @param line – the string to tokenize
	 */
   private static List<String> tokenize(String line) {
   		return null;
   }

	/**
	 * Loads contents from the provided InputStream into this Family.
	 * @param in
	 */
	public void load(InputStream in) throws IOException {
		Scanner scanner = new Scanner(in);

		while (scanner.hasNext()){
			String line = scanner.nextLine();
			if (! line.startsWith("#") && line.trim().length() != 0){
				List<String> lineList = tokenize(line);

				if (line.startsWith("\"")){
					Person parent = this.findMember(lineList.get(0));
					lineList.remove(0);

					for (String childName : lineList){
						Person child = this.findMember(childName);
						parent.addChild(child);
					}
				}
				else {
					Person person = new Person(lineList.get(1));
					person.setGender(Gender.valueOf(lineList.get(0)));
					family.add(person);
				}

			}
		}
		scanner.close();
	}

	public Iterator<Person> iterator(){
		return family.iterator();
	}
}

