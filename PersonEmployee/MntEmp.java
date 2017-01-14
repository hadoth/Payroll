// klasa pracownika technicznego; dziedziczy po pracowniku, dodatkowo posiada rewir(listę budynków), za który jest odpowiedzialny

package PersonEmployee;

import java.time.LocalDate;

public class MntEmp extends Employee{
	private String[] district;
	
	// set methods
	public void setDistrict(String[] district){
		this.district = district;
	}
	
	// get methods
	public String[] getDistrict(){
		return this.district;
	}
	
	// other methods
	public String toString(){
		String result = "          ---***---\n";
		result+="PRACOWNIK TECHNICZNY I SPRZĄTAJĄCY\n";
		result+=super.toString();
		result+="\tPodległe budynki:\n";
		for (int i=0; i<district.length; i++){
			result+="\t\t"+district[i]+"\n";
		}
		return result;
	}
	
	//constructors
	public MntEmp(String firstName, String surname, String title, String gender, double salary, String birthDate, String employmentDate, String pesel, String[] district){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
	}
	public MntEmp(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel, String[] district){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
	}
	public MntEmp(String firstName, String surname, String title, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel, String[] district){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
	}
	public MntEmp(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel, String[] district){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
	}
	public MntEmp(String firstName, String surname, String gender, double salary, String birthDate, String employmentDate, String pesel, String[] district){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
	}
	public MntEmp(String firstName, String surname, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel, String[] district){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
	}
	public MntEmp(String firstName, String surname, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel, String[] district){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
	}
	public MntEmp(String firstName, String surname, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel, String[] district){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.district = district;
	}
}