//package kexam2018;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class DiceScorerController {
//
//	private DiceScorer singleValue, straight, nothing;
//
//	@FXML
//	public void initialize(){
//		this.singleValue = new SingleValue(2, 10);
//		this.straight = new Straight(500);
//		this.nothing = new Nothing(3, 100, singleValue, straight);
//	}
//
//	@FXML private TextField dieValuesInput;
//	@FXML private Label scoreOutput, diceOutput;
//
//	private Dice getDiceInput(){
//		Collection<Integer> dieValues = new ArrayList<>();
//		for (String dieValue : dieValuesInput.getText().split(" ")){
//			dieValues.add(Integer.parseInt(dieValue));
//		}
//		return new Dice(dieValues);
//	}
//
//	private void runDiceScorer(DiceScorer scorer){
//		DiceScore diceScore = scorer.getScore(getDiceInput());
//
//		if (diceScore != null){
//			scoreOutput.setText(diceScore.getScore());
//			diceOutput.setText(diceScore.getDice());
//		} else {
//			scoreOutput.setText("");
//			diceOutput.setText("");
//		}
//	}
//
//	public void testSingleValue(){
//		runDiceScorer(singleValue);
//	}
//
//	public void testStraight(){
//		runDiceScorer(straight);
//	}
//
//	public void testNothing(){
//		runDiceScorer(nothing);
//	}
//
//}
