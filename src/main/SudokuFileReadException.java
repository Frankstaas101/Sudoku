package main;

/** 
 * This class was created to differentiate between arbitrary exceptions
 * and purposely thrown exceptions due to incorrect input from the files.
 */
public class SudokuFileReadException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SudokuFileReadException(String message, String filePath) {
		super(filePath + ": " + message);
	}
}