package gupta.alex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import animal.Animal;
import animal.carnivore.Lion;
import animal.herbivore.Giraffe;

public class Run implements Runnable{
	
	//Run class variables
		public String threadName;
		private String filePathWithName;
		private ArrayList<Animal> animalList;
		private Thread thread;
		
	//User input variables
		private Scanner reader = new Scanner(System.in);
		private int input;
		
	//File variables
		private File outputFile;
		private BufferedWriter fileWriter;
		private	File namesFile;
		private Scanner fileReader;
		
	//Constructor
		Run(String threadName, String filePathWithName, ArrayList<Animal> animalList){
			this.threadName = threadName;
			this.filePathWithName = filePathWithName;
			this.animalList = animalList;
			thread = new Thread(this, threadName);
		}
	
	//Code to be executed
		@Override
		public void run() {
			//Ask user for input
				System.out.println("How many Giraffes?");
				input = reader.nextInt();
				createAnimal("Giraffe");
				System.out.println("How many Lions?");
				input = reader.nextInt();
				createAnimal("Lion");
				
			//Write to file
				try {
					outputFile = new File("" + filePathWithName);
					fileWriter = new BufferedWriter(new FileWriter(outputFile));
					for(Animal thisAnimal : animalList) {
						fileWriter.write("" + thisAnimal.getName() + " is a " + thisAnimal.getType() + "\n");
						//System.out.println("" + thisAnimal.getName() + " is a " + thisAnimal.getType());
					}
						fileWriter.close();
						System.out.println("Successfully outputted to file!\n" + outputFile.getAbsolutePath());
					}catch(Exception e) {
						System.out.println("Error writing to file!");
					}
		}
	
	//Create specified animal from names file
		public void createAnimal(String animal) {
			try {
				namesFile = new File("names.txt");
				fileReader = new Scanner(namesFile);
				if(animal.equalsIgnoreCase("Giraffe")) {
					for(int i = input; i > 0; i--) {
						animalList.add(new Giraffe(fileReader.nextLine()));
					}
				}
				else if(animal.equalsIgnoreCase("Lion")) {
					for(int i = input; i > 0; i--) {
						animalList.add(new Lion(fileReader.nextLine()));
					}
				}
				fileReader.close();
			}catch(Exception e) {
				System.out.println("Error creating an animal!");
			}
		}
	
	//Static factory method
		public static Run createAndStart(String threadName, String filePathWithName, ArrayList<Animal> animalList) {
			Run returnRun = new Run(threadName, filePathWithName, animalList);
			returnRun.thread.start();
			return returnRun;
		}
	
	//Allow Main class to join the threads
		public Thread getThread() {
			return thread;
		}

}
