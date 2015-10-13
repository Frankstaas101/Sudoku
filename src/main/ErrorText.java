package main;

public enum ErrorText {	
	
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
	 

