import PersonEmployee.*;
import Zad2.*;

public class PayrollOut2{
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
		System.out.println(fullList.payrollOut("new/plik.prl"));
	}
}