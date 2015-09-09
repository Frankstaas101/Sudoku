package main;
import java.util.ArrayList;

import main.Cell;
import main.Position;

/*
 * This class represents an instance of a Sudoku puzzle.
 */
public class Puzzle 
{
	/*
	 * A instance is represented by a 2D array of values.
	 * 
	 * Once the instance is created, we note the locations of the empty values.
	 */
	protected int[][] cells;
	//May not be needed..
	protected int unitHeight;
	protected int unitWidth;
	
	/*
	 * An instance receives a width and a height.
	 */
	public Puzzle(int width, int height)
	{
		//Total dimensions are (w*h)^2
		cells = new int[width*height][width*height];
		unitHeight = height;
		unitWidth = width;	
	}
	/*
	 * Returns a list of all locations of unassigned values.
	 * We only run this once, we store the this in the driver.
	 */
	public ArrayList<Position> getUnAssignedValues()
	{
		int totalSize = unitWidth * unitHeight;
		ArrayList<Position> unAssignedCells = new ArrayList<>();
		for(int x = 0; x < totalSize; x++)
		{
			for(int y = 0; y < totalSize; y++)
			{
				if(cells[x][y] == 0)
				{
					unAssignedCells.add(new Position(x, y));
				}
					
			}
		}
		return unAssignedCells;
	}
	
	/*
	 * Set the unassigned cells to the specified values.
	 */
	public void setUnAssignedValues(ArrayList<Cell> assignedCells)
	{
		for(Cell c: assignedCells)
		{
			cells[c.pos.x][c.pos.y] = c.value;
		}
	}
	public boolean check()
	{
		
		//If we reach here the instance is valid.
		return true;
	}
	
}
