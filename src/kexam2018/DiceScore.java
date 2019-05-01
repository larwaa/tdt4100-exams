package kexam2018;

public class DiceScore {

	private final Dice dice;
	private final int score;

	public DiceScore(Dice dice, int score){
		this.dice = dice;
		this.score = score;
	}

	public Dice getDice(){
		return dice;
	}

	public int getScore(){
		return score;
	}


}
