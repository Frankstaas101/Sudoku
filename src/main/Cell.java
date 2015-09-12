package main;

import main.Position;

/*
 * A cell has a value and a position
 */
public class Cell 
{
	protected int value;
	protected Position pos;
	
	public Cell(int value, Position pos)
	{
		this.value = value;
		this.pos = pos;
	}
	
	//no setters and getters since we have package access.
}
