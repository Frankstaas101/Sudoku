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
		    && Functions.checkRow(puzzle.dimension, puzzle.cells, selectedCell.x)
		    && Functions.checkCol(puzzle.dimension, puzzle.cells, selectedCell.y)== true ? true : false;
	}
	
	/**
	 * Check the desired row of the puzzle, ignores any 0 Cell values.
	 * @param dimension the dimension of the puzzle
	 * @param cells all the cells in the puzzle
	 * @param rowNum the specific row being checked
	 * @return if the row is a valid row return true
	 */
	public static boolean checkCol(int dimension, Cell[][] cells, int colNum)
	{
		Boolean passed = true;	
		ArrayList<Integer> checkList = new ArrayList<>();
			for(int row = 0; row < dimension; row++) { //iterates through every column in that row
				if(cells[row][colNum].value != 0)
					checkList.add(cells[row][colNum].value);
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
	public static boolean checkRow(int dimension, Cell[][] cells, int rowNum)
	{
		Boolean passed = true;
		ArrayList<Integer> checkList = new ArrayList<>();
			for(int col = 0; col < dimension; col++) { // iterate through the rows in that column
				if(cells[rowNum][col].value != 0)
					checkList.add(cells[rowNum][col].value);
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
}