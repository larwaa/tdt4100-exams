package exam2018;

import java.util.*;
import java.util.stream.Collectors;

public class Dice implements Iterable<Integer>{

	private static int EYES = 6;

	private List<Integer> dice = new ArrayList<>();
	private int score;

	public Dice(Collection<Integer> dieValues, int score){

		for (int dieValue : dieValues){
			if (dieValue < 1 || dieValue > EYES){
				throw new IllegalArgumentException();
			}
			dice.add(dieValue);
		}
		this.score = score;
	}

	public Dice(int dieCount){
		this(Dice.randomDieValues(dieCount), -1);

	}

	public Dice(Dice dice, int score){
	}

	public String toString(){
		String s = "[";
		for (int dieValue : dice){
			if (s.length() > 1){
				s += ",";
			}
			s += dieValue;
		}
		s += "]";

		if (score >= 0) {
			s += String.format("=%s", score);
		}
		return s;
	}

	public static Dice valueOf(String diceString){
		String[] diceStringArray = diceString.split("=");
		String[] diceStringValues = diceStringArray[0]
									.substring(1, diceStringArray[0].length() - 1)
									.split(",");

		int score = -1;

		if (diceStringArray.length == 1){
			score = Integer.parseInt(diceStringArray[1]);
		}

		List<Integer> dieValues = new ArrayList<>();

		for (String s : diceStringValues) {
			dieValues.add(Integer.parseInt(s));
		}
		return new Dice(dieValues, score);
	}

	public static Collection<Integer> randomDieValues(int dieCount){
		Collection<Integer> dieCounts = new ArrayList<>();

		for (int i = 0; i < dieCount; i++){
			dieCounts.add((int) ((Math.random() + 1) * EYES));
		}

		return dieCounts;
	}

	public int getDieCount(){
		return dice.size();
	}

	public int getDieValue(int dieNum){
		if (dieNum < 0 || dieNum >= getDieCount()){
			throw new IllegalArgumentException();
		}
		return dice.get(dieNum);
	}

	public int getValueCount(int value){
		return (int) dice.stream().filter((die) -> die == value).count();
	}

	public int getScore(){
		return score;
	}

	public void setScore(int score){
		if (this.score >= 0){
			throw new IllegalStateException();
		}
		if (score < 0){
			throw new IllegalArgumentException();
		}
		this.score = score;
	}

	public Iterator<Integer> iterator(){
		return dice.iterator();
	}

	public static void main(String[] args){
		//eksempelkode
		Dice d1 = new Dice(5);
		for (int dice : d1){
			System.out.println(dice);
		}
	}

	public Dice add(Dice dice){
		List<Integer> tempList = new ArrayList<>(this.dice);
		dice.forEach(tempList::add);
		return new Dice(tempList, -1);
	}

	private boolean contains(int value){
		return dice.contains(value);
	}

	public Dice remove(Dice dice){
		List<Integer> tempList = this.dice.stream()
										.filter(i -> ! dice.contains(i))
										.collect(Collectors.toList());
		return new Dice(tempList, -1);
	}



}
