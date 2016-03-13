package group42.heuristics;

import group42.entities.MinimaxIDSResult;
import group42.entities.TerminalResultType;
import group42.gamelogic.GameBoard;

/**
 * Created by rrjan on 3/4/2016.
 * <p/>
 * Dette er en test
 */
public class MinimaxIDSDecision implements IDecisionHandler
{
    private GameBoard gameBoard;
    private int playerId;
    private int opponentPlayerId;
    private long timeStart;
    private final long timeCutOff = 9900;

    public MinimaxIDSDecision(GameBoard gameBoard, int playerId)
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
        this.timeStart = System.currentTimeMillis();
        int doneCount = 0;
        for (int depthCutOff = 1; !this.timeCutOff() && doneCount < state.getGameboard().length; depthCutOff++)
        {
            int alpha = Integer.MIN_VALUE;
            int beta = Integer.MAX_VALUE;

            doneCount = 0;
            for (int i = 0; i < state.getGameboard().length; i++)
            {
                System.out.println("Deciding next move for turn for action: " + i);
                if (!state.fullColumn(i))
                {
                    GameBoard newState = state.clone();
                    newState.insertCoin(i, playerId);
                    MinimaxIDSResult maxUtilityI = minValue(newState, 1, depthCutOff, alpha, beta);
                    if(!maxUtilityI.getCutOff()) doneCount++;

                    if (maxUtilityI.getUtility() > maxUtility)
                    {
                        maxUtility = maxUtilityI.getUtility();
                        action = i;
                        alpha = maxUtility;
                    }
                }
                else
                {
                    doneCount++;
                }
            }
        }

        return action;
    }

    private boolean timeCutOff()
    {
        long ticks = System.currentTimeMillis();
        return (ticks - this.timeStart) > this.timeCutOff;
    }

    private MinimaxIDSResult maxValue(GameBoard state, int depth, int depthCutOff,  int alpha, int beta)
    {
        if (state.terminalTest() != TerminalResultType.NOT_FINISHED ) return new MinimaxIDSResult(utility(state, depth),false);
        else if(depth >= depthCutOff || timeCutOff()) return new MinimaxIDSResult(utility(state, depth),true);
        MinimaxIDSResult maxUtility = new MinimaxIDSResult(Integer.MIN_VALUE, false);

        for (int i = 0; i < state.getGameboard().length; i++)
        {
            if (!state.fullColumn(i))
            {
                GameBoard newState = state.clone();
                newState.insertCoin(i, playerId);

                MinimaxIDSResult maxUtilityI = minValue(newState, depth + 1, depthCutOff, alpha, beta);
                maxUtility = maxUtilityI.getUtility() > maxUtility.getUtility() ? maxUtilityI : maxUtility;
            }
            if(maxUtility.getUtility() >= beta)
            {
                return maxUtility;
            }
            alpha = maxUtility.getUtility() > alpha ? maxUtility.getUtility() : alpha;
        }
        return maxUtility;
    }

    private MinimaxIDSResult minValue(GameBoard state, int depth, int depthCutOff,int alpha, int beta)
    {
        if (state.terminalTest() != TerminalResultType.NOT_FINISHED) return new MinimaxIDSResult(utility(state, depth),false);
        MinimaxIDSResult minUtility = new MinimaxIDSResult(Integer.MAX_VALUE, false);

        for (int i = 0; i < state.getGameboard().length; i++)
        {
            if (!state.fullColumn(i))
            {
                GameBoard newState = state.clone();
                newState.insertCoin(i, opponentPlayerId);

                MinimaxIDSResult minUtilityI = maxValue(newState, depth + 1, depthCutOff, alpha, beta);
                minUtility = minUtilityI.getUtility() < minUtility.getUtility() ? minUtilityI : minUtility;
            }
            if(minUtility.getUtility() <= alpha)
            {
                return minUtility;
            }
            beta = minUtility.getUtility() < beta ? minUtility.getUtility() : beta;
        }
        return minUtility;
    }

    private int utility(GameBoard state, int depth)
    {
        TerminalResultType resultType = state.terminalTest();
        int result = 0;
        switch (resultType)
        {
            case PLAYER1:
                result = playerId == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                break;
            case PLAYER2:
                result = playerId == 2 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                break;
            case TIE:
                result = 0;
                break;
            default:
                result = 0;
        }

        return result / depth;
    }
}