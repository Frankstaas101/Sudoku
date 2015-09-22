package main;

import java.io.File;

public class Main {
	// CHECK FOR MORE THEN THE DIMENSION AMMOUNT OF EACH NUMBER 

	public static void main(String[] args) {
		FileReaderNew fd = new FileReaderNew();
		double count = 0;
		
		try {

			fd.readFile(new File("src/main/test.txt"));
			fd.printDimensions();
			fd.printComments();
			fd.printPuzzle(); }
			/**Puzzle_TESTING puzzle = new Puzzle_TESTING(fd.width, fd.height, fd.puzzle);
			while(puzzle.check() == false)
			{
				//Set the unAssignedCells
				puzzle.setValues(BruteSolver.assignValues(puzzle.unAssignedCells));
				//if(count % 1000000000 == 0)
					//puzzle.printPuzzle();
				//puzzle.setValues(BruteSolver.assignValues(puzzle.missingNumbers));
			}
			System.out.println();
			System.out.println("Solved!");
			puzzle.printPuzzle();

		}*/ catch (Exception e) {
			System.err.println("File Reader Error or no solution found!");
		}
		
		// Proving comments are being placed into the array
		
		
	
	}
}
