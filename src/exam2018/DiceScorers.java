package exam2018;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiceScorers implements DiceScorer {

	private List<Dice> scores = new ArrayList<>();
	private Collection<DiceScorer> diceScorers = new ArrayList<>();

	public Dice getScore(Dice dice){
		Dice remainingDice = new Dice(dice, -1);
		int score = 0;

		for (DiceScorer diceScorer : diceScorers){
			scores.add(diceScorer.getScore(Dice));
		}

		Collections.sort(scores, (d1, d2) -> d2.getScore() - d1.getScore());

		for (Dice score : scores){
			if (remainingDice.containts(score)){
				remainingDice.remove(score);
				score += score.getScore();
			}
		}
		return scoringDice = new Dice(dice, score).remove(remainingDice);
	}

	int totalPoints = diceObjectsWithScore.stream().mapToInt(Dice::getScore).sum();

}
