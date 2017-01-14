import java.util.Arrays;

import PersonEmployee.*;
import Zad2.*;
import Zad2Util.*;

public class IdeaTester{
	public static void main(String[] args){
		Payroll fullList = new Payroll();
		for (int i=0; i<25; i++){
			for (int j=0; j<4; j++){
				Employee newOne = RandomEmployeeGenerator.randEmployee();
				System.out.println("nowy: "+newOne.getFullName());
				fullList.add(newOne);
				System.out.println(fullList.getNames()+"("+fullList.length()+")");
			}
			int randomOut = (int)(Math.random()*fullList.length());
			System.out.print("wylatuje numer "+randomOut);
			System.out.println("("+fullList.getEmployee(randomOut).getFullName()+")");
			fullList.remove(randomOut);
			System.out.println(fullList.getNames()+"("+fullList.length()+")");
		}
		System.out.println("wynik:\n"+fullList.findSurname("Nowak"));
		System.out.println("bogacze:\n"+fullList.findSurname("Nowak").findSalaryGreater(1500));
		System.out.println("biedaki:\n"+fullList.findSurname("Nowak").findSalaryLower(1500));
		int index = fullList.getIndexOf(fullList.findSurname("Nowak").findSalaryLower(1500).getEmployee(0));
		System.out.println(fullList.findSurname("Nowak").findSalaryLower(1500).getEmployee(0));
		System.out.println(fullList.getEmployee(index));
		
		Payroll officeHours = fullList.findOfficeHoursDay(WeekDay.PN);
		for (int i=0; i<officeHours.length(); i++){
			System.out.println(officeHours.getEmployee(i).getFullName()+": "+((EdEmp)(officeHours.getEmployee(i))).getOfficeHours());
		}
		int index2 = fullList.getIndexOf(officeHours.getEmployee(0));
		EdEmp testEmp = (EdEmp)fullList.getEmployee(index2);
		UniAddress address = new UniAddress(testEmp.getOfficeHours().getLocation().getBuilding(), testEmp.getOfficeHours().getLocation().getRoomNumber());
		
		Payroll officeHoursBuildings = fullList.findOfficeHoursBuilding("C-6");
		System.out.println("\n znaleziono wyników: "+officeHoursBuildings.length());
		for (int i=0; i<officeHoursBuildings.length(); i++){
			System.out.println(officeHoursBuildings.getEmployee(i).getFullName()+": "+((EdEmp)(officeHoursBuildings.getEmployee(i))).getOfficeHours());
		}
		
		Payroll officeHoursAddress = fullList.findOfficeHoursAddress(address);
		System.out.println("\n znaleziono wyników: "+officeHoursAddress.length());
		for (int i=0; i<officeHoursAddress.length(); i++){
			System.out.println(officeHoursAddress.getEmployee(i).getFullName()+": "+((EdEmp)(officeHoursAddress.getEmployee(i))).getOfficeHours());
		}
		
		Payroll locations = fullList.findLocation(address);
		System.out.println("wyniki: "+locations);
		System.out.println(officeHoursAddress.getEmployee(0));
		
		Payroll districts = fullList.findDistrictBuilding("C-6");
		System.out.println("districts wyniki: "+districts);
		for (int i=0; i<districts.length(); i++){
			System.out.print(districts.getEmployee(i).getFullName());
			if (districts.getEmployee(i) instanceof SecEmp) System.out.println(Arrays.toString(((SecEmp)districts.getEmployee(i)).getDistrict()));
			else System.out.println(Arrays.toString(((MntEmp)districts.getEmployee(i)).getDistrict()));
		}
		
		Payroll sec = fullList.findSecEmp();
		Payroll nightShift = fullList.findShift(Shift.NIGHT);
		Payroll morningShift = fullList.findShift(Shift.MORNING);
		Payroll afternoonShift = fullList.findShift(Shift.AFTERNOON);
		System.out.println("pełna brygada");
		System.out.println(sec);
		
		for (int i=0; i<sec.length(); i++) System.out.println(sec.getEmployee(i).getFullName()+" "+((SecEmp)sec.getEmployee(i)).getShift());
		System.out.println("ranna brygada");
		System.out.println(morningShift);
		System.out.println("popoudniowa brygada");
		System.out.println(afternoonShift);
		System.out.println("nocna brygada");
		System.out.println(nightShift);
	}
}