package main;

import java.util.ArrayList;

/*
 * Brute Solver will get a list of unassigned Cells and increment them by 1, and return.
 */
public class BruteSolver 
{
	public static ArrayList<Cell> assignValues(ArrayList<Cell> cells)
	{
		//When we first get the unAssignedCells, they will be all 0s.
		boolean unInitialized = true;
		
		if(unInitialized == true)
		{
			//Set all values to 1
			for(Cell c: cells)
			{
				c.value = 1;
			}
			unInitialized = false;
			return cells;
		}
		cells = increment(0, cells);

		return cells;
	}
	
	private static ArrayList<Cell> increment(int cellNo, ArrayList<Cell> cells)
	{
		//We've incremented all we can, we'de get an outOfBounds exception if this were to run.
		try
		{
			Cell cell = cells.get(cellNo);
			if(cell.value == 9)
			{
				cell.value = 0;
				increment(cellNo + 1, cells);
			}
			else
			{
				cell.value += 1;
			}
			return cells;
			}
		catch(Exception e)
		{
			System.out.println("All combinations attempted!");
		}
		//Should be unreachable...
		return cells;
	}
			
}
