package TicTacToe;

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Tank
 * Date: 7/22/12
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class IOTest extends TestSuite {
    private ConsoleIO io;
    private Board mockBoard;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        mockBoard = mock(Board.class);
        io = new ConsoleIO(mockBoard);
    }

    @Test
    public void getValidPlayerMoveShouldBeTrueWhenThePlayerEnters4() {
        createUserInput("4\n");
        when(mockBoard.putMarkInSquare('X', 4)).thenReturn(true);
        assertTrue(io.isValidPlayerMove());
    }

    @Test
    public void getValidPlayerMoveShouldReturnTrueWhenThePlayerEnters8() {
        createUserInput("8\n");
        when(mockBoard.putMarkInSquare('X', 8)).thenReturn(true);
        assertTrue(io.isValidPlayerMove());
    }

    @Test
    public void getValidPlayerMoveShouldReturnFalseWhenThePlayerEnters9() {
        createUserInput("9\n");
        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(io.isValidPlayerMove());
    }

    @Test
    public void getValidPlayerMoveShouldReturnFalseWhenThePlayerEntersAString() {
        createUserInput("what\n");
        when(mockBoard.putMarkInSquare('X', 9)).thenReturn(false);
        assertFalse(io.isValidPlayerMove());
    }

    private void createUserInput(String input) {
        InputStream pseudoUserInput = new ByteArrayInputStream(input.getBytes());
        InputStreamReader userInputReader = new InputStreamReader(pseudoUserInput);
        io.inputReader = new BufferedReader(userInputReader);
    }
}
