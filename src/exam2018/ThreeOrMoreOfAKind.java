package exam2018;

import java.util.HashMap;
import java.util.Map;

public class ThreeOrMoreOfAKind implements DiceScorer {

	public Dice getScore(Dice dice){
		Map<Integer, Integer> dieCounts = new HashMap<>();

		for (int dieValue : dice){
			dieCounts.put(dieValue, dieCounts.getOrDefault(dieValue, 0) + 1);
		}

		Collection<Integer> scores = new ArrayList<>();

		int maxScore = 0;
		int scoringDieValue = 0;

		for (int dieValue : dieCounts.keySet()){
			int tempScore = calculateScore(dieValue, dieCounts.get(dieValue));
			boolean newScore = tempScore > maxScore || (tempScore == maxScore && dieCounts.get(dieValue) > dieCounts.get(scoringDieValue));
			maxScore = newScore ? tempScore : maxScore;
			scoringDieValue = newScore ? dieValue : scoringDieValue;
		}
		return new Dice(diceCollection(scoringDieValue, dieCounts.get(scoringDieValue), maxScore));
	}

	private Collection<Integer> diceCollection(int dieValue, int dieCount){
		Collection<Integer> result = new ArrayList<>();
		for (int i = 0; i < dieCount; i++){
			result.add(dieValue);
		}
		return result;
	}

	private int calculateScore(int dieValue, int dieCount){
		int score = 0;
		if (dieCount >= 3){
			score = dieValue * 100;
			for (int bonus = 0; bonus < dieCount - 3; bonus++){
				score *= 2;
			}
		}
		return score;
	}
}
