package office;

import java.util.function.BinaryOperator;

public interface Employee {

	/**
	 *
	 * @param operation
	 * @param value1
	 * @param value2
	 * @return result of operation on value1 and value2
	 */
	double doCalculations(BinaryOperator<Double> operation, double value1, double value2);

	/**
	 *
	 * @param document
	 */
	void printDocument(String document);

	/**
	 *
	 * @return number of tasks executed by, or on behalf of the employee.
	 */
	int getTaskCount();

	/**
	 *
	 * @return number of employees available, inclusive.
	 */
	int getResourceCount();
}
