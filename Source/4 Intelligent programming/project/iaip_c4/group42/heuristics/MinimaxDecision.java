package group42.heuristics;

import group42.entities.TerminalResultType;
import group42.gamelogic.GameBoard;

/**
 * Created by rrjan on 3/4/2016.
 * <p/>
 * Dette er en test
 */
public class MinimaxDecision implements IDecisionHandler
{
    private GameBoard gameBoard;
    private int playerId;
    private int opponentPlayerId;

    public MinimaxDecision(GameBoard gameBoard, int playerId)
    {
        this.gameBoard = gameBoard;
        this.playerId = playerId;
        this.opponentPlayerId = playerId == 1 ? 2 : 1;
    }

    @Override
    public int decideNextMove()
    {

        GameBoard state = gameBoard;
        int maxUtility = Integer.MIN_VALUE;
        int action = -1;

        for (int i = 0; i < state.getGameboard().length; i++)
        {
            System.out.println("Deciding next move for turn for action: " + i);
            if (!state.fullColumn(i))
            {
                GameBoard newState = state.clone();
                newState.insertCoin(i, playerId);

                int maxUtilityI = minValue(newState, 1);
                if (maxUtilityI > maxUtility)
                {
                    maxUtility = maxUtilityI;
                    action = i;
                }
            }
        }
        return action;
    }

    private int maxValue(GameBoard state, int depth)
    {
        if (state.terminalTest() != TerminalResultType.NOT_FINISHED) return utility(state) / depth;
        int maxUtility = Integer.MIN_VALUE;

        for (int i = 0; i < state.getGameboard().length; i++)
        {
            if (!state.fullColumn(i))
            {
                GameBoard newState = state.clone();
                newState.insertCoin(i, playerId);

                int maxUtilityI = minValue(newState, depth + 1);
                maxUtility = maxUtilityI > maxUtility ? maxUtilityI : maxUtility;
            }
        }
        return maxUtility;
    }

    private int minValue(GameBoard state, int depth)
    {
        if (state.terminalTest() != TerminalResultType.NOT_FINISHED) return utility(state) / depth;
        int minUtility = Integer.MAX_VALUE;

        for (int i = 0; i < state.getGameboard().length; i++)
        {
            if (!state.fullColumn(i))
            {
                GameBoard newState = state.clone();
                newState.insertCoin(i, opponentPlayerId);

                int minUtilityI = maxValue(newState, depth + 1);
                minUtility = minUtilityI < minUtility ? minUtilityI : minUtility;
            }
        }
        return minUtility;
    }

    private int utility(GameBoard state)
    {
        TerminalResultType resultType = state.terminalTest();
        switch (resultType)
        {
            case PLAYER1:
                return playerId == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            case PLAYER2:
                return playerId == 2 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            case TIE:
                return 0;
            default:
                return 0;
        }
    }
}
