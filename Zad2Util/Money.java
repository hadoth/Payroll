// klasa do przechowywania pieniędzy; przechowuje wartość pieniężną w groszach, zwraca w postaci double; zapobiega zmianom przy zaokrągleniach

package Zad2Util;

import java.io.Serializable;

public class Money implements Serializable{
	private int value;
	
	public double  getMoney(){
		 return (double)(value)/100;
	}
	
	public void setMoney(double value){
		setMoney(Double.toString(value));
	}
	
	private void setMoney(String value){
		String[] values;
		if (value.contains(".")){
			values = value.split("\\.");
		} else if(value.contains(",")){
			values = value.split(",");

		} else {
			values = new String[2];
			values[0] = value; 
		}
		if (values.length>2||values.length<1||values[1].length()>2) throw new IllegalArgumentException("Not a monetary value");
		if (values[1].length()==1) values[1] = values[1]+"0";
		this.value = 100*Integer.parseInt(values[0])+Integer.parseInt(values[1]);
	}
	
	public Money(double value){
		this.setMoney(value);
	}
	
	public String toString(){
		return ((double)(value)/100)+"zł";
	}
}