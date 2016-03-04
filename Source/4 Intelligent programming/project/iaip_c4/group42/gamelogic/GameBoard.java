package group42.gamelogic;

import group42.entities.TerminalResultType;

/**
 * Created by rrjan on 3/4/2016.
 */
public class GameBoard
{
    private int[][] gameboard;

    public GameBoard(int x, int y)
    {
        gameboard = new int[x][y];
    }

    public int[][] getGameboard()
    {
        return gameboard;
    }

    public void insertCoin(int column, int playerID)
    {
        int r = gameboard[column].length - 1;
        while (gameboard[column][r] != 0) r--;
        gameboard[column][r] = playerID;
    }

    public TerminalResultType terminalTest()
    {
        return TerminalResultType.NOT_FINISHED;
    }
}
