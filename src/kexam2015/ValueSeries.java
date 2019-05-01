package kexam2015;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class representing series of doubles, all with the same (unmodifiable) Unit.
 * A Values object can only be modified by appending another value.
 * @author hal
 *
 */
public class ValueSeries implements Values {

	private List<Double> values = new ArrayList<>();
	private List<ValuesListener> valuesListeners = new ArrayList<>();
	private final Unit unit;

	/**
	 * Constructs a new ValueSeries object, with the provided Unit.
	 * @param unit
	 */
	public ValueSeries(Unit unit) {
        this.unit = unit;
	}
	/**
	 * Appends the provided double to this ValueSeries. The double value is assumed to be in the Unit of this ValueSeries.
	 * @param value the double to append
	 */
	public void appendValue(double value) {
        values.add(value);
        fireValuesChanged();
	}
	/**
	 * Appends the provided Value to this ValueSeries, by first converting it to this ValueSeries' unit and then appending it.
	 * @param value the Value to append, after converting it to this ValueSeries' Unit.
	 */
	public void appendValue(Value value) {
        values.add(value.getUnit().convert(value.getValue(), getUnit()));
	}
	// Values methods
    public Unit getUnit(){
		return unit;
	}

	public int size(){
		return values.size();
	}

	public double average(){
		return values.stream().mapToDouble(value -> value).average().orElse(0);
	}

	public Values add(Values other){
		Unit base = this.unit.findCommonBaseUnit(other.getUnit());
		Iterator<Double> it = other.iterator();
		ValueSeries result = new ValueSeries(base);

		for (Double value : values){
			double sum = this.getUnit().convert(value, base);
			if (it.hasNext()){
				double otherDouble = it.next();
				sum += other.getUnit().convert(otherDouble, getUnit());
			}
			result.appendValue(sum);
		}
		return result;
	}

	public void addValuesListener(ValuesListener valuesListener){
		valuesListeners.add(valuesListener);
	}

	public void removeValuesListener(ValuesListener valuesListener){
		valuesListeners.remove(valuesListener);
	}

	protected void fireValuesChanged(){
		for (ValuesListener valuesListener : valuesListeners){
			valuesListener.valuesChanged(this);
		}
	}

	public Iterator<Double> iterator(){
		return values.iterator();
	}
}