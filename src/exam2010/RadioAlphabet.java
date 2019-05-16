package exam2010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadioAlphabet {

	private Map<Character, String> alphabet = new HashMap<>();
	private List<RadioAlphabetListener> radioAlphabetListeners = new ArrayList<>();

	public RadioAlphabet(String alphabet, String separator){
		for (String s : alphabet.split(separator)){
			this.alphabet.put(s.charAt(0), s);
		}
	}

	public String convert(char c){
		return alphabet.get(c);
	}

	public String convert(String s){
		String result = "";
		for (Character c : s.toCharArray()){
			result += convert(c) + " ";
		}
		return result;
	}

	public void fireRadioAlphabetChanged(char c){
		for (RadioAlphabetListener radioAlphabetListener : radioAlphabetListeners){
			radioAlphabetListener.radioAlphabetChanged(this, c);
		}
	}

	public void addRadioAlphabetListener(RadioAlphabetListener radioAlphabetListener){
		radioAlphabetListeners.add(radioAlphabetListener);
	}

	public void removeRadioAlphabetListener(RadioAlphabetListener radioAlphabetListener){
		radioAlphabetListeners.remove(radioAlphabetListener);
	}

	// Kan ikke være static fordi de avhenger av en intern tilstand som er koblingen mellom char og tilsvarende string.

	public void setWord(String word){
		char c = word.charAt(0);
		if (alphabet.containsKey(c)){
			alphabet.put(c, word);
			fireRadioAlphabetChanged(c);
		}
	}

	public void removeWord(String word){
		if (alphabet.remove(word.charAt(0)) != null) fireRadioAlphabetChanged(word.charAt(0));
	}

}
