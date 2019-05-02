package kexam2012;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WordList implements Words {

	private List<String> wordList = new ArrayList<>();

	public boolean containsWord(String word){
		return wordList.contains(word);
	}

	public Collection<String> getWordsStartingWith(String prefix){
		return wordList.stream()
						.filter((word) -> word.startsWith(prefix))
						.collect(Collectors.toList());
	}

	public boolean addWord(String word){
		word = word.trim();
		return (word.length() > 0 && !containsWord(word) ? wordList.add(word) : false);
	}

	public boolean removeWord(String word){
		return wordList.remove(word);
	}

	public boolean removeWordsStartingWith(String prefix){
		return wordList.removeAll(getWordsStartingWith(prefix));
	}

	public String getPrefix(String word, String suffix){
		return word.endsWith(suffix) ? word.substring(0, word.lastIndexOf(suffix)) : null;
	}

	public boolean hasSuffixes(String prefix, List<String> suffixes){
		return suffixes.stream().allMatch((suffix) -> containsWord(prefix + suffix));
	}

	public List<String> findPrefixes(List<String> suffixes){
		List<String> prefixes = new ArrayList<>();
		for (String word : wordList){
			prefixes = suffixes.stream()
					.map((suffix) -> getPrefix(word, suffix))
					.filter((prefix) -> prefix != null)
					.collect(Collectors.toList());
		}
		return prefixes;
	}

	public void read(InputStream input) throws Exception {
		Scanner sc = new Scanner(input);

		while (sc.hasNext()){
			String line = sc.next();
			int pos = line.indexOf("#");

			if (pos >= 0){
				line = line.substring(0, line.indexOf("#")).trim();
			}
			System.out.println();

			if (line.indexOf("-") == -1){
				//Just a normal word!
				addWord(line);
			} else {
				String prefix = line.substring(0, line.indexOf("-")).trim();

				if (line.indexOf(",") == -1){
					addWord(prefix);
				}
				else {
					String[] splitLine = line.substring(line.indexOf("-") + 1).split("[,]");
					for (String suffix : splitLine){
						addWord(prefix + suffix.trim());
					}
				}
			}
		}
		sc.close();
	}
}
