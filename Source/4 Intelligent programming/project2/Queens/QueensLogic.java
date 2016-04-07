/**
 * This class implements the logic behind the BDD for the n-queens problem
 * You should implement all the missing methods
 *
 * @author Stavros Amanatidis
 */

import java.util.*;

import net.sf.javabdd.*;

public class QueensLogic
{
    private int sizeX = 0;
    private int sizeY = 0;
    private int[][] board;
    private final int INVALID = -1;
    private final int QUEEN = 1;
    private final int EMPTY = 0;

    private BDDFactory factory;
    private BDD nQueenBdd;
    private BDD queen;

    public QueensLogic()
    {

    }

    public void initializeGame(int size)
    {
        this.sizeX = size;
        this.sizeY = size;
        this.board = new int[sizeX][sizeY];

        factory = JFactory.init(2000000, 200000);
        factory.setVarNum(sizeX * sizeY);
        initBDD();
    }


    public int[][] getGameBoard()
    {
        return board;
    }

    public boolean insertQueen(int column, int row)
    {

        if (board[column][row] == -1 || board[column][row] == 1)
        {
            return true;
        }

        board[column][row] = QUEEN;

        // put some logic here..
        restrictBDD(column, row);
        updateBoard();

        return true;
    }

    private void restrictBDD(int col, int row)
    {
        BDD restrictQueen = factory.nithVar(col*sizeX+row);
        nQueenBdd = nQueenBdd.restrict(restrictQueen);
    }

    private void updateBoard()
    {
        List satisfyingList = nQueenBdd.allsat();

        for (int x = 0; x < sizeX; x++)
        {
            for (int y = 0; y < sizeY; y++){
                /*for(s : satisfyingList){
                    satisfyingList.get(x*sizeX+y);
                }*/
            }
        }
    }

    private void initBDD()
    {
        BDD[][] variables = new BDD[sizeX][sizeY];

        for (int x = 0; x < sizeX; x++)
        {
            for (int y = 0; y < sizeY; y++)
            {
                variables[x][y] = factory.ithVar(x * sizeX + y);
            }
        }

        nQueenBdd = variables[0][0].or(factory.nithVar(0+1).and(factory.nithVar(0+2)));
/*
        if (false)
        {
            // Horizontal rules
            BDD horizontal = null;
            for (int x = 0; x < sizeX; x++)
            {
                BDD col = null;
                for (int y = 0; y < sizeY; y++)
                {
                    BDD cell = null;
                    for (int c = 0; c < sizeY; c++)
                    {
                        if (cell == null)
                            cell = c == y ? variables[x][c] : variables[x][c]NEGATE;
                        else
                            cell = c == y ? cell.and(variables[x][c]) : cell.and(variables[x][c] NEGATE);
                    }
                    if (col == null)
                        col = cell;
                    else
                        col = col.or(cell);
                }
                if (horizontal == null)
                    horizontal = col;
                else horizontal = horizontal.and(col);
            }
            nQueenBdd = horizontal;

            BDD vertical = null;
            nQueenBdd = nQueenBdd.and(horizontal);

            BDD diagonallyAscend = null;
            nQueenBdd = nQueenBdd.and(diagonallyAscend);

            BDD diagonallyDescend = null;
            nQueenBdd = nQueenBdd.and(diagonallyDescend);
        }*/
    }



}
