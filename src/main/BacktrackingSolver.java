package main;

import java.util.*;

public class BacktrackingSolver {
	Puzzle puzzle;
	ArrayList<Cell> unAssignedCells = new ArrayList<>();
	
	public BacktrackingSolver(Puzzle puzzle)
	{
		this.puzzle = puzzle;
		TreeSet<Cell> prioritizedCells = new TreeSet<Cell>(new Compare());
		//Put all of the Cells into a TreeSet sorted by the number of possible values they have available.
		for(Cell c: puzzle.unAssignedCells)
		{
			prioritizedCells.add(c);
		}
		//Put the sorted cells into an ArrayList
		Iterator<Cell> it = prioritizedCells.iterator();
		while(it.hasNext())
		{
			unAssignedCells.add(it.next());
		}
		//System.out.println(unAssignedCells);
	}
	
	public boolean solve(int nextCell)
	{
		if(nextCell >= unAssignedCells.size())
			//No more things to try, we either have a successful assignment or no solution.
			//return Functions.validate(puzzle.cells, puzzle.sections, puzzle.height, puzzle.width);
			return true;
		else
		{
			Cell selectedCell = unAssignedCells.get(nextCell);
			
			for(int i = 0; i < selectedCell.possibleValues.size(); i++)
			{
				selectedCell.value = selectedCell.possibleValues.get(i);
				//Set the cell to the value above in the puzzle.
				puzzle.cells[selectedCell.x][selectedCell.y].value = selectedCell.value;

				//Check if the assignment is valid.
				if((Functions.checkBox(puzzle.dimension, puzzle.cells, puzzle.sections.get(selectedCell.boxNum)) 
						&& Functions.checkRow(puzzle.dimension, puzzle.cells, selectedCell.y)
							&& Functions.checkCol(puzzle.dimension, puzzle.cells, selectedCell.x) )== true)
				{
					/*
					try {
						puzzle.printPuzzle(false);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					
					//At this point we have a seemingly valid assignment for currentCell				
					//recursive call to try the next cell over...
					if(solve(nextCell + 1))
						return true;
				}
				//If we get here, the assignment was not successful:
				//Repeat loop and try the next randomly selected value.
				
				//reset the cell
				puzzle.cells[selectedCell.x][selectedCell.y].value = 0;
			}
			//When we get here, we have tried all possible values for this cell...
			return false;
			
		}

	}
}

	
	/*
	 * public boolean solve()
	{
		Random rand = new Random();
		if(!prioritizedCells.isEmpty())
		{
			//Get the cell with the fewest possibilities.
			Cell selectedCell = prioritizedCells.first();
			//Remove the selected Cell from the Tree.
			prioritizedCells.remove(selectedCell);
			
			//Assign the selectedCell a value.
			//Randomly pick a value to assign to the cell.
			if(selectedCell.possibleValues.size() !=0)
			{
				selectedCell.value = selectedCell.possibleValues.get(rand.nextInt(selectedCell.possibleValues.size()));
				//Set the cell to the value above in the puzzle.
				puzzle.cells[selectedCell.x][selectedCell.y].value = selectedCell.value;
				//remove the value we just assigned from the list of possibleValues.
				selectedCell.possibleValues.remove(new Integer(selectedCell.value));
				//Check if the assignment is valid.
				if(Functions.checkBox(puzzle.dimension, puzzle.cells, puzzle.sections.get(selectedCell.boxNum)) 
						&& Functions.checkRow(puzzle.dimension, puzzle.cells, selectedCell.y)
							&& Functions.checkCol(puzzle.dimension, puzzle.cells, selectedCell.x)== true)
				{
					//We now have a valid assignment for this cell.
					//Now we have to update the possible values for the other prioritizedCells.
					Iterator<Cell> it = prioritizedCells.iterator();
					while(it.hasNext())
					{
						Cell selected = it.next();
						
						//If the selected Cell is in the same column as the cell
						//	we just assigned a value to, try to remove the value from its
						//		list of possible values.
						if(selected.x == selectedCell.x)
							selected.possibleValues.remove(new Integer(selectedCell.value));
						//...So on for if the cell is in the same row...
						if(selected.y == selectedCell.y)
							selected.possibleValues.remove(new Integer(selectedCell.value));
						//...In the same box...
						if(selected.boxNum == selectedCell.boxNum)
							selected.possibleValues.remove(new Integer(selectedCell.value));
					}
					//At this point we have a seemingly valid assignment for currenCell,
					//	and have removed the value from the other Cells in its Row, Col, Box.
					
					//recursive call to try the next cell over...
					solve();
				}
				//If we get here, the value we assigned was not good - undo and backtrack.
				//However we leave the value we picked removed from the list - it didn't work.
				prioritizedCells.add(selectedCell);
				solve();
			}
			else
			{	
				//There are no possible values to assign to this cell.
				//Either the already assigned value is the correct one or something went wrong.
				solve();
			}
			
		}
		else
		{
			return Functions.validate(puzzle.cells, puzzle.sections, puzzle.height, puzzle.width);
		}
		System.err.println("We fell through in solve()!");
		return false;
	}
	 */

