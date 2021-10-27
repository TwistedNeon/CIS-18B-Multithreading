package gupta.alex;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import animal.Animal;
import animal.carnivore.Lion;
import animal.herbivore.Giraffe;

public class Main {
	
	private static Scanner reader = new Scanner(System.in);
	private static int input;
	private static File namesFile = new File("names.txt");
	private static Scanner fileReader;
	private static ArrayList<Animal> animalList = new ArrayList<Animal>();
	private static ArrayList<Run> threadList = new ArrayList<Run>();

	public static void main(String[] args) {
		
		getInput();
		
		for(int i = 0; i < input; i++) {
			Run run = Run.createAndStart("thread" + i, "output" + i + ".txt", animalList);
			threadList.add(run);
		}
		
		for(int i = 0; i < input; i++) {
			try {
				threadList.get(i).getThread().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		Run run0 = Run.createAndStart("thread0", "output0.txt", animalList);
//		Run run1 = Run.createAndStart("thread1", "output1.txt", animalList);
//		Run run2 = Run.createAndStart("thread2", "output2.txt", animalList);
//		
//		try {
//			run0.getThread().join();
//			run1.getThread().join();
//			run2.getThread().join();
//		} catch (InterruptedException e) {
//			System.out.println("Error!");
//		}
		
		
	}
	
	//Ask user for input
		private static void getInput() {
			System.out.println("How many Giraffes?");
			input = reader.nextInt();
			createAnimal("Giraffe");
				
			System.out.println("How many Lions?");
			input = reader.nextInt();
			createAnimal("Lion");
			
			System.out.println("How many threads?");
			input = reader.nextInt();
		}
		
	//Create specified animal from names file
		private static void createAnimal(String animal) {
			try {
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
	
}
