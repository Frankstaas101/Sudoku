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
		//String[] files = {"p1.txt", "p4.txt", "p3.txt", "p2.txt"};
		String[] files = {"test.txt"};
		
		
		for(int i = 0; i < files.length ;i++ )
		{
		System.out.println("---------------------------------------------------");
		System.out.println("Starting solving of: " + files[i]);
		System.out.println();
		FileData fd = new FileData();
		//double count = 1;
		
		try {
			Timer timer = new Timer();	//Start the timer so we may see the time required to solve the puzzle.
			timer.start();
			fd.readFile(new File("src/main/" + files[i]));		//Read the file.
			fd.printComments();			//Print the comments of the file.
			
			Puzzle puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
			puzzle.printPuzzle();
			puzzle.printDimensions();
			
			puzzle.unAssignedCells = BruteSolver.initializeValues(puzzle.unAssignedCells);//We need to set the "0" place holders to "1"
			
			System.out.println("\n\nLoading...");
			while(Functions.validate(puzzle.cells, puzzle.sections, fd.height, fd.width) == false)
			{
				puzzle.setValues(BruteSolver.assignValues(puzzle.unAssignedCells, fd.dimension));	//Increment the unassigned Cells.
			}
			
			//We are done - stop the timer.
			timer.stop();
			System.out.println();
			System.out.println("Solved!");
			puzzle.printPuzzle();
			//Get and print the time elapsed to solve the puzzle. Divide by 1000 to get seconds.
			System.out.println("Time taken to solve the puzzle: " + timer.getDuration() / 1000 + " seconds!");

		} 
		catch (NullPointerException e)
		{
			System.out.println("Sudoku has no solution!");
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			System.out.println("File Reader Error! Please check file names!");
		}
		
	}
	}
}
