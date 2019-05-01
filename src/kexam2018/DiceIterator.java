package kexam2018;

import java.util.Iterator;

public class DiceIterator implements Iterator<Integer> {

	private Dice dice;
	private int dieNum = 0;

	public DiceIterator(Dice dice){
		this.dice = dice;
	}

	public boolean hasNext(){
		return dieNum < dice.getDieCount();
	}

	public Integer next(){
		if (! hasNext()){
			throw new IllegalArgumentException();
		}
		int value = dice.getDieValue(dieNum);
		dieNum++;
		return value;
	}

}
