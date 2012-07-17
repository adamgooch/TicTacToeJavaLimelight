/**
* Created with IntelliJ IDEA.
* User: Tank
* Date: 7/16/12
* Time: 11:50 AM
* To change this template use File | Settings | File Templates.
*/

import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

public class TicTacToeTest extends TestSuite {
    private TicTacToe game;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
        game = new TicTacToe();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setIn(null);
    }

    @Test
    public void playShouldShowTheInitialGameBoardThenAskForThePlayerMove() {
        InputStream userInput = new ByteArrayInputStream("4\n".getBytes());
        System.setIn(userInput);
        game.play();
        assertEquals("\n 0 | 1 | 2\n" +
                       " 3 | 4 | 5\n" +
                       " 6 | 7 | 8\n" +
                       "\n" +
                       "What is your move? ", outContent.toString().substring(0,54));
    }

    @Test
    public void playShouldShowAnXInTheMiddleSquareAfterThePlayerEntersA4() {
        InputStream userInput = new ByteArrayInputStream("4\n".getBytes());
        System.setIn(userInput);
        game.play();
        assertEquals("\n 0 | 1 | 2\n" +
                       " 3 | X | 5\n" +
                       " 6 | 7 | 8\n" +
                       "\n", outContent.toString().substring(54, 89));
    }

    @Test
    public void playShouldShowAnXInTheTopLeftSquareAfterThePlayerEntersA0() {
        InputStream userInput = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(userInput);
        game.play();
        assertEquals("\n X | 1 | 2\n" +
                       " 3 | 4 | 5\n" +
                       " 6 | 7 | 8\n" +
                       "\n", outContent.toString().substring(54, 89));
    }

    @Test
    public void playShouldPutAnOOnTheBoardAfterThePlayerHasEnteredAMove() {
        InputStream userInput = new ByteArrayInputStream("0\n\r2\n".getBytes());
        System.setIn(userInput);
        game.play();
        assertEquals("\n X | O | 2\n" +
                       " 3 | 4 | 5\n" +
                       " 6 | 7 | 8\n" +
                       "\n", outContent.toString().substring(89));
    }

}
