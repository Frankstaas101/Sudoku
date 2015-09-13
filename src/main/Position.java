package main;
/*
 * A cell has a value and a position
 */
public class Position 
{
	protected int x;
	protected int y;
	protected int section;
	
	public Position() {
		this.x = 0;
		this.y = 0;
	}
	
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	 
}
