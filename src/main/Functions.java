package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * NOT NEEDED?
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


	// enter in a completed puzzle
	public static boolean validate(Cell[][] cells, ArrayList<ArrayList<Integer>> sections, int height, int width){

		int dimension = height * width; // get dimension of the puzzle for calculations
		ArrayList<Integer> checkList = new ArrayList<Integer>(); // Initialize
		//boolean valid = true;
		// Split the puzzle up into pieces and validate
		//Check all the rows and all the columns.
		if(checkRow(checkList, dimension, cells) &&
				checkCol(checkList, dimension, cells)) // &&
				//checkBoxes(checkList, dimension, cells, sections))
			return true;
		return false;
	}

	/*
	 * Check all the rows of the puzzle.
	 */
	public static boolean checkRow(ArrayList<Integer> checkList, int dimension, Cell[][] cells)
	{
		Boolean passed = true;
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

	/*
	 * Check all the columns of the puzzle.
	 */
	public static boolean checkCol(ArrayList<Integer> checkList, int dimension, Cell[][] cells)
	{
		Boolean passed = true;
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

	// Sections Example:
	//		 0  1  2  3
	//		 4  5  6  7
	//		 8  9  10 11
	//		 12 13 14 15
	// Section 0 = 0 1 4 5
	/*
	 * Check all the boxes of the puzzle.
	 */
	public static boolean checkBoxes(ArrayList<Integer> checkList, int dimension, Cell[][] cells, ArrayList<ArrayList<Point>> sections)
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