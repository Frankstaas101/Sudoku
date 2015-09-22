package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileData {

	protected int width = 0; // width of each big square
	protected int height = 0; // height of each big square
	protected int dimension = 0; // the dimensions of the puzzle width * height
	protected double maxCells = 0; // maximum amount of cells base off the dimensions of the puzzle
	protected int cellLocation = 0; // location of the cell (0 - maxCells)
	protected ArrayList<String> comments = new ArrayList<String>();
	protected ArrayList<Integer> puzzle = new ArrayList<Integer>();

	public FileData() { 
//			System.out.println("Input file name: ");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//			try {
//				String input = reader.readLine();
//				//readFile(new File(input));
//				reader.close();
//			} catch (IOException e) {
//				System.err.println("Input read error.");
//				
//			}
	}

	/**
	 * Reads the file and parses all the data with regular expressions
	 * and string splits to store it all into the protected variables
	 * for later use.
	 * @param file the text file containing the information about the Suduko puzzle.
	 * @throws Exception 
	 */
	public void readFile(File file) {

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
								puzzle.add(0);  // put the key and the location in the map
								System.out.println("A value falls out of the required range for a puzzle of this size: "
										+ "\n\t'" + value + "' was converted to '0'. Please change this value if you wish it to be another value.");
							} 
							else {
								puzzle.add(value);  // put the key and the location in the map
							}
							cellLocation++;
						}
					}
				}

				nextLine = in.readLine();
				if (nextLine == null && (puzzle.size() < maxCells)) {
					for (int i = puzzle.size(); i < maxCells; i++) {
						puzzle.add(0); // fill in the remaining slots for the puzzle as 0 values;
					}
					System.out.println("Not enough values, assuming you wanted the rest were blank.");
					//return;
				} 
			}

			in.close(); // close the stream

		} catch (FileNotFoundException e) {
			System.err.println("File was not found!");
			e.printStackTrace();
			return;
		} catch (IOException e) {
			System.err.println("There was a problem with reading the file!");
			e.printStackTrace();
			return;
		} 
	}
	
	/**
	 * Prints all the comments in the order they were place into the array for testing
	 */
	public void printComments(){
		for (String s : comments){
			System.out.println("Comment: " + s);
		}
	}

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

	public ArrayList<Integer> getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(ArrayList<Integer> puzzle) {
		this.puzzle = puzzle;
	}
}