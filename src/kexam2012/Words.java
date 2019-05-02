package kexam2012;

import java.util.Collection;

public interface Words {

	public boolean containsWord(String word);
	public Collection<String> getWordsStartingWith(String prefix);
	public boolean addWord(String word);
	public boolean removeWord(String word);

}
