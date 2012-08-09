package gooch.tictactoe;

import junit.framework.TestSuite;
import org.junit.*;

import java.util.ArrayList;

import static junit.framework.Assert.*;

public class BoardTest extends TestSuite {
    private Board board;

    @Before
    public void setUp() {
        board = new Board(3);
    }

    @Test
    public void asStringShouldReturnAnAsciiTicTacToeBoard() {
        assertEquals(" \n 0 | 1 | 2 \n" +
                "-----------\n" +
                " 3 | 4 | 5 \n" +
                "-----------\n" +
                " 6 | 7 | 8 \n", board.asString());

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
    public void printBoardShouldHaveAnXInTheCenterAndAnOInTheTopLeftSquare() {
        board.putMarkInSquare('X', 4);
        assertEquals('X', board.getMarkInSquare(4));
        board.putMarkInSquare('O', 0);
        assertEquals('O', board.getMarkInSquare(0));
    }

    @Test
    public void removeMarkInSquareShouldRemoveAnXFromSquare4() {
        board.putMarkInSquare('X', 4);
        assertEquals('X', board.getMarkInSquare(4));
        board.removeMarkInSquare(4);
        assertEquals(' ', board.getMarkInSquare(4));
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
    public void getMarkInSquareShouldReturnABlankCharacterIfTheSquareIsAvailable() {
        assertEquals(' ', board.getMarkInSquare(0));
    }

    @Test
    public void shouldHaveTheOpenSquaresAvailable() {
        board.putMarkInSquare('X', 0);
        board.putMarkInSquare('O', 1);
        board.putMarkInSquare('X', 2);
        board.putMarkInSquare('O', 8);
        board.putMarkInSquare('X', 5);
        board.putMarkInSquare('O', 6);
        ArrayList availableSquares = board.getAvailableSquares();
        assertTrue(availableSquares.contains(3));
        assertTrue(availableSquares.contains(4));
        assertTrue(availableSquares.contains(7));
        assertTrue(availableSquares.size() == 3);
    }

    @Test
    public void squareIsAvailableIsFalseWhenTheSquareIsTaken() {
        board.putMarkInSquare('X', 4);
        assertFalse(board.squareIsAvailable(4));
        assertTrue(board.squareIsAvailable(8));
    }

    @Test
    public void shouldReturnTheNumberOfAvailableSquares() {
        assertEquals(9, board.countSquaresAvailable());
        board.putMarkInSquare('X', 0);
        assertEquals(8, board.countSquaresAvailable());
        board.putMarkInSquare('O', 8);
        assertEquals(7, board.countSquaresAvailable());
    }

    @Test
    public void shouldGiveAnAsciiRepresentationOfA4x4TicTacToeBoard() {
        board = new Board(4);
        String asciiBoard = " \n 0 | 1 | 2 | 3 \n" +
                "---------------\n" +
                " 4 | 5 | 6 | 7 \n" +
                "---------------\n" +
                " 8 | 9 | 10| 11\n" +
                "---------------\n" +
                " 12| 13| 14| 15\n";
        assertEquals(asciiBoard, board.asString());
    }

    @Test
    public void shouldPutAGivenMarkIntoAGivenSquareOfA4x4Square() {
        board = new Board(4);
        board.putMarkInSquare('X', 14);
        String expected = " \n 0 | 1 | 2 | 3 \n" +
                "---------------\n" +
                " 4 | 5 | 6 | 7 \n" +
                "---------------\n" +
                " 8 | 9 | 10| 11\n" +
                "---------------\n" +
                " 12| 13| X | 15\n";
        assertEquals(expected, board.asString());
    }
}
