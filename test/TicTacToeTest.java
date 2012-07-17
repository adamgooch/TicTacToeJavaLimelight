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
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        mockBoard = mock(TicTacToeBoard.class);
        mockAi = mock(TicTacToeAi.class);
        game = new TicTacToe(mockBoard, mockAi);
    }

    @Test
    public void playShouldShowTheInitialGameBoard() {
        InputStream pseudoUserInput = new ByteArrayInputStream("0\n1\n2\n3\n4\n5\n6\n7\n8\n".getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        game.inputReader = new BufferedReader(userInputReader);

        when(mockBoard.putMarkInSquare('X', 0)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 1)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 2)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 3)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 4)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 5)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 6)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 7)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 8)).thenReturn(true);

        game.play();
        verify(mockBoard, atLeastOnce()).printBoard();
    }

    @Test
    public void playShouldLetTheAIMoveAfterThePlayerHasMoved() {
        InputStream pseudoUserInput = new ByteArrayInputStream("0\n1\n2\n3\n4\n5\n6\n7\n8\n".getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        game.inputReader = new BufferedReader(userInputReader);

        when(mockBoard.putMarkInSquare('X', 0)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 1)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 2)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 3)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 4)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 5)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 6)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 7)).thenReturn(true);
        when(mockBoard.putMarkInSquare('X', 8)).thenReturn(true);

        game.play();
        verify(mockAi, atLeastOnce()).move();
    }

    @Test
    public void getValidPlayerMoveShouldBeTrueWhenThePlayerEnters4() {
        InputStream pseudoUserInput = new ByteArrayInputStream("4\n".getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        game.inputReader = new BufferedReader(userInputReader);

        when(mockBoard.putMarkInSquare('X', 4)).thenReturn(true);
        assertTrue(game.getValidPlayerMove());
    }

    @Test
    public void getValidPlayerMoveShouldReturnTrueWhenThePlayerEnters8() {
        InputStream pseudoUserInput = new ByteArrayInputStream("8\n".getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        game.inputReader = new BufferedReader(userInputReader);

        when(mockBoard.putMarkInSquare('X', 8)).thenReturn(true);
        assertTrue(game.getValidPlayerMove());
    }

    @Test
    public void getValidPlayerMoveShouldReturnFalseWhenThePlayerEnters9() {
        InputStream pseudoUserInput = new ByteArrayInputStream("9\n".getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        game.inputReader = new BufferedReader(userInputReader);

        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(game.getValidPlayerMove());
    }

    @Test
    public void getValidPlayerMoveShouldReturnFalseWhenThePlayerEntersAString() {
        InputStream pseudoUserInput = new ByteArrayInputStream("what\n".getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        game.inputReader = new BufferedReader(userInputReader);

        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(game.getValidPlayerMove());
    }

    @Test
    public void gameOverShouldBeFalseAfter1MoveHasBeenMade() {
        InputStream pseudoUserInput = new ByteArrayInputStream("0\n".getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        game.inputReader = new BufferedReader(userInputReader);

        when(mockBoard.putMarkInSquare('X', 0)).thenReturn(true);
        assertFalse(game.gameOver());
    }

    @Test
    public void gameOverShouldBeTrueAfter9MovesHaveBeenMade() {
        InputStream pseudoUserInput;
        String testData;
        for(int i = 0; i < 9; i++){
            testData = Integer.toString(i);
            pseudoUserInput = new ByteArrayInputStream(testData.getBytes());
            InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
            game.inputReader = new BufferedReader(userInputReader);

            when(mockBoard.putMarkInSquare('X', i)).thenReturn(true);
            game.getPlayerMove();
        }
        assertTrue(game.gameOver());
    }
}
