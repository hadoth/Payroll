import PersonEmployee.Payroll;
import Zad2.*;

public class PayrollIn{
	public static void main(String[] args){
		Payroll fullList = Payroll.payrollIn("new/plik.prl");
		for (int i=0; i<fullList.length(); i++){
			System.out.println(fullList.getEmployee(i));
		}
	}
}