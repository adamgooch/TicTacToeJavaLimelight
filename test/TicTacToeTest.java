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
        createUserInput("0\n1\n2\n3\n4\n5\n6\n7\n8\n");
        for(int i = 0; i < 9; i++) {
            when(mockBoard.putMarkInSquare('X', i)).thenReturn(true);
        }
        game.play();
        verify(mockBoard, atLeastOnce()).printBoard();
    }

    @Test
    public void playShouldLetTheAIMoveAfterThePlayerHasMoved() {
        createUserInput("0\n1\n2\n3\n4\n5\n6\n7\n8\n");
        for(int i = 0; i < 9; i++) {
            when(mockBoard.putMarkInSquare('X', i)).thenReturn(true);
        }
        game.play();
        verify(mockAi, atLeastOnce()).move();
    }

    @Test
    public void getValidPlayerMoveShouldBeTrueWhenThePlayerEnters4() {
        createUserInput("4\n");
        when(mockBoard.putMarkInSquare('X', 4)).thenReturn(true);
        assertTrue(game.isValidPlayerMove());
    }

    @Test
    public void getValidPlayerMoveShouldReturnTrueWhenThePlayerEnters8() {
        createUserInput("8\n");
        when(mockBoard.putMarkInSquare('X', 8)).thenReturn(true);
        assertTrue(game.isValidPlayerMove());
    }

    @Test
    public void getValidPlayerMoveShouldReturnFalseWhenThePlayerEnters9() {
        createUserInput("9\n");
        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(game.isValidPlayerMove());
    }

    @Test
    public void getValidPlayerMoveShouldReturnFalseWhenThePlayerEntersAString() {
        createUserInput("what\n");
        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(game.isValidPlayerMove());
    }

    @Test
    public void gameOverShouldBeFalseAfter1MoveHasBeenMade() {
        createUserInput("0\n");
        when(mockBoard.putMarkInSquare('X', 0)).thenReturn(true);
        assertFalse(game.gameOver());
    }

    @Test
    public void gameOverShouldBeTrueAfter9MovesHaveBeenMade() {
        createUserInput("0\n1\n2\n3\n4\n5\n6\n7\n8\n");
        for(int i = 0; i < 9; i++){
            when(mockBoard.putMarkInSquare('X', i)).thenReturn(true);
            game.getPlayerMove();
        }
        assertTrue(game.gameOver());
    }

    @Test
    public void gameOverShouldBeTrueWhenXWinsInARow() {
        createUserInput("0\n1\n2\n");
        for(int i = 0; i < 3; i++){
            when(mockBoard.putMarkInSquare('X', i)).thenReturn(true);
            game.getPlayerMove();
        }
        when(mockBoard.winner()).thenReturn(true);
        assertTrue(game.gameOver());
    }

    private void createUserInput(String input) {
        InputStream pseudoUserInput = new ByteArrayInputStream(input.getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        game.inputReader = new BufferedReader(userInputReader);
    }
}
