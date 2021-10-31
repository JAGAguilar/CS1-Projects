import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
public class Runner {
	public static void main(String[] args)throws FileNotFoundException{
		try{
		database file1 = new database("student.csv");
		gradeCalculator calc = new gradeCalculator();
		file1.getNumberOfStudents();
		file1.populateDatabase();
		Scanner in = new Scanner(System.in);
		int userInput;
		printMenu();
		userInput = in.nextInt();
		while(userInput!=888) {
			if(userInput == 1) {
				file1.viewListofStudents();
				System.out.println("Which student would you like to view?");
				userInput = in.nextInt();
				file1.viewGrades(userInput);
					System.out.println(calc.convertToLetterGrade(file1.getStudentAvg(userInput)));
					System.out.println();
					printMenu();
					userInput = in.nextInt();
					break;
				}

				if(userInput==2) {
					System.out.println("What test would you like to sort through?");
					userInput = in.nextInt();
					file1.getTestAve(userInput);
					System.out.println();
					printMenu();
					userInput=in.nextInt();
					break;
				}
				if(userInput==3) {
					file1.viewTopStudent();
				}
			}
			System.out.println("Thank you for your use. Goodbye!");
		}catch(Exception e){
		System.out.println("File Not Found " + e);
		}
	}
	public static void printMenu() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t\tGradeBook");
		System.out.println("-----------------------------------------------------------");
		System.out.println();
		System.out.println("Please make a selection. (Digit only)");
		System.out.println();
		System.out.println("\t1. View Student Test Grade and Average");
		System.out.println("\t2. Test Grade Average");
		System.out.println("\t3. Get Top Student per Exam");
		System.out.println("\t888. Exit");
	}
}
