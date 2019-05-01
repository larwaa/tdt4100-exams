package kexam2017;

public abstract class MenuItem {

	protected final String name, description;

	public MenuItem(String name, String description){
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
