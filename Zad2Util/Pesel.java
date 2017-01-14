// klasa pesel; przechowuje i weryfikuje numer pesel poprzez sprawdzenie znaku płci, weryfikację daty urodzenia i obliczenie liczby kontrolnej

package Zad2Util;
import java.io.Serializable;
import java.time.LocalDate;

public class Pesel implements Serializable{
	private String number;
	private static final int[] CONTROLL_WEIGHTS = {9, 7, 3, 1, 9, 7, 3, 1, 9, 7};
	private static final int[] MONTH_DAYS = {0,31,28,31,30,31,30,31,31,30,31,30,31}; 
	private static final int[] MONTH_DAYS_L = {0,31,29,31,30,31,30,31,31,30,31,30,31}; 
	
	public String getNumber(){
		return number;
	}
	
	public String toString(){
		return number;
	}
	
	public void setNumber(String number, LocalDate birthDate, Gender gender){
		if (verifyNumber(number, birthDate, gender)){
			this.number=number;
		} else {
			throw new IllegalArgumentException("Illegal PESEL format");
		}
	}
	
	public Pesel(String number, LocalDate birthDate, Gender gender){
		setNumber(number, birthDate, gender);
	}
	
	private boolean verifyNumber(String number, LocalDate birthDate, Gender gender){
		if (number.length()!=11) return false;
		int yearNumber = Integer.parseInt((number.substring(0, 2)));
		int monthNumber = Integer.parseInt((number.substring(2, 4)));
		int dayNumber = Integer.parseInt((number.substring(4, 6)));
		int randomNumber = Integer.parseInt((number.substring(6, 10)));
		int controllSumNumber = Integer.parseInt((number.substring(10)));
		
		int yearCoded;
		if (monthNumber%20>12||monthNumber<1) return false;
		switch (monthNumber/20){
			case 4:
				yearCoded = 1800+yearNumber;
				break;
			default: 
				yearCoded = 1900+yearNumber+100*(monthNumber/20);
		}
		int monthCoded = monthNumber%20;
		int dayCoded = dayNumber;
		if (dayNumber<1) return false;
		if (yearCoded%4==0){
			if (yearCoded%100==0){
				if (yearCoded%400==0){
					if (dayNumber>MONTH_DAYS_L[monthCoded]) return false;
				} else {
					if (dayNumber>MONTH_DAYS[monthCoded]) return false;
				}
			} else {
				if (dayNumber>MONTH_DAYS_L[monthCoded]) return false;
			}
		} else {
			if (dayNumber>MONTH_DAYS[monthCoded]) return false;
		}
		
		LocalDate dateCoded = LocalDate.of(yearCoded, monthCoded, dayCoded);
		
		if (!dateCoded.equals(birthDate)) return false;
		
		int controllSum=0;
		for (int i=0; i<number.length()-1; i++){
			controllSum+=Integer.parseInt(number.charAt(i)+"")*CONTROLL_WEIGHTS[i];
		}
		if (controllSum%10!=controllSumNumber) return false;
		
		if (randomNumber%2==0 && !gender.isFemale()) return false;
		if (randomNumber%2==1 && gender.isFemale()) return false;
		
		return true;
	}
}