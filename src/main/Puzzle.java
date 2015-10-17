package main;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents an instance of a Sudoku puzzle.
 */
public class Puzzle 
{
	// The Sudoku puzzle's important variables
	protected int height;
	protected int width;
	protected int dimension;
	protected int maxCells;

	// 2D Array of cells, where the cells location is
	// cells[row number][column number] (starting from 0)
	protected Cell[][] cells;

	// All of the unassigned cell locations
	protected ArrayList<Dimension> unAssignedCells;	 

	//List of all the indexes in each section.
	protected ArrayList<ArrayList<Point>> sections = new ArrayList<>();	

	/*
	 * An instance receives a width and a height.
	 * 
	 * @param width the unit width of a Sudoku "box"
	 * @param height the unit height of a Sudoku "box"
	 * @param puzzle Map of all the raw values of the Puzzle read from the file.
	 */
	public Puzzle(int width, int height, Cell[][] puzzle) throws Exception
	{
		// Total amount of cells in the puzzle is dimension*dimension
		this.cells = puzzle;

		// Variable array list of unassigned cells
		this.unAssignedCells = new ArrayList<Dimension>();

		// Store and calculate puzzle variables
		this.height = height;
		this.width = width;
		this.dimension = width * height;
		this.maxCells = dimension * dimension;

		// Iterate though each value in the puzzle
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {

				// We found an unassigned cell, add it to the collection of unAssignedCells
				if(cells[row][col].value == 0) {

					// Unassigned Cells row and column location stored
					// in a Dimension(Row, Column) 
					unAssignedCells.add(new Dimension(row,col));
				}
			}
		}

		findSections();  //find all the section indexes in the puzzle.
	}

	/*
	 * Find the indexes of the Sections.
	 */
	public void findSections()
	{	
		//A section is a list of 2D points.
		ArrayList<Point> section;
		for(int bigWidth = 0; bigWidth < dimension; bigWidth = bigWidth + width)
		{
			for(int bigHeight = 0; bigHeight < dimension; bigHeight = bigHeight + height)
			{
				section = new ArrayList<Point>();		//Start a new section
				for(int innerWidth = 0; innerWidth < width; innerWidth++)
				{
					for(int innerHeight = 0; innerHeight < height; innerHeight++)
					{
						//Might not be correct, gotta test...(Probably not correct at all...)
						section.add(new Point(bigHeight+innerHeight, bigWidth+innerWidth));
						//section.add((bigHeight+innerHeight) * dimension + (bigWidth+innerWidth));
					}
				}
				sections.add(section);		//Add the section to the list of sections.
			}
		}
	}

	/*
	 * Set the desired Cell values.
	 * 
	 * @param cs the List of Cells which we want to set in the puzzle.
	 */
	//	public void setValues(ArrayList<Cell> cs)
	//	{
	//		//Iterate through all the new values
	//		for(Cell c: cs)
	//		{
	//			//Change the value of the cells we want.
	//			cells.get(c.pos).value = c.value;
	//		}
	//	}

	/**
	 * Prints the dimensions from the file for testing
	 */
	public void printDimensions(){
		System.out.println("- DIMENSIONS -");
		System.out.println("Width: " + width + ", Height:" + height);
		System.out.println("Dimension: " + dimension + "x" + dimension + "\n");
	}

	/**
	 *  Prints out the puzzle from the puzzle Hash Map for testing
	 * @throws Exception 
	 */
	public void printPuzzle(boolean showTitle) throws Exception {
		if (showTitle) {
			System.out.println("- PUZZLE -");
		}
		printDashedHorizontalLine(); // prints the top line of the puzzle
		System.out.println();
		for (int r = 0; r < dimension; r++) { // row 0 - dimension

			for (int c = 0; c < dimension; c++) { // column 0 - dimension
				if (c % height == 0)
					System.out.print("| " + cells[r][c] + " ");
				else {
					System.out.print(cells[r][c] + " ");
				}
			}
			System.out.print("|\n");
			if ((r + 1) % width == 0){
				printDashedHorizontalLine();
				System.out.println(); 
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
