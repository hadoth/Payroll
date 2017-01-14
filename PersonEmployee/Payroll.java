/* 
 * klasa listy płac; służy do agregowania pracowników
 * posiada funkcje pozwalające na dodawanie, usuwanie i wyszukiwanie pracowników
 * ponadto pozwala na zapis i wczytanie listy płac z pliku
*/
package PersonEmployee;

import java.util.*;
import Zad2Util.*;
import java.io.*;

public class Payroll implements Serializable{
	private Employee[] employeeList;
	private int counter;
	
	// set methods
	
	// get methods
	public Employee getEmployee(int index){ // zwraca pracownika o indeksie i
		if (index>=0 && index<counter) return employeeList[index];
		else throw new IndexOutOfBoundsException("test");
	}
	
	public String getNames(){ // zwraca listę wszystkich nazwisk
		String result ="";
		for (int i=0; i<counter; i++) result+=employeeList[i].getSurname()+"; ";
		return result;
	}
	
	public int getIndexOf(Employee employee){ // zwraca indeks pracownika; w przypadku gdy szukanego pracownika nie ma na liście zwraca wartość -1
		for (int i=0; i<counter; i++) if (employeeList[i].equals(employee)) return i;
		return -1;
	}
	
	
	// IO methods
	
	public String payrollOut(String savePath){ // zapisuje plik pod podanym adresem
		try{
			FileOutputStream fileOut = new FileOutputStream(savePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			return "Udana operacja zapisu w: "+savePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "error: "+e.getMessage();
		}
	}
	
	public static Payroll payrollIn(String loadPath){ // wczytuje plik z podanego adresu
		try {
			FileInputStream fileIn = new FileInputStream(loadPath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Payroll result = (Payroll) in.readObject();
			in.close();
			fileIn.close();
			return result;
		} catch (Exception e){
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}
	
	// find methods
	
	public Payroll findEdEmp(){ // zwraca listę pracowników naukowo-dydaktycznych
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++) if (employeeList[i] instanceof EdEmp) result.add(employeeList[i]);
		return result;
	}
	
	public Payroll findAdmEmp(){ // zwraca listę pracowników administracyjnych
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++) if (employeeList[i] instanceof AdmEmp) result.add(employeeList[i]);
		return result;
	}
	
	public Payroll findSecEmp(){ // zwraca listę pracowników ochrony
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++) if (employeeList[i] instanceof SecEmp) result.add(employeeList[i]);
		return result;
	}
	
	public Payroll findMntEmp(){ // zwraca listę pracowników technicznych
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++) if (employeeList[i] instanceof MntEmp) result.add(employeeList[i]);
		return result;
	}
	
	public Payroll findName(String name){ // zwraca listę pracowników o podanym imieniu
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i].getName().contains(name)) result.add(employeeList[i]);
		}
		return result;
	}
	
	public Payroll findSurname(String surname){ // zwraca listę pracowników o podanym nazwisku
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i].getSurname().contains(surname)) result.add(employeeList[i]);
		}
		return result;
	}
	
	public Payroll findTitle(String title){ // zwraca listę pracvowników o podanym tytule
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i].getTitle().contains(title)) result.add(employeeList[i]);
		}
		return result;
	}
	
	public Payroll findGender(Gender gender){ // zwraca listę pracowników o zadanej płci
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i].getGender().equals(gender.getGender())) result.add(employeeList[i]);
		}
		return result;
	}
	
	public Payroll findSalary(double minValue, double maxValue){ // zwraca listę pracowników których, pensja mieści się w podanym przedziale
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i].getSalary()>=minValue && employeeList[i].getSalary()<=maxValue) result.add(employeeList[i]);
		}
		return result;
	}
	
	public Payroll findSalaryGreater(double minValue){ // zwraca listę pracowników, których pensja jest większa niż
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i].getSalary() > minValue) result.add(employeeList[i]);
		}
		return result;
	}
	
	public Payroll findSalaryLower(double maxValue){ // zwraca listę pracowników, których pensja jest mniejsza niż
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i].getSalary() < maxValue) result.add(employeeList[i]);
		}
		return result;
	}
	
	public Payroll findOfficeHoursDay(WeekDay day){ // zwraca listę pracowników, którzy mają konsultacje w podanym dniu tygodnia
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i] instanceof EdEmp){
				EdEmp educationEmp = (EdEmp)(employeeList[i]);
				if (educationEmp.getOfficeHours().getWeekDay()==day) result.add(employeeList[i]);
			}
		}
		return result;
	}
	
	public Payroll findOfficeHoursBuilding(String building){ // zwraca listę pracowników, którzy mają konsultacje w zadanym budynku
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++)if (employeeList[i] instanceof EdEmp && ((EdEmp)(employeeList[i])).getOfficeHours().getLocation().getBuilding().equals(building)) result.add(employeeList[i]);
		return result;
	}
	
	public Payroll findOfficeHoursAddress(UniAddress address){ // zwraza listę pracowników, którzy mają konsultacje pod zadanym adresem
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++)if (employeeList[i] instanceof EdEmp && ((EdEmp)(employeeList[i])).getOfficeHours().getLocation().toString().equals(address.toString())) result.add(employeeList[i]);
		return result;
	}
	
	public Payroll findLocation(UniAddress location){ // zwraca listę pracowników, którzy mają gabinet w danym budynku
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i] instanceof EdEmp && ((EdEmp)employeeList[i]).getLocation().toString().equals(location.toString())) result.add(employeeList[i]);
			if (employeeList[i] instanceof AdmEmp && ((AdmEmp)employeeList[i]).getLocation().toString().equals(location.toString())) result.add(employeeList[i]);
		}
		return result;
	}
	
	public Payroll findDistrictBuilding(String building){ // zwraca listę pracwoników, którzy mają zada
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++){
			if (employeeList[i] instanceof SecEmp && Arrays.binarySearch(((SecEmp)employeeList[i]).getDistrict(),building)>=0) result.add(employeeList[i]);
			if (employeeList[i] instanceof MntEmp && Arrays.binarySearch(((MntEmp)employeeList[i]).getDistrict(),building)>=0) result.add(employeeList[i]);			
		}
		return result;
	}
	
	public Payroll findShift(Shift shift){
		Payroll result = new Payroll();
		for (int i=0; i<counter; i++) if (employeeList[i] instanceof SecEmp && ((SecEmp)employeeList[i]).getShift().toString().equals(shift.toString())) result.add(employeeList[i]);
		return result;
	}
		
	//other methods
	
	public String toString(){
		String result ="";
		for (int i=0; i<counter; i++)result+=employeeList[i].getFullName()+" ("+employeeList[i].getPesel()+")\n";
		return result;
	}
	
	public int length(){
		return this.counter;
	}
	
	public boolean add(Employee employee){
		try{
			employeeList[counter] = employee;
			counter++;
			if (counter == employeeList.length){
				employeeList = resize(employeeList, counter);
			}
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	public boolean remove(int position){
		if (position<counter && position >=0){
			this.employeeList[position] = this.employeeList[this.counter-1];
			this.employeeList[this.counter-1] = null;
			counter--;
			if (counter < employeeList.length-4){
				employeeList = resize(employeeList, counter);
			}
			return true;
		} else{
			return false;
		}
	}
	
	private Employee[] resize(Employee[] employeeList, int counter){
		Employee[] result;
		if (employeeList.length == counter){
			result = new Employee[counter+4];
		} else {
			result = new Employee[counter+1];
		}
		for (int i=0; i<counter; i++){
			result[i] = employeeList[i];
		}
		return result;
	}
	
	// constructors
	public Payroll(){
		this.counter = 0;
		this.employeeList = new Employee[4];
	}
	
	public Payroll(Payroll ... payrolls){
		super();
		for (int i=0; i<payrolls.length; i++){
			for(int j=0; j<payrolls[i].length(); j++)
				this.add(payrolls[i].getEmployee(j));
		}
	}
}