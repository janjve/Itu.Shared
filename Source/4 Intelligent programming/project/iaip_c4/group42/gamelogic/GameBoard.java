package group42.gamelogic;

import group42.entities.TerminalResultType;

import java.util.Arrays;

/**
 * Created by rrjan on 3/4/2016.
 */
public class GameBoard
{
    private int[][] gameboard;
    private int[] colCount;
    private final int WINNING_CONDITION = 3;

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
        type = type == TerminalResultType.NOT_FINISHED ? winConditionDiagonallyAscending() : type;
        type = type == TerminalResultType.NOT_FINISHED ? winConditionDiagonallyDescending() : type;
        type = type == TerminalResultType.NOT_FINISHED ? winConditionHorizontally() : type;
        type = type == TerminalResultType.NOT_FINISHED ? winConditionVertically() : type;
        type = type == TerminalResultType.NOT_FINISHED ? tieCondition() : type;
        return type;
    }

    public GameBoard clone()
    {
        int[][] _gameBoard = new int[gameboard.length][gameboard[0].length];
        for (int x = 0; x < gameboard.length; x++)
        {
            for (int y = 0; y < gameboard[x].length; y++)
            {
                _gameBoard[x][y] = gameboard[x][y];
            }
        }

        return new GameBoard(_gameBoard, Arrays.copyOf(colCount, colCount.length));
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

    private TerminalResultType winConditionHorizontally()
    {
        int points;

        for (int y = 0; y < gameboard[0].length; y++)
        {
            points = 0;
            for (int x = 0; x < gameboard.length; x++)
            {
                points = gameboard[x][y] != 0 && (points == 0 || gameboard[x][y] == gameboard[x - 1][y]) ? points + 1 : 1;

                if (points == WINNING_CONDITION)
                {
                    return resultTypeFromPlayerId(gameboard[x][y]);
                }
            }
        }
        return TerminalResultType.NOT_FINISHED;
    }

    private TerminalResultType winConditionVertically()
    {
        int points;

        for (int x = 0; x < gameboard.length; x++)
        {
            points = 0;
            for (int y = 0; y < gameboard[x].length; y++)
            {
                points = gameboard[x][y] != 0 && (points == 0 || gameboard[x][y] == gameboard[x][y - 1]) ? points + 1 : 1;
                if (points == WINNING_CONDITION)
                {
                    return resultTypeFromPlayerId(gameboard[x][y]);
                }
            }
        }
        return TerminalResultType.NOT_FINISHED;
    }

    private TerminalResultType winConditionDiagonallyAscending()
    {
        int points;
        int rows = this.gameboard[0].length;
        int cols = this.gameboard.length;

        //
        for (int row = 0; row < rows - (WINNING_CONDITION - 1); row++)
        {
            points = 0;
            for (int count = 0; count < cols && count+row < rows; count++)
            {
                int x = count;
                int y = row + count;
                points = gameboard[x][y] != 0 && (points == 0 || gameboard[x][y] == gameboard[x - 1][y - 1]) ? points + 1 : 1;

                if (points == WINNING_CONDITION)
                {
                    return resultTypeFromPlayerId(gameboard[x][y]);
                }
            }
        }

        for (int col = 1; col < cols - (WINNING_CONDITION - 1); col++)
        {
            points = 0;
            for (int count = 0; count+col < cols && count < rows; count++)
            {
                int x = col+count;
                int y = count;
                points = gameboard[x][y] != 0 && (points == 0 || gameboard[x][y] == gameboard[x - 1][y - 1]) ? points + 1 : 1;

                if (points == WINNING_CONDITION)
                {
                    return resultTypeFromPlayerId(gameboard[x][y]);
                }
            }
        }

        return TerminalResultType.NOT_FINISHED;
    }

    private TerminalResultType winConditionDiagonallyDescending()
    {
        int points;
        int rows = this.gameboard[0].length;
        int cols = this.gameboard.length;

        for (int row = rows-1; row >= WINNING_CONDITION-1; row--)
        {
            points = 0;
            for (int count = 0; row - count  >= 0 && count < cols; count++)
            {
                int x = count;
                int y = row - count;
                points = gameboard[x][y] != 0 && (points == 0 || gameboard[x][y] == gameboard[x - 1][y + 1]) ? points + 1 : 1;

                if (points == WINNING_CONDITION)
                {
                    return resultTypeFromPlayerId(gameboard[x][y]);
                }
            }
        }

        for (int col = 1; col < cols - (WINNING_CONDITION - 1); col++)
        {
            points = 0;
            for (int count = 0; col + count < rows && rows - 1 - count > 0; count++)
            {
                int x = col + count;
                int y = rows - 1 - count;
                points = gameboard[x][y] != 0 && (points == 0 || gameboard[x][y] == gameboard[x - 1][y + 1]) ? points + 1 : 1;

                if (points == WINNING_CONDITION)
                {
                    return resultTypeFromPlayerId(gameboard[x][y]);
                }
            }
        }

        return TerminalResultType.NOT_FINISHED;
    }

    private TerminalResultType resultTypeFromPlayerId(int playerId)
    {
        return playerId == 1 ? TerminalResultType.PLAYER1 : TerminalResultType.PLAYER2;
    }
}
