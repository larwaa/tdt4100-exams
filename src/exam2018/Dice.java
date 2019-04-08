package exam2018;

import java.util.*;
import java.util.stream.Collectors;

public class Dice implements Iterable<Integer>{

	private static int EYES = 6;

	private List<Integer> dieValues = new ArrayList<>();
	private int score;

	public Dice(Collection<Integer> dieValues, int score){

		for (int dieValue : dieValues){
			if (dieValue < 1 || dieValue > EYES){
				throw new IllegalArgumentException();
			}
			this.dieValues.add(dieValue);
		}
		this.score = score;
	}

	public Dice(int dieCount){
		this(randomDieValues(dieCount), -1);
	}

	public Dice(Dice dieValues, int score){
		this(dieValues.dieValues, score);
	}

	public String toString(){
		String s = dieValues.toString();
		/*
		String s = "[";
		for (int dieValue : dieValues){
			if (s.length() > 1){
				s += ",";
			}
			s += dieValue;
		}
		s += "]";
		*/
		if (score >= 0) {
			s += String.format(" = %s", score);
		}
		return s;
	}

	public static Dice valueOf(String diceString){
		String[] diceStringArray = diceString.split("=");
		String[] diceStringValues = diceStringArray[0]
									.substring(1, diceStringArray[0].indexOf("]"))
									.split(",");

		int score = -1;

		if (diceStringArray.length == 2){
			score = Integer.parseInt(diceStringArray[1].strip());
		}

		List<Integer> dieValues = new ArrayList<>();

		for (String s : diceStringValues) {
			dieValues.add(Integer.parseInt(s.strip()));
		}
		return new Dice(dieValues, score);
	}

	public static Collection<Integer> randomDieValues(int dieCount){
		Collection<Integer> dieValues = new ArrayList<>();

		for (int i = 0; i < dieCount; i++){
			dieValues.add((int) (Math.random() * EYES + 1));
		}
		return dieValues;
	}

	public int getDieCount(){
		return dieValues.size();
	}

	public int getDieValue(int dieNum){
		if (dieNum < 0 || dieNum >= getDieCount()){
			throw new IllegalArgumentException();
		}
		return dieValues.get(dieNum);
	}

	public int getValueCount(int value){
		return (int) dieValues.stream().filter((die) -> die == value).count();
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
		return dieValues.iterator();
	}

	public Dice add(Dice dice){
		List<Integer> tempList = new ArrayList<>(this.dieValues);
		dice.forEach(tempList::add);
		return new Dice(tempList, -1);
	}

	private boolean contains(int value){
		return dieValues.contains(value);
	}

	public Dice remove(Dice dice){
		List<Integer> tempList = new ArrayList<>(this.dieValues);

		for (Integer dieValue : dice){
			tempList.remove(dieValue);
		}

		//List<Integer> tempList = this.dieValues.stream()
		//								.filter(i -> ! dice.contains(i))
		//								.collect(Collectors.toList());
		return new Dice(tempList, -1);
	}


	public static void main(String[] args){
		//eksempelkode
		Dice d1 = new Dice(5);
		d1.setScore(10);
		System.out.println(valueOf(d1.toString()));
		Dice d2 = new Dice(5);
		System.out.println(d2);
		System.out.println(d1.remove(d2));
	}
}
