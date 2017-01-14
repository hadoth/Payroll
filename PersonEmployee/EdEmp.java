// klasa pracownika naukowo-dydaktycznego; dziedziczy po pracowniku, dodatkowo posiada adres urzędowania, konsultację i plan zajęć

package PersonEmployee;

import java.time.LocalDate;

import Zad2Util.*;

public class EdEmp extends Employee{
	private Activity officeHours;
	private Activity[] classes;
	private UniAddress location;
	
	// set methods
	public void setOfficeHours(Activity officeHours){
		this.officeHours = officeHours;
	}
	
	public void setClasses(Activity[] classes){
		this.classes = classes;
	}
	
	public void setLocation(UniAddress location){
		this.location = location;
	}
	
	// get methods
	public UniAddress getLocation(){
		return this.location;
	}
	
	public Activity getOfficeHours(){
		return this.officeHours;
	}
	
	public Activity[] getClasses(){
		return this.classes;
	}
	
	// other methods
	public String toString(){
		String result = "          ---***---\n";
		result+="PRACOWNIK NAUKOWO-DYDAKTYCZNY\n";
		result+=super.toString();
		result+="\tKonsultacje:\t"+officeHours+"\n";
		result+="\tGabinet:\t"+location+"\n";
		result+="\tPlan zajęć:\n";
		for (int i=0; i<classes.length; i++){
			result+="\t\t"+classes[i]+"\n";
		}
		return result;
	}
	
	// constructors
	public EdEmp(String firstName, String surname, String title, String gender, double salary, String birthDate, String employmentDate, String pesel, Activity officeHours, Activity[] classes, UniAddress location){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.officeHours = officeHours;
		this.classes = classes;
		this.location = location;
	}
	public EdEmp(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel, Activity officeHours, Activity[] classes, UniAddress location){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.officeHours = officeHours;
		this.classes = classes;
		this.location = location;
	}
	public EdEmp(String firstName, String surname, String title, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel, Activity officeHours, Activity[] classes, UniAddress location){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.officeHours = officeHours;
		this.classes = classes;
		this.location = location;
	}
	public EdEmp(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel, Activity officeHours, Activity[] classes, UniAddress location){
		super(firstName, surname, title, gender, salary, birthDate, employmentDate, pesel);
		this.officeHours = officeHours;
		this.classes = classes;
		this.location = location;
	}
	public EdEmp(String firstName, String surname, String gender, double salary, String birthDate, String employmentDate, String pesel, Activity officeHours, Activity[] classes, UniAddress location){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.officeHours = officeHours;
		this.classes = classes;
		this.location = location;
	}
	public EdEmp(String firstName, String surname, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel, Activity officeHours, Activity[] classes, UniAddress location){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.officeHours = officeHours;
		this.classes = classes;
		this.location = location;
	}
	public EdEmp(String firstName, String surname, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel, Activity officeHours, Activity[] classes, UniAddress location){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.officeHours = officeHours;
		this.classes = classes;
		this.location = location;
	}
	public EdEmp(String firstName, String surname, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel, Activity officeHours, Activity[] classes, UniAddress location){
		super(firstName, surname, gender, salary, birthDate, employmentDate, pesel);
		this.officeHours = officeHours;
		this.classes = classes;
		this.location = location;
	}
}