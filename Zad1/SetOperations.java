package Zad1;
/*
 * Autor: Karol Pokomeda
 * Data: 21.11.2016
 * Opis: Klasa metod statycznych wykonujących działania na zbiorach
 */

public abstract class SetOperations {
	
	// deklaracja zmiennych
	public static final String ALL_CHARS = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890"; // zbiór dopuszczalnych wartości elementów
	
	
	// metody publiczne
	
		// metoda wyznaczająca sumę dwóch zbiorów
	public static MySet union(MySet s1, MySet s2){
		char[] s1Ch = s1.getSet();
		char[] s2Ch = s2.getSet();
		char[] result = new char[s1Ch.length+s2Ch.length];
		int k=0;
		for (int i=0; i<s1Ch.length; i++){
			if (!isInSet(s1Ch[i], s2Ch)) result[k++]=s1Ch[i];
		}
		for (int i=0; i<s2Ch.length; i++) result[k++]=s2Ch[i];
		result = trimSet(result, --k);
		MySet setResult = new MySet(result);
		return setResult;
	}
	
	
		// metoda wyznaczająca iloczyn dwóch zbiorów
	public static MySet intersection(MySet s1, MySet s2){
		char[] s1Ch = s1.getSet();
		char[] s2Ch = s2.getSet();
		char[] result = new char[Math.min(s1Ch.length, s2Ch.length)];
		int k=0;
		for (int i=0; i<s1Ch.length; i++){
			if (isInSet(s1Ch[i], s2Ch)) result[k++]=s1Ch[i];
		}
		result = trimSet(result, --k);
		MySet setResult = new MySet(result);
		return setResult;
	}
		
		// metoda wyznaczająca różnicę dwóch zbiorów
	public static MySet complement(MySet s1, MySet s2){
		char[] s1Ch = s1.getSet();
		char[] s2Ch = s2.getSet();
		char[] result = new char[s1Ch.length];
		int k=0;
		for (int i=0; i<s1Ch.length; i++){
			if (!isInSet(s1Ch[i], s2Ch)) result[k++]=s1Ch[i];
		}
		result = trimSet(result, --k);
		MySet setResult = new MySet(result);
		return setResult;
	}
		
		// metoda sprawdzająca, czy zbiór zawiera element
	public static boolean isInSet(char elem, char[] set){
		return isInSet(elem,set,0);
	}
	// metoda sprawdzająca, czy podzbiór zbioru zaczynający się od elementu o zadanym indeksie zawiera poszukiwany element
	public static boolean isInSet(char elem, char[] set, int searchStart){
		boolean result = false; 
		for (int i=searchStart; i<set.length; i++){
			result = (set[i]==elem);
			if (result) break;
		}
		return result;
	}
	
	// metody prywatne
	
		// metoda usuwająca puste elementy (null) z końca tablicy elementów
	private static char[] trimSet(char[] set, int lastInd){
		if(lastInd>(set.length-1))throw new IndexOutOfBoundsException("index of last element exceeds set length");
		char[] result = new char[lastInd+1];
		for (int i=0; i<result.length; i++){
			result[i]=set[i];
		}
		return result;
	}
}