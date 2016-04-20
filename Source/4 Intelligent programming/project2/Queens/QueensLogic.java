/**
 * This class implements the logic behind the BDD for the n-queens problem
 * You should implement all the missing methods
 *
 * @author Stavros Amanatidis
 */

import java.util.*;

import net.sf.javabdd.*;

public class QueensLogic {
    private int sizeX = 0;
    private int sizeY = 0;
    private int[][] board;
    private final int INVALID = -1;
    private final int QUEEN = 1;
    private final int EMPTY = 0;

    private BDDFactory factory;
    private BDD nQueenBdd;
    private BDD queen;

    public QueensLogic() {

    }

    public void initializeGame(int size) {
        this.sizeX = size;
        this.sizeY = size;
        this.board = new int[sizeX][sizeY];

        factory = JFactory.init(2000000, 200000);
        factory.setVarNum(sizeX * sizeY);
        initBDD();
        updateBoard();
    }


    public int[][] getGameBoard() {
        return board;
    }

    public boolean insertQueen(int column, int row) {

        if (board[column][row] == -1 || board[column][row] == 1) {
            return true;
        }

        board[column][row] = QUEEN;

        // put some logic here..
        restrictBDD(column, row);
        updateBoard();

        return true;
    }

    private void restrictBDD(int col, int row) {
        BDD restrictQueen = factory.ithVar(col * sizeX + row);
        nQueenBdd = nQueenBdd.restrictWith(restrictQueen);
    }

    private void updateBoard() {
        List satisfyingList = nQueenBdd.allsat();

        if(satisfyingList.size() == 1){
            byte[] solution = (byte[])satisfyingList.get(0);
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++)
                {
                    if (board[x][y] != QUEEN)
                    {
                        board[x][y] = solution[x * sizeX + y] == 1 ? QUEEN : INVALID;
                    }
                }
            }
        } else{
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    if (board[x][y] != QUEEN) {
                        boolean legalMove = false;
                        for (Object object : satisfyingList) {
                            byte[] satList = (byte[]) object;
                            if (!legalMove) {
                                int index = x * sizeX + y;
                                int value = satList[index];
                                legalMove = value == 1 || value == -1;
                            } else break;
                        }
                        board[x][y] = legalMove ? EMPTY : INVALID;
                    }
                }
            }
        }
    }

    private void initBDD() {
        // vertical rules
        nQueenBdd = factory.one()
                .and(getVertical())
                .and(getHorizontal())
                .and(getDiagonallyAscendHorizontal())
                .and(getDiagonallyAscendVertical())
                .and(getDiagonallyDescendHorizontal())
                .and(getDiagonallyDescendVertical())
                ;
    }

    private BDD getVertical()
    {
        BDD vertical = null;
        for (int x = 0; x < sizeX; x++) {
            BDD col = null;
            for (int y = 0; y < sizeY; y++) {
                BDD cell = null;
                for (int c = 0; c < sizeY; c++) {
                    if (cell == null)
                        cell = c == y ? factory.ithVar(x * sizeX + c) : factory.nithVar(x * sizeX + c);
                    else
                        cell = c == y ? cell.and(factory.ithVar(x * sizeX + c)) : cell.and(factory.nithVar(x * sizeX + c));
                }
                if(col== null)
                    col = cell;
                else
                    col = col.or(cell);
            }
            if (vertical == null)
                vertical = col;
            else vertical = vertical.and(col);
        }
        return vertical;
    }

    private BDD getHorizontal()
    {
        BDD horizontal = null;
        for (int y = 0; y < sizeY; y++) {
            BDD row = null;
            for (int x = 0; x < sizeX; x++) {
                BDD cell = null;
                for (int c = 0; c < sizeX; c++) {
                    if (cell == null)
                        cell = c == x ? factory.ithVar(c * sizeX + y) : factory.nithVar(c * sizeX + y);
                    else
                        cell = c == x ? cell.and(factory.ithVar(c * sizeX + y)) : cell.and(factory.nithVar(c * sizeX + y));
                }
                if(row== null)
                    row = cell;
                else
                    row = row.or(cell);
            }
            if (horizontal == null)
                horizontal = row;
            else horizontal = horizontal.and(row);
        }
        return horizontal;
    }

    private BDD getDiagonallyDescendVertical()
    {
        BDD diagonallyDescendVertical = null;
        for (int x = 0; x < sizeY-1; x++) {
            BDD row = null;
            for (int y = x; y < sizeX*sizeX - sizeX*x; y += sizeX+1)
            {
                row = row == null ? factory.nithVar(y) : row.and(factory.nithVar(y));
            }
            for (int y = x; y < sizeX*sizeX - sizeX*x; y += sizeX+1) {

                BDD cell = null;
                for (int c = x; c < sizeX*sizeX - sizeX*x; c += sizeX+1) {
                    if (cell == null)
                    {
                        cell = c == y ? factory.ithVar(c) : factory.nithVar(c);
                        System.out.print(c);
                    }
                    else
                        cell = c == y ? cell.and(factory.ithVar(c)) : cell.and(factory.nithVar(c));
                }
                row = row.or(cell);
            }

            if (diagonallyDescendVertical == null)
                diagonallyDescendVertical = row;
            else {
                diagonallyDescendVertical = diagonallyDescendVertical.and(row);
            }
        }
        return diagonallyDescendVertical;
    }

    private BDD getDiagonallyDescendHorizontal()
    {
        BDD diagonallyDescendHorizontal = null;
        for (int x = 1; x < sizeX; x++) {
            BDD row = null;
            for (int y = sizeX*x; y < sizeX*sizeX - x; y += sizeX+1)
            {
                row = row == null ? factory.nithVar(y) : row.and(factory.nithVar(y));
            }

            for (int y = sizeX*x; y < sizeX*sizeX - x; y += sizeX+1) {
                BDD cell = null;
                for (int c = sizeX*x; c < sizeX*sizeX - x; c += sizeX+1) {
                    if (cell == null)
                        cell = c == y ? factory.ithVar(c) : factory.nithVar(c);
                    else
                        cell = c == y ? cell.and(factory.ithVar(c)) : cell.and(factory.nithVar(c));
                }
                row = row.or(cell);
            }
            if (diagonallyDescendHorizontal == null)
                diagonallyDescendHorizontal = row;
            else diagonallyDescendHorizontal = diagonallyDescendHorizontal.and(row);
        }
        return diagonallyDescendHorizontal;
    }

    private BDD getDiagonallyAscendVertical()
    {
        BDD diagonallyAscendVertical = null;
        for (int x = sizeX-1; x > 0; x--) {
            BDD row = null;
            for (int y = x; y <= sizeX*(sizeX)-sizeX*(sizeX-x); y += sizeX-1)
            {
                row = row == null ? factory.nithVar(y) : row.and(factory.nithVar(y));
            }
            for (int y = x; y <= sizeX*(sizeX)-sizeX*(sizeX-x); y += sizeX-1) {
                BDD cell = null;
                for (int c = x; c <= sizeX*(sizeX)-sizeX*(sizeX-x); c += sizeX-1) {
                    if (cell == null)
                        cell = c == y ? factory.ithVar(c) : factory.nithVar(c);
                    else
                        cell = c == y ? cell.and(factory.ithVar(c)) : cell.and(factory.nithVar(c));
                }
                row = row.or(cell);
            }
            if (diagonallyAscendVertical == null)
                diagonallyAscendVertical = row;
            else diagonallyAscendVertical = diagonallyAscendVertical.and(row);
        }
        return diagonallyAscendVertical;
    }

    private BDD getDiagonallyAscendHorizontal()
    {
        BDD diagonallyAscendHorizontal = null;
        for (int x = 1; x < sizeX-1; x++) {
            BDD row = null;
            for (int y = sizeX - 1 + sizeX*x; y <= sizeX*(sizeX-1) + x; y += sizeX-1)
            {
                row = row == null ? factory.nithVar(y) : row.and(factory.nithVar(y));
            }

            for (int y = sizeX - 1 + sizeX*x; y <= sizeX*(sizeX-1) + x; y += sizeX-1) {
                BDD cell = null;
                for (int c = sizeX - 1 + sizeX*x; c <= sizeX*(sizeX-1) + x; c += sizeX-1) {
                    if (cell == null)
                        cell = c == y ? factory.ithVar(c) : factory.nithVar(c);
                    else
                        cell = c == y ? cell.and(factory.ithVar(c)) : cell.and(factory.nithVar(c));
                }
                if (row == null)
                    row = cell;
                else
                    row = row.or(cell);
            }
            if (diagonallyAscendHorizontal == null)
                diagonallyAscendHorizontal = row;
            else diagonallyAscendHorizontal = diagonallyAscendHorizontal.and(row);
        }
        return diagonallyAscendHorizontal;
    }
}
