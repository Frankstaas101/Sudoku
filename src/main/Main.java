package main;

public class Main {
	
	/**
	 * Executing this method will attempt to read a sudoku puzzle from 
	 * file and solve it.<br><br>
	 * 
	 * This method will:<br>
	 *  a. Initialize a timer.<br>
	 *  b. Set the file path of the file being read.<br>
	 *  c. Try to read a Sudoku puzzle data file from the file path.<br><br>
	 *  
	 * If it is successful then it will:<br>
	 *  a. Print all the comments in that file.<br>
	 *  b. Print all the calculated missing values from that puzzle.<br>
	 *  c. Initialize a new Puzzle object with the puzzle data.<br>
	 *  d. Print the new puzzle object to the console.<br>
	 *  e. Start the timer, Solve the puzzle, then stop the timer.<br>
	 *  f. If the puzzle was solved it will then print the solved version.<br>
	 *  g. The time it took is then printed last.<br>
	 *  
	 * @note This program requires a properly formatted file in 
	 * 	the source directory of this package. The file path must 
	 * 	be named SudokuPuzzle.txt<br><br>
	 */
	public static void main(String[] args) {
		 
		Timer timer = new Timer();
		
		try {
			// File Reading and data printing
			String filePath = "src/tests/SudokuPuzzle.txt";
			SudokuFileReader sfr = new SudokuFileReader(filePath);
			sfr.printComments();	
			sfr.printMissingNumbers();
			
			// Puzzle object creation and printing
			Puzzle puzzle = new Puzzle(sfr.width, sfr.height, sfr.puzzle);
			puzzle.printPuzzle(true);
			puzzle.printDimensions();
			
			// Solve and time how long it takes to solve the puzzle
			timer.start(); 
			
			// START SOLVING THE PUZZLE HERE
			
			timer.stop();
			
			// If the puzzle was solved, display that it was solved
			System.out.println("\n- SOLVED PUZZLE -");
			puzzle.printPuzzle(false);
			System.out.println("Time taken to solve the puzzle: " 
			+ timer.getDuration() + " milliseconds!");

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
	
	/**
	 * The display that is shown when the puzzle is being solved and loaded
	 * @param filePath file path to show the user which file is being solved
	 */
	public static void loadingDisplay(String filePath) {
		System.out.println("-------------------");
		System.out.println("Attempting to solve: " + filePath + "\n");
		System.out.println("Solving...");
	}
}