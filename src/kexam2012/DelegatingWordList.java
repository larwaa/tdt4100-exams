package kexam2012;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class DelegatingWordList implements Words {

	private Words words1, words2;

	public DelegatingWordList(Words words1, Words words2){
		this.words1 = words1;
		this.words2 = words2;
	}

	public boolean containsWord(String word){
		return words1.containsWord(word) || words2.containsWord(word);
	}

	public Collection<String> getWordsStartingWith(String prefix){
		Collection<String> result = new ArrayList<>();
		result.addAll(words1.getWordsStartingWith(prefix));
		result.addAll(words2.getWordsStartingWith(prefix));
		return result.stream().distinct().collect(Collectors.toList());
	}

	public boolean addWord(String word){
		return words1.addWord(word);
	}

	public boolean removeWord(String word){
		return words1.removeWord(word) || words2.removeWord(word);
	}
}
