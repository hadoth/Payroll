import javax.swing.JButton;
import javax.swing.JFileChooser;

import PersonEmployee.*;
import Zad2.*;

public class PayrollOut{
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
		JButton save = new JButton("Zapisz");
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		fc.setDialogTitle("Wczytaj listę pracowników");
		fc.showSaveDialog(save);
		
		System.out.println(fullList.payrollOut(fc.getSelectedFile().getPath()));
	}
}