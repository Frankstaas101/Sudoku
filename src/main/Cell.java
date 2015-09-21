package main;


/*
 * A cell has a value and a position
 */
public class Cell 
{
	protected int value;
	protected int pos;
	
	/*
	 * @param value int value of the cell
	 * @param pos int position of the cell in the puzzle.
	 */
	public Cell(int value, int pos)
	{
		this.value = value;
		this.pos = pos;
	}
	
	public String toString()
	{
		return "" + value;
	}
	
	//no setters and getters since we have package access.
}
