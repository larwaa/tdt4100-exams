package exam2019;

import java.util.ArrayList;
import java.util.List;

public class Medicine {

	public static final Medicine
			ANTIBIOTICS = new Medicine("Antibiotics"),
			PAINKILLER = new Medicine("Painkiller"),
			CHEMOTHERAPY = new Medicine("Chemotherapy"),
			CYANIDE = new Medicine("Cyanide");
	private String name;

	public Medicine(String name){
		this.name = name;
	}

	public String toString(){
		return name;
	}
}
