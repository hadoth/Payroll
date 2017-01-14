import Zad2Util.Money;

public class MoneyTest{
	public static void main(String[] args){
		
		
		Money[] moneyList = new Money[100];
		for (int i=0; i<100; i++){
			double value = (double)((int)(Math.random()*100000))/100;
			System.out.print(value+":"+'\t');
			try {
				moneyList[i] = new Money(value);
				System.out.print(moneyList[i].getMoney());
				if (value !=moneyList[i].getMoney())System.out.println('\t'+"error: "+value+" != "+moneyList[i].getMoney());
				else System.out.println("");
			} catch (Exception e){
				System.out.println(e);
			}
		}
	}
}