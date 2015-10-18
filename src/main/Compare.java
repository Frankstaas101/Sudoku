package main;

import java.util.Comparator;

class Compare implements Comparator<Cell>
{

	@Override
	public int compare(Cell o1, Cell o2) {
		int difference = o1.possibleValues.size() - o2.possibleValues.size();
		if(difference == 0)
				return 1;
		else
			return difference;
	}
	
}
