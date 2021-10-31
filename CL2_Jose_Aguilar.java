//Author:Jose Aguilar
//CS1101
//Instructor: Dr. Daniel Mejia
/*
Step One:

Nowadays, it is important to personalize the customer’s shopping experience, so we need to
ask users to sign up in our interface. Hence, the first task for us is to design an interface that
asks the user for his/her username and password.
The username must be at least 6 characters, and the password must be at least 8 characters.
Password must have at least one lowercase letter, one uppercase letter, one digit, and one
special character (@, #, $, &). Keep in your mind, to prevent any password mistyping, you need
to ask for the password twice, and compare them together. Your program will display messages
if the username or password doesn’t meet the requirements, and you program must tell the
user which criteria didn’t pass.
* There is no need to verify the username and password from a database or store them there
since they are signing up, not signing in.

Step Two:

Your program needs to help customers by filtering the cars in inventory by asking questions
below:
- Are you looking to buy a new or a used car?
- What is the maximum budget you want to pay for the car?
You need to store these answers in separate variables and apply them when you want to
display cars to the user.
2

Step Three:

You need to read cars’ information from the CSV file that is provided for you with this
assignment. This file has the list of cars with their id, model, manufacture name, price,
new/used, color, year.
You need to display these cars after applying the user’s filters (step two). The user will choose
the car by entering the car’s id.

Step Four:

When the customer is ready to finalize the purchase, we should check if they are eligible for
discount programs. The program should ask the following questions before proceeding.
1- Are you a student? ($500 discount)
2- Are you military? Active, reserve, veteran ($500 discount)
3- Are you a first responder? ($500 discount)
Your program should add the total discounts together and store the total discount in a variable.
Then, deduct the total discount from total price of the car.
Your program should ask the user for the type of purchase, meaning they will have the option
to pay in cash, or to finance the car.
After calculating and applying the eligible discounts to the car price, you need to apply the tax
and fees. To do so, you need to declare a function called totalValue(double carPrice) which will
add 5% as tax and a $109 registration fee and return the new price after tax and fees.
If the user chooses to pay in cash, you will show the final price and a thank you message.
If the user chooses to finance, you need to ask for the number of months they would like to pay
in and add a loan interest to the price. The user can choose from 48 months, 60 months, or 72
months.
You need to declare a function called paymentCalculator(double finalPrice, int month) to
calculate the loan payments. This function will calculate the payments and return the payment
amount for each month based on the following criteria:
The 48-month loan has a 3.5% APR
The 60-month loan has a 4.2% APR
The 72-month loan has a 5.2% APR
3
You need to display the payment due for each month to the user and display a thank you
message.
*/

import java.util.Scanner;
import java.io.File;

public class CL2_Jose_Aguilar{
	public static void main(String[] args){
		Scanner userInput = new Scanner(System.in);
		String userName = "";
		String passWord = "";
		String newOrUsed = "";
		String[][] carsForSale = storeData();
		//Welcome Menu
		System.out.println("\tWelcome\nPlease signup to browse for cars");
		
		//Checkpoints for the Sign-Up
		boolean passed = false;
		boolean passedUsername = false;
		boolean passedPassword = false;
		boolean checkPasswordTwice = false;
		String tempPass;
		while(passed == false){
			while(passedUsername == false){
				System.out.println("Please enter your username: ");
				userName = userInput.nextLine();
				passedUsername = checkUsername(userName);
			}
			while(passedPassword == false){
				System.out.println("Please enter your password:");
				passWord = userInput.nextLine();
				passedPassword = checkPassword(passWord);
			}
			while(passedPassword && !checkPasswordTwice){
				System.out.println("Re-Enter your password to confirm:");
				tempPass = userInput.nextLine();
				checkPasswordTwice = comparePass(tempPass,passWord);
				
			}
			if(passedPassword && passedUsername && checkPasswordTwice){
				passed = true;
			}
		}
		
		//Filtering Cars
		System.out.println("Are you looking to buy a new or used car? [New(1)/Used(2)]");
		int numUserInput = userInput.nextInt();
		boolean inputted = false;
		while(!inputted){
			if(numUserInput ==1){
				newOrUsed = "new";
				inputted = true;
			}
			else if(numUserInput == 2 ){
				newOrUsed = "used";
				inputted = true;
			}
			else{
				System.out.println("not a recognized input");
				System.out.println("Are you looking to buy a new or used car? [New(1)/Used(2)]");
				numUserInput = userInput.nextInt();
			}
		}
		System.out.println("What is the max budget you want to pay for the car? [Only input numbers]");
		int priceRange = userInput.nextInt();
		
		filterCars(newOrUsed,priceRange,carsForSale);
		
		//Step 3
		System.out.println("Please enter ID of the car you would like: ");
		int carID = userInput.nextInt();
		double priceOfCar = returnCarPrice(carID,carsForSale);
		//Step 4
		
		double totalDiscount = 0.0;
		String strUserInput = "";
		
		System.out.println("Are you a student? [yes/no]");
		strUserInput = userInput.nextLine();
		strUserInput = userInput.nextLine();
		strUserInput = strUserInput.toLowerCase();
		if(strUserInput.equals("yes")){
			totalDiscount += 500.0;
		}
		
		System.out.println("Are active or reserve military/a veteran? [yes/no]");
		strUserInput = userInput.nextLine();
		strUserInput = strUserInput.toLowerCase();
		if(strUserInput.equals("yes")){
			totalDiscount += 500.0;
		}
		
		System.out.println("Are you a first-responder? [yes/no]");
		strUserInput = userInput.nextLine();
		strUserInput = strUserInput.toLowerCase();
		if(strUserInput.equals("yes")){
			totalDiscount += 500.0;
		}
		
		System.out.println("You are eligible for "+totalDiscount+" discount");
		priceOfCar = priceOfCar -totalDiscount;
		System.out.println("Price of car is: " +totalValue(priceOfCar)+"\nWould you like to finance or pay in full?[Finance(1)/Pay-in-Full(2)]");
		numUserInput = userInput.nextInt();
		inputted = false;
		while(!inputted){
			if(numUserInput == 2){
				System.out.println(priceOfCar);
				System.out.println("Thank you for shopping with us!");
				break;
			}
			else if(numUserInput == 1){
				System.out.println("How many months would you like to finance? [48/60/72]");
				int months = userInput.nextInt();
				System.out.println("These are your payments: " +paymentCalculator(priceOfCar,months));
				System.out.println("Thank you for shopping with us!");
				inputted = true;
			}
			else{
				System.out.println("Not an option");
				numUserInput = userInput.nextInt();
			}
		}
		
	}
		
	//Methods
	public static double paymentCalculator(double finalPrice, int month){
		if(month == 48){
			finalPrice = (finalPrice * 1.035)/48;
			return finalPrice;
		}
		else if(month == 60){
			finalPrice = (finalPrice * 1.042)/60;
			return finalPrice;
		}
		else{
			finalPrice = (finalPrice * 1.052)/72;
			return finalPrice;
		}
	}
	
	public static double totalValue(double carPrice){
		return(carPrice * 1.05)+109.0;
	}
	
	public static double returnCarPrice(int carId, String[][] arr){
		double carPrice = 0.0;

		for(int i = 0; i < arr.length;i++){
			if(carId == Integer.parseInt(arr[i][0])){
				carPrice = Double.parseDouble(arr[i][6]);
			}
		}

		return carPrice;
	}
	
	//filters the cars throught selected parameters
	public static void filterCars(String newUsed, int maxPrice, String[][] arr){
		Scanner userInput = new Scanner(System.in);
		boolean carFound = false;
		while(!carFound){
		for(int i = 0;i < arr.length;i++){
			for(int j = 0;j < arr[i].length;j++){
				//Compares strings
				if(arr[i][j].equals(newUsed)){
					//Compares prices of cars to maxPrice
					if(Integer.parseInt(arr[i][6]) <= maxPrice){
						for(int k = 0; k<arr[i].length;k++){
							if(k==0){
								System.out.print("ID: "+arr[i][k]+" | ");
								carFound = true;
							}
							if(k==1){
								System.out.print("Model: "+arr[i][k]+" | ");
							}
							if(k==2){
								System.out.print("Brand: "+arr[i][k]+" | ");
							}
							if(k==3){
								System.out.print("New/Used: "+arr[i][k]+" | ");
							}
							if(k==4){
								System.out.print("Color: "+arr[i][k]+" | ");
							}
							if(k==5){
								System.out.print("Year: "+arr[i][k]+" | ");
							}
							if(k==6){
								System.out.print("Price: "+arr[i][k]+" | ");
							}
						}
						System.out.println();
						System.out.println("---------------------------------------------------------------------------------------------------------");
					}
				}
			}
		}
		if(!carFound){
			System.out.println("No car was found\nPlease input new price:");
			maxPrice = userInput.nextInt();
		}
		}
	}
	public static String[][] storeData(){
		String [][] empty = new String[0][0];
		try{
			File fileObj = new File("car-database.csv");
			Scanner in = new Scanner(fileObj);
			//obtain the number of columns the in csv
			int colNum = in.nextLine().split(",").length;
			
			//obtain the number of rows in the csv
			int rowNum=0;
			while(in.hasNextLine()){
				in.nextLine();
				rowNum++;
			}
			
			String [][] carsForSale = new String[rowNum][colNum];
			
			//restart the scanner obj
			in = new Scanner(fileObj);
			
			//skipping the header
			in.nextLine();
			int row = 0;
			while(in.hasNextLine()){
				carsForSale[row] = in.nextLine().split(",");
				row++;
			}
			
			return carsForSale;
		}catch(Exception e){
			System.out.println("Error occured while reading data from the file" + e);
		}
		return empty;
	}
	public static boolean checkUsername(String user_Name){
		if(user_Name.length() <6){
			System.out.println("Username is not greater than 6 characters");
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean checkPassword(String pass){
		//boolean statements for each checkpoint
		boolean isUpperCase = false;
		boolean isLowerCase = false;
		boolean hasDigit = false;
		boolean hasSymbol = false;
		//If statments so goes through each check and changes each checkpoint
		//Also prints out what is missing from the password
		if(pass.length() > 8){
			String newName = pass.toUpperCase();
			for(int i = 0; i<pass.length();i++){
				if(pass.charAt(i)==newName.charAt(i)){
					isUpperCase = true;
				}
			}
			if(isUpperCase == false){
				System.out.println("Password is missing upper case letter");
				return false;
			}
			newName = pass.toLowerCase();
			for(int i = 0; i<pass.length();i++){
				if(pass.charAt(i)==newName.charAt(i)){
					isLowerCase = true;
				}
			}
			if(isLowerCase == false){
				System.out.println("Password is missing lower case letter");
				return false;
			}
			//stores possible numbers in array
			char[] nums = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			//store the possible characters that have to be sorted through
			char[] letAt = new char[pass.length()];
			for(int i = 0;i<pass.length();i++){
				letAt[i] = pass.charAt(i);
				for(int j = 0;j<nums.length;j++){
					if(letAt[i] == nums[j]){
						hasDigit = true;
					}
				}
			}
			if(hasDigit == false){
				System.out.println("Password missing integer");
				return false;
			}
			for(int i = 0;i<pass.length();i++){
				if(pass.charAt(i)=='$'||pass.charAt(i)=='@'||pass.charAt(i)=='#'||pass.charAt(i)=='%'||pass.charAt(i)=='?'||pass.charAt(i)=='^'||pass.charAt(i)=='&'){
					hasSymbol = true;
				}
			}
			if(hasSymbol == false){
				System.out.println("Password missing symbol");
				return false;
			}
		}
		else{
			System.out.println("Password not greater than 8 characters.");
			return false;
		}
		if(hasDigit && hasSymbol && isLowerCase && isUpperCase){
				return true;
		}
		return false;
	}
	
	public static boolean comparePass(String pass, String pass2){
		//If the check attempt is shorter than the initial password 
		//it catches the error and continues to loop
		try{
			for(int i = 0;i<pass2.length();i++){
				if(!(pass.charAt(i) == pass2.charAt(i))){
					System.out.println("Incorrect password. Try Again.");
					return false;
				}
			}
		}
		catch(Exception e){
			System.out.println("Incorrect password. Try Again.");
			return false;
		}
		return true;
	}
}