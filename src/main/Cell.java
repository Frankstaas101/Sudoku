package main;

import java.util.ArrayList;

//A cell has a value, and a list of possible values.
public class Cell {
	
	protected int value;
	protected int x;
	protected int y;
	
	protected ArrayList<Integer> possibleValues = new ArrayList<>();
	
	/*
	 * Create a new Cell based on x, y positions
	 */
	public Cell(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Empty constructor to create a cell without position data.
	 */
	public Cell()
	{
		
	}
}
