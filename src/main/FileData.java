package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileData {

	protected int width = 0; // width of each big square
	protected int height = 0; // height of each big square
	protected int dimension = 0; // the dimensions of the puzzle width * height
	protected double maxCells = 0; // maximum amount of cells base off the dimensions of the puzzle
	protected int cellLocation = 0; // location of the cell (0 - maxCells)
	protected ArrayList<String> comments = new ArrayList<String>();
	protected HashMap<Integer, Integer> puzzle = new HashMap<Integer, Integer>();

	public FileData() { }

	/**
	 * Reads the file and parses all the data with regular expressions
	 * and string splits to store it all into the protected variables
	 * for later use.
	 * @param file the text file containing the information about the Suduko puzzle.
	 * @throws Exception 
	 */
	public void readFile(File file) throws Exception{

		try {
			BufferedReader in = new BufferedReader(new FileReader(file));

			String nextLine = in.readLine();

			while(nextLine != null) {

				// COMMENTS
				if (nextLine.matches("^c(\\s+).*")){
					String[] comment = nextLine.split("^c(\\s+)"); // removes the "c" when added to the comment array
					comments.add(comment[1]);
				}

				// DIMENSIONS
				else if (nextLine.matches("^[1-9]$")){
					if (width == 0) {
						width = new Integer(nextLine);
					}
					else if (height == 0 && width != 0) {
						height = new Integer(nextLine);
						dimension = width * height;
						maxCells = Math.pow((dimension), 2);
					}
				}

				// PUZZLE DATA
				else if (nextLine.matches("^[\\d\\s]+$") || nextLine.matches("^[\\s\\d]+$")){

					String trimmedLine = nextLine.trim(); // removes leading and trailing whitespace
					String[] arrayOfIntegers = trimmedLine.split("\\s"); // splits the string of digits by each space

					for (String s: arrayOfIntegers) // go though the array of integers
					{ 
						if (cellLocation < maxCells) { // counts from 0 to max number of cells - 1

							Integer value = new Integer(s); // cast the string to an integer with the Integer Object constructor
							if (!(value <= dimension) || (value < 0)) { // if the value falls out of the range of the expect values of the puzzles dimensions
								puzzle.put(cellLocation,  0 );  // put the key and the location in the map
								System.out.println("A value falls out of the required range for a puzzle of this size: "
										+ "\n\t'" + value + "' was converted to '0'. Please change this value if you wish it to be another value.");
							} 
							else {
								puzzle.put(cellLocation,  value );  // put the key and the location in the map
							}
							cellLocation++;
						}
					}
					
					if (puzzle.size() != maxCells) { // if the read in values don't reach the end assume the rest are blank or 0's
						for (int i = puzzle.size(); i < maxCells; i++) {
							puzzle.put(i, 0); // fill in the remaining slots for the puzzle as 0 values;
						}
					}
				}
				nextLine = in.readLine();
				if (nextLine == null && (puzzle.size() < maxCells)) {
					System.out.println("Not enough values");
					return;
				}
			}

			in.close(); // close the stream

		} catch (FileNotFoundException e) {
			System.out.println("File was not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There was a problem with reading the file!");
			e.printStackTrace();
		} 
	}

	/**
	 * Prints the dimensions from the file for testing
	 */
	public void printDimensions(){
		System.out.println("\nWidth: " + width + ", Height:" + height);
		System.out.println("Dimension: " + dimension + "x" + dimension);
	}

	/**
	 * Prints all the comments in the order they were place into the array for testing
	 */
	public void printComments(){
		for (String s : comments){
			System.out.println("Comment: " + s);
		}
	}

	/**
	 *  Prints out the puzzle from the puzzle Hash Map for testing
	 * @throws Exception 
	 */
	public void printPuzzle() throws Exception {
		System.out.println();
		printDashedHorizontalLine(); // prints the top line of the puzzle
		System.out.println();
		for (int i = 0; i < maxCells; i++) { // 0 to maxCells - 1 

			if (i % width == 0) { // prints a vertical line for each number that is Width number of numbers in from the left
				System.out.print("| " + puzzle.get(i) + " ");
			} 
			else {
				System.out.print(puzzle.get(i) + " ");
				if ((i + 1) % dimension == 0) { // if the count is at the dimension(max columns) make a new line
					System.out.print("|\n");
					if ((i + 1) % (dimension * height) == 0) { // if the count is the dimension * height
						printDashedHorizontalLine(); // ex: so 4 * 2 = 8, count is at 8 so make a new horizontal line
						System.out.println();
					}
				}
			}
		}
		System.out.println();
	}

	/**
	 * Prints a dashed horizontal line based on the dimension and width of the puzzle
	 */
	private void printDashedHorizontalLine(){
		for (int j = 0; j < dimension; j++) {
			if ( j != 0){
				if (j % width == 0) 
				{
					System.out.print("  ");System.out.print(" -");
				} else {
					System.out.print(" -");
				}
			} else {
				System.out.print("  -");
			}
		}
	}
}