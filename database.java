/*
*/
import java.io.*;
import java.util.Scanner;
public class database{
	private String filename;
	private Student[] studentList;
	private int numOfTests;
	
	public database(String fileName){
		this.filename=fileName;
	}
	
	public int getNumberOfStudents()throws FileNotFoundException{
		File fileObj = new File(this.filename);
		Scanner in = new Scanner(fileObj);
		try {
			int count = 0;
			in.nextLine();//Skip the header
			while(in.hasNextLine()){
				count++;
				in.nextLine();
			}
			in.close();
			return count;
		}
		catch(Exception e) {
			System.out.println("File not found or recognized");
			return -1;
		}
	}
	
	public Student[] populateDatabase()throws FileNotFoundException{
		try {
			this.studentList = new Student[getNumberOfStudents()];
			File fileObj = new File(this.filename);
			Scanner in = new Scanner(fileObj);
			in.nextLine();
			while(in.hasNextLine()) {
				for(int i = 0; i<in.nextLine().split(",").length;i++) {
					String[] temp = in.nextLine().split(",");
					this.numOfTests = in.nextLine().split(" ").length;
					String[] grade = temp[temp.length-1].split(" ");
					double[] tempGrades = new double[grade.length];
					for(int k=0;k<grade.length;k++) {
						tempGrades[k] = Double.parseDouble(grade[k]);
					}
					
					for(int j = 0; j<temp.length;j++) {
						Student tempStud = new Student();
						switch(j) {
							case 0:
								tempStud.setFirstName(temp[j]);
								break;
							case 1:
								tempStud.setLastName(temp[j]);
								break;
							case 2:
								tempStud.setID(Integer.parseInt(temp[j]));
								break;
							case 3: 
								tempStud.setGrades(tempGrades);
								break;
							default:
								break;
						}	
						this.studentList[i] = tempStud;
					}
					
				}
			}
			in.close();
			return this.studentList;
		}
		catch(Exception e) {
			System.out.println("File not found. " + e);
			return this.studentList;
		}
		
	}
	
	public void viewListofStudents()throws FileNotFoundException {
		for(int i = 0;i<this.studentList.length;i++) {
			System.out.println(this.studentList[i].toString());
		}
	}
	
	public double getStudentAvg(int studID)throws FileNotFoundException {
		boolean isFound = false;
		double ave = 0.0;
		for(int i=0;i<this.studentList.length;i++) {
			if(this.studentList[i].getID()==studID) {
				ave = this.studentList[i].calculateAverage(this.studentList[i].getGrades());
				isFound = true;
			}
		}
		if(!isFound) {
			return -1.0;
		}
		return ave;
	}
	
	public double getTestAve(int testNum) {
		boolean isFound = false;
		double ave =0.0;
		for(int i=0;i<this.studentList.length;i++) {
			if(this.studentList[i].getGrades().length>testNum) {
				double[] temp = this.studentList[i].getGrades();
				for(int j=0;j<temp.length;j++) {
					if(testNum == j) {
						ave += temp[j];
					}
				}	
			}
		}
		if(!isFound) {
			System.out.println("Test not found");
			return -1.0;
		}
		return ave/this.studentList.length;
	}
	
	public void viewTopStudent() {
		double top = Double.MIN_VALUE;
		int student =0;
		for(int i=0;i<this.studentList.length;i++) {
			double[] temp = this.studentList[i].getGrades();
			for(int j=0;j<temp.length;j++) {
				for(int k=0;k<this.studentList.length;k++) {
					if(top<temp[k]) {
						top=temp[k];
						student = i;
					}
				}
				System.out.println("Top student for test: "+j+"/nWith Grade of: "+top);
				System.out.println(this.studentList[student].toString());
			}
		}
	}
	
	public void viewGrades(int idNum){
		for(int k=0;k<this.studentList.length;k++) {
			if(this.studentList[k].getID()==idNum) {
				double[] grades = this.studentList[k].getGrades();
				for(int i=0;i<grades.length;i++){
					System.out.println(grades[i]);
				}
			}
		}
	}
	
	public void getNumOfTest() {
		for(int i=0;i<this.numOfTests;i++) {
			System.out.print(i+" ");
		}
	}
}