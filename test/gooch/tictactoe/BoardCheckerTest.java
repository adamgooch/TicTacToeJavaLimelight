package gooch.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class BoardCheckerTest {
    private Board board;
    private BoardChecker checker;

    @Before
    public void setUp() {
        board = new Board(3);
        checker = new BoardChecker(board);
    }

    @Test
    public void thereIsAWinnerShouldBeTrueIfXHasWonInARow() {
        board.putMarkInSquare('X', 3);
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('X', 5);
        assertTrue(checker.thereIsAWinner());
    }

    @Test
    public void thereIsAWinnerShouldBeTrueIfOHasWonInARow() {
        board.putMarkInSquare('O', 6);
        board.putMarkInSquare('O', 7);
        board.putMarkInSquare('O', 8);
        assertTrue(checker.thereIsAWinner());
    }

    @Test
    public void thereIsAWinnerShouldBeTrueIfXHasWonInAColumn() {
        board.putMarkInSquare('X', 0);
        board.putMarkInSquare('X', 3);
        board.putMarkInSquare('X', 6);
        assertTrue(checker.thereIsAWinner());
    }

    @Test
    public void thereIsAWinnerShouldBeTrueIfOHasWonInAColumn() {
        board.putMarkInSquare('O', 2);
        board.putMarkInSquare('O', 5);
        board.putMarkInSquare('O', 8);
        assertTrue(checker.thereIsAWinner());
    }

    @Test
    public void thereIsAWinnerShouldBeTrueIfXHasWonInADiagonal() {
        board.putMarkInSquare('X', 0);
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('X', 8);
        assertTrue(checker.thereIsAWinner());
    }

    @Test
    public void thereIsAWinnerShouldBeTrueIfOHasWonInADiagonal() {
        board.putMarkInSquare('O', 2);
        board.putMarkInSquare('O', 4);
        board.putMarkInSquare('O', 6);
        assertTrue(checker.thereIsAWinner());
    }

    @Test
    public void thereIsAWinnerShouldBeFalseIfNobodyHasWon() {
        board.putMarkInSquare('O', 2);
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('O', 6);
        assertFalse(checker.thereIsAWinner());
    }

    @Test
    public void getWinnerShouldReturnXWhenXHasWon() {
        board.putMarkInSquare('X', 2);
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('X', 6);
        assertEquals('X', checker.getWinner());

    }

    @Test
    public void getWinnerShouldReturnOWhenOHasWon() {
        board.putMarkInSquare('O', 0);
        board.putMarkInSquare('O', 4);
        board.putMarkInSquare('O', 8);
        assertEquals('O', checker.getWinner());

    }

    @Test
    public void getWinnerShouldReturnNWhenNobodyHasWon() {
        board.putMarkInSquare('X', 0);
        board.putMarkInSquare('O', 8);
        assertEquals('N', checker.getWinner());

    }

    @Test
    public void givesTheWinningSquaresWhenAsked() {
        board.putMarkInSquare('O', 0);
        board.putMarkInSquare('X', 1);
        board.putMarkInSquare('O', 2);
        board.putMarkInSquare('X', 3);
        board.putMarkInSquare('O', 4);
        board.putMarkInSquare('X', 5);
        board.putMarkInSquare('X', 6);
        board.putMarkInSquare('X', 7);
        board.putMarkInSquare('O', 8);
        assertTrue(checker.thereIsAWinner());
        int[] winningSquares = checker.getWinningSquares();
        assertEquals(0, winningSquares[0]);
        assertEquals(4, winningSquares[1]);
        assertEquals(8, winningSquares[2]);
    }

    @Test
    public void shouldReportIfTheBoardIsFullOrNot() {
        board.putMarkInSquare('O', 0);
        board.putMarkInSquare('X', 1);
        board.putMarkInSquare('O', 2);
        board.putMarkInSquare('X', 3);
        board.putMarkInSquare('O', 4);
        board.putMarkInSquare('X', 5);
        board.putMarkInSquare('X', 6);
        board.putMarkInSquare('X', 7);
        assertFalse(checker.boardIsFull());
        board.putMarkInSquare('O', 8);
        assertTrue(checker.boardIsFull());
    }

    @Test
    public void shouldBehaveWhenTheBoardIs4x4() {
        board = new Board(4);
        checker = new BoardChecker(board);
        assertFalse(checker.boardIsFull());
        assertFalse(checker.thereIsAWinner());
        assertEquals('N', checker.getWinner());
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('X', 5);
        board.putMarkInSquare('X', 6);
        board.putMarkInSquare('X', 7);
        assertTrue(checker.thereIsAWinner());
        assertEquals('X', checker.getWinner());
    }
}
