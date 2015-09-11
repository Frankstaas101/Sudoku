package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileData {

	protected int width = 0;
	protected int height = 0;
	protected int dimension = 0;
	protected double maxCells = 0;
	protected int cellLocation = 0; // possibly make this a dimension?
	protected ArrayList<String> comments = new ArrayList<String>();
	protected HashMap<Integer, Integer> puzzle = new HashMap<Integer, Integer>();

	public FileData() {

	}

	public void readFile(File name){
		try {
			BufferedReader in = new BufferedReader(new FileReader(name));

			String nextLine = in.readLine();

			while(nextLine != null) {
				//System.out.println(nextLine);

				// matches a comment format
				if (nextLine.matches("^c(\\s+).*")){
					String[] comment = nextLine.split("^c(\\s+)"); // removes the "c" when added to the comment array
					comments.add(comment[1]);
				}

				// first two numbers are the height and width
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

				// matches the pattern 
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