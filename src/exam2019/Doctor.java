package exam2019;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Doctor extends Person implements Employee {

	private List<Patient> patients = new ArrayList<>();
	private Hospital employer;

	public Doctor(String name, int age){
		super(name, age);
	}

	public void setEmployer(Hospital employer){
		if (this.employer != null){
			employer.removeEmployee(this);
		}
		this.employer = employer;
	}

	public void addPatient(Patient patient){
		if (!patients.contains(patient)){
			patients.add(patient);
			patient.setDoctor(this);
		}
	}

	public void removePatient(Patient patient){
		if (patients.remove(patient) && patient.getDoctor() == this){
			patient.setDoctor(null);
		}
	}

	public boolean medicate(Patient patient, Medicine medicine){
		Iterator<Nurse> nurseIterator = employer.nurseIterator();
		if (nurseIterator.hasNext()) {
			Nurse nurse = nurseIterator.next();
			nurse.medicate(patient, medicine);
			System.out.println(nurse + " has been given the responsibility for completing this task");
			return true;
		}
		else if (patient.getMedication() == null && patient.getDoctor() == this){
			patient.setMedication(medicine);
			return true;
		}
		else {
			throw new IllegalArgumentException("Not my patient, and no nurses to handle it for me!");
		}
	}

}
