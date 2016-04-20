/**
 * This class implements the logic behind the BDD for the n-queens problem
 * You should implement all the missing methods
 *
 * @author Jan Vium Enghoff & Søren Harrison
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

    // Empty constructor
    public QueensLogic() {}

    // Initialize the attributes for starting a game.
    public void initializeGame(int size) {
        this.sizeX = size;
        this.sizeY = size;
        this.board = new int[sizeX][sizeY];

        factory = JFactory.init(2000000, 200000);
        factory.setVarNum(sizeX * sizeY);
        initBDD();
        updateBoard();
    }

    // Returns the game board without changing it.
    public int[][] getGameBoard() {
        return board;
    }

    // Insert the queen on the board and update the BDD.
    public boolean insertQueen(int column, int row) {
        // Return if the chosen field already contains a queen or is an invalid move.
        if (board[column][row] == -1 || board[column][row] == 1) {
            return true;
        }

        // Update the board matrix.
        board[column][row] = QUEEN;

        // Update the BDD
        restrictBDD(column, row);
        updateBoard();

        return true;
    }

    // Restrict the BDD by assigning the variable matching the field to 1.
    private void restrictBDD(int x, int y) {
        BDD restrictQueen = factory.ithVar(getCellVariableIndex(x, y));
        nQueenBdd = nQueenBdd.restrictWith(restrictQueen);
    }

    // Update the board to match the possible solutions in the BDD.
    private void updateBoard() {
        List satisfyingList = nQueenBdd.allsat();

        // Auto-fill the board if there exists only a single solution.
        if(satisfyingList.size() == 1){
            byte[] solution = (byte[])satisfyingList.get(0);
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++)
                {
                    if (board[x][y] != QUEEN)
                    {
                        board[x][y] = solution[getCellVariableIndex(x,y)] == 1 ? QUEEN : INVALID;
                    }
                }
            }
        } else // Assign a field to invalid if there does not exist a solution with a queen on it.
        {
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    if (board[x][y] != QUEEN) {
                        boolean legalMove = false;
                        for (Object object : satisfyingList) {
                            byte[] satList = (byte[]) object;
                            if (!legalMove) {
                                int value = satList[getCellVariableIndex(x,y)];
                                legalMove = value == 1 || value == -1;
                            } else break;
                        }
                        board[x][y] = legalMove ? EMPTY : INVALID;
                    }
                }
            }
        }
    }

    // Generate the initial BDD with the rules of the n-queen problem.
    private void initBDD() {
        nQueenBdd = factory.one()
                .and(getVertical())
                .and(getHorizontal())
                .and(getDiagonallyAscendHorizontal())
                .and(getDiagonallyAscendVertical())
                .and(getDiagonallyDescendHorizontal())
                .and(getDiagonallyDescendVertical());
    }

    // Generate BDD for vertical rules.
    private BDD getVertical()
    {
        BDD vertical = null;
        for (int x = 0; x < sizeX; x++) {
            BDD col = null;
            for (int y = 0; y < sizeY; y++) {
                BDD cell = null;
                for (int c = 0; c < sizeY; c++) {
                    if (cell == null)
                        cell = c == y ? factory.ithVar(getCellVariableIndex(x,c)) : factory.nithVar(getCellVariableIndex(x,c));
                    else
                        cell = c == y ? cell.and(factory.ithVar(getCellVariableIndex(x,c))) : cell.and(factory.nithVar(getCellVariableIndex(x,c)));
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

    // Generate BDD for horizontal rules.
    private BDD getHorizontal()
    {
        BDD horizontal = null;
        for (int y = 0; y < sizeY; y++) {
            BDD row = null;
            for (int x = 0; x < sizeX; x++) {
                BDD cell = null;
                for (int c = 0; c < sizeX; c++) {
                    if (cell == null)
                        cell = c == x ? factory.ithVar(getCellVariableIndex(c,y)) : factory.nithVar(getCellVariableIndex(c,y));
                    else
                        cell = c == x ? cell.and(factory.ithVar(getCellVariableIndex(c,y))) : cell.and(factory.nithVar(getCellVariableIndex(c,y)));
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

    // Generate BDD for descending direction on the vertical axis rules.
    private BDD getDiagonallyDescendVertical()
    {
        BDD diagonallyDescendVertical = null;
        for (int x = 0; x < sizeX-1; x++) {
            BDD row = null;
            // Allow diagonals to be empty
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

    // Generate BDD for descending direction on the horizontal axis rules.
    private BDD getDiagonallyDescendHorizontal()
    {
        BDD diagonallyDescendHorizontal = null;
        for (int x = 1; x < sizeX; x++) {
            BDD row = null;
            // Allow diagonals to be empty
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

    // Generate BDD for ascending direction on the vertical axis rules.
    private BDD getDiagonallyAscendVertical()
    {
        BDD diagonallyAscendVertical = null;
        for (int x = sizeX-1; x > 0; x--) {
            BDD row = null;
            // Allow diagonals to be empty
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

    // Generate BDD for ascending direction on the horizontal axis rules.
    private BDD getDiagonallyAscendHorizontal()
    {
        BDD diagonallyAscendHorizontal = null;
        for (int x = 1; x < sizeX-1; x++) {
            BDD row = null;
            // Allow diagonals to be empty
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

    // Returns the BDD variable index from a cell coordinate pair.
    private int getCellVariableIndex(int x, int y){
        return x*sizeX + y;
    }
}
