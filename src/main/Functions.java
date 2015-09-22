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
	public static boolean validate(ArrayList<Integer> puzzle, int height, int width){

		int dimension = height * width; // get dimension of the puzzle for calcualtions
		ArrayList<Integer> checkList = new ArrayList<Integer>(); // Initialize
		boolean valid = true;
		// Split the puzzle up into pieces and validate

		// Rows - starts at 1 counts to dimension breaks the arrays by rows
		for(int i = 0; i < puzzle.size(); i++) { // iterates though ever value in the puzzle
			checkList.add(puzzle.get(i));
			if ((i+1)  % dimension == 0) { // this check happens every "dimension" values
				if (hasDuplicate(checkList)) {
					valid = false; // break out as soon as a false value is found
				}
				checkList.clear(); // clears the list if not false to be used again
			}
		}

		// Column
		for (int colNum = 0; colNum < dimension; colNum++) { // does this dimension times
			for(int i = 0; i < puzzle.size(); i++) { // iterate through whole puzzle
				if ((i + colNum)  % dimension == 0) { // next row
					checkList.add(puzzle.get(i));	// adds the first value of the next row
				}
			}
			if (hasDuplicate(checkList)) {
				valid =  false; // break out as soon as a false value is found
			}
			checkList.clear(); // clears the list
		}
//		 0  1  2  3
//		 4  5  6  7
//		 8  9  10 11
//		 12 13 14 15
		//Sections
		
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


		return valid;
	}
}
