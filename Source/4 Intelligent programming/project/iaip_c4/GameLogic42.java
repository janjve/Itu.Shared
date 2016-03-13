import group42.entities.TerminalResultType;
import group42.gamelogic.*;
import group42.heuristics.*;

public class GameLogic42 implements IGameLogic
{
    private int x = 0;
    private int y = 0;
    private int playerID;

    private GameBoard gameBoard;
    private IDecisionHandler decisionHandler;

    public GameLogic42()
    {

    }

    public void initializeGame(int x, int y, int playerID)
    {
        this.x = x;
        this.y = y;
        this.playerID = playerID;
        this.gameBoard = new GameBoard(x, y);
        this.decisionHandler = new MinimaxIDSDecision(gameBoard, playerID);
    }

    public Winner gameFinished()
    {
        TerminalResultType terminalResultType = gameBoard.terminalTest();
        switch(terminalResultType){
            case PLAYER1:
                return Winner.PLAYER1;
            case PLAYER2:
                return Winner.PLAYER2;
            case TIE:
                return Winner.TIE;
            default:
                return Winner.NOT_FINISHED;
        }
    }


    public void insertCoin(int column, int playerID)
    {
        gameBoard.insertCoin(column, playerID);
    }

    public int decideNextMove()
    {
        return decisionHandler.decideNextMove();
    }

}

