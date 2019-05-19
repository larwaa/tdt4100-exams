package office;

import java.util.*;

public class Printer {

	private Map<Employee, List<String>> prints = new HashMap<>();

	public void printDocument(String document, Employee employee){
		if (prints.containsKey(employee)){
			prints.get(employee).add(document);
		} else {
			prints.put(employee, Arrays.asList(document));
		}
		System.out.println(document);
	}

	public List<String> getPrintHistory(Employee employee){
		return prints.get(employee);
	}

}
