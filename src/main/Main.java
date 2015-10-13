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
		
		try {
			String filePath = "src/tests/p4.txt";
			SudokuFileReader fd = new SudokuFileReader(filePath);
			
			fd.printComments();			//Print the comments of the file.
		
			Timer timer = new Timer();	//Start the timer so we may see the time required to solve the puzzle.
			timer.start();
			
			Puzzle puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
			puzzle.printPuzzle(true);
			puzzle.printDimensions();
			puzzle.printMissingNumbers();
			
			puzzle.unAssignedCells = BruteSolver.initializeValues(puzzle.unAssignedCells);//We need to set the "0" place holders to "1"
			
			System.out.println("---------------------------------------------------");
			System.out.println("Attempting to solve: " + fd.filePath + "\n");
			System.out.println("Solving...");
			
			while(Functions.validate(puzzle.cells, puzzle.sections, fd.height, fd.width) == false)
			{
				puzzle.setValues(BruteSolver.assignValues(puzzle.unAssignedCells, fd.dimension));	//Increment the unassigned Cells.
			}
			
			//We are done - stop the timer.
			timer.stop();
			System.out.println();
			System.out.println("- SOLVED PUZZLE -");
			puzzle.printPuzzle(false);
			//Get and print the time elapsed to solve the puzzle.
			System.out.println("Time taken to solve the puzzle: " + timer.getDuration() + " milliseconds!");

		} catch (NullPointerException e) {
			System.out.println(ErrorText.NO_SOLUTION.getText());
		}  catch (SudokuFileReadException e)  {
			System.err.println(e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}