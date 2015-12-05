package main;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
	protected Cell[][] cells = new Cell[dimension][dimension];

	// All of the unassigned cell locations
	protected ArrayList<Cell> unAssignedCells;	 

	//List of all the indexes in each section.
	protected ArrayList<ArrayList<Point>> sections = new ArrayList<>();
        
        protected ArrayList<Integer> missingValues = new ArrayList<>();

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

		// Store and calculate puzzle variables
		this.height = height;
		this.width = width;
		this.dimension = width * height;
		this.maxCells = dimension * dimension;
		
		// Variable array list of unassigned cells
		this.unAssignedCells = new ArrayList<Cell>();
		
		findSections();  //Assign each cell a box of the puzzle.
		
		//Find all the unassignedCells in the puzzle:
		// Iterate though each value in the puzzle
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {

				// We found an unassigned cell, add it to the collection of unAssignedCells
				if(cells[row][col].value == 0) {
					unAssignedCells.add(cells[row][col]);
				}
			}
		}
		findPossibleValues();	//Find all the possible values for unassignedCells.
	}

	/*
	 * Find the indexes of the Sections.
	 * 
	 * Example Section layout for a 2x2:
	 * 0 0 1 1
	 * 0 0 1 1
	 * 2 2 3 3 
	 * 2 2 3 3
	 * 
	 * Somewhat counter-intuitive. This could be changed by swapping the two for loops below.
	 * 
	 */
	public void findSections()
	{	
		//A section is a list of 2D points.
		ArrayList<Point> section;
		int currentSection = -1;
		for(int bigWidth = 0; bigWidth < dimension; bigWidth = bigWidth + width)
		{
			for(int bigHeight = 0; bigHeight < dimension; bigHeight = bigHeight + height)
			{
				currentSection++;
				section = new ArrayList<Point>();		//Start a new section
				for(int innerWidth = 0; innerWidth < width; innerWidth++)
				{
					for(int innerHeight = 0; innerHeight < height; innerHeight++)
					{
						//Might not be correct, gotta test...(Probably not correct at all...)
						//DEPRECATED!
						section.add(new Point(bigWidth+innerWidth, bigHeight+innerHeight));
						//Assign the Cell to the section we are currently on.
						cells[bigWidth+innerWidth][bigHeight+innerHeight].boxNum = currentSection;
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
					System.out.print("| " + cells[r][c].value + " ");
				else {
					System.out.print(cells[r][c].value + " ");
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
	/*
	 * Find and set all the possible values for each unassignedCell.
	 */
	public void findPossibleValues()
	{
		for(Cell c: unAssignedCells)
		{
			ArrayList<Integer> possibleValues = new ArrayList<>();
			//Each unAssignedCell has a set of (at max) dimension possible values.
			HashSet<Integer> values = new HashSet<>(dimension);
			//Find all the missing values in the cell's row:
			values.addAll(findRowMissingValues(c.x));
			//Find all the missing values in the cell's column:
			values.retainAll(findColumnMissingValues(c.y));
			//Find all the missing values in the cell's box:
			values.retainAll(findBoxMissingValues(c.boxNum));
			
			Iterator<Integer> it = values.iterator();
			while(it.hasNext())
			{
				possibleValues.add(it.next());
			}
			c.possibleValues = possibleValues;
			//System.out.println(c.possibleValues);
		}
	}
	
	/*
	 * Find and set all the possible values for each unassignedCell.
	 */
	public void findPossibleValues(Cell c)
	{
		
			ArrayList<Integer> possibleValues = new ArrayList<>();
			//Each unAssignedCell has a set of (at max) dimension possible values.
			HashSet<Integer> values = new HashSet<>(dimension);
			//Find all the missing values in the cell's row:
			values.addAll(findRowMissingValues(c.x));
			//Find all the missing values in the cell's column:
			values.retainAll(findColumnMissingValues(c.y));
			//Find all the missing values in the cell's box:
			values.retainAll(findBoxMissingValues(c.boxNum));
			
			Iterator<Integer> it = values.iterator();
			while(it.hasNext())
			{
				possibleValues.add(it.next());
			}
			c.possibleValues = possibleValues;
			//System.out.println(c.possibleValues);
		
	}
	
	/*
	 * Find all the missing values for a specified row.
	 * 
	 * Zero Cell values are ignored.
	 */
	private ArrayList<Integer> findRowMissingValues(int rowNum)
	{
		int[]  values = new int[dimension+1];
		//ArrayList of all the missing values.
		missingValues.clear();
		
		for(int i = 0; i < dimension; i++)
		{
			//Hold rowNum constant while we iterate through all the columns,
			//add Cell value in the corresponding position in the array of values.
			//If a Cell's value is 0, it is added but ignored in a later step.
			//System.out.println("Got position: " + i + " " + rowNum);
			values[cells[rowNum][i].value] += 1 ;
		}
		//Iterate through all the row's values.
		for(int i = 1; i <= dimension; i++)
		{
			//If the number did not occur in the row, add it to the missing Values.
			if(values[i] == 0)
				missingValues.add(i);				
		}
		return missingValues;
	}
	/*
	 * Find all the missing values for a specified column.
	 * 
	 * Zero Cell values are ignored.
	 */
	private ArrayList<Integer> findColumnMissingValues(int colNum)
	{
		int[]  values = new int[dimension+1];
		//ArrayList of all the missing values.
		missingValues.clear();
		
		for(int i = 0; i < dimension; i++)
		{
			//Hold colNum constant while we iterate through all the rows,
			//add Cell value in the corresponding position in the array of values.
			//If a Cell's value is 0, it is added but ignored in a later step.
			values[cells[i][colNum].value] += 1 ;
		}
		//Iterate through all the row's values.
		for(int i = 1; i <= dimension; i++)
		{
			//If the number did not occur in the row, add it to the missing Values.
			if(values[i] == 0)
				missingValues.add(i);				
		}
		return missingValues;
	}
	/*
	 * Find all the missing values for a specified box.
	 * 
	 * 0 0 2 2 
	 * 0 0 2 2
	 * 1 1 3 3
	 * 1 1 3 3
	 * 
	 * Zero Cell values are ignored.
	 */
	private ArrayList<Integer> findBoxMissingValues(int boxNum)
	{
		//We need an array 1 size larger than dimension to hold the 0 too.
		int[]  values = new int[dimension+1];
		//ArrayList of all the missing values.
		missingValues.clear();
                
		
		for(int i = 0; i < dimension; i++)
		{
			//Iterate through points in the desired box
			//add Cell value in the corresponding position in the array of values.
			//If a Cell's value is 0, it is added but ignored in a later step.
			values[cells[sections.get(boxNum).get(i).x][sections.get(boxNum).get(i).y].value] += 1 ;
		}
		//Iterate through all the row's values.
		for(int i = 1; i <= dimension; i++)
		{
			//If the number did not occur in the row, add it to the missing Values.
			if(values[i] == 0)
				missingValues.add(i);				
		}
		return missingValues;
	}
}
