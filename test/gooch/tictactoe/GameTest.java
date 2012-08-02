package gooch.tictactoe;

import gooch.tictactoe.*;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Author: Adam Gooch
 * Date: 7/16/12
 */
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
        game = new Game(mockAi, mockIO, board, Game.AI_VS_AI);
    }

    @Test
    public void gameOverShouldBeFalseWhenTheGameHasJustBegun() {
        assertFalse(game.gameOver());
    }

    @Test
    public void gameOverShouldBeTrueAfter9MovesHaveBeenMade() {
        game.play();
        assertTrue(game.gameOver());
    }

    @Test
    public void playShouldDisplayTheBoard10Times() {
        game.play();
        verify(mockIO, times(10)).displayBoard();
    }

    @Test
    public void playShouldAllowXToMove5Times() {
        game.play();
        verify(mockAi, times(5)).move('X');
    }

    @Test
    public void playShouldAllowXToMove4Times() {
        game.play();
        verify(mockAi, times(4)).move('O');
    }

}
