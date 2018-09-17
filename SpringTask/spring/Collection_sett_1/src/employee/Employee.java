package employee;

public class Employee {
	private String name;
	int id;
	public Employee(int id){
		this.id=id;
		System.out.println("id="+id);
	}
	public void setName(String name) {
		this.name = name;
	}
	public void show(){

		System.out.println("name:-"+name);
	}
}
