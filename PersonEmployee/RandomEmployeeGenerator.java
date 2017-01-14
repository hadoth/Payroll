// generator losowych pracowników; klasa testowa, należy ją usunąć z końcowej wersji programu

package PersonEmployee;

import java.time.*;

import PersonEmployee.*;
import Zad2Util.*;

public class RandomEmployeeGenerator{
	private static final int[] WEIGHTS = {9, 7, 3, 1, 9, 7, 3, 1, 9, 7};
	private final static char[] BUILDINGS = {'A', 'B', 'C', 'D', 'H', 'L','T'};
	private final static String[] ACTIVITY_NAMES = {"Podstawy programowania", "Organizacja systemów komputerowych", "Podstawy konstrukcji maszyn", "Chemia fizyczna", "Procesy cieplne", "Podstawy technologii chemicznej"};
	private final static WeekDay[] DAYS = {WeekDay.PN, WeekDay.WT, WeekDay.SR, WeekDay.CW, WeekDay.PT, WeekDay.SO, WeekDay.ND};
	private final static Shift[] SHIFTS = {Shift.MORNING, Shift.AFTERNOON, Shift.NIGHT};

	
	public static Employee randEmployee(){
		switch ((int)(Math.random()*4)){
			case 0: return randAdmEmp();
			case 1: return randEdEmp();
			case 2: return randSecEmp();
			case 3: return randMntEmp();
			default: throw new IllegalArgumentException("Employee type not recognized");
		}
	}
	
	public static AdmEmp randAdmEmp(){
		Gender gender = randGender();
		String name = randName(gender);
		String surname = randSurname();
		String title = randTitle();
		LocalDate birthDate = randBirthDate();
		Pesel pesel = randPesel(birthDate, gender);
		LocalDate employmentDate = randEmploymentDate(birthDate);
		Money salary = randSalary();
		//
		UniAddress location = randUniAddress();
		return new AdmEmp(name, surname, title, gender.getGender(), salary.getMoney(), birthDate, employmentDate, pesel.getNumber(), location);
	}
	public static EdEmp randEdEmp(){
		Gender gender = randGender();
		String name = randName(gender);
		String surname = randSurname();
		String title = randTitle();
		LocalDate birthDate = randBirthDate();
		Pesel pesel = randPesel(birthDate, gender);
		LocalDate employmentDate = randEmploymentDate(birthDate);
		Money salary = randSalary();
		//
		UniAddress location = randUniAddress();
		Activity[] classes = randActivityList();
		Activity officeHours = randOfficeHours(location);
		return new EdEmp(name, surname, title, gender.getGender(), salary.getMoney(), birthDate, employmentDate, pesel.getNumber(),officeHours, classes, location);
	}
	public static SecEmp randSecEmp(){
		Gender gender = randGender();
		String name = randName(gender);
		String surname = randSurname();
		String title = randTitle();
		LocalDate birthDate = randBirthDate();
		Pesel pesel = randPesel(birthDate, gender);
		LocalDate employmentDate = randEmploymentDate(birthDate);
		Money salary = randSalary();
		//
		String[] district = randDistrict();
		Shift shift = randShift();
		return new SecEmp(name, surname, title, gender.getGender(), salary.getMoney(), birthDate, employmentDate, pesel.getNumber(), district, shift);
	}
	public static MntEmp randMntEmp(){
		Gender gender = randGender();
		String name = randName(gender);
		String surname = randSurname();
		String title = randTitle();
		LocalDate birthDate = randBirthDate();
		Pesel pesel = randPesel(birthDate, gender);
		LocalDate employmentDate = randEmploymentDate(birthDate);
		Money salary = randSalary();
		//
		String[] district = randDistrict();
		return new MntEmp(name, surname, title, gender.getGender(), salary.getMoney(), birthDate, employmentDate, pesel.getNumber(), district);
	}
	
	private static Gender randGender(){
		int rand = (int)(Math.random()*2+1);
		switch (rand){
			case 1: return new Gender("m");
			case 2: return new Gender("k");
			default: throw new IllegalArgumentException("Gender not recognized");
		}
	}
	
	private static String randName(Gender gender){
		if(gender.isFemale()){
			switch ((int)(Math.random()*10)){
				case 0: return "Joanna";
				case 1: return "Agnieszka";
				case 2: return "Celina";
				case 3: return "Maria";
				case 4: return "Dorota";
				case 5: return "Anna";
				case 6: return "Katarzyna";
				case 7: return "Elżbieta";
				case 8: return "Teresa";
				case 9: return "Krystyna";
				default: return "Magdalena";
			}
		} else {
			switch ((int)(Math.random()*10)){
				case 0: return "Karol";
				case 1: return "Jan";
				case 2: return "Franciszek";
				case 3: return "Wiesław";
				case 4: return "Kornel";
				case 5: return "Adrian";
				case 6: return "Maurycy";
				case 7: return "Łukasz";
				case 8: return "Patryk";
				case 9: return "Jakub";
				default: return "Grzegorz";
			}
		}
	}
	private static String randSurname(){
		switch ((int)(Math.random()*10)){
			case 0: return "Nowak";
			case 1: return "Macierewicz";
			case 2: return "Miler";
			case 3: return "Banaszak";
			case 4: return "Mickiewicz";
			case 5: return "Asnyk";
			case 6: return "Mykita";
			case 7: return "Sola";
			case 8: return "Janczura";
			case 9: return "Cyculak";
			default: return "Pacholewicz";
		}
	}
	
	private static String randTitle(){
		switch ((int)(Math.random()*7)){
			case 0: return "doc.";
			case 1: return "dr";
			case 2: return "mgr";
			case 3: return "mgr inż.";
			case 4: return "prof.";
			case 5: return "dr hab.";
			default: return "sz.p.";
		}
	}
	
	private static LocalDate randBirthDate(){
		int randYear = (int)(Math.random()*57)+1941;
		int randDay = (int)(Math.random()*365)+1;
		LocalDate result = LocalDate.ofYearDay(randYear, randDay);
		return result;
	}

	private static Pesel randPesel(LocalDate birthDate, Gender gender){
		String[] dateStrings = birthDate.toString().split("-");
		dateStrings[0] = dateStrings[0].substring(2);
		String pesel = dateStrings[0]+dateStrings[1]+dateStrings[2];
		for (int i=0; i<3; i++){
			pesel += (int)(Math.random()*10);
		}
		if (gender.isFemale()){
			pesel+=((int)(Math.random()*5)*2);
		} else {
			pesel+=((int)(Math.random()*5)*2+1);
		}
		int controlSum =0;
		for (int i=0; i<pesel.length(); i++){
			controlSum +=WEIGHTS[i]*Integer.parseInt(pesel.charAt(i)+"");
		}
		pesel+= controlSum%10;
		return new Pesel(pesel, birthDate, gender);
	}
	
	private static Money randSalary(){
		return new Money((double)((int)(Math.random()*1000)+1000));
	}
	
	private static LocalDate randEmploymentDate(LocalDate birthDate){
		int randYear = (int)(Math.random()*(LocalDate.now().getYear()-birthDate.getYear()-18))+birthDate.getYear()+18;
		int randDay = (int)(Math.random()*365)+1;
		return LocalDate.ofYearDay(randYear, randDay);
	}
	
	private static UniAddress randUniAddress(){
		return new UniAddress(BUILDINGS[(int)(Math.random()*BUILDINGS.length)]+"-"+(int)(Math.random()*11+1),(int)(Math.random()*100+1));
	}
	
	private static Activity[] randActivityList(){
		Activity[] list = new Activity[(int)(Math.random()*3+1)];
		for (int i=0; i<list.length; i++) list[i] = randActivity();
		return list;
	}
	
	private static Activity randActivity(){
		LocalTime beginning = LocalTime.of((int)(Math.random()*12)+7, (int)(Math.random()*4)*15);
		Duration duration = Duration.ofMinutes(30+(int)(Math.random()*7)*15);
		String name = ACTIVITY_NAMES[(int)(Math.random()*ACTIVITY_NAMES.length-1)];
		WeekDay day = DAYS[(int)(Math.random()*DAYS.length-1)];
		return new Activity(day, beginning, duration, name, randUniAddress());
	}
	
	private static Activity randOfficeHours(UniAddress location){
		LocalTime beginning = LocalTime.of((int)(Math.random()*12)+7, (int)(Math.random()*4)*15);
		Duration duration = Duration.ofMinutes(30+(int)(Math.random()*7)*15);
		String name = "Konsultacje";
		WeekDay day = DAYS[(int)(Math.random()*DAYS.length-1)];
		return new Activity(day, beginning, duration, name, location);
	}
	
	private static String[] randDistrict(){
		String[] list = new String[(int)(Math.random()*3+1)];
		for (int i=0; i<list.length; i++) list[i] = BUILDINGS[(int)(Math.random()*BUILDINGS.length)]+"-"+(int)(Math.random()*11+1);
		return list;
	}
	
	private static Shift randShift(){
		return SHIFTS[(int)(Math.random()*SHIFTS.length)];
	}
}