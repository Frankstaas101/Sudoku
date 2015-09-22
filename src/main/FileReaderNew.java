package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileReaderNew {

	protected int width = 0; // width of each big square
	protected int height = 0; // height of each big square
	protected int dimension = 0; // the dimensions of the puzzle width * height
	protected double maxCells = 0; // maximum amount of cells base off the dimensions of the puzzle
	protected int cellLocation = 0; // location of the cell (0 - maxCells)
	protected ArrayList<String> comments = new ArrayList<String>();
	protected int[][] puzzle = new int[dimension][dimension];

	public FileReaderNew() { }
		
		public void readFile(File file) throws Exception{

			try {
				BufferedReader in = new BufferedReader(new FileReader(file));

				String nextLine = in.readLine();
				int rowCount = 0;

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
						int colCount = 0;
						
						//fill the puzzle form the file
						for(String index: arrayOfIntegers)
						{
								int number = Integer.parseInt(index); // cast the string to an integer with the Integer Object constructor
								if ((number >= dimension) || (number < 0)) { // if the value falls out of the range of the expect values of the puzzles dimensions
									System.out.println("A value falls out of the required range for a puzzle of this size: "
											+ "\n\t'" + number + "' was converted to '0'. Please change this value if you wish it to be another value.");
								} 
								else {
									puzzle[rowCount][colCount] = number; //fill the row
									colCount++;
								}
						}
					}
					
					if (puzzle.length != maxCells) { // if the read in values don't reach the end assume the rest are blank or 0's
						throw new IllegalArgumentException();
					}
				
	}
}
	catch (FileNotFoundException e) {
			System.err.println("File was not found!");
			e.printStackTrace();
		} 
	catch (IOException e) {
			System.err.println("There was a problem with reading the file!");
			e.printStackTrace();
		} 
	catch (IllegalArgumentException e) {
			System.err.println("The file did not contain the proper amount of numbers. Fill in the missing positions and try again!");
			e.printStackTrace();
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

		public int[][] getPuzzle() {
			return puzzle;
		}

		public void setPuzzle(int[][] puzzle) {
			this.puzzle = puzzle;
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
			
			for(int x=0;x<dimension;x++)
			{
				if(x == dimension/2)
					printDashedHorizontalLine();
				else System.out.println("|");
				
				for(int y=0;y<dimension;y++)
				{
					if(y == dimension/2)
						System.out.println("|");
					if(y == dimension-1)
						System.out.println(puzzle[x][y] + "|");
					else System.out.println(puzzle[x][y]);
					
				}
			}
			printDashedHorizontalLine();
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
