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
        board.winner = false;
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
    public void winnerShouldReturnTrueWhenXHasWonInARow() {
        for(int i = 0; i < board.BOARD_DIMENSION; i++){
            board.putMarkInSquare('X', i);
        }
        assertTrue(board.winner);
    }

    @Test
    public void winnerShouldReturnTrueWhenOHasWonInAColumn() {
        board.putMarkInSquare('O', 1);
        board.putMarkInSquare('O', 4);
        board.putMarkInSquare('O', 7);
        assertTrue(board.winner);
    }

    @Test
    public void winnerShouldReturnTrueWhenXHasWonDiagonally() {
        board.putMarkInSquare('X', 0);
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('X', 8);
        assertTrue(board.winner);
    }

    @Test
    public void winnerShouldReturnTrueWhenOHasWonDiagonally() {
        board.putMarkInSquare('O', 2);
        board.putMarkInSquare('O', 4);
        board.putMarkInSquare('O', 6);
        assertTrue(board.winner);
    }

    @Test
    public void winnerShouldReturnFalseWhenXBlocksDiagonally() {
        board.putMarkInSquare('O', 2);
        board.putMarkInSquare('X', 4);
        board.putMarkInSquare('O', 6);
        assertFalse(board.winner);
    }

    @Test
    public void winnerShouldReturnFalseWhenOBlocksDiagonally() {
        board.putMarkInSquare('X', 0);
        board.putMarkInSquare('O', 4);
        board.putMarkInSquare('X', 8);
        assertFalse(board.winner);
    }
}
