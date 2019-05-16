package exam2010;

public class MorseAlphabet extends RadioAlphabet {


	public MorseAlphabet(String alphabet){ // Format: s... o--- s...
		super(alphabet, " ");
	}

	public void setWord(char c, String s){
		super.setWord(c + s);
	}


}
