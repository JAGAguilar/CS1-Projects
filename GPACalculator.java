/*
Author: Jose Aguilar
CS 1101
Instructor: Dr. Daniel Mejia
Declare variable for course size as N
Declare a variable to hold the courses average, average
Declare variable for students grade, grade
Initialize a variable for the sum of all the students grades, totalSum
read and assign number of course grades
for variable < N 
	read student grades 
	add grade to totalSum
assign totalSum/N to average
print average
*/

import java.util.Scanner;

public class GPACalculator{
	public static void main(String[] args){
		int N;
		double average;
		int grade;
		int totalSum = 0;
		
		Scanner in = new Scanner(System.in);
		System.out.println("Number of course grades?");
		N = in.nextInt();
		
		for(int i=0;i<N;i++){
			System.out.println("Student grade?");
			grade = in.nextInt();
			totalSum += grade;
		}
		average = totalSum/N;
		System.out.println("Course average: " + average);
	}
}