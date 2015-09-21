package main;

import java.io.File;

public class Main {
	// CHECK FOR MORE THEN THE DIMENSION AMMOUNT OF EACH NUMBER 

	public static void main(String[] args) {
		FileData fd = new FileData();
		//double count = 1;
		
		try {
			//Start the timer so we may see the time required to solve the puzzle.
			Timer timer = new Timer();
			timer.start();
			fd.readFile(new File("src/main/test.txt"));
			fd.printDimensions();
			fd.printComments();
			fd.printPuzzle();
			Puzzle_TESTING puzzle = new Puzzle_TESTING(fd.width, fd.height, fd.puzzle);
			//We need to set the "0" placeholders to "1"
			puzzle.unAssignedCells = BruteSolver.initializeValues(puzzle.unAssignedCells);
			while(puzzle.check() == false)
			{
				//Set the unAssignedCells
				puzzle.setValues(BruteSolver.assignValues(puzzle.unAssignedCells, fd.dimension));
				/*
				if(count++ % 10000000 == 0)
				{
					System.out.println("Tried " + count + " combinations");
				}
				*/
			}
			//We are done - stop the timer.
			timer.stop();
			System.out.println();
			System.out.println("Solved!");
			puzzle.printPuzzle();
			//Get and print the time elapsed to solve the puzzle. Divide by 1000 to get seconds.
			System.out.println("Time taken to solve the puzzle: " + timer.getDuration() / 1000 + " seconds!");

		} catch (Exception e) {
			System.err.println("File Reader Error or no solution found!");
		}
		
		// Proving comments are being placed into the array
		
		
	
	}
}
