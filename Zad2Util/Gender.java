// klasa płci; posiada zmienną przechowującą płeć w formie boolean, zwraca i przyjmuje płeć w formie string (długa lub krótka)
package Zad2Util;

import java.io.Serializable;

public class Gender implements Serializable{
	private boolean isFemale;
	
	public String getGender(){
		if (this.isFemale) return "kobieta";
		else return "mężczyzna";
	}
	
	public String getGenderShort(){
		if (this.isFemale) return "k";
		else return "m";
	}
	
	public void setGender(String gender){
		gender = gender.toLowerCase();
		switch (gender){
		case "k":
		case "kobieta":
		case "żeńska":
		case "zeńska":
		case "żenska":
			this.isFemale = true;
			break;
		case "m":
		case "mężczyzna":
		case "meżczyzna":
		case "męzczyzna":
		case "mezczyzna":
		case "meska":
		case "męska":
			this.isFemale = false;
			break;
		}
	}
	
	public boolean isFemale(){
		return this.isFemale;
	}
	
	public Gender(String gender){
		setGender(gender);
	}
	
	public String toString(){
		return getGenderShort();
	}
}