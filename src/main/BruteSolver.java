package main;

import java.util.ArrayList;

/*
 * Brute Solver will get a list of unassigned Cells and increment them by 1, and return.
 */
public class BruteSolver 
{
	/*
	 * Increment the values in a list of Cells.
	 * 
	 * @param cells the List of Cells to be incremented.
	 * @param dimension the upper limit what each Cell will increment to.
	 */
	public static ArrayList<Cell> assignValues(ArrayList<Cell> cells, int dimension) throws Exception
	{
		//The actual recursive method call.
		//Note that we always want to try to increment the first cell.
		
		
		cells = increment(0, cells, dimension);
		/*
		for(Cell c: cells)
		{
			System.out.print(c.toString() + " ");
		}
		System.out.println();
		*/

		return cells;
		
	}
	/*
	 * Recursive method to increment Cell values given a List of Cells
	 * 
	 * @param cellNo the Cell number to attempt to increment first.
	 * @param cells the List of Cells to increment.
	 * @param dimension the upper limit to increment to, also the number of digits in a puzzle's row or column.
	 */
	private static ArrayList<Cell> increment(int cellNo, ArrayList<Cell> cells, int dimension) throws Exception
	{
		//When the below line throws an OutOfBounds exception, we have tried all the possible values.
		Cell cell = cells.get(cellNo);
		if(cell.value == dimension)
		{	
			cell.value = 1;		//If the Cell's value has reached its upper limit, reset it to 1.
			increment(cellNo + 1, cells, dimension);		//Then try to increment the next one.
		}
		else
		{
			//Increment a Cell's value.
			cell.value += 1;
		}
		return cells;
	

	}
	/*
	 * Method to initialize all "0" place holders from the input to 1
	 * 
	 * @param cells: ArrayList<Cell> of all unassigned cells.
	 * @return: ArrayList<Cell> with all Cell's values set to 1.
	 */
	public static ArrayList<Cell> initializeValues(ArrayList<Cell> cells)
	{
		for(Cell c : cells)
		{
			c.value = 1;
		}
		return cells;
	}
	
//	public ArrayList<Integer> findValues(ArrayList<Integer> missingValues) {
//		if (Validators.)
//	}
	
	/*
	public static void combination(Object[]  elements, int K){
		 
        // get the length of the array
        // e.g. for {'A','B','C','D'} => N = 4 
        int N = elements.length;
         
        if(K > N){
            System.out.println("Invalid input, K > N");
            return;
        }
        // calculate the possible combinations
        // e.g. c(4,2)
        c(N,K);
         
        // get the combination by index 
        // e.g. 01 --> AB , 23 --> CD
        int combination[] = new int[K];
         
        // position of current index
        //  if (r = 1)              r*
        //  index ==>        0   |   1   |   2
        //  element ==>      A   |   B   |   C
        int r = 0;      
        int index = 0;
         
        while(r >= 0){
            // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
            // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"
             
            // for r = 0 ==> index < (4+ (0 - 2)) = 2
            if(index <= (N + (r - K))){
                    combination[r] = index;
                     
                // if we are at the last position print and increase the index
                if(r == K-1){
 
                    //do something with the combination e.g. add to list or print
                    print(combination, elements);
                    index++;                
                }
                else{
                    // select index for next position
                    index = combination[r]+1;
                    r++;                                        
                }
            }
            else{
                r--;
                if(r > 0)
                    index = combination[r]+1;
                else
                    index = combination[0]+1;   
            }           
        }
    }
    */
}

			

