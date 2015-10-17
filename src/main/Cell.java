package main;

import java.util.HashSet;

/**
 * A cell has a value, and a list of possible values.
 * 	@author Sebastion Wild
 *	@author Frank A. Staas Jr.
 */

public class Cell {
	
	protected int value;
	protected int x;
	protected int y;
	protected int boxNum;
	
	protected HashSet<Integer> possibleValues = new HashSet<>();
	
	/**
	 * Create a new Cell based on ( x , y ) positions
	 * @param x the column position of the cell
	 * @param y the row position of the cell
	 */
	public Cell(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Create a new Cell based on ( x , y ) positions and value.
	 * @param x the column position of the cell
	 * @param y the row position of the cell
	 * @param value the value being placed into the cell
	 */
	public Cell(int x, int y, int value)
	{
		super();
		this.x = x;
		this.y = y;
		this.value = value;
	}
	
	/**
	 * Empty constructor to create a cell without position data.
	 * Initialize box number to -1 since it will not have a box yet
	 * (This will be determined later).
	 */
	public Cell()
	{
		this.boxNum = -1;
	}
}
