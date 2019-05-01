package kexam2018;

import java.util.Arrays;

public class DiceTest {

	private Dice dice, diceZero, diceEmpty;

	public void setUp(){
		dice = new Dice(Arrays.asList(2, 2, 4, 3, 0, 0));
		diceZero = new Dice(Arrays.asList(0, 0, 0, 0, 0, 0));
		diceEmpty = new Dice(Arrays.asList());

	}

//
//	public void testDiceContainsDice(){
//		assertTrue(dice.contains(dice));
//	}
//
//	public void testDiceContainsDiceZero(){
//		assertTrue(dice.contains(diceZero));
//	}
//
//	public void testDiceZeroDoesNotContainDice(){
//		assertFalse(diceZero.contains(dice));
//	}
//
//	public void testZeroContainsZero(){
//		assertTrue(diceZero.contains(diceZero));
//	}
//
//	public void testEmptyContainsEmpty(){
//		assertTrue(diceEmpty.contains(diceEmpty));
//	}
//
//	public void testDiceZeroContainsEmpty(){
//		assertTrue(diceZero.contains(diceEmpty));
//	}
}
