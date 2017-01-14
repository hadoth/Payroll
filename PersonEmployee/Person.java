// klasa osoby; zawiera imię, nazwisko, tytuł, płeć, datę urodzenia i pesel

package PersonEmployee;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.regex.Pattern;

import Zad2Util.Gender;
import Zad2Util.Pesel;

public abstract class Person implements Serializable{
	
	// stałe
	private static final int[] MONTH_DAYS = {0,31,28,31,30,31,30,31,31,30,31,30,31}; 
	private static final int[] MONTH_DAYS_L = {0,31,29,31,30,31,30,31,31,30,31,30,31}; 
	
	// deklaracja zmiennych
	private String name;
	private String surname;
	private String title;
	private Gender gender;
	private LocalDate birthDate;
	private Pesel pesel;
	
	
	// to string
	public String toString(){
		String result = (title+" "+name+" "+surname+"\n");
		result+=("\tpłeć:\t\t"+gender.getGenderShort()+"\n");
		result+=("\tdata urodzenia:\t"+birthDate.getDayOfMonth()+"."+birthDate.getMonth()+"."+birthDate.getYear()+"\n ");
		result+=("\tPESEL:\t\t"+pesel+"\n");
		return result;
	}
	
	// metody get
	public String getName(){
		return this.name;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getFullName(){
		if (this.title.isEmpty()) return (this.name+" "+this.surname);
		else return (this.title+" "+this.name+" "+this.surname);
	}
	
	public String getGender(){
		return this.gender.getGender();
	}
	
	public LocalDate getBirthDate(){
		return this.birthDate;
	}
	
	public String getPesel(){
		return this.pesel.getNumber();
	}
	
	// metody set
	public void setName(String name){
		name = checkName(name);
		this.name=name;
	}
	
	public void setSurname(String surname){
		surname = checkSurname(surname);
		this.surname=surname;
	}
	
	public void setTitle(String title){
		if (title==""){
			if (this.gender.isFemale()) title="pani";
			else title="pan";
		}
		this.title = title;
	}
	
	public void setGender(String gender){
		this.gender.setGender(gender);
	}
	
	public void setBirthDate(String date){
		setBirthDate(verifyDate(date));
	}
	
	public void setBirthDate(LocalDate date){
		this.birthDate = date;
	}
	
	public void setPesel(String number){
		this.pesel = new Pesel(number, this.birthDate, this.gender);
	}
	
	// konstruktory
	
	public Person(String firstName, String surname, String title, String gender, String birthDate, String pesel){
		setName(firstName);
		setSurname(surname);
		this.gender = new Gender(gender);
		setBirthDate(birthDate);
		this.pesel = new Pesel(pesel, this.birthDate, this.gender);
		setTitle(title);
	}
	
	public Person(String firstName, String surname, String title, String gender, LocalDate birthDate, String pesel){
		setName(firstName);
		setSurname(surname);
		this.gender = new Gender(gender);
		setBirthDate(birthDate);
		this.pesel = new Pesel(pesel, this.birthDate, this.gender);
		setTitle(title);
	}
	
	public Person(String firstName, String surname, String gender, String birthDate, String pesel){
		setName(firstName);
		setSurname(surname);
		this.gender = new Gender(gender);
		setBirthDate(birthDate);
		this.pesel = new Pesel(pesel, this.birthDate, this.gender);
		setTitle("");
	}
	
	public Person(String firstName, String surname, String gender, LocalDate birthDate, String pesel){
		setName(firstName);
		setSurname(surname);
		this.gender = new Gender(gender);
		setBirthDate(birthDate);
		this.pesel = new Pesel(pesel, this.birthDate, this.gender);
		setTitle("");
	}
	
	// metody prywatne
	
	private static String checkSurname(String surname){
		String regEx = "((\\s|^)[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśżź]{2,20}[\\-]{1}[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśżź]{2,20}(\\s|$))|((\\s|^)[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśżź]{2,20}(\\s|$))";
		ArrayList<String> names = regexChecker(regEx, surname);
		if (names.isEmpty()) throw new IllegalArgumentException("Invalid data or no data provided");
		if (names.size()>1) throw new IllegalArgumentException("Invalid number of words");
		return names.get(0);
	}
	
	private static String checkName(String name){
		String regEx = "(\\s|^)[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśżź]{2,20}(\\s|$)";
		ArrayList<String> names = regexChecker(regEx, name);
		if (names.isEmpty()) throw new IllegalArgumentException("Invalid data or no data provided");
		if (names.size()>2) throw new IllegalArgumentException("Invalid number of words");
		if (names.size()==2) return names.get(0)+" "+names.get(1);
		return names.get(0);
	}
	
	private static ArrayList<String> regexChecker(String regEx, String str2chk){
		String[] names = str2chk.split(" ");
		ArrayList<String> resultList = new ArrayList<String>();
		Pattern myPattern = Pattern.compile(regEx);
		Matcher regExMatch;
		String name;
		for (int i=0; i<names.length; i++) {
			regExMatch = myPattern.matcher(names[i]);
			if(regExMatch.find()){
				if(regExMatch.group().trim().length()!=0){
					name = regExMatch.group().trim();
					if(name.contains("-")){					// tutaj trzeba dodać warunek, że gdy names[i] jest niepuste, a name jest puste, to wywala błąd
						String[] twoNames = name.split("-");
						twoNames[0] = toFUpperCase(twoNames[0]);
						twoNames[1] = toFUpperCase(twoNames[1]);
						name = twoNames[0] + "-" + twoNames[1]; 
					} else {
						name = toFUpperCase(name);
					}
					resultList.add(name);
				}
			}
		}
		return resultList;
	}
	
	private static String toFUpperCase(String name){
		return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
	}
	
	public LocalDate verifyDate(String date){
		String[] dateParts;
		if (date.contains(".")) dateParts = date.split("\\.");
		else dateParts = date.split("-");
		if (dateParts.length!=3) throw new IllegalArgumentException("invalid data format: try DD-MM-YYYY"+dateParts.length);
		int dayNum = Integer.parseInt(dateParts[0].trim());
		int monthNum = Integer.parseInt(dateParts[1].trim());
		dateParts[2] = dateParts[2].trim();
		int yearNum;
		if (dateParts[2].length()==2) yearNum = 1900+Integer.parseInt(dateParts[2]);
		else if(dateParts[2].length()==4) yearNum = Integer.parseInt(dateParts[2]);
		else throw new IllegalArgumentException("invalid data format: try DD-MM-YYYY");
		if (monthNum<1||monthNum>12) throw new IllegalArgumentException("invalid data format: try DD-MM-YYYY");
		if (dayNum<1) throw new IllegalArgumentException("invalid data format: try DD-MM-YYYY");
		if (yearNum%4==0){
			if (yearNum%100==0){
				if (yearNum%400==0){
					if (dayNum>MONTH_DAYS_L[monthNum]) throw new IllegalArgumentException("invalid data format: try DD-MM-YYYY");
				} else {
					if (dayNum>MONTH_DAYS[monthNum]) throw new IllegalArgumentException("invalid data format: try DD-MM-YYYY");
				}
			} else {
				if (dayNum>MONTH_DAYS_L[monthNum]) throw new IllegalArgumentException("invalid data format: try DD-MM-YYYY");
			}
		} else {
			if (dayNum>MONTH_DAYS[monthNum]) throw new IllegalArgumentException("invalid data format: try DD-MM-YYYY");
		}
		 return LocalDate.of(yearNum, monthNum, dayNum);
	}
}