import java.util.ArrayList;

/**
 * Author: Adam Gooch
 * Date: 7/25/12
 */
public class Minimax {

    public static Board findBestMove(Board board) {
        ArrayList<Integer> positions = board.getAvailableSquares();
        Board bestChild = null;
        int previousScore = Integer.MIN_VALUE;
        for(Integer i : positions){
            Board child = new Board(board, i, 'O');
            int currentScore = min(child);
            System.out.println("Child Score for position " +
                i + " is : " + currentScore);
            if(currentScore > previousScore){
                bestChild = child;
                previousScore = currentScore;
            }
        }
        return bestChild;
    }

    public static int max(Board board){
        BoardAnalyzer analyzer = new BoardAnalyzer(board);
        if(analyzer.thereIsAWinner() && analyzer.winner == 'O')
            return 1;
        else if(analyzer.thereIsAWinner() && analyzer.winner == 'X')
            return -1;
        else if(board.countSquaresAvailable() == 0)
            return 0;
        ArrayList<Integer> positions = board.getAvailableSquares();
        int best = Integer.MIN_VALUE;
        for(Integer p : positions){
            Board b = new Board(board, p, 'O');
            int move = min(b);
            if(move > best)
                best = move;
        }
        return best;
    }

    public static int min(Board board){
        BoardAnalyzer analyzer = new BoardAnalyzer(board);
        if(analyzer.thereIsAWinner() && analyzer.winner == 'O')
            return 1;
        else if(analyzer.thereIsAWinner() && analyzer.winner == 'X')
            return -1;
        else if(board.countSquaresAvailable() == 0)
            return 0;
        ArrayList<Integer> positions = board.getAvailableSquares();
        int best = Integer.MAX_VALUE;
        for(Integer p : positions){
            Board b = new Board(board, p, 'X');
            int move = max(b);
            if(move < best)
                best = move;
        }
        return best;
    }
}
