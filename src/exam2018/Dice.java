package exam2018;

import java.util.*;

/**
 * Represents a set of die values. A die has six possible values 1-6,
 * but the number of dice may vary from Dice instance to Dice instance.
 * In addition, a Dice-instance can have a score.
 */
public class Dice implements Iterable<Integer> {


	public Dice(int dieCount, Supplier<Integer> supplier){
		Collection<Integer> result = new ArrayList<>();
		int dieValue;
		for (int i = 0; i < dieCount; i++){
			dieValue = supplier.get();
			result.add(dieValue);
		}
	}

	private int score;
	private List<Integer> dieValues = new ArrayList<>();
	/**
	 * @param dieCount
	 * @return a collection of random integer values in the range 1-6
	 */
	public static Collection<Integer> randomDieValues(int dieCount) {
		Collection<Integer> result = new ArrayList<>();
		double rand;
		for (int i = 0; i < dieCount; i++){
			rand = Math.random();
			result.add((rand * 6) + 1);
		}
		return result;
	}

	/** (part 1)
	 * Initializes this Dice with the values in dieValues, and a score.
	 * @param dieValues
	 * @param score the score to set, may be -1 for yet unknown
	 * @throws a suitable exception if the die values are outside the valid range
	 */
	public Dice(Collection<Integer> dieValues, int score) {
		checkDieValues(dieValues);
		this.dieValues = dieValues;
		this.score = score;
	}

	private void checkDieValues(Collection<Integer> dieValues){
		if (dieValues.stream().anyMatch((dieValue) -> dieValue > 6 || dieValue < 1)){
			throw new IllegalArgumentException();
		}
	}

	/** (part 1)
	 * Initializes this Dice with dieCount random values (using Math.random())
	 * @param dieCount
	 */
	public Dice(int dieCount) {
		this(randomDieValues(dieCount), -1);
	}

	/** (part 1)
	 * Initializes this Dice with the values in dice, and a score
	 * @param dieValues // Denne skulle vært bare "dice", ikke "dieValues"
	 * @param score the score to set, may be -1 for yet unknown
	 */
	public Dice(Dice dice, int score) {
      this(new ArrayList<>(dice.dieValues), score);
	}

	/** (part 2)
	 * Format: [die1, die2, ...] = score (score is omitted when < 0)
	 */

	public String toString(){
		String s = dieValues.toString();
		if (score >= 0){
			s += " = " + score;
		}
		return s;
	}

	/** (part 2)
	 * Parses a string using the toString format (see above) and
	 * returns a corresponding Dice.
	 * @param s
	 * @return a new Dice instance initialized with die values and score from the String argument
	 */
	public static Dice valueOf(String s) {
		int scoreIndex = s.indexOf("=");
		int score = -1;
		String[] splitString = s.split("=");
		String[] dieValues = splitString[0].trim().substring(1, splitString[0].lastIndexOf("]")).split(",");
		Collection<Integer> dice = new ArrayList<>();

		for (String st : dieValues){
			dice.append(Integer.valueOf(st.trim()));
		}

		if (scoreIndex > -1){
			score = Integer.valueOf(splitString[1].trim());
		}
		return new Dice(dice, score);
	}

	/** (part 3)
	 * @return the number of die values
	 */
	public int getDieCount() {
		return dieValues.size();
	}

	/** (part 3)
	 * @param dieNum
	 * @return the value of die number dieNum
	 */
	public int getDieValue(int dieNum) {
		return dieValues.get(dieNum - 1); //Antar 1-indexering??
	}

	/** (part 3)
	 * @param value
	 * @return the number of dice with the provided value
	 */
	public int getValueCount(int value) {
		return dieValues.stream().filter((dieValue) -> dieValue == value).count();
	}

	/** (part 4)
	 * @return the current score
	 */
	public int getScore() {
		return score;
	}

	/** (part 4)
	 * Sets the score, but only if it isn't already set to a non-negative value
	 * @param score
	 * @throws a suitable exception if score already is set to a non-negative value
	 */
	public void setScore(int score) {
		checkScore(score);
		this.score = score;
	}

	private void checkScore(int score){
		if (this.score >= 0){
			throw new IllegalStateException("Score already set");
		}

		if (score < 0){
			throw new IllegalArgumentException("Score must be positive");
		}
	}


	public Iterator<Integer> iterator(){
		return dieValues.iterator();
	}

	private void exampleUsecase(){
		for (int i : this){
			System.out.println(i);
		}
	}

	/** (part 6) // Denne ble det ikke spurt om, og det var ikke meningen at den skulle implementeres, men den kunne brukes
	 * @param dice
	 * @return true if all die values in the argument appear in this Dice
	 */
	public boolean contains(Dice dice) {
		for (int dieValue : dice){
			if (dice.getDieCount(dieValue) > this.getDieCount(dieValue)){
				return false;
			}
		}
		return true;
	}

	/** (part 6)
	 * @param dices a Collection of Dice // Denne linja var feil, det skulle være bare "dice a Dice"
	 * @return a new Dice instance with the all the die values this Dice and
	 * all Dice in the argument, without any specific order
	 */
	public Dice add(Dice dice) {
		Collection<Integer> result = new ArrayList<>(dice.dieValues);
		result.addAll(this.dieValues);
		return new Dice(result, -1);
	}

	/** (part 6)
	 * @param dice
	 * @return a new Dice instance with the die values from this Dice, but
	 * without those from the argument, without any specific order
	 */
	public Dice remove(Dice dice) {
		Map<Integer, Integer> counts = new HashMap<>();
		Collection<Integer> result = new ArrayList<>();

		for (int die : dieValues){
			counts.put(die, counts.getOrDefault(die, 0) + 1);
		}

		for (int die : dice){
			counts.put(die, counts.getOrDefault(die, 1) - 1);
		}

		for (int dieValue : counts.keySet()){
			for (int i = 0; i < counts.get(dieValue); i++){
				result.add(dieValue);
			}
		}
		return new Dice(result, - 1);
	}
}
