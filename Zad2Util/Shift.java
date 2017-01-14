// enumerator systemu zmianowego; zawiera trzy zmiany- poranną, popołudniową i nocną; zwraca godziny pracy

package Zad2Util;

import java.io.Serializable;

public enum Shift implements Serializable{
	MORNING, AFTERNOON, NIGHT;
	
	public String toString(){
		switch(this.name()){
		case "MORNING": return "6:00 - 14:00";
		case "AFTERNOON": return "14:00 - 22:00";
		case "NIGHT": return "22:00 - 6:00";
		default: throw new IllegalArgumentException("invalid shift designation");
		}
	}
}