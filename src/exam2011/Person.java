package exam2011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Person {

	private final char gender;
	private Date birthday;
	private String personalIdentity;

	public Person(char gender){
		checkGender(gender);
		this.gender = gender;
	}

	private void checkGender(char gender){
		if (!(gender == 'F' || gender == 'M')){
			throw new IllegalArgumentException();
		}
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public char getGender(){
		return gender;
	}

	public Date getBirthday(){
		return birthday;
	}

	public void setPersonalIdentity(String personalIdentity){
		checkPersonalIdentity(personalIdentity);
		this.personalIdentity = personalIdentity;

	}

	private void checkPersonalIdentity(String personalIdentity){
		verifyDigits(personalIdentity);
		verifyDate(personalIdentity);
		verifyN3(personalIdentity);
		verifyK(personalIdentity);
	}

	private void verifyDigits(String personalIdentity){
		if (personalIdentity.length() != 11){
			throw new IllegalArgumentException();
		}

		try{
			Integer.valueOf(personalIdentity);
		} catch (NumberFormatException e){
			throw new IllegalArgumentException(e);
		}
	}

	private void verifyN3(String personalIdentity){
		int n3 = getInteger(personalIdentity, 8);
		 if (!(getGender() == 'F' ? n3 % 2 == 0 : n3 % 2 == 1)){
		 	throw new IllegalArgumentException();
		 }
	}

	private void verifyDate(String personalIdentity){
		String birthdate = personalIdentity.substring(0, 7);
		Boolean day = verifyStringToInt(birthdate.substring(0, 2), birthday.getDate());
		Boolean month = verifyStringToInt(birthdate.substring(2, 4), birthday.getMonth());
		Boolean year = verifyStringToInt(birthdate.substring(4, 6), birthday.getYear());

		if (! (day && month && year)){
			throw new IllegalArgumentException();
		}
	}

	private int computeK(String personalIdentity, List<Integer> verificationList){
		char[] personalIdentityArray = personalIdentity.toCharArray();
		int weightedSum = 0;
		for (int i = 0; i < verificationList.size(); i++){
			weightedSum += personalIdentityArray[i] * verificationList.get(i);
		}
		return 11 - (weightedSum % 11);
	}

	private void verifyK(String personalIdentity){
		int k1 = computeK(personalIdentity, Arrays.asList(3, 7, 6, 1, 8, 9, 4, 5, 2));
		int k2 = computeK(personalIdentity, Arrays.asList(5, 4, 3, 2, 7, 6, 5, 4, 3, 2));

		if (!(k1 == getInteger(personalIdentity, 9) && k2 == getInteger(personalIdentity, 10))){
			throw new IllegalArgumentException();
		}
	}

	private int getInteger(String personalIdentity, int index){
		return Character.getNumericValue(personalIdentity.charAt(index));
	}

	private boolean verifyStringToInt(String actual, int expected){
		return Integer.valueOf(actual) == expected;
	}
}
