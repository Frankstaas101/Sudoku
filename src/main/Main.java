package main;

import java.io.File;

public class Main {
	// CHECK FOR MORE THEN THE DIMENSION AMMOUNT OF EACH NUMBER 

	public static void main(String[] args) {
		FileData fd = new FileData();
		
		try {
			fd.readFile(new File("src/main/test.txt"));
			fd.printDimensions();
			fd.printComments();
			fd.printPuzzle();
			Puzzle_TESTING puzzle = new Puzzle_TESTING(fd.width, fd.height, fd.puzzle);
			while(puzzle.check() == false)
			{
				//Set the unAssignedCells
				puzzle.setValues(BruteSolver.assignValues(puzzle.unAssignedCells));
				puzzle.check();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		// Proving comments are being placed into the array
		
		
	
	}
}
