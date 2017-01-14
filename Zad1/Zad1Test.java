package Zad1;

public class Zad1Test{
	public static void main(String[] args){
		char[] testSet = new char[200];
		MySet[] testMySet = new MySet[200];
		for (int i=0; i<200; i++){
			testSet[i] = (char)i;
			System.out.print(testSet[i]);
			try {
				char[] smallSet = {'A',testSet[i]};
				testMySet[i] = new MySet(smallSet);
				System.out.println("succes!"); 
			} catch (Exception e){
				System.out.println("; error: "+e);
			}
		}
	}
}