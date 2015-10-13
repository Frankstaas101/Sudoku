package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SudokuFileReader {

	// VARIABLES
	protected int 		width; 			// width of each big square
	protected int 		height; 		// height of each big square
	protected int 		dimension; 		// the dimensions of the puzzle width * height
	protected double 	maxCells; 		// maximum amount of cells base off the dimensions of the puzzle
	protected int 		cellCount; 		// location of the cell (0 - maxCells)
	protected String 	filePath;		// file path of the file to be read

	// COLLECTIONS
	protected ArrayList<String> comments; 	// all the comments in the text file
	protected int[][] puzzle;	// all the values in the puzzle

	/**
	 * Called with a file name to establish a new SudokuFileReader object
	 * @param file the text file containing the information about the Sudoku puzzle.
	 * @throws Exception file read error
	 */
	public SudokuFileReader(String filePath) throws Exception { 
		this.width = 0;								// initialize
		this.height = 0;							// initialize
		this.comments = new ArrayList<String>(); 	// initialize
		this.filePath = filePath.trim();			// set the file name
		this.readFile(); 							// read the file
	}

	/**
	 * Gets user input to retrieve the file name
	 * 	Need to be able to just use the file names from a list or collection.
	 */
	@Deprecated
	public void getInput(){
		//		String fileName;
		//		System.out.println("Input file name: ");
		//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//		try {
		//			fileName = reader.readLine(); // reads the user input from the console
		//			readFile();
		//
		//			reader.close();// data parsing
		//		} catch (Exception e) {
		//			System.err.println(e.getLocalizedMessage());
		//			getInput();
		//		}
	}

	/**
	 * Reads the file and parses all the data with regular expressions
	 * and string splits to store it all into the protected variables
	 * for later use.
	 * @param file 
	 * @throws Exception not enough values
	 */
	protected void readFile() throws Exception   {
		try {
			// Initialize buffered reader and grab the first line in the file
			BufferedReader in = new BufferedReader(new FileReader(new File(this.filePath)));
			String nextLine = in.readLine();

			// While a next line exists
			while(nextLine != null) {

				/* COMMENTS
				 *	Splits the string at the c and any other space
				 *	after the c, then stores the rest of the string
				 *	into the comments array.
				 */
				if (nextLine.matches("^c(\\s+).*")){
					String[] comment = nextLine.split("^c(\\s+)");
					comments.add(comment[1]);
				}

				/* DIMENSIONS
				 * 	Store the first non-zero solo values into width and height
				 * 	where width is stored first and height second. Every 
				 * 	value after that is considered to be puzzle data.
				 */
				else if (nextLine.matches("^[1-9][0-9]*\\s*$")){
					if (width == 0) {
						width = new Integer(nextLine.trim());
					}
					else if (height == 0 && width != 0) {
						height = new Integer(nextLine.trim());
						dimension = width * height;
						maxCells = Math.pow((dimension), 2);
						puzzle = new int[dimension][dimension];
					} 
				}

				/* PUZZLE DATA
				 *  - Trims the line of leading and trailing whitespace.
				 *  - Splits the line based off of the spaces in the line.
				 *  - Iterates though each number in the line and if the
				 *    current cell count is less then the maximum number of
				 *    cells then it's parsed to an integer.
				 *  - If a value falls out of the proper range of values for
				 *    the puzzle an exception is thrown.
				 */
				else if (nextLine.matches("^[\\d\\s]+$") || nextLine.matches("^[\\s\\d]+$")){

					int rowCount = 0;
					int colCount = 0;
					
					String trimmedLine = nextLine.trim(); 
					String[] arrayOfIntegers = trimmedLine.split("\\s+");

					for (String s: arrayOfIntegers){ 	

						if (cellCount < maxCells) { 	

							Integer value = new Integer(s);

							if ((value > dimension) || (value < 0)) { 	
								in.close();  // close the stream
								throw new SudokuFileReadException("A value falls out of the"
										+ " require range of this puzzle.", filePath);
							} else { 
								puzzle[rowCount][colCount] = value; 
								rowCount++;
								if (rowCount == dimension) {
									rowCount = 0;
									colCount++;
								}
							}
							cellCount++; 
						}
					}
				}

				/* BLANK LINE
				 * 	Do nothing
				 */
				else if (nextLine.matches("^\\s*$")) { } // do nothing if just a blank line

				/* IMPRROPER FORMATS
				 * If there exists a letter in the puzzle at all it will throw an exception
				 * Any other weird characters that don't match the formats will throw an exception
				 */
				else {
					in.close();  // close the stream
					throw new SudokuFileReadException( "There exisits an input which is not "
							+ "a digit in the puzzle.", filePath);
				}

				// Go to the next line in the file
				nextLine = in.readLine();

				if (nextLine == null && (cellCount < maxCells)) {
					in.close();  // close the stream
					throw new SudokuFileReadException( "The puzzle file does not contain "
							+ "enough values for the given dimensions.", filePath);
				} 
			}
			in.close(); // close the stream

			/* If the width or height is 0 at this point there was 
			 *	something wrong with the file format
			 */
			if (width == 0 ) {
				throw new SudokuFileReadException( "Improper file format, the value for"
						+ " width could not be determined.", filePath);
			} else if (height == 0) {
				throw new SudokuFileReadException("Improper file format, "
						+ "the value for height could not be determined.", filePath);
			}

		}   catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Prints out all the comments in the order they were read
	 */
	public void printComments(){

		System.out.println("\n- COMMENTS -");

		/* 
		 * If comments exist in the array print them out and number 
		 * them starting at 1.
		 */
		if (comments.size() != 0) { 
			int count = 1;
			for (String s : comments){
				System.out.println(count + ".\t" + s);
				count++;
			}
		} else {
			// If no comments exists print N/A
			System.out.println("N/A"); 
		}
		System.out.println();
	}

	/*
	 * GETTERS AND SETTERS 
	 */
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[][] getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(int[][] puzzle) {
		this.puzzle = puzzle;
	}
}