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
		

        assertEquals(false, Functions.checkBoxes(checkList, dimension, puzzle.cells, puzzle.sections ));

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
		

        assertEquals(true, Functions.checkBoxes(checkList, dimension, puzzle.cells, puzzle.sections ));

    }
	
	/*
	 * Asser that a correct puzzle passes.
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

        assertEquals(true, Functions.validate(puzzle.cells, puzzle.sections, height, width ));
	
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

        assertEquals(false, Functions.validate(puzzle.cells, puzzle.sections, height, width ));
	
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
	/*
	 * Assert that BruteSolver actually increments through all numbers
	 * and handles the end of incrementing properly.
	 */
	@Test
	public void testAllPossibleAssignments() {
		ArrayList<Cell> testCells = new ArrayList<Cell>();		
		testCells.add(new Cell(0, 0));		//Make some test cells.
		testCells.add(new Cell(0, 0));
		testCells.add(new Cell(0, 0));
		
		testCells = BruteSolver.initializeValues(testCells);		//Set all the cell values to 1
		for(Cell c: testCells)
		{
			assertTrue(c.value == 1);		//Test if the values were set correctly.
		}
		
			//Test if the incrementing works as expected, with an upper bound of 2 for the values.
			
			testCells = BruteSolver.assignValues(testCells, 2);	//The first increment call
			assertTrue(testCells.get(0).value == 2);
			assertTrue(testCells.get(1).value == 1);
			assertTrue(testCells.get(2).value == 1);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 1);
			assertTrue(testCells.get(1).value == 2);
			assertTrue(testCells.get(2).value == 1);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 2);
			assertTrue(testCells.get(1).value == 2);
			assertTrue(testCells.get(2).value == 1);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 1);
			assertTrue(testCells.get(1).value == 1);
			assertTrue(testCells.get(2).value == 2);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 2);
			assertTrue(testCells.get(1).value == 1);
			assertTrue(testCells.get(2).value == 2);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 1);
			assertTrue(testCells.get(1).value == 2);
			assertTrue(testCells.get(2).value == 2);
			testCells = BruteSolver.assignValues(testCells, 2);
			assertTrue(testCells.get(0).value == 2);
			assertTrue(testCells.get(1).value == 2);
			assertTrue(testCells.get(2).value == 2);
			
			//Try to increment one more time. Since we have exhausted all combinations this should return null.
			assertTrue((testCells = BruteSolver.assignValues(testCells, 2)) == null);
			
			//Edge case: try to increment a number which cannot be incremented any further.
			testCells = new ArrayList<Cell>();	
			testCells.add(new Cell(1, 0));		//test cell has a value of 1
			//Since the cell's value is already at its maximum, trying to increment will return null
			assertTrue((testCells = BruteSolver.assignValues(testCells, 1)) == null);		
	
	}

	
}