package group42.gamelogic;

import group42.entities.TerminalResultType;

/**
 * Created by rrjan on 3/4/2016.
 */
public class GameBoard
{
    private int[][] gameboard;
    private int[] colCount;
    private final int WINNING_CONDITION = 4;

    public GameBoard(int x, int y)
    {
        gameboard = new int[x][y];
        colCount = new int[x];
    }

    private GameBoard(int[][] gameboard, int[] colCount)
    {
        this.gameboard = gameboard;
        this.colCount = colCount;
    }

    public int[][] getGameboard()
    {
        return gameboard;
    }


    public void insertCoin(int column, int playerID)
    {
        int r = gameboard[column].length - 1;
        while (r > 0 && gameboard[column][r] != 0) r--;
        gameboard[column][r] = playerID;
        this.colCount[column]++;
    }

    public TerminalResultType terminalTest()
    {
        TerminalResultType type = TerminalResultType.NOT_FINISHED;
        //type =  type == TerminalResultType.NOT_FINISHED ? winConditionDiagonallyAscending() : TerminalResultType.NOT_FINISHED;
        //type =  type == TerminalResultType.NOT_FINISHED ? winConditionDiagonallyDescending() : TerminalResultType.NOT_FINISHED;
        type = type == TerminalResultType.NOT_FINISHED ? winConditionVertically() : TerminalResultType.NOT_FINISHED;
        type = type == TerminalResultType.NOT_FINISHED ? winConditionHorizontally() : TerminalResultType.NOT_FINISHED;
        type = type == TerminalResultType.NOT_FINISHED ? tieCondition() : TerminalResultType.NOT_FINISHED;
        return type;
    }

    public GameBoard clone()
    {
        return new GameBoard(gameboard.clone(), colCount.clone());
    }

    public boolean fullColumn(int col)
    {
        return colCount[col] >= gameboard[col].length;
    }

    private TerminalResultType tieCondition()
    {
        boolean fullBoard = true;
        for (int i = 0; i < colCount.length; i++)
        {
            fullBoard &= fullColumn(i);
        }

        return fullBoard ? TerminalResultType.TIE : TerminalResultType.NOT_FINISHED;
    }

    private TerminalResultType winConditionVertically()
    {
        int count = 0;

        for (int y = 0; y < gameboard.length; y++)
        {
            count = 0;
            for (int x = 0; x < gameboard[y].length; x++)
            {
                count = gameboard[x][y] != 0 && (count == 0 || gameboard[x][y] == gameboard[x-1][y]) ? count+1 : 0;

                if(count == WINNING_CONDITION)
                {
                    return resultTypeFromPlayerId(gameboard[x][y]);
                }
            }
        }
        return TerminalResultType.NOT_FINISHED;
    }

    private TerminalResultType winConditionHorizontally()
    {
        int count = 0;

        for (int x = 0; x < gameboard.length; x++)
        {
            count = 0;
            for (int y = 0; y < gameboard[x].length; y++)
            {
                count = gameboard[x][y] != 0 && (count == 0 || gameboard[x][y] == gameboard[x][y-1]) ? count+1 : 0;

                if(count == WINNING_CONDITION)
                {
                    return resultTypeFromPlayerId(gameboard[x][y]);
                }
            }
        }
        return TerminalResultType.NOT_FINISHED;
    }

    private TerminalResultType winConditionDiagonallyDescending()
    {
        return TerminalResultType.NOT_FINISHED;
    }

    private TerminalResultType winConditionDiagonallyAscending()
    {
        return TerminalResultType.NOT_FINISHED;
    }

    private TerminalResultType resultTypeFromPlayerId(int playerId)
    {
        return playerId == 1 ? TerminalResultType.PLAYER1 : TerminalResultType.PLAYER2;
    }
}
