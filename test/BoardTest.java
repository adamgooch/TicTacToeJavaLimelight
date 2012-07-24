import junit.framework.TestSuite;
import org.junit.*;
import static junit.framework.Assert.*;

/**
 * Author: Adam Gooch
 * Date: 7/17/12
 */
public class BoardTest extends TestSuite {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void printBoardShouldReturnAnAsciiTicTacToeBoard() {
        assertEquals(board.asString(),
                "\n 0 | 1 | 2\n" +
                  " 3 | 4 | 5\n" +
                  " 6 | 7 | 8\n\n");
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
                  " 3 | X | 5\n" +
                  " 6 | 7 | 8\n\n");
    }

    @Test
    public void printBoardShouldHaveAnXInTheCenterAndAnOInTheTopLeftSquare() {
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('O', 0);
        assertEquals(board.asString(),
                "\n O | 1 | 2\n" +
                  " 3 | X | 5\n" +
                  " 6 | 7 | 8\n\n");
    }

    @Test
    public void removeMarkInSquareShouldRemoveAnXFromSquare4() {
        board.putMarkInSquare('X', 4);
        assertEquals(board.asString(),
                      "\n 0 | 1 | 2\n" +
                        " 3 | X | 5\n" +
                        " 6 | 7 | 8\n\n");
        board.removeMarkInSquare(4);
        assertEquals(board.asString(),
                      "\n 0 | 1 | 2\n" +
                        " 3 | 4 | 5\n" +
                        " 6 | 7 | 8\n\n");
    }
}