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
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        mockAi = mock(UnbeatableAI.class);
        mockIO = mock(ConsoleIO.class);
        game = new Game(mockAi, mockIO);
    }

    @Test
    public void playShouldShowTheInitialGameBoard() {
        game.play();
        verify(mockIO, atLeastOnce()).displayBoard();
    }

    @Test
    public void playShouldLetThePlayerMove() {
        game.play();
        verify(mockIO, atLeastOnce()).getPlayerMove();
    }

    @Test
    public void playShouldLetTheAIMoveAfterThePlayerHasMoved() {
        game.play();
        verify(mockAi, atLeastOnce()).move();
    }

    @Test
    public void gameOverShouldBeFalseAfter1MoveHasBeenMade() {
        Game.movesMade = 1;
        assertFalse(game.gameOver());
    }

    @Test
    public void gameOverShouldBeTrueAfter9MovesHaveBeenMade() {
        Game.movesMade = 9;
        assertTrue(game.gameOver());
    }

}
