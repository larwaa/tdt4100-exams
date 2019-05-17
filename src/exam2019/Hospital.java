package exam2019;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hospital implements Iterable<Employee> {

	private List<Employee> employees = new ArrayList<>();
	private List<Patient> patients = new ArrayList<>();
	private String name;
	private int capacity;

	public Hospital(String name, int capacity, Person... people){
		this(name, capacity, getEmployees(people), getPatients(people));
	}

	public void removeEmployee(Employee employee){
		if (employees.remove(employee)){
			employee.setEmployer(null);
		}
	}

	public void addEmployee(Employee employee){
		if (!employees.contains(employee)){
			employee.setEmployer(this);
			employees.add(employee);
		}
	}

	public static List<Employee> getEmployees(Person... people){
		return getFilteredStream(person -> person instanceof Employee, people)
						.map(person -> (Employee) person)
						.collect(Collectors.toList());
	}

	private static Stream<Person> getFilteredStream(Predicate<Person> predicate, Person... people){
		return Arrays.stream(people).filter(predicate);
	}

	public static List<Patient> getPatients(Person... people){
		return getFilteredStream(person -> person instanceof Patient, people)
						.map(person -> (Patient) person)
						.collect(Collectors.toList());
	}

	public Hospital(String name, int capacity, List<Employee> employees, List<Patient> patients){
		this.name = name;
		this.capacity = capacity;
		this.employees = employees;
		this.patients = patients;
		for (Employee employee : this.employees){
			employee.setEmployer(this);
		}
	}

	public Iterator<Employee> iterator(){
		return employees.iterator();
	}

	public Iterator<Nurse> nurseIterator(){
		List<Nurse> nurses = employees.stream()
									.filter(employee -> employee instanceof Nurse)
									.map(employee -> (Nurse) employee)
									.collect(Collectors.toList());
		return nurses.iterator();
	}

	public String toString(){
		return String.format("%s\nEmployees: %s\nPatients: %s", name, employees.toString(), patients.toString());
	}

	public static void main(String[] args){
		Disease d1 = new Disease("Common cold", 2, Symptom.FEVER, Symptom.RUNNY_NOSE);
		Disease d2 = new Disease("Cancer", 10, Symptom.DIARRHEA, Symptom.FEVER, Symptom.HAIR_LOSS);
		Disease d3 = new Disease("Ebola", 10, Symptom.HAIR_LOSS, Symptom.FEVER, Symptom.DIARRHEA, Symptom.SWEAT, Symptom.RUNNY_NOSE);

		Patient p1 = new Patient("Lars", 22, d1);
		Patient p2 = new Patient("Walter White", 50, d2);
		Patient p3 = new Patient("Donald Trump", 75, d3, d2, d1);

		Doctor dr1 = new Doctor("Vegard", 40);
		Doctor dr2 = new Doctor("Kari", 65);
		Doctor dr3 = new Doctor("Anne", 60);

		Nurse n1 = new Nurse("Per", 18);
		Nurse n2 = new Nurse("Beta", 50);
		Nurse n3 = new Nurse("Mo", 70);


		Hospital h1 = new Hospital("Karolinska", 500, p1, p2, p3, dr1, dr2, dr3, n1, n2, n3);
		System.out.println(h1);
		Collections.sort(h1.patients);
		System.out.println(h1);
		dr1.medicate(p1, Medicine.PAINKILLER);
		System.out.println(p1.getMedication());
	}
}
