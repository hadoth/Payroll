// klasa pracownika; dziedziczy po osobie, dodatkowo posiada datę zatrudnienia i pensję

package PersonEmployee;
import java.time.LocalDate;

import Zad2Util.Money;

public class Employee extends Person{
	
	// deklaracja zmiennych
	private Money salary;
	private LocalDate employmentDate;
	
	// metody get
			
	public double getSalary(){
		return this.salary.getMoney();
	}
		
	public LocalDate getEmploymentDate(){
		return this.employmentDate;
	}
	
	// metody set
		
	public void setSalary(double salary){
		if (salary<0) throw new IllegalArgumentException("salary cannot have negative value");
		this.salary = new Money(salary);
	}
	
	public void setEmploymentDate(String date){
		setEmploymentDate(super.verifyDate(date));
	}
	
	public void setEmploymentDate(LocalDate date){
		this.employmentDate = date;
	}
	
	// other methods
	public String toString(){
		return super.toString()+"\tdata zatrudn.:\t"+employmentDate.getDayOfMonth()+"."+employmentDate.getMonth()+"."+employmentDate.getYear()+"\n\tpensja:\t\t"+salary+"\n";
	}

	// konstruktory
	
	public Employee(String firstName, String surname, String title, String gender, double salary, String birthDate, String employmentDate, String pesel){
		super(firstName, surname, title, gender, birthDate, pesel);
		if (salary<0) throw new IllegalArgumentException("salary cannot have negative value");
		this.salary = new Money(salary);
		setEmploymentDate(employmentDate);	
	}
	
	public Employee(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel){
		super(firstName, surname, title, gender, birthDate, pesel);
		if (salary<0) throw new IllegalArgumentException("salary cannot have negative value");
		this.salary = new Money(salary);
		setEmploymentDate(employmentDate);	
	}
	
	public Employee(String firstName, String surname, String title, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel){
		super(firstName, surname, title, gender, birthDate, pesel);
		if (salary<0) throw new IllegalArgumentException("salary cannot have negative value");
		this.salary = new Money(salary);
		setEmploymentDate(employmentDate);	
	}
	
	public Employee(String firstName, String surname, String title, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel){
		super(firstName, surname, title, gender, birthDate, pesel);
		if (salary<0) throw new IllegalArgumentException("salary cannot have negative value");
		this.salary = new Money(salary);
		setEmploymentDate(employmentDate);	
	}
	
	public Employee(String firstName, String surname, String gender, double salary, String birthDate, String employmentDate, String pesel){
		super(firstName, surname, gender, birthDate, pesel);
		if (salary<0) throw new IllegalArgumentException("salary cannot have negative value");
		this.salary = new Money(salary);
		setEmploymentDate(employmentDate);	
	}
	
	public Employee(String firstName, String surname, String gender, double salary, LocalDate birthDate, String employmentDate, String pesel){
		super(firstName, surname, gender, birthDate, pesel);
		if (salary<0) throw new IllegalArgumentException("salary cannot have negative value");
		this.salary = new Money(salary);
		setEmploymentDate(employmentDate);	
	}
	
	public Employee(String firstName, String surname, String gender, double salary, String birthDate, LocalDate employmentDate, String pesel){
		super(firstName, surname, gender, birthDate, pesel);
		if (salary<0) throw new IllegalArgumentException("salary cannot have negative value");
		this.salary = new Money(salary);
		setEmploymentDate(employmentDate);	
	}
	
	public Employee(String firstName, String surname, String gender, double salary, LocalDate birthDate, LocalDate employmentDate, String pesel){
		super(firstName, surname, gender, birthDate, pesel);
		if (salary<0) throw new IllegalArgumentException("salary cannot have negative value");
		this.salary = new Money(salary);
		setEmploymentDate(employmentDate);	
	}
}