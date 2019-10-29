/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/*
 * @author Alex YYhn
 */
import java.util.Scanner;

public class Project2 {
	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
  
	PetDatabase petDB = new PetDatabase();
	int choice;
  
	System.out.println();
	
	do {
			printMenu();
			choice = Integer.parseInt(input.nextLine().trim());
			
			//Different options of the program(1-4)
			switch(choice) {
			
			//Displays pets
			case 1:{
				System.out.println();
				petDB.viewAllPets();
				break;
			}
			
			//Adds new pets
			case 2: {
				System.out.println();
				petDB.addNewPets();
				break;
			}
			
			//Remove pets
			case 3: {
				System.out.println();
				petDB.removeAPet();
				break;
			}
			
			//Exits program and writes to file
			case 4: {
				petDB.writeToFile();
				System.out.println("\nGoodbye!\n");
				System.exit(0);
			}
			
			
			//Default to handle any option other than case 1-4
			default:
				System.out.println("\nInvalid choice!\n");
			}
		}
		while(choice != 4);
	}
  
  
	//Prints the menu of options
	private static void printMenu() {
		System.out.print("What would you like to do?\n"
		+ "1. View all pets\n"
		+ "2. Add new pets\n"
		+ "3. Remove a pet\n"
		+ "4. Exit program\n"
		+ "Your choice: ");
		}
}
