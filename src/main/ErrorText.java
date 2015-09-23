package main;

public enum ErrorText {	
	
	OUT_OF_RANGE ("A value falls out of the require range of this puzzle."),
	INVALID_VALUE ("You have an input which is not a digit in the puzzle."),
	NO_SOLUTION ("This Sudoku puzzle has no solution!");
	
	private final String text;
	
	ErrorText(String text){
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void printTextToConsole() {
		System.err.println(text);
	}
};
	 

