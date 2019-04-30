package exam2014;

import java.io.PrintStream;
import java.util.*;

public class Question {

	private String question;
	private String answer;
	private List<String> options = new ArrayList<>();

	public Question(String question, String answer, Collection<String> options){
		if (question == null || answer == null){
			throw new IllegalArgumentException();
		}
		this.question = question;
		this.answer = answer;

		if (options != null){
			this.options.addAll(options);
		}

		if (! this.options.contains(answer)){
			throw new IllegalArgumentException("The answer is not an option.");
		}
	}

	public Question(String question, String answer, String...options){
		this(question, answer, Arrays.asList(options));
	}

	public void askQuestion(PrintStream printStream){
		printStream.println(question);
		int i = 1;
		for (String option : options){
			printStream.println(i + ". " + option);
		}
	}


	public static void main(String[] args){

	}


}
