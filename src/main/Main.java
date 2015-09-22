package main;

import java.io.File;
import java.util.ArrayList;


public class Main {
	// CHECK FOR MORE THEN THE DIMENSION AMMOUNT OF EACH NUMBER 
	
	/*
	 * Execute this method to read a puzzle from file and attempt to solve it. 
	 * Note that the program requires a properly formatted file called "test.txt" in the source directory of this package.
	 * 
	 * This method will read the file, print the puzzle, and then attempt to solve it.
	 */
	public static void main(String[] args) {
		FileData fd = new FileData();
		//double count = 1;
		
		try {
			Timer timer = new Timer();	//Start the timer so we may see the time required to solve the puzzle.
			timer.start();
			fd.readFile(new File("src/main/test.txt"));		//Read the file.
			fd.printComments();			//Print the comments of the file.
			
			
			Puzzle_TESTING puzzle = new Puzzle_TESTING(fd.width, fd.height, fd.puzzle);
			puzzle.unAssignedCells = BruteSolver.initializeValues(puzzle.unAssignedCells);//We need to set the "0" place holders to "1"
			System.out.println("\n\nLoading...");
			while(Functions.validate(fd.getPuzzle(), fd.getHeight(), fd.getWidth()) == false)
			{
				puzzle.setValues(BruteSolver.assignValues(puzzle.unAssignedCells, fd.dimension));//Increment the unassigned Cells.
//
//				/*
//				if(count++ % 10000000 == 0)
//				{
//					System.out.println("Tried " + count + " combinations");
//				}
//				*/
//
//
//				//if(count % 1000000000 == 0)
//					//puzzle.printPuzzle();
//				//puzzle.setValues(BruteSolver.assignValues(puzzle.missingNumbers));
//
			}
			//We are done - stop the timer.
			timer.stop();
			System.out.println();
			System.out.println("Solved!");
			puzzle.printPuzzle();
			//Get and print the time elapsed to solve the puzzle. Divide by 1000 to get seconds.
			System.out.println("Time taken to solve the puzzle: " + timer.getDuration() / 1000 + " seconds!");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("File Reader Error or no solution found!");
		}	
	}
}
