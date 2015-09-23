package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileData {

	protected int width = 0; // width of each big square
	protected int height = 0; // height of each big square
	protected int dimension = 0; // the dimensions of the puzzle width * height
	protected double maxCells = 0; // maximum amount of cells base off the dimensions of the puzzle
	protected int cellLocation = 0; // location of the cell (0 - maxCells)
	protected ArrayList<String> comments = new ArrayList<String>(); // all the comments in the text file
	protected ArrayList<Integer> puzzle = new ArrayList<Integer>(); // all the values in the puzzle
	protected String fileName; // name of the file we are reading from

	/**
	 * Once the constructor is called it will as the user for a file name in the
	 * "src/tests/" directory.
	 */
	public FileData() { 
		getInput();
	}

	public void getInput(){
		System.out.println("Input file name: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			fileName = reader.readLine(); // reads the user input from the console
			readFile(new File("src/tests/" + fileName));

			reader.close();// data parsing
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			getInput();
		}

	}

	/**
	 * Reads the file and parses all the data with regular expressions
	 * and string splits to store it all into the protected variables
	 * for later use.
	 * @param file the text file containing the information about the Suduko puzzle.
	 * @throws Exception 
	 */
	public void readFile(File file)   {

		try {
			// new buffered reader for reading in whole lines
			BufferedReader in = new BufferedReader(new FileReader(file));

			// set the next line to the next line in the reader
			String nextLine = in.readLine();

			// while the next line exists
			while(nextLine != null) {

				// COMMENTS
				if (nextLine.matches("^c(\\s+).*")){

					// removes the "c" when added to the comment array
					String[] comment = nextLine.split("^c(\\s+)"); 

					// add the comment to the comment array
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
					String trimmedLine = nextLine.trim(); 					// removes leading/trailing whitespace
					String[] arrayOfIntegers = trimmedLine.split("\\s"); 	// splits string by space
					for (String s: arrayOfIntegers){ 						 
						if (cellLocation < maxCells) { 						 
							Integer value = new Integer(s); 				// convert string to int
							if (!(value <= dimension) || (value < 0)) { 	// when value falls out of expected range
								ErrorText.OUT_OF_RANGE.printTextToConsole();
								return; 
							} else {
								puzzle.add(value);  // put the key and the location in the map
							}
							cellLocation++;
						}
					}
				}

				else if (nextLine.matches("^[\\d\\s\\w]+$") || nextLine.matches("^[\\s\\d\\w]+$")){
					ErrorText.INVALID_VALUE.printTextToConsole();;
					return;
				}


				nextLine = in.readLine();
				if (nextLine == null && (puzzle.size() < maxCells)) {
					for (int i = puzzle.size(); i < maxCells; i++) {
						puzzle.add(0); // fill in the remaining slots for the puzzle as 0 values;
					}
					System.out.println("Not enough values, assuming you wanted the rest were blank.");
				} 
			}
			in.close(); // close the stream

		}   catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
			getInput();
		} 
	}

	/**
	 * Prints all the comments in the order they were place into the array for testing
	 */
	public void printComments(){
		System.out.println("\n- COMMENTS -");
		if (comments.size() != 0) {
			int count = 1;
			for (String s : comments){
				System.out.println(count + ".\t" + s);
				count++;
			}
		} else {
			System.out.println("No Comments");
		}
		System.out.println();
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