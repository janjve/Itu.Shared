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

    public boolean emptyColumn(int col)
    {
        return colCount[col] == 0;
    }

    public int stateHeuristic(int player, int opponent)
    {
        int statePoints = 0;
        statePoints = stateHeuristicVertical(player, opponent);
        //statePoints += stateHeuristicVertical();
        //statePoints += stateHeuristicDiagonallyAscend();
        //statePoints += stateHeuristicDiagonallyDesc();
        return statePoints;
    }

    private int stateHeuristicVertical(int player, int opponent)
    {
        int points = 0;

        for (int col = 0; col < gameboard[0].length; col++)
        {
            int tmpPlayerPoints = 0;
            int tmpOpponentPoints = 0;
            for (int row = 0; row < gameboard.length; row++)
            {
                if (row > 0 && ((gameboard[row - 1][col] == player  && (gameboard[row][col] == player || gameboard[row][col] == 0)) ||
                        (gameboard[row - 1][col] == 0 && gameboard[row][col] == player)))
                {
                    tmpPlayerPoints++;
                    tmpOpponentPoints = 0;
                }
                else if (tmpPlayerPoints > 1)
                {
                    points += tmpPlayerPoints;
                    tmpPlayerPoints = 0;
                }
                if (row > 0 && ((gameboard[row - 1][col] == opponent  && (gameboard[row][col] == opponent || gameboard[row][col] == 0)) ||
                        (gameboard[row - 1][col] == 0 && gameboard[row][col] == opponent)))
                {
                    tmpOpponentPoints++;
                    tmpPlayerPoints = 0;
                }
                else if (tmpOpponentPoints > 1)
                {
                    points -= tmpOpponentPoints;
                    tmpOpponentPoints = 0;
                }
            }
        }

        return points;
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


        int col = 0;
        for (int row = rows - 1; row > rows - WINNING_CONDITION; row--)
        {
            points = 0;
            int maxCnt = Math.min(row + 1, cols - col);
            if (maxCnt >= WINNING_CONDITION)
            {
                for (int count = 0; count < maxCnt; count++)
                {
                    int c = col + count;
                    int r = row - count;
                    points = gameboard[c][r] != 0 && (points == 0 || gameboard[c][r] == gameboard[c - 1][r + 1]) ? points + 1 : 1;

                    if (points == WINNING_CONDITION)
                    {
                        return resultTypeFromPlayerId(gameboard[c][r]);
                    }
                }
            }
        }

        int row = rows - 1;
        for (col = 1; col <= cols - WINNING_CONDITION; col++)
        {
            points = 0;
            int maxCnt = Math.min(row + 1, cols - col);
            if (maxCnt >= WINNING_CONDITION)
            {
                for (int count = 0; count < maxCnt; count++)
                {
                    int c = col + count;
                    int r = row - count;
                    points = gameboard[c][r] != 0 && (points == 0 || gameboard[c][r] == gameboard[c - 1][r + 1]) ? points + 1 : 1;

                    if (points == WINNING_CONDITION)
                    {
                        return resultTypeFromPlayerId(gameboard[c][r]);
                    }
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


        int col = 0;
        for (int row = 0; row <= rows - WINNING_CONDITION; row++)
        {
            points = 0;
            int maxCnt = Math.min(rows - row, cols - col);
            if (maxCnt >= WINNING_CONDITION)
            {
                for (int count = 0; count < maxCnt; count++)
                {
                    int c = col + count;
                    int r = row + count;
                    points = gameboard[c][r] != 0 && (points == 0 || gameboard[c][r] == gameboard[c - 1][r - 1]) ? points + 1 : 1;

                    if (points == WINNING_CONDITION)
                    {
                        return resultTypeFromPlayerId(gameboard[c][r]);
                    }
                }
            }
        }

        int row = 0;
        for (col = 1; col <= cols - WINNING_CONDITION; col++)
        {
            points = 0;
            int maxCnt = Math.min(rows - row, cols - col);
            if (maxCnt >= WINNING_CONDITION)
            {
                for (int count = 0; count < maxCnt; count++)
                {
                    int c = col + count;
                    int r = row + count;
                    points = gameboard[c][r] != 0 && (points == 0 || gameboard[c][r] == gameboard[c - 1][r - 1]) ? points + 1 : 1;

                    if (points == WINNING_CONDITION)
                    {
                        return resultTypeFromPlayerId(gameboard[c][r]);
                    }
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
