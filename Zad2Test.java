import PersonEmployee.Employee;

public class Zad2Test{
	public static void main(String[] args){
		Employee ja = new Employee("Karol", "Pokomeda", "", "m", 1624.34, "24-10-88", "01-09-2004", "88102400915");
		System.out.println(ja.getFullName());
		System.out.println(ja.getSalary());
		System.out.println(ja.getGender());
		System.out.println(ja.getBirthDate());
		System.out.println(ja.getEmploymentDate());
		System.out.println(ja.getPesel());
	}
}