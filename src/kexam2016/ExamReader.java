package kexam2016;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class ExamReader {

	public Collection<Exam> readExams(Reader input){
		Scanner scanner = new Scanner(input);
		Collection<Exam> result = new ArrayList<>();
		String time = "";

		while (scanner.hasNextLine()){

			String line = scanner.nextLine().trim();
			String[] splitLine = line.split(" ");

			if (splitLine.length == 1){
				//Semesterkode
				time = splitLine[0];
			} else if (splitLine.length > 1){
				//courseline
				String code = splitLine[0];
				double credits = Double.parseDouble(splitLine[1]);
				//create the course
				Course course = new Course(code);
				//set properties
				course.setTime(time);
				course.setCredits(credits);

				for (String s : Arrays.asList(splitLine).subList(2, splitLine.length)){
					//for every remaining character in splitLine, create an exam with that grade
					result.add(new Exam(course, s.charAt(0)));
				}
			}
		}
		return result;
	}

}
