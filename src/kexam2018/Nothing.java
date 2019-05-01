package kexam2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Nothing extends AbstractScorer {

	private final int numDice;
	private final Collection<DiceScorer> diceScorers;

	public Nothing(int numDice, int score, DiceScorer...diceScorers){
		super(score);
		this.numDice = numDice;
		this.diceScorers = Arrays.asList(diceScorers);
	}

	@Override
	public DiceScore getScore(Dice dice){
		if (dice.getDieCount() < numDice){
			return null;
		}

		for (DiceScorer diceScorer : diceScorers){
			if (! (diceScorer instanceof Nothing) && diceScorer.getScore(dice) != null){
				return null;
			}
		}
		return new DiceScore(dice, score);
	}

}
