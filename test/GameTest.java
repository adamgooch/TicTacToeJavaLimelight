import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Author: Adam Gooch
 * Date: 7/16/12
 */
public class GameTest extends TestSuite {
    private Game game;
    private UnbeatableAI mockAi;
    private IO mockIO;

    @Before
    public void setUp() {
        mockAi = mock(UnbeatableAI.class);
        mockIO = mock(ConsoleIO.class);
        Board.squaresTaken = 0;
        game = new Game(mockAi, mockIO);
    }

    @Test
    public void gameOverShouldBeFalseAfter1MoveHasBeenMade() {
        Board.squaresTaken = 1;
        assertFalse(game.gameOver());
    }

    @Test
    public void gameOverShouldBeTrueAfter9MovesHaveBeenMade() {
        Board.squaresTaken = 9;
        assertTrue(game.gameOver());
    }

}
