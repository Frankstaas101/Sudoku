package main;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;



public class UnitTests {
	

	
	@Test
    public void testRowCheckingSuccess() {
        
		FileData fd = new FileData();
		File file = new File("src/tests/PerfectSudoku");
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
    public void testRowCheckingFailure() {
        
		FileData fd = new FileData();
		File file = new File("src/tests/RowsFail");
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
		

        assertEquals(false, Functions.checkRow(checkList, dimension, puzzle.cells ));

    }
	
	@Test
    public void testColCheckingFailure() {
        
		FileData fd = new FileData();
		File file = new File("src/tests/ColFail");
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
		

        assertEquals(false, Functions.checkCol(checkList, dimension, puzzle.cells ));

    }
	
	@Test
    public void testColCheckingSuccess() {
        
		FileData fd = new FileData();
		File file = new File("src/tests/PerfectSudoku");
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
    public void testBoxCheckingFailure() {
        
		FileData fd = new FileData();
		File file = new File("src/tests/boxesFail.txt");
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
	
	
	@Test
    public void testBoxCheckingSuccess() {
        
		FileData fd = new FileData();
		File file = new File("src/tests/PerfectSudoku.txt");
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
		

        assertEquals(true, Functions.checkBoxes(checkList, dimension, puzzle.cells, puzzle.sections ));

    }
	
	
	@Test
	public void testPuzzleSuccess()
	{
		FileData fd = new FileData();
		File file = new File("src/tests/puzzleSuccess.txt");
		
		fd.readFile(file);
		
		int height = fd.height;
		int width = fd.width;
		
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        assertEquals(true, Functions.validate(puzzle.cells, puzzle.sections, height, width ));
	
	}
	
	@Test
	public void testPuzzleFailure()
	{
		FileData fd = new FileData();
		File file = new File("src/tests/puzzleFail.txt");
		
		fd.readFile(file);
		
		int height = fd.height;
		int width = fd.width;
		
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        assertEquals(false, Functions.validate(puzzle.cells, puzzle.sections, height, width ));
	
	}
	
	@Test
	public void testFileReader()
	{
		FileData fd = new FileData();
		File file = new File("src/tests/test.txt");
		fd.readFile(file);
		
		assertEquals(3, fd.width);
		assertEquals(3, fd.height);
		assertEquals(9, fd.dimension);
		assertEquals("This is a test comment", fd.comments.toString());
		//comments are the same
		//height is equal
		//width is equal
		//puzzle is read in correctly
		
	}

	
}
