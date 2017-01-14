import java.time.LocalDate;

import Zad2Util.CalendarGUI;
public class CalendarGUITester {
	public static void main(String[] args){
		CalendarGUI testGUI = new CalendarGUI(null, LocalDate.now().minusMonths(2));
		testGUI.setDate();
		System.out.println(testGUI.getDate().toString());
	}
}