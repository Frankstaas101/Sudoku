package main;

import java.io.File;

public class Main {
	
	/*
	 * Execute this method to read a puzzle from file and attempt to solve it. 
	 * Note that the program requires a properly formatted file called "test.txt" in the source directory of this package.
	 * 
	 * This method will read the file, print the puzzle, and then attempt to solve it.
	 */
	public static void main(String[] args) {
		 
		// Initialize a new timer
		Timer timer = new Timer();
		
		try {
			
			// set the file path of the file being read
			String filePath = "src/tests/p4.txt";
			SudokuFileReader sfr = new SudokuFileReader(filePath);
			
			//Print the comments of the file.
			sfr.printComments();			
			sfr.printMissingNumbers();
			
			// Extract all of the data from the SudukoFileReader and build a puzzle
			Puzzle puzzle = new Puzzle(sfr.width, sfr.height, sfr.puzzle);
			
			// Print Puzzle data for clarity
			puzzle.printPuzzle(true);
			puzzle.printDimensions();
			
			
			// Start the time as it solves the puzzle
			timer.start(); 
			//Start solving the puzzle here.
			

			
			
			//We are done - stop the timer.
			timer.stop();
			
			// If the puzzle was solved display that it was solved
			System.out.println("\n- SOLVED PUZZLE -");
			
			// Print the solved version of the puzzle
			puzzle.printPuzzle(false);
			
			// Retrieve and print the time that it took to solve the puzzle.
			System.out.println("Time taken to solve the puzzle: " + timer.getDuration() + " milliseconds!");

		} catch (NullPointerException e) { 
			// if there is no solution
			System.out.println("The puzzle has no solution.");
			timer.stop();
		}  catch (SudokuFileReadException e)  { 
			// if there is a problem with the format of the file
			System.err.println(e.getLocalizedMessage());
		} catch (Exception e) {
			// if some other exceptions occur
			e.printStackTrace();
		}
	}
	
	// The display that is shown when the puzzle is being solved and loaded
	public static void loadingDisplay(String filePath) {
		System.out.println("---------------------------------------------------");
		System.out.println("Attempting to solve: " + filePath + "\n");
		System.out.println("Solving...");
	}
}