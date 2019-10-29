/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 * @author Alex Yahn
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//Created pet database class to streamline code
public class PetDatabase {
	private String fileName;
	private ArrayList<Pet> pets;
	private int size;

	//Created PetDatabase object to streamline code
	public PetDatabase() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the input/output file name for pet data: ");
		this.fileName = input.nextLine().trim();
		this.pets = new ArrayList<>();
		this.size = 0;
	}
  
	//Loads file in with pet names
	public void loadData() {
		Scanner fileReader;
		//Handles loading in file
		try {
			fileReader = new Scanner(new File(fileName));
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine().trim();
				String[] data = line.split(",");
				
				//Creates a better and easier to manage array than previous code
				String name = data[0];
				int age = Integer.parseInt(data[1]);
				this.pets.add(new Pet(name, age));
			}
			fileReader.close();
		}

		//Handles file not found error
		catch(FileNotFoundException fnfe){
			System.out.println("Error: Cannot find " + fileName + ".\n");
			System.exit(1);
		}
	}
  
	//Reworked how to display data in nicer table format
	public void viewAllPets() {
		
		//Handles no pets to show error
		if(this.pets.isEmpty()) {
			System.out.println("Error: There are no pets to show!\n");
		}
		
		else {
			System.out.println("+-----------------------+");
			System.out.printf("| %2s | %-10s | %3s |\n", "ID", "NAME", "AGE");
			System.out.println("+-----------------------+");
			
			//Gets pet data from petDatabase() class and displays the information in neat columns
			for(int i = 0; i < size; i++) {
				System.out.printf("| %2d | %-10s | %3d |\n", i, this.pets.get(i).getName(), this.pets.get(i).getAge());
			}
			System.out.println("+-----------------------+\n"
			+ size + " rows in set.\n");
		}
	}
  
	public void addNewPets() {
		Scanner input = new Scanner(System.in);
		String line;
		

		do {
			System.out.print("add pet (name, age): ");
			line = input.nextLine().trim();
			
			if(line.equalsIgnoreCase("done")){
				break;
			}
			
			//Handles database only being able to hold 5 pets
			if(size >= 5) {
				System.out.println("Error: Database is full.\n");
				return;
			}
  
			String name;
			int age;
			
			//Makes sure there are 2 values in the array, and split by a space
			while(line.split(" ").length != 2 || isDigit(line.split(" ")[0]) || (Integer.parseInt(line.split(" ")[1]) < 1 || Integer.parseInt(line.split(" ")[1]) > 20)) {
				if(line.split(" ").length != 2) {
					System.out.println("Error: " + line + " is not a valid input.");
					System.out.print("add pet (name, age): ");
					line = input.nextLine().trim();
				}	
			
				//Handles error not splitting values
				if(isDigit(line.split(" ")[0])) {
					System.out.println("Error: " + line.split(" ")[0] + " is not a valid input.");
					System.out.print("add pet (name, age): ");
					line = input.nextLine().trim();
				}
				
				//Makes sure the age of the pet is less than 20 and greater than 1
				if(Integer.parseInt(line.split(" ")[1]) < 1 || Integer.parseInt(line.split(" ")[1]) > 20) {
					System.out.println("Error: " + line.split(" ")[1] + " is not a valid age.");
					System.out.print("add pet (name, age): ");
					line = input.nextLine().trim();
				}
			}
			
			String[] data = line.split(" ");
			name = data[0];
			age = Integer.parseInt(data[1]);
  
			this.pets.add(new Pet(name, age));
			size++;
		}
		
		//Repeats the loop for each entry until the ignoreCase is met
		while(!line.equalsIgnoreCase("done"));
		
		//Prints how many pets are in current array list
		System.out.println(size + " pets added.\n");
	}
  
	public void removeAPet() {
		//Shows list of removable pets
		viewAllPets();
  
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the pet ID to remove: ");
		int Id = Integer.parseInt(input.nextLine().trim());
		
		//Handles user only being allowed to enter valid pet ID in list
		if(Id < 0 || Id > size) {
			System.out.println("Error: ID " + Id + " does not exist.\n");
		}
		
		//Delets selection ID from list
		else {
			String oldName = this.pets.get(Id).getName();
			int oldAge = this.pets.get(Id).getAge();
  
			this.pets.remove(Id);
			size--;
			System.out.println(oldName + " " + oldAge + " is removed.\n");
			}
	}
  
	//Writes the file to memory
	public void writeToFile() {
		FileWriter fw;
		PrintWriter pw;
  
		try {
			fw = new FileWriter(new File(fileName));
			pw = new PrintWriter(fw);
			for(Pet pet : this.pets) {
				pw.write(pet.getName() + "," + pet.getAge() + System.lineSeparator());
			}
  
			pw.flush();
			fw.close();
			pw.close();
		} 
		
		//Handles not being able to write to file
		catch (IOException ex) {
			System.out.println("Error: Writing to file " + fileName + " - " + ex.getMessage());
		}
	}
  
	private boolean isDigit(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException nfe){
			return false;
		}
	}
}
