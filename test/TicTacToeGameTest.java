/**
* Created with IntelliJ IDEA.
* User: Tank
* Date: 7/16/12
* Time: 11:50 AM
* To change this template use File | Settings | File Templates.
*/

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

public class TicTacToeGameTest extends TestSuite {
    private TicTacToeGame game;
    private UnbeatableAI mockAi;
    private TicTacToeIO mockIO;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        mockAi = mock(UnbeatableAI.class);
        mockIO = mock(TicTacToeConsoleIO.class);
        game = new TicTacToeGame(mockAi, mockIO);
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
        TicTacToeGame.movesMade = 1;
        assertFalse(game.gameOver());
    }

    @Test
    public void gameOverShouldBeTrueAfter9MovesHaveBeenMade() {
        TicTacToeGame.movesMade = 9;
        assertTrue(game.gameOver());
    }

}
