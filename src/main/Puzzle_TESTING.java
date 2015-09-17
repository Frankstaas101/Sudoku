package main;
import java.util.ArrayList;
import java.util.Arrays;
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

	protected ArrayList<Cell> cells;
	//May not be needed..
	protected int unitHeight;
	protected int unitWidth;
	protected int dimension;

	protected ArrayList<Integer> missingNumbers = new  ArrayList<Integer>(); // <NUMBER, COUNT>
	protected ArrayList<Cell> unAssignedCells = new ArrayList<>();		//All the unassigned Cells

	/*
	 * An instance receives a width and a height.
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
			if (count % 15 == 0)
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

		// Row checking
		//System.out.println();
		int rowCount = 0;
		for(int i = 0; i < Math.pow(dimension, 2); i++)  // for every cell in the puzzle
		{
			// Row Checking
			rowCheckingHash.put(cells.get(i).value, cells.get(i).pos); // if there is a duplicate value it will be over written
			if ((i+1) % dimension == 0) { // when ((i + 1) mod dimenion) is 0 this means when its the next row
				rowCount++;
				if (!(rowCheckingHash.size() == dimension)) { // if the hashmap does not contain 'dimension' values then there is a duplicate in that row.
					//System.out.println("Puzzle has a duplicate value at cell position " + cells.get(i).pos + " with a value of " + cells.get(i).value +  " in row "+ rowCount);
					return false;
				}
				rowCheckingHash = new HashMap<Integer, Integer>(); // new hash map for every row
			}
		}

		// Column Checking
		for (int i = 0; i < dimension; i++) // Every column
		{
			for (int j = 0; j < dimension; j++) // every cell in that column
			{ // if there is a duplicate value in the column it will be over
				// written
				colCheckingHash.put(cells.get((j * dimension) + i).value,
						cells.get((j * dimension) + i).pos);
			}
			// If the colCheckingHash does not contains the same amount of
			// numbers as the dimension of the puzzle then
			// a value has been over written because it was a duplicate thus
			// making the table invalid.
			if (!(colCheckingHash.size() == dimension)) {
				// System.out.println("Puzzle has a duplicate value at cell position "
				// + cells.get(i).pos + " with a value of " + cells.get(i).value
				// + " in column "+ i);
				return false;
			}
			colCheckingHash = new HashMap<Integer, Integer>(); // new hash map
																// for every row
		}

		HashMap<Integer, Integer> boxCheckingHash = new HashMap<Integer, Integer>();

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				boxCheckingHash.put(cells.get((i / unitWidth) * unitWidth + j/ unitWidth).value,
								    cells.get(i * unitHeight % dimension + j% unitHeight).pos);
			}
			if (!(boxCheckingHash.size() == dimension)) {
				// System.out.println("Puzzle has a duplicate value at cell position "
				// + cells.get(i).pos + " with a value of " + cells.get(i).value +
				// " in column "+ i);
				return false;
			}
			
			boxCheckingHash = new HashMap<Integer, Integer>(); // new hash map for
			// every row
		}
		// If the colCheckingHash does not contains the same amount of numbers
		// as the dimension of the puzzle then
		// a value has been over written because it was a duplicate thus making
		// the table invalid.
	
		return true;
	}

	/*
	 * Set the desired Cell values.
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
