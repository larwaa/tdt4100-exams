package exam2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Disease implements Iterable<Symptom>, Comparable<Disease> {

	private List<Symptom> symptoms = new ArrayList<>();
	private String name;
	private int severity;

	public Disease(String name, int severity, Symptom...symptoms){
		this.name = name;
		this.severity = severity;
		this.symptoms = Arrays.asList(symptoms);
	}

	public String getName(){
		return name;
	}

	public int getSeverity(){
		return severity;
	}

	public void addSymptom(Symptom symptom){
		symptoms.add(symptom);
	}

	public void removeSymptom(String symptom){
		symptoms.remove(symptom);
	}

	public Iterator<Symptom> iterator(){
		return symptoms.iterator();
	}

	public int compareTo(Disease other){
		return this.getSeverity() - other.getSeverity();
	}

}
