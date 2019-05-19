package binarycomputingiterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.BinaryOperator;

public class BinaryComputingIterator implements Iterator<Double> {

	private Iterator<Double> iterator1, iterator2;
	private BinaryOperator<Double> operator;
	private Double default1, default2;

	public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2, BinaryOperator<Double> operator){
		this(iterator1, iterator2, Double.NaN, Double.NaN, operator);
	}

	public BinaryComputingIterator(Iterator<Double> iterator1, Iterator<Double> iterator2, Double default1, Double default2, BinaryOperator<Double> operator){
		this.iterator1 = iterator1;
		this.iterator2 = iterator2;
		this.default1 = default1;
		this.default2 = default2;
		this.operator = operator;
	}

	public boolean hasNext(){
		boolean it1HasNext = iterator1.hasNext();
		boolean it2HasNext = iterator2.hasNext();

		return (
				(it1HasNext || it2HasNext) &&
				(it1HasNext || !default1.isNaN()) &&
				(it2HasNext || !default2.isNaN())
		);

	}

	public Double next(){
		if (!hasNext()){
			throw new IllegalArgumentException();
		}
		Double value1 = iterator1.hasNext() ? iterator1.next() : default1;
		Double value2 = iterator2.hasNext() ? iterator2.next() : default2;
		return operator.apply(value1, value2);
	}


}
