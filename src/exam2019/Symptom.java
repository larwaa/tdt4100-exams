package exam2019;

public class Symptom {

	public final static Symptom
				FEVER = new Symptom("Fever"),
				SWEAT = new Symptom("Sweat"),
				DIARRHEA = new Symptom("Diarrhea"),
				HAIR_LOSS = new Symptom("Hair loss"),
				RUNNY_NOSE = new Symptom("Runny nose"),
				DEATH = new Symptom("Death");

	private String description;

	public Symptom(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public String toString(){
		return description;
	}
}
