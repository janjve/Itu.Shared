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
        nQueenBdd = nQueenBdd.restrict(restrictQueen);
    }

    private void updateBoard() {
        List satisfyingList = nQueenBdd.allsat();

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (board[x][y] != QUEEN) {
                    boolean legalMove = false;
                    for (Object object : satisfyingList) {
                        byte[] satList = (byte[]) object;
                        if (!legalMove) {
                            int value = satList[x * sizeX + y];
                            legalMove = value == 1 || value == -1;
                        } else break;
                    }
                    board[x][y] = legalMove ? EMPTY : INVALID;
                    legalMove = false;
                }
            }
        }
    }

    private void initBDD() {
        //nQueenBdd = factory.ithVar(0).or(factory.nithVar(0 + 1).and(factory.nithVar(0 + 2)).and(factory.nithVar(5)));

        // Horizontal rules
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
                if (col == null)
                    col = cell;
                else
                    col = col.or(cell);
            }
            if (vertical == null)
                vertical = col;
            else vertical = vertical.and(col);
        }

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
                if (row == null)
                    row = cell;
                else
                    row = row.or(cell);
            }
            if (horizontal == null)
                horizontal = row;
            else horizontal = horizontal.and(row);
        }



        BDD diagonallyDescendVertical = null;
        for (int x = 0; x < sizeY-1; x++) {
            BDD row = null;
            for (int y = x; y < sizeX*sizeX - sizeX*x; y += sizeX+1) {
                BDD cell = null;
                for (int c = x; c < sizeX*sizeX - sizeX*x; c += sizeX+1) {
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
            if (diagonallyDescendVertical == null)
                diagonallyDescendVertical = row;
            else diagonallyDescendVertical = diagonallyDescendVertical.and(row);
        }
        //nQueenBdd = diagonallyDescendVertical;

        BDD diagonallyDescendHorizontal = null;
        for (int x = 1; x < sizeX; x++) {
            BDD row = null;
            for (int y = sizeX*x; y < sizeX*sizeX - x; y += sizeX+1) {
                BDD cell = null;
                for (int c = sizeX*x; c < sizeX*sizeX - x; c += sizeX+1) {
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
            if (diagonallyDescendHorizontal == null)
                diagonallyDescendHorizontal = row;
            else diagonallyDescendHorizontal = diagonallyDescendHorizontal.and(row);
        }


        BDD diagonallyAscendVertical = null;
        for (int x = sizeX-1; x > 0; x--) {
            BDD row = null;
            int sdf = sizeX*(sizeX-1)-sizeX*(sizeX-x);
            for (int y = x; y <= sizeX*(sizeX)-sizeX*(sizeX-x); y += sizeX-1) {
                BDD cell = null;
                for (int c = x; c <= sizeX*(sizeX)-sizeX*(sizeX-x); c += sizeX-1) {
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
            if (diagonallyAscendVertical == null)
                diagonallyAscendVertical = row;
            else diagonallyAscendVertical = diagonallyAscendVertical.and(row);
        }

        BDD diagonallyAscendHorizontal = null;
        for (int x = 1; x < sizeX-1; x++) {
            BDD row = null;
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
        nQueenBdd = factory.one();

        nQueenBdd = nQueenBdd.and(vertical);
        nQueenBdd = nQueenBdd.and(horizontal);
        nQueenBdd = nQueenBdd.and(diagonallyDescendVertical);
        nQueenBdd = nQueenBdd.and(diagonallyDescendHorizontal);
        nQueenBdd = nQueenBdd.and(diagonallyAscendVertical);
        nQueenBdd = nQueenBdd.and(diagonallyAscendHorizontal);
    }


}
/*
        BDD vertical = null;
        for (int x = 0; x < sizeX; x++) {
            BDD row = null;
            for (int y = 0; y < sizeY; y++) {
                BDD cell = null;
                for (int c = 0; c < sizeX; c++) {
                    if (cell == null)
                        cell = c == x ? factory.ithVar(c * sizeX + y) : factory.nithVar(c * sizeX + y);
                    else
                        cell = c == x ? cell.and(factory.ithVar(c * sizeX + y)) : cell.and(factory.nithVar(c * sizeX + y));
                }
                if (row == null)
                    row = cell;
                else
                    row = row.or(cell);
            }
            if (vertical == null)
                vertical = row;
            else vertical = vertical.and(row);
        }
        nQueenBdd = vertical;
* */
