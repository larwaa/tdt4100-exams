package exam2019;

public class Nurse extends Person implements Employee {

	private Hospital employer;

	public Nurse(String name, int age){
		super(name, age);
	}

	public boolean medicate(Patient patient, Medicine medicine){
		if (patient.getMedication() == null){
			patient.setMedication(medicine);
			return true;
		}
		return false;
	}

	public void setEmployer(Hospital employer){
		if (this.employer != null){
			employer.removeEmployee(this);
		}
		this.employer = employer;
	}
}
