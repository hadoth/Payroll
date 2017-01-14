// enumerator dnia tygodnia

package Zad2Util;

import java.io.Serializable;

public enum WeekDay implements Serializable{
	PN, WT, SR, CW, PT, SO, ND;
	
	public String toString(){
		switch (this.name()){
			case "PN": return "poniedziałek";
			case "WT": return "wtorek";
			case "SR": return "środa";
			case "CW": return "czwartek";
			case "PT": return "piątek";
			case "SO": return "sobota";
			case "ND": return "niedziela";
			default: throw new IllegalArgumentException("Weekday name not recognized");
		}
	}
}