package gooch.tictactoe;

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class MinimaxAITest extends TestSuite {
    private MiniMaxAI ai;
    private Board board;

    @Before
    public void setUp() {
        board = new Board(3);
        ai = new MiniMaxAI(board);
    }

    @Test
    public void moveShouldStartInCenterIfPossible() {
        board.putMarkInSquare('X', 0);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(4));
    }

    @Test
    public void moveShouldBlockXInARow() {
        board.putMarkInSquare('X', 0);
        board.putMarkInSquare('O', 3);
        board.putMarkInSquare('X', 1);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(2));
    }

    @Test
    public void moveShouldBlockXInAColumn() {
        board.putMarkInSquare('X', 1);
        board.putMarkInSquare('O', 0);
        board.putMarkInSquare('X', 4);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(7));
    }

    @Test
    public void moveShouldGoInSquare6() {
        board.putMarkInSquare('X', 7);
        board.putMarkInSquare('O', 1);
        board.putMarkInSquare('X', 5);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(6));
    }

    @Test
    public void moveShouldBlockXInADiagonal() {
        board.putMarkInSquare('X', 2);
        board.putMarkInSquare('O', 0);
        board.putMarkInSquare('X', 4);
        ai.move('O');
        assertEquals('O', board.getMarkInSquare(6));
    }

    @Test
    public void exhaustiveAITest() {
        BoardChecker checker = new BoardChecker(board);
        for(int i = 0; i < 9; i++){
            board.putMarkInSquare('X', i);
            ai.move('O');
            ai.move('X');
            ai.move('O');
            ai.move('X');
            ai.move('O');
            ai.move('X');
            ai.move('O');
            ai.move('X');
            assertEquals('N', checker.getWinner());
            board = new Board(3);
            ai = new MiniMaxAI(board);
        }
    }

    @Test
    public void spike() {
        board.putMarkInSquare('X', 0);
        //board.putMarkInSquare('X', 1);
        //board.putMarkInSquare('X', 2);
        //board.putMarkInSquare('X', 3);
        //board.putMarkInSquare('X', 4);
        //board.putMarkInSquare('X', 5);
        //board.putMarkInSquare('X', 6);
        //board.putMarkInSquare('X', 7);
        //board.putMarkInSquare('X', 8);
        ai.move('O');
        //System.out.println(board.asString());
        assertEquals('O', board.getMarkInSquare(4));
    }
}
