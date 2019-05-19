package office;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;

public class Manager implements Employee {

	private List<Employee> employees;
	private int employeeCount = 0;
	private int tasks = 0;

	public Manager(Collection<Employee> employees){
		if (employees.isEmpty()){
			throw new IllegalArgumentException();
		}
		this.employees = new ArrayList<>(employees);
	}


	private Employee getEmployee(){
		Employee employee = employees.get(employeeCount);
		employeeCount = (employeeCount + 1) % employees.size();
		return employee;
	}

	@Override
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		tasks++;
		return getEmployee().doCalculations(operation, value1, value2);
	}

	@Override
	public void printDocument(String document) {
		getEmployee().printDocument(document);
	}

	@Override
	public int getTaskCount() {
		return tasks;
	}

	@Override
	public int getResourceCount() {
		return employees.size() + 1;
	}
}
