package exam2019;

public class Person {

	private final String name;
	private int age;

	public Person(String name, int age){
		this.name = name;
		setAge(age);
	}

	public int getAge(){
		return age;
	}

	public String getName(){
		return name;
	}

	public void setAge(int age){
		this.age = age;
	}

	public String toString(){
		return name;
	}

}
