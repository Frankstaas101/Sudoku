package main;

import java.io.File;

public class Main {
	// CHECK FOR MORE THEN THE DIMENSION AMMOUNT OF EACH NUMBER 

	public static void main(String[] args) {
		FileData fd = new FileData();
		fd.readFile(new File("/Users/frank/Desktop/Repositories/Sudoku/src/main/test.txt"));
		
		// Proving comments are being placed into the array
		for (String s : fd.comments){
			System.out.println("Comment: " + s);
		}
	}
}
