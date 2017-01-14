// klasa pracownika administracyjnego; dziedziczy po pracowniku, dodatkowo posiada adres urzędowania

package PersonEmployee;
import java.time.LocalDate;

import Zad2Util.*;

public class AdmEmp extends Employee{
	private UniAddress location;
	
	// set methods
	public void setLocation(UniAddress location){
		this.location = location;
	}
	
	// get methods
	public UniAddress getLocation(){
		return this.location;
	}
	
	// other methods
	public String toString(){
		String result = "          ---***---\n";
		result+="PRACOWNIK ADMINISTRACYJNY\n";
		result+=super.toString();
		result+="\tGabinet:\t"+location+"\n";
		return result;
	}
	
	//constructors
	public AdmEmp(String firstName, String surname, String title, String gender, double salary, String birthDate, String employmentDate, String pesel, UniAddress location){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.location = location;
	}
	public AdmEmp(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel, UniAddress location){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.location = location;
	}
	public AdmEmp(String firstName, String surname, String title, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel, UniAddress location){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.location = location;
	}
	public AdmEmp(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel, UniAddress location){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.location = location;
	}
	public AdmEmp(String firstName, String surname, String gender, double salary, String birthDate, String employmentDate, String pesel, UniAddress location){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.location = location;
	}
	public AdmEmp(String firstName, String surname, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel, UniAddress location){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.location = location;
	}
	public AdmEmp(String firstName, String surname, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel, UniAddress location){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.location = location;
	}
	public AdmEmp(String firstName, String surname, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel, UniAddress location){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.location = location;
	}
}