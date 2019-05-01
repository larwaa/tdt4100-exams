package kexam2018;

import java.util.*;

public class Dice implements Iterable<Integer> {

	private List<DiceObserver> diceObservers = new ArrayList<>();
	final int[] valueCounters;

	public Dice(Iterator<Integer> dieValues){
		valueCounters = new int[6];
		while (dieValues.hasNext()){
			int dieValue = dieValues.next();
			checkDieValue(dieValue);
			valueCounters[dieValue - 1]++;
		}
	}

	public Dice(Iterable<Integer> dieValues){
		this(dieValues.iterator());
	}

	public Dice(int[] valueCounters){
		this.valueCounters = valueCounters;
	}

	private void checkDieValue(int dieValue){
		if (dieValue > 6 || dieValue < 1){
			throw new IllegalArgumentException("DieValues must be between 1-6");
		}
	}

	public int getDieCount(){
		return Arrays.stream(valueCounters).sum();
	}

	public int getDieValue(int dieNum){
		if (dieNum >= getDieCount() || dieNum < 0){
			throw new IllegalArgumentException(dieNum + " is out of range");
		}
		for (int dieValue = 1; dieValue <= 6; dieValue++){
			int counter = valueCounters[dieValue - 1];
			if (dieNum < counter){
				return dieValue;
			}
			dieNum -= counter;
		}
		throw new UnknownError("No clue what happened");
	}

	public int getValueCount(int value){
		return valueCounters[value - 1];
	}

	public boolean isSame(Dice dice){
		return contains(dice) && dice.contains(this);
	}

	public boolean contains(Dice dice){
		for (int i = 1; i <= 6; i++){
			if (valueCounters[i - 1] < dice.getValueCount(i)){
				return false;
			}
		}
		return true;
	}

	public Dice add(Dice dice){
		int[] valueCounters = new int[6];
		for (int i = 0; i < getDieCount(); i++){
			valueCounters[i] = dice.valueCounters[i] + this.valueCounters[i];
		}
		return new Dice(valueCounters);
	}

	public Dice remove(Dice dice){
		int[] valueCounters = new int[6];
		for (int i = 0; i < getDieCount(); i++){
			int value = this.valueCounters[i] - dice.valueCounters[i];
			valueCounters[i] = value < 0 ? 0 : value;
		}
		return new Dice(valueCounters);
	}

	public Iterator<Integer> iterator(){
		return new DiceIterator(this);
	}

	public static void main(String[] args){
		Dice dice = new Dice(Arrays.asList(2, 3, 2, 1, 0, 0));
		DiceIterator iterator = new DiceIterator(dice);

		for (int dieValue : dice){
			System.out.println(dieValue + " ");
		}
		dice.forEach(dieValue -> System.out.println(dieValue + " "));
	}
}
