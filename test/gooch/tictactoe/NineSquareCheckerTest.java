package gooch.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.*;

public class NineSquareCheckerTest {
    private Board board;
    private NineSquareChecker checker;

    @Before
    public void setUp() {
        board = new Board();
        checker = new NineSquareChecker(board);
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
        if(checker.thereIsAWinner()) {
            ArrayList<Integer> winningSquares = checker.getWinningSquares();
            assertTrue(winningSquares.contains(0));
            assertTrue(winningSquares.contains(4));
            assertTrue(winningSquares.contains(8));
        }
    }

}
