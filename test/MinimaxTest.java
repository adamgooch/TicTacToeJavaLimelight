import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Author: Adam Gooch
 * Date: 7/25/12
 */
public class MinimaxTest extends TestSuite {
    private Board oldBoard;

    @Before
    public void setUp() {
        oldBoard = new Board();
    }

    @Test
    public void findBestMoveTest() {
        oldBoard.putMarkInSquare('X', 0);
        oldBoard.putMarkInSquare('X', 8);
        oldBoard.putMarkInSquare('O', 4);
        Board newBoard = Minimax.findBestMove(oldBoard);
        System.out.println(oldBoard.asString());
        System.out.println(newBoard.asString());
    }

    @Test
    public void moveShouldBlockXInARow() {
        oldBoard.putMarkInSquare('X', 0);
        oldBoard.putMarkInSquare('O', 3);
        oldBoard.putMarkInSquare('X', 1);
        Board newBoard = Minimax.findBestMove(oldBoard);
        System.out.println(oldBoard.asString());
        System.out.println(newBoard.asString());

    }

    @Test
    public void moveShouldBlockXInADiagonal() {
        oldBoard.putMarkInSquare('X', 0);
        //oldBoard.putMarkInSquare('O', 1);
        //oldBoard.putMarkInSquare('X', 4);
        Board newBoard = Minimax.findBestMove(oldBoard);
        System.out.println(oldBoard.asString());
        System.out.println(newBoard.asString());
    }
}
