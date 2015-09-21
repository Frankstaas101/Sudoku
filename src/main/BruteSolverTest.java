package main;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

public class BruteSolverTest {

	@Test
	public void test() {
		ArrayList<Cell> testCells = new ArrayList<Cell>();		
		testCells.add(new Cell(0, 0));		//Make some test cells.
		testCells.add(new Cell(0, 0));
		testCells.add(new Cell(0, 0));
		
		testCells = BruteSolver.initializeValues(testCells);		//Set all the cell values to 1
		for(Cell c: testCells)
		{
			assertTrue(c.value == 1);		//Test if the values were set correctly.
		}
		
		//Test if the incrementing works as expected, with an upper bound of 2 for the values.
		try
		{
			//The first increment call
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 2);
			assertTrue(testCells.get(1).value == 1);
			assertTrue(testCells.get(2).value == 1);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 1);
			assertTrue(testCells.get(1).value == 2);
			assertTrue(testCells.get(2).value == 1);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 2);
			assertTrue(testCells.get(1).value == 2);
			assertTrue(testCells.get(2).value == 1);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 1);
			assertTrue(testCells.get(1).value == 1);
			assertTrue(testCells.get(2).value == 2);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 2);
			assertTrue(testCells.get(1).value == 1);
			assertTrue(testCells.get(2).value == 2);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 1);
			assertTrue(testCells.get(1).value == 2);
			assertTrue(testCells.get(2).value == 2);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 2);
			assertTrue(testCells.get(1).value == 2);
			assertTrue(testCells.get(2).value == 2);
			
			//This should throw an exception.
			testCells = BruteSolver.assignValues(testCells, 2);
		}
		catch(Exception e)
		{
			//Here the OutOfBoundsException was triggered.
			//All values should have been reset to 1.
			assertTrue(testCells.get(0).value == 1);
			assertTrue(testCells.get(1).value == 1);
			assertTrue(testCells.get(2).value == 1);
		}
	}

}
