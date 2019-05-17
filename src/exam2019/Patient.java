package exam2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Patient extends Person implements Comparable<Patient> {

	private Doctor doctor;
	private List<Disease> diseases = new ArrayList<>();
	private Medicine medication;

	public Patient(String name, int age, Disease...diseases){
		super(name, age);
		this.diseases = Arrays.asList(diseases);
		Collections.sort(this.diseases);
	}

	public void setDoctor(Doctor doctor){
		if (this.doctor != null){
			this.doctor.removePatient(this);
		}
		this.doctor = doctor;
	}

	public void setMedication(Medicine medicine){
		this.medication = medicine;
	}

	public Medicine getMedication(){
		return medication;
	}

	public Doctor getDoctor(){
		return doctor;
	}

	public int compareTo(Patient other){
		return this.diseases.get(0).compareTo(other.diseases.get(0));
	}
}
