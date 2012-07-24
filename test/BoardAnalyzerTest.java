import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Author: Adam Gooch
 * Date: 7/24/12
 */
public class BoardAnalyzerTest {
    private Board board;
    private BoardAnalyzer analyzer;

    @Before
    public void setUp() {
        board = new Board();
        analyzer = new BoardAnalyzer(board);
    }

    @Test
    public void squareMarkCanWinShouldBe4WhenXCanWinInSquare4() {
        board.putMarkInSquare('X', 3);
        board.putMarkInSquare('X', 5);
        assertEquals(4, analyzer.squareMarkCanWinIn('X'));
    }

    @Test
    public void squareMarkCanWinShouldBe4WhenOCanWinInSquare8() {
        board.putMarkInSquare('O', 6);
        board.putMarkInSquare('O', 7);
        assertEquals(8, analyzer.squareMarkCanWinIn('O'));
    }

    @Test
    public void squareMarkCanWinShouldBe4WhenXCanWinInSquare6() {
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('X', 2);
        assertEquals(6, analyzer.squareMarkCanWinIn('X'));
    }

    @Test
    public void numberOfPossibleWinsShouldBe0WhenNoOneCanWin() {
        board.putMarkInSquare('X', 4);
        assertEquals(0, analyzer.numberOfPossibleWins('X'));
        assertEquals(0, analyzer.numberOfPossibleWins('O'));
    }

    @Test
    public void numberOfPossibleWinsShouldBe1WhenXCanWinInARow() {
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('X', 5);
        assertEquals(1, analyzer.numberOfPossibleWins('X'));
    }

    @Test
    public void numberOfPossibleWinsShouldBe1WhenXCanWinInAColumn() {
        board.putMarkInSquare('X', 1);
        board.putMarkInSquare('X', 4);
        assertEquals(1, analyzer.numberOfPossibleWins('X'));
    }

    @Test
    public void numberOfPossibleWinsShouldBe1WhenXCanWinInADiagonal() {
        board.putMarkInSquare('X', 2);
        board.putMarkInSquare('X', 4);
        assertEquals(1, analyzer.numberOfPossibleWins('X'));
    }

    @Test
    public void numberOfPossibleWinsShouldBe2WhenXCanWinInARowAndColumn() {
        board.putMarkInSquare('X', 0);
        board.putMarkInSquare('X', 5);
        board.putMarkInSquare('X', 6);
        board.putMarkInSquare('X', 7);
        assertEquals(2, analyzer.numberOfPossibleWins('X'));
    }

    @Test
    public void numberOfPossibleWinsShouldBe1WhenXCanWin() {
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('X', 5);
        assertEquals(1, analyzer.numberOfPossibleWins('X'));
        assertEquals(0, analyzer.numberOfPossibleWins('O'));
    }

    @Test
    public void numberOfPossibleWinsShouldBe2WhenXCanWinIn2Places() {
        board.putMarkInSquare('O', 0);
        board.putMarkInSquare('X', 2);
        board.putMarkInSquare('O', 4);
        board.putMarkInSquare('X', 6);
        board.putMarkInSquare('X', 8);
        assertEquals(2, analyzer.numberOfPossibleWins('X'));
    }

    @Test
    public void numberOfPossibleWinsShouldBe1WhenOCanWinInColumn() {
        board.putMarkInSquare('O', 1);
        board.putMarkInSquare('X', 2);
        board.putMarkInSquare('O', 4);
        board.putMarkInSquare('X', 6);
        assertEquals(1, analyzer.numberOfPossibleWins('O'));
    }
}
