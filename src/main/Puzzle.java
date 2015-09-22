package main;
import java.util.ArrayList;
import main.Cell;

/*
 * This class represents an instance of a Sudoku puzzle.
 */
public class Puzzle 
{
	/*
	 * A instance is represented by a 2D array of values.
	 * 
	 * Once the instance is created, we note the locations of the empty values.
	 */
	
	//List of all the values in the Puzzle.
	protected ArrayList<Cell> cells;
	
	protected int height;
	protected int width;
	protected int dimension;
	protected int maxCells;

	protected ArrayList<Integer> missingNumbers = new  ArrayList<Integer>(); // <NUMBER, COUNT>
	protected ArrayList<Cell> unAssignedCells = new ArrayList<>();		//All the unassigned Cells

	/*
	 * An instance receives a width and a height.
	 * 
	 * @param width the unit width of a Sudoku "box"
	 * @param height the unit height of a Sudoku "box"
	 * @param puzzle Map of all the raw values of the Puzzle read from the file.
	 */
	public Puzzle(int width, int height, ArrayList<Integer> puzzle) throws Exception
	{
		//Total dimension of the puzzle is [(WIDTH * HEIGHT) ^ 2]
		this.cells = new ArrayList<Cell>();
		this.height = height;
		this.width = width;
		this.dimension = width * height;
		this.maxCells = dimension * dimension;

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

	/**
	 * Prints the dimensions from the file for testing
	 */
	public void printDimensions(){
		System.out.println("\nWidth: " + width + ", Height:" + height);
		System.out.println("Dimension: " + dimension + "x" + dimension);
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
				System.out.print("| " + cells.get(i).value + " ");
			} 
			else {
				System.out.print(cells.get(i).value + " ");
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
