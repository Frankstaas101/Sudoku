package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Functions.java is a class containing all of the 
 * checking and validation methods for the the sudoku 
 * puzzles rows, columns and boxs.
 */
public class Functions {

	/**
	 * Returns whether or not there is a duplicate in the list
	 * @param list to be checked for duplicates
	 * @return true if a duplicate exists otherwise false
	 */
	public static boolean hasDuplicate(List<Integer> list){

		// boolean to be returned
		boolean hasDuplicate = false; 

		// store all values of the array in the hash set
		Set<Integer> checkingSet = new HashSet<Integer>(list);

		// if the set contains less values then the array then
		// there is a duplicate value in the list
		if (checkingSet.size() != list.size()) {
			hasDuplicate = true;
		}
		return hasDuplicate;
	}
	
	/**
	 * Validates a single cell in the selected puzzle
	 * @param puzzle the puzzle the ce
	 * ll is contained in
	 * @param selectedCell the specific cell being validated
	 * @return true if the cell is a valid choice and false otherwise.
	 */
	public static boolean validateCell(Puzzle puzzle, Cell selectedCell){
		return Functions.checkBox(puzzle.dimension, puzzle.cells, puzzle.sections.get(selectedCell.boxNum)) 
		    && Functions.checkRow(puzzle.dimension, puzzle.cells, selectedCell.y)
		    && Functions.checkCol(puzzle.dimension, puzzle.cells, selectedCell.x)== true ? true : false;
	}
	
	/**
	 * Check the desired row of the puzzle, ignores any 0 Cell values.
	 * @param dimension the dimension of the puzzle
	 * @param cells all the cells in the puzzle
	 * @param rowNum the specific row being checked
	 * @return if the row is a valid row return true
	 */
	public static boolean checkRow(int dimension, Cell[][] cells, int rowNum)
	{
		Boolean passed = true;	
		ArrayList<Integer> checkList = new ArrayList<>();
			for(int col = 0; col < dimension; col++) { //iterates through every column in that row
				if(cells[col][rowNum].value != 0)
					checkList.add(cells[col][rowNum].value);
				if(checkList.size() == dimension)
				{
					if(hasDuplicate(checkList))
					{
						passed = false;
					}
				checkList.clear();
				}
		}
		return passed;
	}

	/**
	 * Check the desired row of the puzzle, ignores any 0 Cell values.
	 * @param dimension the dimension of the puzzle
	 * @param cells all the cells in the puzzle
	 * @param colNum the specific column being checked
	 * @return if the row is a valid row return true
	 */
	public static boolean checkCol(int dimension, Cell[][] cells, int colNum)
	{
		Boolean passed = true;
		ArrayList<Integer> checkList = new ArrayList<>();
			for(int row = 0; row < dimension; row++) { // iterate through the rows in that column
				if(cells[colNum][row].value != 0)
					checkList.add(cells[colNum][row].value);
				if(checkList.size() == dimension)
				{
					if(hasDuplicate(checkList))
					{						
						passed = false;
					}
				checkList.clear();
				}
			}
		return passed;
		
	}

	/**
	 * Check the desired box of the puzzle, ignoring Cells with 0 values.
	 * @param dimension the dimension of the puzzle
	 * @param cells all the cells in the puzzle
	 * @param section the section or box being checked
	 * @return returns if the box being checked is valid or not (true or false)
	 */
	public static boolean checkBox(int dimension, Cell[][] cells, ArrayList<Point> section)
	{		
			ArrayList<Integer> sectionValues = new ArrayList<>();		//Create a new List of values for each section.
			for(Point p: section)		//Iterate through all the indexes in the section.
			{
				if(cells[p.x][p.y].value != 0)
				sectionValues.add(cells[p.x][p.y].value);		//Get the value at the given index and add it to the section.
			}
			if(hasDuplicate(sectionValues))		//Test the section values.
			{
				return false;
			}
		return true;
	}
	

	/**
	 * Enter in a full puzzle to validate it. If only one of the validation
	 * methods fail then the puzzle is then invalid an returns false.
	 * @param cells all the cells of the puzzle
	 * @param sections the sections being checked
	 * @param height the height of the puzzle
	 * @param width the width of the puzzle
	 * @return true if the puzzle is valid and false otherwise
	 */
	@Deprecated
	public static boolean validate(Cell[][] cells, ArrayList<ArrayList<Point>> sections, int height, int width){

		int dimension = height * width; // get dimension of the puzzle for calculations
		
		ArrayList<Integer> checkList = new ArrayList<Integer>(); // Initialize
		//boolean valid = true;
		// Split the puzzle up into pieces and validate
		//Check all the rows and all the columns.
		if(validateIncomingRows(checkList, dimension, cells) &&
				validateIncomingColumns(checkList, dimension, cells)  &&
					validateIncomingBoxes(dimension, cells, sections))
			return true;
		return false;
	}

	/**
	 * Validates the incoming rows in the puzzle when they read in from the file.
	 * @param checkList
	 * @param dimension
	 * @param cells
	 * @return whether or not the rows are all valid
	 */
	@Deprecated
	public static boolean validateIncomingRows(ArrayList<Integer> checkList, int dimension, Cell[][] cells)
	{
		Boolean passed = true;
		//What is the point of this variable? It will get garbage collected as soon as this method is done. -Sebastian
		ArrayList<Integer> failedRow = new ArrayList<Integer>();
		
		for(int row = 0; row < dimension; row++) { // iterates though every row
			for(int col = 0; col < dimension; col++) { //iterates through every column in that row
				checkList.add(cells[row][col].value);
				if(checkList.size() == dimension)
				{
					if(hasDuplicate(checkList))
					{
						failedRow.add(row);
						passed = false;
					}
				checkList.clear();
				}
			}
		}
		return passed;
	}

	/**
	 * Validates the incoming columns in the puzzle when they read in from the file.
	 * @param checkList the list of cells to be checked for duplicates
	 * @param dimension the dimension of the puzzle
	 * @param cells all the cells in the puzzel
	 * @return whether or not the columns are all valid
	 */
	@Deprecated
	public static boolean validateIncomingColumns(ArrayList<Integer> checkList, int dimension, Cell[][] cells)
	{
		Boolean passed = true;
		//What is the point of this variable? It will get garbage collected as soon as this method is done. -Sebastian
		ArrayList<Integer> failedCol = new ArrayList<Integer>();
		// Column
		for (int col = 0; col < dimension; col++) { // iterate through the columns
			for(int row = 0; row < dimension; row++) { // iterate through the rows in that column
				checkList.add(cells[row][col].value);
				if(checkList.size() == dimension)
				{
					if(hasDuplicate(checkList))
					{
						failedCol.add(col);
						passed = false;
					}
				checkList.clear();
				}
			}
		}
		return passed;
	}
	
	/**
	 * Validates the incoming boxes in the puzzle when they read in from the file.
	 * @param dimension the dimension of the puzzel
	 * @param cells all the cells in the puzzle
	 * @param sections all the sections or boxes in the puzzle
	 * @return whether or not the boxes are all valid
	 */
	@Deprecated
	public static boolean validateIncomingBoxes(int dimension, Cell[][] cells, ArrayList<ArrayList<Point>> sections)
	{
		//Check all the sections		
		for(ArrayList<Point> section: sections)
		{
			ArrayList<Integer> sectionValues = new ArrayList<>();		//Create a new List of values for each section.
			for(Point p: section)		//Iterate through all the indexes in each section.
			{
				sectionValues.add(cells[p.x][p.y].value);		//Get the value at the given index and add it to the section.
			}
			if(hasDuplicate(sectionValues))		//Test the section values.
			{
				//valid = false;
				return false;
			}
		}
		return true;
	}
}