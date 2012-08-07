package gooch.tictactoe;

import junit.framework.TestSuite;
import org.junit.*;
import static junit.framework.Assert.*;

public class BoardTest extends TestSuite {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void asStringShouldReturnAnAsciiTicTacToeBoard() {
        assertEquals(board.asString(),
                "\n 0 | 1 | 2\n" +
                  "-----------\n" +
                  " 3 | 4 | 5\n" +
                  "-----------\n" +
                  " 6 | 7 | 8\n");
    }

    @Test
    public void putMarkInSquareShouldReturnTrueWhenXIsPutIntoSquare4() {
        assertTrue(board.putMarkInSquare('X', 4));
    }

    @Test
    public void putMarkInSquareShouldReturnFalseWhenXIsPutIntoSquare4Twice() {
        board.putMarkInSquare('X', 4);
        assertFalse(board.putMarkInSquare('X', 4));
    }

    @Test
    public void putMarkInSquareShouldReturnFalseWhenXIsPutIntoSquare4AfterOHas() {
        board.putMarkInSquare('O', 4);
        assertFalse(board.putMarkInSquare('X', 4));
    }

    @Test
    public void putMarkInSquareShouldReturnFalseWhenXIsPutIntoSquare9() {
        assertFalse(board.putMarkInSquare('X', 9));
    }

    @Test
    public void printBoardShouldHaveAnXInTheCenterAfterAnXHasMovedInSquare4() {
        board.putMarkInSquare('X', 4);
        assertEquals(board.asString(),
                "\n 0 | 1 | 2\n" +
                  "-----------\n" +
                  " 3 | X | 5\n" +
                  "-----------\n" +
                  " 6 | 7 | 8\n");
    }

    @Test
    public void printBoardShouldHaveAnXInTheCenterAndAnOInTheTopLeftSquare() {
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('O', 0);
        assertEquals(board.asString(),
                "\n O | 1 | 2\n" +
                  "-----------\n" +
                  " 3 | X | 5\n" +
                  "-----------\n" +
                  " 6 | 7 | 8\n");
    }

    @Test
    public void removeMarkInSquareShouldRemoveAnXFromSquare4() {
        board.putMarkInSquare('X', 4);
        assertEquals(board.asString(),
                      "\n 0 | 1 | 2\n" +
                        "-----------\n" +
                        " 3 | X | 5\n" +
                        "-----------\n" +
                        " 6 | 7 | 8\n");
        board.removeMarkInSquare(4);
        assertEquals(board.asString(),
                      "\n 0 | 1 | 2\n" +
                        "-----------\n" +
                        " 3 | 4 | 5\n" +
                        "-----------\n" +
                        " 6 | 7 | 8\n");
    }

    @Test
    public void getMarkInSquareShouldReturnXIfXIsInTheGivenSquare() {
        board.putMarkInSquare('X', 4);
        assertEquals('X', board.getMarkInSquare(4));
    }

    @Test
    public void getMarkInSquareShouldReturnOIfOIsInTheGivenSquare() {
        board.putMarkInSquare('O', 8);
        assertEquals('O', board.getMarkInSquare(8));
    }

    @Test
    public void getMarkInSquareShouldReturnTheSquareNumberIfTheSquareIsAvailable() {
        assertEquals('0', board.getMarkInSquare(0));
    }

    @Test
    public void cloneShouldReturnACopyOfTheBoard() {
        Board newBoard = new Board();
        newBoard = board.clone();
        for(int i = 0; i < Board.NUMBER_OF_SQUARES; i++) {
            assertEquals(board.getMarkInSquare(i), newBoard.getMarkInSquare(i));
        }
        assertFalse(board == newBoard);
    }
}
