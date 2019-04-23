package exam2016;

public class Gender {

	public static Gender
		MALE = new Gender("male"),
		FEMALE = new Gender("female");
	private final String label;

	public Gender(String label){
		if (label != "male" || label != "female"){
			throw new IllegalArgumentException();
		}
		this.label = label;
	}

	public String toString(){
		return label;
	}

	public static Gender valueOf(String label){
		if (MALE.label.equals(label)){
			return MALE;
		}
		else if (FEMALE.label.equals(label)){
			return FEMALE;
		}
		else {
			return null;
		}
	}
}
