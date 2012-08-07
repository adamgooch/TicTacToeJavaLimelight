package gooch.tictactoe;

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class MinimaxAITest extends TestSuite {
    private MiniMaxAI ai;


    @Before
    public void setUp() {
        GameMaker.game = mock(Game.class);
        ai = new MiniMaxAI();
        GameMaker.board = new Board();
    }

    @Test
    public void moveShouldStartInCenterIfPossible() {
        GameMaker.board.putMarkInSquare('X', 0);
        ai.move('O');
        assertEquals('O', GameMaker.board.getMarkInSquare(4));
    }

    @Test
    public void moveShouldBlockXInARow() {
        GameMaker.board.putMarkInSquare('X', 0);
        GameMaker.board.putMarkInSquare('O', 3);
        GameMaker.board.putMarkInSquare('X', 1);
        ai.move('O');
        assertEquals('O', GameMaker.board.getMarkInSquare(2));
    }

    @Test
    public void moveShouldBlockXInAColumn() {
        GameMaker.board.putMarkInSquare('X', 1);
        GameMaker.board.putMarkInSquare('O', 0);
        GameMaker.board.putMarkInSquare('X', 4);
        ai.move('O');
        assertEquals('O', GameMaker.board.getMarkInSquare(7));
    }

    @Test
    public void moveShouldGoInSquare6() {
        GameMaker.board.putMarkInSquare('X', 7);
        GameMaker.board.putMarkInSquare('O', 1);
        GameMaker.board.putMarkInSquare('X', 5);
        ai.move('O');
        assertEquals('O', GameMaker.board.getMarkInSquare(6));
    }

    @Test
    public void moveShouldBlockXInADiagonal() {
        GameMaker.board.putMarkInSquare('X', 2);
        GameMaker.board.putMarkInSquare('O', 0);
        GameMaker.board.putMarkInSquare('X', 4);
        ai.move('O');
        assertEquals('O', GameMaker.board.getMarkInSquare(6));
    }

    @Test
    public void exhaustiveAITest() {
        NineSquareChecker checker = new NineSquareChecker(GameMaker.board);
        for(int i = 0; i < 9; i++){
            GameMaker.board.putMarkInSquare('X', i);
            ai.move('O');
            ai.move('X');
            ai.move('O');
            ai.move('X');
            ai.move('O');
            ai.move('X');
            ai.move('O');
            ai.move('X');
            assertEquals(checker.getWinner(), 'N');
            ai = new MiniMaxAI();
            GameMaker.board = new Board();
        }
    }
}
