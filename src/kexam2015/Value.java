package kexam2015;

import java.util.function.BinaryOperator;
/**
 * A class representing a double value in a certain Unit.
 * Instances are immutable, i.e. cannot be modified once created.
 * @author hal
 *
 */
public class Value implements Comparable<Value> {


	private final Unit unit;
	private final double value;

	/**
	 * Creates a new value with the provided Unit and (double) value.
	 * @param unit the Unit of the new Value
	 * @param value the double value of the new Value
	 */
	public Value(Unit unit, double value) {
        this.unit = unit;
        this.value = value;
	}
	@Override
	public String toString() {
		return getValue() + unit.toString();
	}

	/**
	 * Returns this Value's Unit
	 * @return this Value's Unit
	 */
    public Unit getUnit(){
        return unit;
	}
	/**
	 * Returns this Value's double value
	 * @return this Value's double value
	 */
    public double getValue(){
        return value;
	}

	/**
	 * Returns a Value instance from the provided String.
	 * The format is a double (as parsed by Double.valueOf) followed by a Unit symbol (as parsed by Unit.valueOf), e.g. "1.0m" or "2.5km".
	 * @param s
	 * @return the String parses as a Value instance
	 */
	public static Value valueOf(String s) {
		int i = 0;
        while (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.'){
        	i++;
		}
        Unit unit = Unit.valueOf(s.substring(i));
        double value = Double.valueOf(s.substring(0, i));
        return new Value(unit, value);
	}

	/**
	 * Computes the sum of this and other (both Value objects).
	 * The Unit of the returned Value is the common base Unit of this and other's Units.
	 * Hence, both Value object's double values are properly converted before adding.
	 * @param other the other Value
	 * @return a new Value object representing the sum of this and other
	 */
	public Value add(Value other) {
		Unit base = this.unit.findCommonBaseUnit(other.unit);
        double convert1 = this.unit.convert(this.value, base);
        double convert2 = other.unit.convert(other.value, base);
        return new Value(base, convert1 + convert2);
	}
	/**
	 * Computes a new value that is the combination of this Value's double value and the provided double.
	 * The double values are combined using the provided BinaryOperator.
	 * The Unit of the returned Value is the this Value's Unit.
	 * @param other the double value to combine with this
	 * @return a new Value object representing the sum of this and other
	 */
	public Value compute(BinaryOperator<Double> op, double other) {
		return new Value(this.unit, op.apply(this.value, other));
	}
	/**
	 * Computes the product of this Value and other (a double) using the compute method.
	 * @param other the other factor
	 * @return the product of this Value's double value and other (also a double)
	 */
	public Value mult(double other) {
        return compute((d1, d2) -> d1 * d2, other);
	}

	/**
	 * Compares this Value with other according the the requirements of Comparable.
	 * Note that this Value and other may have different Units.
	 * @throws IllegalArgumentException if this and other don't have a common base Unit.
	 */
	@Override
	public int compareTo(Value other) {
        Unit base = this.unit.findCommonBaseUnit(other.unit);
        if (base == null){
        	throw new IllegalArgumentException();
		}
        return (int) (this.unit.convert(other.value, base) - other.unit.convert(this.value, base));
	}
}