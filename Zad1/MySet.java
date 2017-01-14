package Zad1;
/*
 * Autor: Karol Pokomeda
 * Data: 21.11.2016
 * Opis: Klasa pozwalająca na tworzenie obiektów zbioru i edycję ich zawartości
 */

// api import
import java.util.Scanner;

public class MySet{
	// deklaracja zmiennych
	
	private char[] set; // zbiór elementów
	private static Scanner myScanner = new Scanner(System.in); //skaner
	
	// metody publiczne
	
		// metoda konwertująca zbiór do zmiennej String
	public String toString(){
		String result = "{";
		for (int i=0; i<this.set.length; i++){
			if (i>0) result+=(", "+set[i]);
			else result+=set[i];
		}
		result+="}";
		return result;
	}
	
		// metoda zwracająca ilość elementów zbioru
	public int getLength(){
		return this.set.length;
	}
		// metoda zwracająca element zbioru o zadanym indeksie
	public char getElem(int ind){
		if (ind<0 || ind>=set.length) throw new IndexOutOfBoundsException("Element index has to be positive and smaller than the length of set");
		return this.set[ind];
	}
		// metoda zwracająca zawartość zbioru w formie tablicy elementów
	public char[] getSet(){
		return this.set;
	}
	
	// metody prywatne
		
		// metoda generująca losowy element zbioru
	private static char randomChar(char[] set){
		char c;
		do {
			c = SetOperations.ALL_CHARS.charAt((int)(Math.random()*SetOperations.ALL_CHARS.length()));
		} while(SetOperations.isInSet(c,set));
		return c;
	}
	
		// metoda wczytująca element zbioru z konsoli
	private static char readChar(char[] set){
		boolean wrongInput = true;
		String s ="";
		char c='[';
		do{
			System.out.println("podaj kolejny element zbioru");
			try {
				s = myScanner.nextLine();
				s=s.toUpperCase();
				wrongInput = false;
			} catch (Exception e) {
				wrongInput = true;
			}
			if (s.length()>1) wrongInput = true;
			else if(!SetOperations.ALL_CHARS.contains(s.substring(0, 1))) wrongInput = true;
			else if(SetOperations.isInSet(s.charAt(0), set)) wrongInput = true;
			else c = s.charAt(0);
			if(wrongInput) System.out.println("Zła wartość argumentu / argument już jest elementem zbioru");
		} while (wrongInput);
		return c;
	}
	
	// konstruktory
		// konstruktor wypełniający zbiór o zadanej wielkości "length"
		// dla argumentu true, zbiór wypełniany jest losowymi wartościami, natomiast dla argumentu false, zbiór wypełniany jest
		// elementami wpisywanymi przez użytkownika w konsoli
	public MySet(int length, boolean isRandom){
		if (length>SetOperations.ALL_CHARS.length()) throw new IndexOutOfBoundsException("Set is too big");
		char[] set = new char[length];
		if (isRandom){
			for (int i=0; i<length; i++){
				set[i] = randomChar(set);
			}
		} else {
			for(int i=0; i<length; i++){
				set[i] = readChar(set);
			}
		}
		this.set = set;
	}
		// konstruktor tworzący zbiór na podstawie zadanej tablicy elementów;
		// w przypadku, gdy elementy tablicy powtarzają się, konstruktor wyświetla błąd
	public MySet(char[] set){
		for (int i=0;i<set.length;i++) {
			set[i] = ((set[i]+"").toUpperCase()).charAt(0);
			if (!SetOperations.ALL_CHARS.contains(set[i]+"")) throw new IllegalArgumentException("Element must be letter or number");
		}
		for (int i=0; i<set.length-1;){
			if (SetOperations.isInSet(set[i], set, ++i)) throw new IllegalArgumentException("Element occurs more than once; element is not a set");
		}
		this.set = set;
	}
}