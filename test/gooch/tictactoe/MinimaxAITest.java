package gooch.tictactoe;

import gooch.tictactoe.Board;
import gooch.tictactoe.BoardAnalyzer;
import gooch.tictactoe.MiniMaxAI;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Author: Adam Gooch
 * Date: 7/26/12
 */
public class MinimaxAITest extends TestSuite {
    private Board board;
    private MiniMaxAI ai;

    @Before
    public void setUp() {
        board = new Board();
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
    public void exhaustiveAITest() {
        BoardAnalyzer analyzer = new BoardAnalyzer(board);
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
            assertEquals(analyzer.getWinner(), 'N');
            board = new Board();
            ai = new MiniMaxAI(board);
        }
    }
}
