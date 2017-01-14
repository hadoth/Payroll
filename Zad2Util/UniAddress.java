// klasa adresu uniwersyteckiego; zawiera oznaczenie buidynku i numer pokoju;

package Zad2Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniAddress implements Serializable{
	private String building;
	private int roomNumber;
	
	// set methods
	public void setBuilding(String building){
		this.building = checkBuilding(building);
	}
	public void setRoomNumber(int roomNumber){
		this.roomNumber = checkRoomNumber(roomNumber);
	}
	
	// get methods
	public String getBuilding(){
		return this.building;
	}
	public int getRoomNumber(){
		return this.roomNumber;
	}
	
	// other methods
	public String toString(){
		return this.roomNumber+"("+this.building+")";
	}
	
	private int checkRoomNumber(int roomNumber) {
		if (roomNumber<0) throw new IllegalArgumentException("Rooom number must be positive");
		else return roomNumber;
	}
	
	private static String checkBuilding(String building){
		String regEx = "((\\s|^)[A-DHLTa-dhlt]{1}[\\-]{1}[0-9]{1,2}(\\s|$))";
		ArrayList<String> buildings = regexChecker(regEx, building);
		if (buildings.isEmpty()) throw new IllegalArgumentException("Invalid building signature or no signature provided");
		if (buildings.size()>1) throw new IllegalArgumentException("Invalid number of words");
		return buildings.get(0).toUpperCase();
	}
	
	private static ArrayList<String> regexChecker(String regEx, String str2chk){
		String[] buildings = str2chk.split(" ");
		ArrayList<String> resultList = new ArrayList<String>();
		Pattern myPattern = Pattern.compile(regEx);
		Matcher regExMatch;
		String building;
		for (int i=0; i<buildings.length; i++) {
			regExMatch = myPattern.matcher(buildings[i]);
			if(regExMatch.find()){
				if(regExMatch.group().trim().length()!=0){
					building = regExMatch.group().trim();
					resultList.add(building);
				}
			}
		}
		return resultList;
	}
	
	// constructor
	public UniAddress(String building, int roomNumber){
		setBuilding(building);
		setRoomNumber(roomNumber);
	}
}