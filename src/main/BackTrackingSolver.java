package main;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BackTrackingSolver {

	protected int dimension;
	protected int[][] cells;
	
	protected ArrayList<Integer> missingRow = new ArrayList<Integer>();
	protected ArrayList<Integer> missingCol = new ArrayList<Integer>();
	protected ArrayList<Integer> missingBox = new ArrayList<Integer>();
	
	ArrayList<Integer> fullRow = new ArrayList<Integer>();
	ArrayList<Integer> fullColumn = new ArrayList<Integer>();
	ArrayList<Integer> fullBox = new ArrayList<Integer>();
	
	public BackTrackingSolver() {
		
		for(int i = 0; i<dimension; i++){
			fullRow.add(i);
			fullColumn.add(i);
			fullBox.add(i);	
		}
		this.cells = Functions.cells;
	}
	
	public int[][] initializeValues(Dimension dimension, ArrayList<Integer> missingNumbers, int count, int flag)
	{
		try {
			cells = increment(dimension, missingNumbers, count, flag);
		}
		catch(Exception e)
		{
			return null;
		}
		
		return cells;
		
		
		
	}
	
	private int[][] increment(Dimension dimension, ArrayList<Integer> missingNumbers, int count, int flag){
		
		
		if(missingNumbers.size() == 0)
			return cells;
		if(flag == -1)
			count++;
		
		
		int row = (int) dimension.getHeight();
		int col = (int) dimension.getWidth();
		ArrayList<Integer> missingValues = new ArrayList<Integer>();	
		
		missingValues = missingValues(row,col);
		
		if(missingValues.size() == 0)
			return null;//initializeValues(dimension, missingNumbers, count, -1 );
		assignValues(missingValues, missingNumbers, row, col, count);
		
		initializeValues(dimension, missingNumbers, count, 0);
		
		return cells;
	}
	

	private void assignValues(ArrayList<Integer> missingValues, ArrayList<Integer> missingNumbers, int row, int col, int count) {
		
		cells[row][col] =  missingValues.get(count);
		missingValues.remove(count);
			
	}

	public ArrayList<Integer> missingValues(int row, int col){
		
		HashSet<Integer> missingValuesHash = new HashSet<Integer>();	
		ArrayList<Integer> missingValuesArray = new ArrayList<Integer>();
		
		missingValuesHash.addAll(missingRowValues(row));
		missingValuesHash.addAll(missingColValues(col));
		//missingValues.addAll(missingBoxValues());
		
		for(Integer value: missingValuesHash){
			missingValuesArray.add(value);
		}
		
		return missingValuesArray;
	}
	
	public ArrayList<Integer> missingRowValues(int row) {
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int col = 0; col<dimension; col++){
			temp.add(cells[row][col]);
		}
		
		fullRow.removeAll(temp);
		missingRow = fullRow;
		return missingRow;
		
	}
	
	public ArrayList<Integer> missingColValues(int col) {
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int row = 0; row<dimension; row++){
			temp.add(cells[row][col]);
		}
		
		fullRow.removeAll(temp);
		missingCol = fullRow;
		return missingCol;
	}
	
	/**public ArrayList<Integer> missingBoxValues() {
		return missingBox;
		
	}*/
	
}
