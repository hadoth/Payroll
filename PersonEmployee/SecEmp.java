// klasa pracownika ochrony; dziedziczy po pracowniku, dodatkowo posiada rewir (lista budynków) oraz zmianę

package PersonEmployee;
import java.time.LocalDate;

import Zad2Util.*;

public class SecEmp extends Employee{
	private String[] district;
	private Shift shift;
	
	
	// set methods
	public void setShift (Shift shift){
		this.shift = shift;
	}
	
	public void setDistrict(String[] district){
		this.district = district;
	}
	
	// get methods
	public Shift getShift (){
		return this.shift;
	}
	
	public String[] getDistrict(){
		return this.district;
	}
	
	// other methods
	public String toString(){
		String result = "          ---***---\n";
		result+="PRACOWNIK TECHNICZNY I SPRZĄTAJĄCY\n";
		result+=super.toString();
		result+="\tZmiana:\t"+shift+"\n";
		result+="\tRewir:\n";
		for (int i=0; i<district.length; i++){
			result+="\t\t"+district[i]+"\n";
		}
		return result;
	}
	
	//constructors
	public SecEmp(String firstName, String surname, String title, String gender, double salary, String birthDate, String employmentDate, String pesel, String[] district, Shift shift){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
		this.shift = shift;
	}
	public SecEmp(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel, String[] district, Shift shift){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
		this.shift = shift;
	}
	public SecEmp(String firstName, String surname, String title, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel, String[] district, Shift shift){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
		this.shift = shift;
	}
	public SecEmp(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel, String[] district, Shift shift){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
		this.shift = shift;
	}
	public SecEmp(String firstName, String surname, String gender, double salary, String birthDate, String employmentDate, String pesel, String[] district, Shift shift){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
		this.shift = shift;
	}
	public SecEmp(String firstName, String surname, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel, String[] district, Shift shift){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
		this.shift = shift;
	}
	public SecEmp(String firstName, String surname, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel, String[] district, Shift shift){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
		this.shift = shift;
	}
	public SecEmp(String firstName, String surname, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel, String[] district, Shift shift){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
		this.shift = shift;
	}
}