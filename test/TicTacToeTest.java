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

public class TicTacToeTest extends TestSuite {
    private TicTacToe game;
    private TicTacToeBoard mockBoard;
    private TicTacToeAi mockAi;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        mockBoard = mock(TicTacToeBoard.class);
        mockAi = mock(TicTacToeAi.class);
        game = new TicTacToe(mockBoard, mockAi);
    }

    @Test
    public void playShouldShowTheInitialGameBoard() {
        InputStream userInput = new ByteArrayInputStream("4\n".getBytes());
        game.userInputReader = new InputStreamReader(userInput);
        when(mockBoard.putMarkInSquare('X', 4)).thenReturn(true);
        game.play();
        verify(mockBoard, atLeastOnce()).printBoard();
    }

    @Test
    public void playShouldLetTheAIMoveAfterThePlayerHasMoved() {
        InputStream userInput = new ByteArrayInputStream("4\n".getBytes());
        game.userInputReader = new InputStreamReader(userInput);
        when(mockBoard.putMarkInSquare('X', 4)).thenReturn(true);
        game.play();
        verify(mockAi, atLeastOnce()).move();
    }

    @Test
    public void getPlayerMoveShouldBeTrueWhenThePlayerEnters4() {
        InputStream userInput = new ByteArrayInputStream("4\n".getBytes());
        game.userInputReader = new InputStreamReader(userInput);
        when(mockBoard.putMarkInSquare('X', 4)).thenReturn(true);
        assertTrue(game.getValidPlayerMove());
    }

    @Test
    public void getPlayerMoveShouldReturnTrueWhenThePlayerEnters8() {
        InputStream userInput = new ByteArrayInputStream("8\n".getBytes());
        game.userInputReader = new InputStreamReader(userInput);
        when(mockBoard.putMarkInSquare('X', 8)).thenReturn(true);
        assertTrue(game.getValidPlayerMove());
    }

    @Test
    public void getPlayerMoveShouldReturnFalseWhenThePlayerEnters9() {
        InputStream userInput = new ByteArrayInputStream("9\n".getBytes());
        game.userInputReader = new InputStreamReader(userInput);
        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(game.getValidPlayerMove());
    }

    @Test
    public void getPlayerMoveShouldReturnFalseWhenThePlayerEntersAString() {
        InputStream userInput = new ByteArrayInputStream("what\n".getBytes());
        game.userInputReader = new InputStreamReader(userInput);
        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(game.getValidPlayerMove());
    }

    /*
    @Test
    public void playShouldPutAnOOnTheBoardAfterThePlayerHasEnteredAMove() {
        InputStream userInputReader = new ByteArrayInputStream("0\n".getBytes());
        game.userInputReader = new InputStreamReader(userInputReader);
        game.play();
        assertEquals("\n X | O | 2\n" +
                       " 3 | 4 | 5\n" +
                       " 6 | 7 | 8\n" +
                       "\n", outContent.toString().substring(89));
    }
    */
}
