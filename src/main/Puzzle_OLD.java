package main;
import java.util.ArrayList;

import main.Cell;
import main.Position;

/*
 * This class represents an instance of a Sudoku puzzle.
 */
public class Puzzle_OLD 
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
	public Puzzle_OLD(int width, int height)
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
			//cells[c.pos.x][c.pos.y] = c.value;
		}
	}
	/*
	 * @returns true if all the rows pass the checks.
	 */
	public boolean checkRows()
	{
		boolean passed = true;
		ArrayList<Integer> rowValues = new ArrayList<>();
		for(int x = 0; x < (unitWidth * unitHeight); x++)
		{
			for(int y = 0; y < (unitWidth * unitHeight); y++ )
			{
				rowValues.add(cells[x][y]);
			}
			if(!Validators.checkRow(rowValues))
			{
				passed = false;
			}
		}
		return passed;
		
	}
	/*
	 * @returns true if all the columns pass the checks.
	 */
	public boolean checkColumns()
	{
		boolean passed = true;
		ArrayList<Integer> columnValues = new ArrayList<>();
		for(int y = 0; y < (unitWidth * unitHeight); y++)
		{
			for(int x = 0; x < (unitWidth * unitHeight); x++ )
			{
				columnValues.add(cells[x][y]);
			}
			if(!Validators.checkRow(columnValues))
			{
				passed = false;
			}
		}
		return passed;
		
	}
	
	public boolean checkSections()
	{
		boolean passed = true;
		ArrayList<Integer> section = new ArrayList<>();
		int dimension = unitHeight * unitWidth;
		
		
		//Inefficient as hell...
		for(int width = 0; width < dimension; width = width + unitWidth)
		{
			for(int height = 0; height < dimension; height = height + unitHeight)
			{
				section = new ArrayList<>();
				for(int innerWidth = 0; innerWidth < unitWidth; innerWidth++)
				{
					for(int innerHeight = 0; innerHeight < unitHeight; innerHeight++)
					{
						section.add(cells[width+innerWidth][height+innerHeight]);
					}
				}
				//passed = Validators.checkSection(section);
			}
		}
		return passed;
	}
	
	
}
