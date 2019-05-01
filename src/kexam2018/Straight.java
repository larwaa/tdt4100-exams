package kexam2018;

public class Straight extends AbstractScorer{

	public Straight(int score){
		super(score);
	}

	@Override
	public DiceScore getScore(Dice dice){
		for (int i = 0; i < dice.getDieCount() - 1; i++){
			int difference = dice.getDieValue(i + 1) - dice.getDieValue(i);
			if (difference != 1){
				return null;
			}
		}
		return new DiceScore(dice, score);
	}

}
