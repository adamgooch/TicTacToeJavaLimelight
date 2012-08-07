package gooch.tictactoe;

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

public class GameTest extends TestSuite {
    private Game game;
    private MiniMaxAI mockAi;
    private IO mockIO;
    private Board board;

    @Before
    public void setUp() {
        mockAi = mock(MiniMaxAI.class);
        mockIO = mock(ConsoleIO.class);
        board = new Board();
        NineSquareChecker checker = new NineSquareChecker(board);
        game = new Game(mockAi, mockIO, checker, PlayType.AI_VS_AI);
    }

    @Test
    public void gameOverShouldBeFalseWhenTheGameHasJustBegun() {
        assertFalse(game.gameOver());
    }

    @Test
    public void gameOverShouldBeTrueAfter9MovesHaveBeenMade() {
        game.play();
        for(int i = 0; i < 9; i++)
            game.moveGameForward();
        assertTrue(game.gameOver());
    }

}
