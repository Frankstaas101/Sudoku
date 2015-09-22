package main;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;



public class RowTest {
	
	/**@Test
	public void testFileReading() {
		
		FileData fd = new FileData();
		fd.readFile(new File("testFileReading.txt"));
		String comments = "";
		
	}*/
	
	@Test
    public void testRowChecking() {
        
		FileData fd = new FileData();
		File file = new File("PerfectSudoku");
		fd.readFile(file);
		
		int dimension = fd.dimension;
		ArrayList<Integer> checkList = new ArrayList<Integer>(); // Initialize
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

        assertEquals(true, Functions.checkRow(checkList, dimension, puzzle.cells ));

    }
	
	@Test
    public void testColChecking() {
        
		FileData fd = new FileData();
		File file = new File("PerfectSudoku");
		fd.readFile(file);
		
		int dimension = fd.dimension;
		ArrayList<Integer> checkList = new ArrayList<Integer>(); // Initialize
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

        assertEquals(true, Functions.checkCol(checkList, dimension, puzzle.cells ));

    }
	
	@Test
    public void testBoxChecking() {
        
		FileData fd = new FileData();
		File file = new File("boxesFail.txt");
		fd.readFile(file);
		
		int dimension = fd.dimension;
		ArrayList<Integer> checkList = new ArrayList<Integer>(); // Initialize
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//happens in constructor
		//puzzle.findSections();
		

        assertEquals(false, Functions.checkBoxes(checkList, dimension, puzzle.cells, puzzle.sections ));

    }


}
