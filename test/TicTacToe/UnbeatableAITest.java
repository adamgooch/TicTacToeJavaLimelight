package TicTacToe;

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
public class UnbeatableAITest extends TestSuite {
    private UnbeatableAI ai;
    private Board mockBoard;

    @Before
    public void setUp() {
        mockBoard = mock(Board.class);
        ai = new UnbeatableAI(mockBoard);
        Game.movesMade = 0;
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

    @Test
    public void firstMoveShouldBeInACornerIfXStartsInCenter() {
        setupFakeBoard("0123X5678".toCharArray());
        ensureNoOtherMoveWasMade();
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 0);
    }

    @Test
    public void firstMoveShouldBeInCenterIfXStartsInACorner() {
        setupFakeBoard("01X345678".toCharArray());
        ensureNoOtherMoveWasMade();
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 4);
    }

    @Test
    public void firstMoveShouldBeAdjacentToXIfXStartsInSquare7() {
        setupFakeBoard("0123456X8".toCharArray());
        ensureNoOtherMoveWasMade();
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 8);
    }

    @Test
    public void firstMoveShouldBeAdjacentToXIfXStartsSquare5() {
        setupFakeBoard("01234X678".toCharArray());
        ensureNoOtherMoveWasMade();
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 8);
    }

    @Test
    public void secondMoveShouldCreateAThreatIfNoBlockIsNecessary() {
        setupFakeBoard("01X3O5X78".toCharArray());
        ensureNoOtherMoveWasMade();
        Game.movesMade = 3;
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 1);
    }

    private void setupFakeBoard(char[] board) {
        for(int i = 0; i < board.length; i++){
            when(mockBoard.getMarkInSquare(i)).thenReturn(board[i]);
        }
    }

    private void ensureNoOtherMoveWasMade() {
        for(int i = 0; i < 9; i++){
            when(mockBoard.putMarkInSquare('O', i)).thenReturn(true);
        }
    }

}
