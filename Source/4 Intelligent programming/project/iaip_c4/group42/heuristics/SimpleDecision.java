package group42.heuristics;

import group42.gamelogic.GameBoard;

/**
 * Created by rrjan on 3/4/2016.
 */
public class SimpleDecision implements IDecisionHandler
{
    private GameBoard gameBoard;
    private int playerId;

    public SimpleDecision(GameBoard gameBoard, int playerId)
    {
        this.gameBoard = gameBoard;
        this.playerId = playerId;
    }

    @Override
    public int decideNextMove()
    {
        int[][] currentBoard = gameBoard.getGameboard();
        for (int i = 0; i < currentBoard.length; i++)
        {
            int r = currentBoard[i].length - 1;
            while (r >= 0 && currentBoard[i][r] != 0) r--;
            if (r >= 0)
            {
                return i;
            }
        }

        return -1;
    }
}
