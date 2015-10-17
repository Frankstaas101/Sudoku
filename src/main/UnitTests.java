package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class UnitTests {
	
	/*
	 * Test that rows without duplicates are correctly accepted.
	 */
	@Test
    public void testRowCheckingSuccess() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/PerfectSudoku.txt");
		fd.readFile();
		
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
	
	/*
	 * Test that rows with duplicates are correctly rejected.
	 */
	@Test
    public void testRowCheckingFailure() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/RowsFail.txt");
		fd.readFile();
		
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
	
	/*
	 * Assert that puzzles with duplicates in the columns are correctly rejected.
	 */
	@Test
    public void testColCheckingFailure() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/ColFail.txt");
		fd.readFile();
		
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
	/*
	 * Assert that correct columns are accepted.
	 */
	@Test
    public void testColCheckingSuccess() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/PerfectSudoku.txt");
		fd.readFile();
		
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
	/*
	 * Assert that boxes with duplicates are rejected properly.
	 */
	@Test
    public void testBoxCheckingFailure() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/boxesFail.txt");
		fd.readFile();
		
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
		

        //assertEquals(false, Functions.checkBoxes(checkList, dimension, puzzle.cells, puzzle.sections ));

    }
	
	/*
	 * Assert that box checking correctly accepts correct boxes.
	 */
	@Test
    public void testBoxCheckingSuccess() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/PerfectSudoku.txt");
		fd.readFile();
		
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
		

        //assertEquals(true, Functions.checkBoxes(checkList, dimension, puzzle.cells, puzzle.sections ));

    }
	
	/*
	 * Assert that a correct puzzle passes.
	 */
	@Test
	public void testPuzzleSuccess() throws Exception
	{
		SudokuFileReader fd = new SudokuFileReader("src/tests/puzzleSuccess.txt");
		fd.readFile();
		
		int height = fd.height;
		int width = fd.width;
		
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //assertEquals(true, Functions.validate(puzzle.cells, puzzle.sections, height, width ));
	
	}
	/*
	 * Assert that an incorrect puzzle fails.
	 */
	@Test
	public void testPuzzleFailure() throws Exception
	{
		SudokuFileReader fd = new SudokuFileReader("src/tests/puzzleFail.txt");
		fd.readFile();
		
		int height = fd.height;
		int width = fd.width;
		
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}

        //assertEquals(false, Functions.validate(puzzle.cells, puzzle.sections, height, width ));
	
	}
	/*
	 * Test that the file reader handles the puzzle, dimension, and comments properly.
	 */
	@Test
	public void testFileReader() throws Exception
	{
		SudokuFileReader fd = new SudokuFileReader("src/tests/test.txt");
		fd.readFile();
		
		assertEquals(3, fd.width);
		assertEquals(3, fd.height);
		assertEquals(9, fd.dimension);
		System.out.println(fd.comments.toString());
		assertEquals("This is a test comment",
				fd.comments.get(0).toString());
		//comments are the same
		//height is equal
		//width is equal
		
	}
	

	
}