/*
*/
public class Student{
	private String firstName;
	private String lastName;
	private int id;
	private double[] grades;
	
	//Constructors
	public Student(){
		
	}
	
	public Student(String fName, String lName, int ID, double[] Grades){
		this.firstName = fName;
		this.lastName = lName;
		this.id = ID;
		this.grades = Grades;
	}
	
	//Getters
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public int getID(){
		return this.id;
	}
	
	public double[] getGrades() {
		return this.grades;
	}
	
	public void viewGrades(){
		for(int i=0;i<this.grades.length;i++){
			System.out.println(this.grades[i]);
		}
	}
	
	//Setters
	public void setFirstName(String fName){
		this.firstName=fName;
	}
	
	public void setLastName(String lName){
		this.lastName=lName;
	}
	
	public void setID(int ID){
		this.id=ID;
	}
	
	public void setGrade(double Grade, int pos){
		double[] temp = this.grades; 
		for(int i=0;i<temp.length;i++){
			if(i==pos){
				temp[i]=Grade;
			}
		}
		this.grades=temp;
	}
	
	public void setGrades(double[] arr) {
		this.grades = arr;
	}
	//Methods
	public double calculateAverage(double[] arr){
		double average = 0.0;
		if(arr.length<1){
			return -1.0;
		}
		for(int i=0;i<arr.length;i++){
			if(arr[i]<0){
				average +=0.0;
			}
			else{
				average += arr[i];
			}
		}
		return average/arr.length;
	}
	
	
	public String toString(){
		return this.firstName + "" + this.lastName;
	}
}