package main;

import java.util.ArrayList;

/*
 * Brute Solver will get a list of unassigned Cells and increment them by 1, and return.
 */
public class BruteSolver 
{
	public static ArrayList<Cell> assignValues(ArrayList<Cell> cells) throws Exception
	{
		//When we first get the unAssignedCells, they will be all 0s.
		cells = increment(0, cells);

		return cells;
	}
	
	private static ArrayList<Cell> increment(int cellNo, ArrayList<Cell> cells) throws Exception
	{
		//We've incremented all we can, we'de get an outOfBounds exception if this were to run.
			Cell cell = cells.get(cellNo);
			if(cell.value == 9)
			{
				cell.value = 1;
				increment(cellNo + 1, cells);
			}
			else
			{
				cell.value += 1;
			}
			return cells;
	

	}
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

			
