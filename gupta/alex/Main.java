package gupta.alex;
import java.util.ArrayList;
import animal.Animal;

public class Main {
	
	private static ArrayList<Animal> animalList = new ArrayList<Animal>();
	private static int numOfThreads = 5; //I just like the #5
	private static Run run;

	public static void main(String[] args) {
		
		for(int i = 0; i < numOfThreads; i++) {
			try {
				run = Run.createAndStart("thread" + i, "output" + i + ".txt", animalList);
				run.getThread().join();
				
				//Clear shared data between threads after each thread has finished
				animalList.clear();
			} catch (Exception e) {
				System.out.println("Error starting threads!");
			}
		}
		
	}
	
}
