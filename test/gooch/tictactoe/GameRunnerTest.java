package gooch.tictactoe;

import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import static org.mockito.Mockito.*;

public class GameRunnerTest extends TestSuite {
    private GameRunner gameRunner;
    private IO mockIO;
    private AI mockAI;
    private Game mockGame;

    @Before
    public void setUp() {
        mockAI = mock(MiniMaxAI.class);
        mockIO = mock(ConsoleIO.class);
        mockGame = mock(Game.class);
        mockIO.addActionListener(mockGame);
        gameRunner = new GameRunner(mockAI, mockIO, mockGame, PlayType.AI_VS_AI);
    }

    @Test
    public void playTheGameShouldDisplayTheBoardThenGetPlayerOneMove() {
        gameRunner.start();
        verify(mockIO).displayBoard();
        verify(mockAI).move('X');
    }

    @Test
    public void shouldDisplayTheBoardAfterAMoveHasBeenMade() {
        gameRunner.actionPerformed(new ActionEvent(
                mockIO, ActionEvent.ACTION_PERFORMED, "mock"));
        verify(mockIO).displayBoard();
    }

    @Test
    public void shouldCheckIfTheGameIsOverAfterAMoveHasBeenMade() {
        gameRunner.actionPerformed(new ActionEvent(
                mockIO, ActionEvent.ACTION_PERFORMED, "mock"));
        verify(mockGame).gameOver();
    }

    @Test
    public void shouldEndTheGameIfItIsOver() {
        when(mockGame.gameOver()).thenReturn(true);
        gameRunner.actionPerformed(new ActionEvent(
                mockIO, ActionEvent.ACTION_PERFORMED, "mock"));
        verify(mockGame).end();
    }

    @Test
    public void shouldGetTheNextPlayersMoveIfTheGameIsNotOver() {
        when(mockGame.gameOver()).thenReturn(false);
        gameRunner.actionPerformed(new ActionEvent(
                mockIO, ActionEvent.ACTION_PERFORMED, "mock"));
        verify(mockAI).move('O');
    }
}
