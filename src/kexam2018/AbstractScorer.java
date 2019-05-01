package kexam2018;

public abstract class AbstractScorer implements DiceScorer {

	protected final int score;

	public AbstractScorer(int score){
		this.score = score;
	}

	@Override
	public abstract DiceScore getScore(Dice dice);

}
