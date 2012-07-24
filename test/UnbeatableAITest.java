import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Author: Adam Gooch
 * Date: 7/18/12
 */
public class UnbeatableAITest extends TestSuite {
    private UnbeatableAI ai;
    private Board mockBoard;

    @Before
    public void setUp() {
        mockBoard = mock(Board.class);
        ai = new UnbeatableAI(mockBoard);
        Board.squaresTaken = 0;
    }

    @Test
    public void firstMoveShouldBeInACornerIfXStartsInCenter() {
        setupFakeBoard("0123X5678".toCharArray());
        allowOToMoveInAnySquare();
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 0);
    }

    @Test
    public void firstMoveShouldBeInCenterIfXStartsInACorner() {
        setupFakeBoard("01X345678".toCharArray());
        allowOToMoveInAnySquare();
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 4);
    }
    /* I don't think I need these any more with the new algorithm
    @Test
    public void firstMoveShouldBeAdjacentToXIfXStartsInSquare7() {
        setupFakeBoard("0123456X8".toCharArray());
        allowOToMoveInAnySquare();
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 8);
    }

    @Test
    public void firstMoveShouldBeAdjacentToXIfXStartsSquare5() {
        setupFakeBoard("01234X678".toCharArray());
        allowOToMoveInAnySquare();
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 8);
    }
    */
    @Test
    public void moveShouldWinIfPossible() {
        setupFakeBoard("0123OO678".toCharArray());
        allowOToMoveInAnySquare();
        Board.squaresTaken = 2;
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 3);
    }

    @Test
    public void moveShouldBlockXWinIfOneExists() {
        setupFakeBoard("X123X5678".toCharArray());
        allowOToMoveInAnySquare();
        Board.squaresTaken = 2;
        ai.move();
        verify(mockBoard).putMarkInSquare('O', 8);
    }

    @Test
    public void nextPlayerMoveForcesLossShouldBeFalse() {
        setupFakeBoard("01234X678".toCharArray());
        allowOToMoveInAnySquare();
        assertFalse(ai.nextPlayerMoveForcesLoss());
    }

    @Test
    public void nextPlayerMoveForcesLossShouldBeTrueIfXCanWin2WaysWithNextMove() {
        Board realBoard = new Board();
        ai = new UnbeatableAI(realBoard);
        realBoard.putMarkInSquare('X', 0);
        realBoard.putMarkInSquare('O', 1);
        realBoard.putMarkInSquare('O', 2);
        realBoard.putMarkInSquare('O', 4);
        realBoard.putMarkInSquare('X', 5);
        realBoard.putMarkInSquare('X', 7);
        assertTrue(ai.nextPlayerMoveForcesLoss());
    }

    @Test
    public void moveShouldGoInSquare6IfThatSquareCauses2WinScenarioForX() {
        Board realBoard = new Board();
        ai = new UnbeatableAI(realBoard);
        realBoard.putMarkInSquare('X', 0);
        realBoard.putMarkInSquare('O', 1);
        realBoard.putMarkInSquare('O', 4);
        realBoard.putMarkInSquare('X', 5);
        realBoard.putMarkInSquare('X', 7);
        ai.move();
        assertEquals('O', realBoard.getMarkInSquare(6));
    }

    @Test
    public void moveShouldGoInSquare1IfXHasTakenSquares2and6() {
        Board realBoard = new Board();
        ai = new UnbeatableAI(realBoard);
        realBoard.putMarkInSquare('X', 2);
        realBoard.putMarkInSquare('O', 4);
        realBoard.putMarkInSquare('X', 6);
        ai.move();
        assertEquals(4, Board.squaresTaken);
        assertEquals('O', realBoard.getMarkInSquare(1));
    }

    @Test
    public void playerBlockDoesNotForceLossShouldBeTrueWhenXBlocksAndCannotWin() {
        Board realBoard = new Board();
        ai = new UnbeatableAI(realBoard);
        realBoard.putMarkInSquare('O', 1);
        realBoard.putMarkInSquare('X', 2);
        realBoard.putMarkInSquare('O', 4);
        realBoard.putMarkInSquare('X', 6);
        assertTrue(ai.playerBlockDoesNotForceLoss());
    }

    @Test
    public void playerBlockDoesNotForceLossShouldBeFalseWhenXBlocksAndEnsuresWin() {
        Board realBoard = new Board();
        ai = new UnbeatableAI(realBoard);
        realBoard.putMarkInSquare('O', 0);
        realBoard.putMarkInSquare('X', 2);
        realBoard.putMarkInSquare('O', 4);
        realBoard.putMarkInSquare('X', 6);
        assertFalse(ai.playerBlockDoesNotForceLoss());
    }

    @Test
    public void only4MovesShouldBeMade() {
        Board realBoard = new Board();
        ai = new UnbeatableAI(realBoard);
        realBoard.putMarkInSquare('X', 7);
        realBoard.putMarkInSquare('O', 8);
        realBoard.putMarkInSquare('X', 5);
        ai.move();
        assertEquals(4, Board.squaresTaken);
    }

    private void setupFakeBoard(char[] board) {
        for(int i = 0; i < board.length; i++){
            when(mockBoard.getMarkInSquare(i)).thenReturn(board[i]);
        }
    }

    private void allowOToMoveInAnySquare() {
        for(int i = 0; i < 9; i++){
            when(mockBoard.putMarkInSquare('O', i)).thenReturn(true);
        }
    }
}
