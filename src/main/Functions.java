package main;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * NOT NEEDED?
 */
public class Functions {

	// Returns true if there is a duplicate
	public static boolean hasDuplicate(ArrayList<Integer> array){

		int size = array.size(); // size of array
		boolean hasDuplicate = false; 
		HashMap<Integer, Integer> checkMap = new HashMap<Integer, Integer>();

		for(Integer i: array) {
			checkMap.put(i, 0);
		}

		if (checkMap.size() != size) {
			hasDuplicate = true;
		}

		return hasDuplicate ? true : false;
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
		
		
//		 0  1  2  3
//		 4  5  6  7
//		 8  9  10 11
//		 12 13 14 15
		//Sections
		
		public static boolean checkBoxes(ArrayList<Integer> checkList, int dimension, ArrayList<Cell> puzzle, ArrayList<ArrayList<Integer>> sections)
		{
//			 0  1  2  3
//			 4  5  6  7
//			 8  9  10 11
//			 12 13 14 15
			//Sections
			
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
		
		
		
		
		/* LEGACY CODE BELOW!
		int location = 0;
		for(int i = 0; i < dimension; i++){
		
			System.out.println("Section: " + i);
			 
				//location = i * width * ((dimension * height) * w1);
			
			
			for (int h = 0; h < height; h++){
				for (int w = 0; w < width; w++){
					System.out.println(location + ", ");
					checkList.add(puzzle.get(location));
					location++;
				}
				for (int h1 = 0; h1 < height - 1; h1++){
					location += width;
				}
			}
			if (hasDuplicate(checkList)) {
				valid = false; // break out as soon as a false value is found
			}
			
			checkList.clear(); // clears the list
		}

		*/
