import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tank
 * Date: 7/18/12
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacToeAiTest extends TestSuite {
    private TicTacToeAi ai;
    private TicTacToeBoard mockBoard;

    @Before
    public void setUp() {
        mockBoard = mock(TicTacToeBoard.class);
        ai = new TicTacToeAi(mockBoard);
    }

    @Test
    public void possibleWinShouldBeTrueWhenAICanWinInARow() {
        setupFakeBoard("012O4O678".toCharArray());
        when(mockBoard.putMarkInSquare('O', 4)).thenReturn(true);
        assertTrue(ai.possibleWin());
    }

    @Test
    public void possibleWinShouldBeTrueWhenAICanWinInAColumn() {
        setupFakeBoard("O12345O78".toCharArray());
        when(mockBoard.putMarkInSquare('O', 3)).thenReturn(true);
        assertTrue(ai.possibleWin());
    }

    @Test
    public void possibleWinShouldBeFalseWhenAICannotWin() {
        setupFakeBoard("01234O678".toCharArray());
        assertFalse(ai.possibleWin());
    }

    @Test
    public void possibleWinShouldBeTrueWhenAICanWinInADiagonal() {
        setupFakeBoard("O123O5678".toCharArray());
        when(mockBoard.putMarkInSquare('O', 8)).thenReturn(true);
        assertTrue(ai.possibleWin());
    }

    @Test
    public void possibleWinShouldBeTrueWhenAICanWinInTheOtherDiagonal() {
        setupFakeBoard("01O3O5678".toCharArray());
        when(mockBoard.putMarkInSquare('O', 6)).thenReturn(true);
        assertTrue(ai.possibleWin());
    }

    @Test
    public void possibleThreatShouldBeTrueWhenPlayerCanWinInARow() {
        setupFakeBoard("012345X7X".toCharArray());
        when(mockBoard.putMarkInSquare('O', 7)).thenReturn(true);
        assertTrue(ai.possibleThreat());
    }

    @Test
    public void possibleThreatShouldBeTrueWhenPlayerCanWinInAColumn() {
        setupFakeBoard("X12X45678".toCharArray());
        when(mockBoard.putMarkInSquare('O', 6)).thenReturn(true);
        assertTrue(ai.possibleThreat());
    }

    @Test
    public void possibleThreatShouldBeTrueWhenPlayerCanWinInADiagonal() {
        setupFakeBoard("X1234567X".toCharArray());
        when(mockBoard.putMarkInSquare('O', 4)).thenReturn(true);
        assertTrue(ai.possibleThreat());
    }

    @Test
    public void possibleThreatShouldBeTrueWhenPlayerCanWinInTheOtherDiagonal() {
        setupFakeBoard("01X345X78".toCharArray());
        when(mockBoard.putMarkInSquare('O', 4)).thenReturn(true);
        assertTrue(ai.possibleThreat());
    }

    private void setupFakeBoard(char[] board) {
        for(int i = 0; i < board.length; i++){
            when(mockBoard.getMarkInSquare(i)).thenReturn(board[i]);
        }
    }

}
