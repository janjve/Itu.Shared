import group42.entities.TerminalResultType;
import group42.gamelogic.GameBoard;
import group42.heuristics.IDecisionHandler;
import group42.heuristics.MinimaxIDSDecision;

public class GameLogic42IDSAB implements IGameLogic
{
    private int x = 0;
    private int y = 0;
    private int playerID;

    private GameBoard gameBoard;
    private IDecisionHandler decisionHandler;

    public GameLogic42IDSAB()
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

    public IGameLogic.Winner gameFinished()
    {
        TerminalResultType terminalResultType = gameBoard.terminalTest();
        switch(terminalResultType){
            case PLAYER1:
                return IGameLogic.Winner.PLAYER1;
            case PLAYER2:
                return IGameLogic.Winner.PLAYER2;
            case TIE:
                return IGameLogic.Winner.TIE;
            default:
                return IGameLogic.Winner.NOT_FINISHED;
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

