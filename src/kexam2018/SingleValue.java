package kexam2018;

import java.util.Arrays;

public class SingleValue extends AbstractScorer {

	private int value;

	public SingleValue(int value, int score){
		super(score);
		this.value = value;
	}

	@Override
	public DiceScore getScore(Dice dice){
		if (dice.getValueCount(value) > 0){
			return new DiceScore(new Dice(Arrays.asList(value)), score);
		}
		return null;
	}

}
