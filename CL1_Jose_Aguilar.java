//Author: Jose Aguilar
//CS1101
//Instructor: Dr. Daniel Mejia
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class CL1_Jose_Aguilar{
	public static void main(String args[])throws FileNotFoundException{
		//declaring variables that can be used throughout the program
		int cart = 0;
		double totalPrice = 0.00;
		Scanner userChoice = new Scanner(System.in);
		int menuOption;
		File fileObj = new File("database_products.txt");
		Scanner in = new Scanner(fileObj);
		
		//Printing out welcome screen and options
		System.out.println("Welcome to Mejiazon!");
		System.out.println("");
		System.out.println("What would you like to do today?");
		System.out.println("");
		System.out.println("[1] Add Items to Cart");
		System.out.println("[2] View Cart");
		System.out.println("[3] Clear Cart");
		System.out.println("[4] Checkout Cart");
		System.out.println("[5] Exit");
		System.out.println("");
		
		//Beginning loop of options
		menuOption = userChoice.nextInt();
		while(menuOption != 5){
			//Option 1
			if(menuOption == 1){
				//print out available item to add to cart
				try{
					while(in.hasNextLine()){
						System.out.println(in.nextLine());
					}
				}
				catch(Exception e){
					System.out.println("File does not exist");
				}
				
					System.out.println("What item would you like?");
					String userChoiceItem = userChoice.next();
					in = new Scanner(fileObj);
				//Now in take option in while loop
				while(in.hasNextLine()){
					String itemName = in.next();
					double itemPrice = in.nextDouble();
					if(itemName.equals(userChoiceItem)){
						cart += 1;
						totalPrice += itemPrice;
						System.out.println(cart);
						System.out.println(totalPrice);
					}
					if(userChoiceItem.equals("back")||userChoiceItem.equals("exit")){
						System.out.println("What would you like to do today?");
						System.out.println("");
						System.out.println("[1] Add Items to Cart");
						System.out.println("[2] View Cart");
						System.out.println("[3] Clear Cart");
						System.out.println("[4] Checkout Cart");
						System.out.println("[5] Exit");
						System.out.println("");
						menuOption = userChoice.nextInt();
						break;
					}
				}
				in = new Scanner(fileObj);
			}
			
			//Second Option Loop
			else if(menuOption == 2 ){
				System.out.println("Cart Price: " +totalPrice);
				System.out.println("Amount in Cart: "+cart);
				
				//printing out menu again
				System.out.println("What would you like to do today?");
				System.out.println("");
				System.out.println("[1] Add Items to Cart");
				System.out.println("[2] View Cart");
				System.out.println("[3] Clear Cart");
				System.out.println("[4] Checkout Cart");
				System.out.println("[5] Exit");
				System.out.println("");
				menuOption = userChoice.nextInt();
			}
			//Third Option Loop
			else if(menuOption == 3){
				cart = 0;
				totalPrice = 0.0;
				System.out.println("Cart Price: " +totalPrice);
				System.out.println("Amount in Cart: "+cart);
				
				//printing out menu again
				System.out.println("What would you like to do today?");
				System.out.println("");
				System.out.println("[1] Add Items to Cart");
				System.out.println("[2] View Cart");
				System.out.println("[3] Clear Cart");
				System.out.println("[4] Checkout Cart");
				System.out.println("[5] Exit");
				System.out.println("");
				menuOption = userChoice.nextInt();
			}
			
			//Fourth Option Loop
			//This option has the payment plans and percentages
			else if(menuOption == 4){
				System.out.println("Total Items: " +cart);
				System.out.println("Current Price: " + totalPrice);
				int giftCard;
				System.out.println("Choose your payment plan: ");
				System.out.println("[1] Pay in full");
				System.out.println("[2] Make payments over 6 months with 1% interest");
				System.out.println("[3] Make payments over 12 months with 2% interest");
				menuOption = userChoice.nextInt();
				
				//After choice on payment will enter one of these three options involving percentages and payments over time or payment all at once
				if(menuOption == 1){
					System.out.println("Amount due: " +totalPrice);
					System.out.println("Enter gift card amount:");
					giftCard = userChoice.nextInt();
					if(giftCard>=totalPrice){
						totalPrice = 0;
						cart = 0;
						System.out.println("Amount left on card: " + (giftCard - totalPrice));
					}
					else{
						System.out.println("You don't have enough money :(");
					}
				}
				
				//Six month payment plan with 1% interest
				else if(menuOption ==2){
					System.out.println("Amount due: " + ((totalPrice *1.01)/6));
					System.out.println("Enter gift card amount:");
					giftCard = userChoice.nextInt();
					if(giftCard>=totalPrice){
						totalPrice = 0;
						cart = 0;
						System.out.println("Amount left on card: " + (giftCard - totalPrice));
					}
					else{
						System.out.println("You don't have enough money :(");
					}					
				}
				else if(menuOption == 3){
					System.out.println("Amount due: " +((totalPrice*1.02)/12));
					System.out.println("Enter gift card amount:");
					giftCard = userChoice.nextInt();
					if(giftCard>=totalPrice){
						totalPrice = 0;
						cart = 0;
						System.out.println("Amount left on card: " + (giftCard - totalPrice));
					}
					else{
						System.out.println("You don't have enough money :(");
					}
				}
				else{
					System.out.println("Option not available");
				}
			}
		}
		
		//Exit statement when option equals 5
		System.out.println("Thank you for shopping with Mejiazon!");
		in.close();
	}
}

