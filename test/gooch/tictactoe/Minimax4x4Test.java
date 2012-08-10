package gooch.tictactoe;

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Minimax4x4Test extends TestSuite {
    private MiniMaxAI ai;
    private Board board;

    @Before
    public void setUp() {
        board = new Board(Board.FOUR_X_FOUR);
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

    @Test
    public void shouldBlockXInARow() {
        board.putMarkInSquare('X', 15);
        board.putMarkInSquare('X', 14);
        board.putMarkInSquare('X', 13);
        board.putMarkInSquare('O', 5);
        board.putMarkInSquare('O', 6);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(12));
    }

    @Test
    public void shouldBlockXInAColumn() {
        board.putMarkInSquare('X', 1);
        board.putMarkInSquare('X', 5);
        board.putMarkInSquare('X', 9);
        board.putMarkInSquare('O', 10);
        board.putMarkInSquare('O', 6);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(13));
    }

}
