package beans;

public class car {
	private String name;
	private int model;
	public car(){
		System.out.println("test");
	}
	
public void setModel(int model) {
	this.model = model;
}
public void setName(String name) {
	this.name = name;
}
public void show(){
	
	System.out.print("name of cat"+name+"model"+model);
}
}
