package Zad1;

/*
 * Autor: Karol Pokomeda
 * Data: 21.11.2016
 * Opis: Program do przeprowadzania działań na zbiorach
 */

public class Zad1{
	public static void main(String[] args){
		MySet mySet1 = new MySet(32,true);
		System.out.println("Zbiór A: "+mySet1);
		MySet mySet2 = new MySet(16,true);
		System.out.println("Zbiór B: "+mySet2);
		MySet mySet1s2 = SetOperations.union(mySet1, mySet2);
		System.out.println("Zbiór A suma B: "+mySet1s2);
		MySet mySet1i2 = SetOperations.intersection(mySet1, mySet2);
		System.out.println("Zbiór A iloczyn B: "+mySet1i2);
		MySet mySet1c2 = SetOperations.complement(mySet1, mySet2);
		System.out.println("Zbiór A ró¿nica B: "+mySet1c2);
		MySet mySet2c1 = SetOperations.complement(mySet2, mySet1);
		System.out.println("Zbiór B ró¿nica A: "+mySet2c1);
	}
}