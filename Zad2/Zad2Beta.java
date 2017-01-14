package Zad2;

import Zad2Util.*;
import PersonEmployee.*;


public class Zad2Beta{
	public static void main(String[] args){
		Payroll fullList = Payroll.payrollIn("new/plik.prl");
		System.out.println("Na liście płac znajduje się "+fullList.length()+" osób");
		
		Payroll officeHours = fullList.findOfficeHoursDay(WeekDay.PN);
		System.out.println('\n'+"Lista pracowników, którzy mają konsultacje w poniedziałek ("+officeHours.length()+")");
		for (int i=0; i<officeHours.length(); i++){
			System.out.println(officeHours.getEmployee(i).getFullName()+": "+((EdEmp)(officeHours.getEmployee(i))).getOfficeHours());
		}
		
		Payroll officeHoursBuildings = fullList.findOfficeHoursBuilding("D-9");
		System.out.println('\n'+"Lista pracowników, którzy mają konsultacje w budynku D-9 ("+officeHoursBuildings.length()+")");
		for (int i=0; i<officeHoursBuildings.length(); i++){
			System.out.println(officeHoursBuildings.getEmployee(i).getFullName()+": "+((EdEmp)(officeHoursBuildings.getEmployee(i))).getOfficeHours());
		}
		
		Payroll officeAddress = fullList.findLocation(new UniAddress("D-4", 100));
		System.out.println('\n'+"Lista pracowników, którzy pracują w pokoju 100 w budynku D-4 ("+officeAddress.length()+")");
		for (int i=0; i<officeAddress.length(); i++){
			if (officeAddress.getEmployee(i) instanceof EdEmp) System.out.println(officeAddress.getEmployee(i).getFullName()+": "+((EdEmp)(officeAddress.getEmployee(i))).getLocation());
			if (officeAddress.getEmployee(i) instanceof AdmEmp) System.out.println(officeAddress.getEmployee(i).getFullName()+": "+((AdmEmp)(officeAddress.getEmployee(i))).getLocation());
		}
		
		Payroll securityNightShift = fullList.findSecEmp().findShift(Shift.NIGHT);
		System.out.println('\n'+"Lista pracowników ochrony, którzy pracują na nocną zmianę ("+securityNightShift.length()+")");
		for (int i=0; i<securityNightShift.length(); i++) System.out.println(securityNightShift.getEmployee(i).getFullName()+": "+((SecEmp)(securityNightShift.getEmployee(i))).getShift());
		
		
	}
}