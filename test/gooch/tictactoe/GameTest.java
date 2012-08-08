package gooch.tictactoe;

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameTest extends TestSuite {

    private Game game;
    private IO mockIO;

    @Before
    public void setUp() {
        Board mockBoard = mock(Board.class);
        mockIO = mock(IO.class);
        game = new Game(mockIO, mockBoard);
        game.checker = mock(BoardChecker.class);
    }

    @Test
    public void shouldGetTheDesiredPlayTypeAsAGameIsCreated() {
        verify(mockIO).getPlayType();
    }

    @Test
    public void shouldBeOverWhenTheBoardIsFull() {
        when(game.checker.boardIsFull()).thenReturn(true);
        assertTrue(game.gameOver());
    }

    @Test
    public void shouldBeOverWhenAPlayerWins() {
        when(game.checker.thereIsAWinner()).thenReturn(true);
        assertTrue(game.gameOver());
    }

    @Test
    public void shouldNotBeOverWhenTheBoardIsNotFullAndNoOneHasWon() {
        when(game.checker.boardIsFull()).thenReturn(false);
        when(game.checker.thereIsAWinner()).thenReturn(false);
        assertFalse(game.gameOver());
    }

    @Test
    public void shouldDisplayAMessageWhenItHasEnded() {
        game.end();
        verify(mockIO).displayMessage(Game.NOBODY_WINS);
    }

    @Test
    public void shouldPlayAnAudioMessageWhenItHasEnded() {
        game.end();
        verify(mockIO).playAudioMessage(Game.NOBODY_WINS);
    }

    @Test
    public void shouldHighlightWinningSquaresWhenItHasEnded() {
        game.end();
        verify(mockIO).highlightWin(new ArrayList());
    }
}
