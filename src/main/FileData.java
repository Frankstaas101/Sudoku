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
	 */
	public void readFile(File file){
		
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
				else if (nextLine.matches("^[1-9\\s]+$")){

					String[] arrayOfIntegers = nextLine.split("\\s"); // splits the string of digits by each space

					for (String s: arrayOfIntegers) // go though the array of integers
					{ 
						if (cellLocation < maxCells) { // counts from 0 to max number of cells - 1
							Integer value = new Integer(s); // cast the string to an integer with the Integer Object constructor

							puzzle.put(cellLocation, value);  // put the key and the location in the map

							cellLocation++;
						}
					}
				}
				nextLine = in.readLine();
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
		System.out.println("Width: " + width + ", Height:" + height);
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
	 */
	public void printPuzzle() {
		printDashedHorizontalLine();
		System.out.println();
		for (int i = 0; i < maxCells; i++) { // 0 to maxCells - 1 

			if (i % width == 0) 
			{
				System.out.print("| " + puzzle.get(i) + " ");
			} 
			else 
			{
				System.out.print(puzzle.get(i) + " ");
				if ((i + 1) % dimension == 0) {
					System.out.print("|\n");
					if ((i + 1) % (dimension * height) == 0) {
						printDashedHorizontalLine();
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