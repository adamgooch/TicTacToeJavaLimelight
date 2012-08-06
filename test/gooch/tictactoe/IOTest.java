package gooch.tictactoe;

import gooch.tictactoe.Board;
import gooch.tictactoe.ConsoleIO;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Author: Adam Gooch
 * Date: 7/22/12
 */
public class IOTest extends TestSuite {
    private ConsoleIO io;
    private Board mockBoard;

    @Before
    public void setUp() {
        mockBoard = mock(Board.class);
        io = new ConsoleIO(mockBoard);
    }

    @Test
    public void getValidPlayerMoveShouldBeTrueWhenThePlayerEnters4() {
        createUserInput("4\n");
        when(mockBoard.putMarkInSquare('X', 4)).thenReturn(true);
        assertTrue(io.isValidPlayerMove('X'));
    }

    @Test
    public void getValidPlayerMoveShouldReturnTrueWhenThePlayerEnters8() {
        createUserInput("8\n");
        when(mockBoard.putMarkInSquare('X', 8)).thenReturn(true);
        assertTrue(io.isValidPlayerMove('X'));
    }

    @Test
    public void getValidPlayerMoveShouldReturnFalseWhenThePlayerEnters9() {
        createUserInput("9\n");
        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(io.isValidPlayerMove('X'));
    }

    @Test
    public void getValidPlayerMoveShouldReturnFalseWhenThePlayerEntersAString() {
        createUserInput("what\n");
        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(io.isValidPlayerMove('X'));
    }

    private void createUserInput(String input) {
        InputStream pseudoUserInput = new ByteArrayInputStream(input.getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        io.inputReader = new BufferedReader(userInputReader);
    }
}
