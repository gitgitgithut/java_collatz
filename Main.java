/**
 * CMPE 365 Lab2
 * @author Xiaofeng Lin, 10138176
 */
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] arg) {
		int startInt = 500;
		System.out.println("Executing Collatz Program without reached numbers recorded:");
		collatz(startInt,false);
		System.out.println("Executing Collatz Program with reached numbers recorded:");
		collatz(startInt,true);
	}

	/**
	 *
	 * @param startInt the starting integer
	 * @param optimize indicator suggesting if the array list should be used or not
	 */
	public static void collatz(int startInt, boolean optimize) {
		int n,max,loop = 0; //n: storing the current number the algorithm reaches; max: storing the max number reached; 
							//loop: counting the number of iteration the Collatz program has
		ArrayList<Integer> history = new ArrayList<>(); //storing the reached numbers
		history.addAll(Collections.nCopies(startInt + 1, null)); //initialize the arraylist and fill it with null
		long start =  System.nanoTime();
		for (int i = startInt; i >= 1; i--) {
			n = i;
			max = 0; //reinitialize the max number
			while (n != 1) {
				loop++;
				if (optimize) {
					if (history.size() <= n) { 				//check if the arraylist needs to be expanded or not
						int desiredSize = history.size()*4;
						history.ensureCapacity(desiredSize);
					    while (history.size() < desiredSize) {
					        history.add(null);
					    }
					}
					if (history.get(n) != null) { //check if the number has been reached
						//System.out.println("The calculation has reached number " + n + ", which has been reached before.");
						break;
					}
					history.set(n, 1); //set to 1 to indicate the number has been reached
				}
				if (n > max)
					max = n;
				//The Collatz Program
				if ((n % 2) != 0)
					n = 3 * n + 1;
				else
					n = n / 2;
			} 
			//System.out.println("The program has been terminated with starting integer " + i);
			if (max != 0)
				System.out.println("The largest number the program ever reached is " + max);
		}
		long end =  System.nanoTime();
		System.out.println("The time elapsed is " + (end-start)/Math.pow(10, 6) + " milliseconds");
		System.out.println("It has been iterated " + loop + " times\n");
		
	}
	
}

