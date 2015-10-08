package main;

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
	public static boolean validate(ArrayList<Cell> puzzle, ArrayList<ArrayList<Integer>> sections, int height, int width){

		int dimension = height * width; // get dimension of the puzzle for calculations
		ArrayList<Integer> checkList = new ArrayList<Integer>(); // Initialize
		//boolean valid = true;
		// Split the puzzle up into pieces and validate

		if(checkRow(checkList, dimension, puzzle) &&
				checkCol(checkList, dimension, puzzle) &&
				checkBoxes(checkList, dimension, puzzle, sections))
			return true;
		return false;
	}


	public static boolean checkRow(ArrayList<Integer> checkList, int dimension, ArrayList<Cell> puzzle)
	{
		// Rows - starts at 1 counts to dimension breaks the arrays by rows
		for(int i = 0; i < puzzle.size(); i++) { // iterates though ever value in the puzzle
			checkList.add(puzzle.get(i).value);
			if ((i+1)  % dimension == 0) { // this check happens every "dimension" values
				if (hasDuplicate(checkList)) {
					//valid = false; 
					return false;		// break out as soon as a false value is found
				}
				checkList.clear(); // clears the list if not false to be used again
			}
		}
		return true;

	}


	public static boolean checkCol(ArrayList<Integer> checkList, int dimension, ArrayList<Cell> puzzle)
	{
		// Column
		for (int colNum = 0; colNum < dimension; colNum++) { // does this dimension times
			for(int i = 0; i < puzzle.size(); i++) { // iterate through whole puzzle
				if ((i + colNum)  % dimension == 0) { // next row
					checkList.add(puzzle.get(i).value);	// adds the first value of the next row
				}
			}
			if (hasDuplicate(checkList)) {
				//valid =  false; 
				return false;		// break out as soon as a false value is found
			}
			checkList.clear(); // clears the list
		}
		return true;
	}

	// Sections Example:
	//		 0  1  2  3
	//		 4  5  6  7
	//		 8  9  10 11
	//		 12 13 14 15
	// Section 0 = 0 1 4 5
	public static boolean checkBoxes(ArrayList<Integer> checkList, int dimension, ArrayList<Cell> puzzle, ArrayList<ArrayList<Integer>> sections)
	{
		//Check all the sections		
		for(ArrayList<Integer> section: sections)
		{
			ArrayList<Integer> sectionValues = new ArrayList<>();		//Create a new List of values for each section.
			for(Integer i: section)		//Iterate through all the indexes in each section.
			{
				sectionValues.add(puzzle.get(i).value);		//Get the value at the given index and add it to the section.
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