package main;
import java.util.ArrayList;
import java.util.HashMap;

import main.Cell;
import main.Position;

/*
 * This class represents an instance of a Sudoku puzzle.
 */
public class Puzzle_TESTING 
{
	/*
	 * A instance is represented by a 2D array of values.
	 * 
	 * Once the instance is created, we note the locations of the empty values.
	 */
	
	//List of all the values in the Puzzle.
	protected ArrayList<Cell> cells;
	
	protected int unitHeight;
	protected int unitWidth;
	protected int dimension;

	protected ArrayList<Integer> missingNumbers = new  ArrayList<Integer>(); // <NUMBER, COUNT>
	protected ArrayList<Cell> unAssignedCells = new ArrayList<>();		//All the unassigned Cells

	/*
	 * An instance receives a width and a height.
	 * 
	 * @param width the unit width of a Sudoku "box"
	 * @param height the unit height of a Sudoku "box"
	 * @param puzzle Map of all the raw values of the Puzzle read from the file.
	 */
	public Puzzle_TESTING(int width, int height, HashMap<Integer, Integer> puzzle) throws Exception
	{
		//Total dimension of the puzzle is [(WIDTH * HEIGHT) ^ 2]
		this.cells = new ArrayList<Cell>();
		this.unitHeight = height;
		this.unitWidth = width;
		this.dimension = width * height;

		for (int i = 0; i < Math.pow(dimension, 2); i++) {
			int cellValue = puzzle.get(i);
			cells.add(new Cell(cellValue, i));
			//we found an unassigned cell, add it to the collection of unAssignedCells
			if(cellValue == 0)
				unAssignedCells.add(new Cell(1, i));
		}

		for (int j = 1; j <= dimension; j ++) {
			int count = 0;
			for (Cell c : cells) {
				if (c.value == j) {
					count++; // counts how many times that number shows up in the puzzle
				}
				if (count > dimension) {
					System.out.println("There are too many " + j + "s in this puzzle. Impossible to solve!");
					return;
				}
			}
			for (int i = 1; i <= dimension - count; i ++) {
				missingNumbers.add(j);
			}
		}

		// prints out the missing numbers
		int count = 0;
		for (Integer num: missingNumbers){
			count++;
			if (count % 15 == 0) // every 15 numbers make a new line
				System.out.print(num + ",\n");
			else 
				System.out.print(num + ", ");
		}
	}

	/*
	 * @returns true if all the rows pass the checks.
	 */
	public boolean check()
	{
		boolean passed = true; 
		HashMap<Integer, Integer> rowCheckingHash = new HashMap<Integer, Integer>(); 
		HashMap<Integer, Integer> colCheckingHash = new HashMap<Integer, Integer>(); 

		// ROW CHECKING
		int rowCount = 0;
		for(int i = 0; i < Math.pow(dimension, 2); i++) {
			
			// if there is a duplicate value it will be over written
			rowCheckingHash.put(cells.get(i).value, cells.get(i).pos);
			
			// when ((i + 1) mod dimenion) is 0 this means when its the next row
			if ((i+1) % dimension == 0) { 
				rowCount++;
				
				// if the hashmap does not contain 'dimension' values then there is a duplicate in that row.
				if (!(rowCheckingHash.size() == dimension)) { 
					// System.out.println("Puzzle has a duplicate value at cell position " 
					//+ cells.get(i).pos + " with a value of " + cells.get(i).value +  " in row "+ rowCount);
					passed = false;
				}
				rowCheckingHash = new HashMap<Integer, Integer>(); // new hash map for every row
			}
		}

		// COLUMN CHECKING
		for(int i = 0; i < dimension; i++) {
			
			// Each cell in the column
			for(int j = 0; j < dimension; j++) {	
				// if there is a duplicate value in the column it will be over written
				colCheckingHash.put(cells.get((j * dimension) + i).value, cells.get((j * dimension) + i).pos); 
			}
			// If the colCheckingHash does not contains the same amount of numbers as the dimension of the puzzle then
			// a value has been over written because it was a duplicate thus making the table invalid.
			if (!(colCheckingHash.size() == dimension)) {  
				// System.out.println("Puzzle has a duplicate value at cell position " 
				//+ cells.get(i).pos + " with a value of " + cells.get(i).value +  " in column "+ i);
				passed = false;
			}
			// reset the hash map to check the next column
			colCheckingHash = new HashMap<Integer, Integer>(); 
		}
		return passed;
	}
	
	/*
	 * Set the desired Cell values.
	 * 
	 * @param cs the List of Cells which we want to set in the puzzle.
	 */
	public void setValues(ArrayList<Cell> cs)
	{
		//Iterate through all the new values
		for(Cell c: cs)
		{
			//Change the value of the cells we want.
			cells.get(c.pos).value = c.value;
		}
	}
	
	public void printPuzzle() throws Exception {
		System.out.println();
		printDashedHorizontalLine(); // prints the top line of the puzzle
		System.out.println();
		for (int i = 0; i < cells.size(); i++) { // 0 to maxCells - 1 

			if (i % unitWidth == 0) { // prints a vertical line for each number that is Width number of numbers in from the left
				System.out.print("| " + cells.get(i) + " ");
			} 
			else {
				System.out.print(cells.get(i) + " ");
				if ((i + 1) % dimension == 0) { // if the count is at the dimension(max columns) make a new line
					System.out.print("|\n");
					if ((i + 1) % (dimension * unitHeight) == 0) { // if the count is the dimension * height
						printDashedHorizontalLine(); // ex: so 4 * 2 = 8, count is at 8 so make a new horizontal line
						System.out.println();
					}
				}
			}
		}
		System.out.println();
	}
	
	/*
	 * Print a correctly formatted dashed horizontal line to the console.
	 */
	private void printDashedHorizontalLine(){
		for (int j = 0; j < dimension; j++) {
			if ( j != 0){
				if (j % unitWidth == 0) 
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
