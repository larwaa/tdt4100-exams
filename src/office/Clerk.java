package office;

import java.util.function.BinaryOperator;

public class Clerk implements Employee {

	private Printer printer;
	private int tasks;

	public Clerk(Printer printer){
		tasks = 0;
		this.printer = printer;
	}

	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2){
		tasks++;
		return operation.apply(value1, value2);
	}

	@Override
	public void printDocument(String document) {
		tasks++;
		printer.printDocument(document, this);
	}

	@Override
	public int getTaskCount() {
		return tasks;
	}

	@Override
	public int getResourceCount() {
		return 1;
	}
}
