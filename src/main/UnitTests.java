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
		int dimension = fd.dimension;
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<dimension; i++){
       		assertEquals(true, Functions.checkRow(dimension, puzzle.cells, i ));
		}
    }
	
	/*
	 * Test that rows with duplicates are correctly rejected.
	 */
	@Test
    public void testRowCheckingFailure() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/RowsFail.txt");
		
		
		int dimension = fd.dimension;
		
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = true;
		
		for(int i=0;i<dimension; i++){
			if(!(Functions.checkRow(dimension, puzzle.cells, i )))
				flag = false;
		}
		
		assertEquals(false, flag);
    }
	
	/*
	 * Assert that puzzles with duplicates in the columns are correctly rejected.
	 */
	@Test
    public void testColCheckingFailure() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/ColFail.txt");
		
		
		int dimension = fd.dimension;
		
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
			puzzle.printPuzzle(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = true;
		for(int i=0;i<dimension; i++){ 
        if(!(Functions.checkCol(dimension, puzzle.cells, i )))
        		flag = false;
		}
		assertEquals(false, flag);
    }
	/*
	 * Assert that correct columns are accepted.
	 */
	@Test
    public void testColCheckingSuccess() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/PerfectSudoku.txt");
		
		int dimension = fd.dimension;
		
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<dimension; i++){
	        assertEquals(true, Functions.checkCol(dimension, puzzle.cells, i ));
			}
    }
	/*
	 * Assert that boxes with duplicates are rejected properly.
	 */
	@Test
    public void testBoxCheckingFailure() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/boxesFail.txt");
		
		
		int dimension = fd.dimension;
		
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cell[][] cells = puzzle.cells;
		
		for(int i=0;i<dimension; i++){
			for(int j=0;j<dimension;j++) {
			assertEquals(false, Functions.checkBox(dimension, cells, puzzle.sections.get(cells[i][j].boxNum)));
			}
		}
        

    }
	
	/*
	 * Assert that box checking correctly accepts correct boxes.
	 */
	@Test
    public void testBoxCheckingSuccess() throws Exception {
        
		SudokuFileReader fd = new SudokuFileReader("src/tests/PerfectSudoku.txt");
		
		
		int dimension = fd.dimension;
		
		Puzzle puzzle = null;
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cell[][] cells = puzzle.cells;
		
		for(int i=0;i<dimension; i++){
			for(int j=0;j<dimension;j++) {
			assertEquals(true, Functions.checkBox(dimension, cells, puzzle.sections.get(cells[i][j].boxNum)));
			}
		}
    }
	
	/*
	 * Assert that a correct puzzle passes.
	 */
	@Test
	public void testPuzzleSuccess() throws Exception
	{
		SudokuFileReader fd = new SudokuFileReader("src/tests/puzzleSuccess.txt");
		
		int dimension = fd.dimension;
		Puzzle puzzle = null;
		
		
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Cell[][] cells = puzzle.cells;
		
		for(int i=0;i<dimension; i++) //all the rows
			for(int j=0;j<dimension; j++) //all the columns
				assertEquals(true, Functions.validateCell(puzzle, cells[i][j]));
	
	}
	/*
	 * Assert that an incorrect puzzle fails.
	 */
	@Test
	public void testPuzzleFailure() throws Exception
	{
		SudokuFileReader fd = new SudokuFileReader("src/tests/puzzleFail.txt");
		
		int dimension = fd.dimension;
		Puzzle puzzle = null;
		
		
		try {
			puzzle = new Puzzle(fd.width, fd.height, fd.puzzle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		
		Cell[][] cells = puzzle.cells;
		boolean flag = true;

		for(int i=0;i<dimension; i++) //all the rows
			for(int j=0;j<dimension; j++) //all the columns
				if(!(Functions.validateCell(puzzle, cells[i][j])))
					flag = false;
		assertEquals(false, flag);
	
	}
	/*
	 * Test that the file reader handles the puzzle, dimension, and comments properly.
	 */
	@Test
	public void testFileReader() throws Exception
	{
		SudokuFileReader fd = new SudokuFileReader("src/tests/SudokuPuzzle.txt");
		
		
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