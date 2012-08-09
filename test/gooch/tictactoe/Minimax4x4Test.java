package gooch.tictactoe;

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Author: Adam Gooch
 * Date: 8/9/12
 */
public class Minimax4x4Test extends TestSuite {
    private MiniMaxAI ai;
    private Board board;

    @Before
    public void setUp() {
        board = new Board(4);
        ai = new MiniMaxAI(board);
    }

    @Test
    public void shouldTakeACenterSquareAsAFirstMove() {
        board.putMarkInSquare('X', 15);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(5));
    }

    @Test
    public void shouldTakeAnotherMiddleSquareAsASecondMove() {
        board.putMarkInSquare('X', 15);
        board.putMarkInSquare('X', 14);
        board.putMarkInSquare('O', 5);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(6));
    }
    /* This one takes some time
    @Test
    public void shouldBlockXInARow() {
        board.putMarkInSquare('X', 15);
        board.putMarkInSquare('O', 5);
        board.putMarkInSquare('X', 14);
        board.putMarkInSquare('O', 6);
        board.putMarkInSquare('X', 13);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(12));
    }
    */
}
