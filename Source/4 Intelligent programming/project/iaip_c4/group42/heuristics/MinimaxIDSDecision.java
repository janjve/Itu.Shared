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
    private final long timeCutOff = 10000;

    public MinimaxIDSDecision(GameBoard gameBoard, int playerId)
    {
        this.gameBoard = gameBoard;
        this.playerId = playerId;
        this.opponentPlayerId = playerId == 1 ? 2 : 1;
    }

    @Override
    public int decideNextMove()
    {
        System.out.println("STARTED DECISION SEARCH");

        GameBoard state = gameBoard;
        int maxUtility = Integer.MIN_VALUE;
        int action = -1;
        int tempAction = -1;

        this.timeStart = System.currentTimeMillis();
        int doneCount = 0;
        int start = 0;
        int end = state.getGameboard().length;

        //IDS, starting at depth 1. CutOff by either time or solution found.
        for (int depthCutOff = 1; !this.timeCutOff() && doneCount < (end+1 - start); depthCutOff++)
        {
            System.out.println("SEARCHING DEPTH: " + depthCutOff);

            int alpha = Integer.MIN_VALUE;
            int beta = Integer.MAX_VALUE;

            doneCount = 0;
            // Preanalysis:
            // Reduction function, reducing the number of possible actions.
            // Will drop in center if the board is empty.
            start = (state.getGameboard().length) / 2;
            end = (state.getGameboard().length) / 2;
            for (int idx = 1; idx <= state.getGameboard().length - 1; idx++)
            {
                if (!(state.emptyColumn(idx - 1) && state.emptyColumn(idx)))
                {
                    start = idx - 1;
                    for (int idx2 = state.getGameboard().length - 1; idx2 >= start; idx2--)
                    {
                        if (!(state.emptyColumn(idx2 - 1) && state.emptyColumn(idx2)))
                        {
                            end = idx2;
                            break;
                        }
                    }

                    break;
                }
            }

            //Iterating through all actions.
            for (int i = 0; i < state.getGameboard().length; i++)
            {
                //Skip action if full column
                if (!state.fullColumn(i))
                {
                    //Perform action
                    state.insertCoin(i, playerId);
                    //Starting minimax
                    MinimaxIDSResult maxUtilityI = minValue(state, 1, depthCutOff, alpha, beta);
                    //Reverse action
                    state.removeCoin(i);
                    if (!maxUtilityI.getCutOff()) doneCount++;

                    if (maxUtilityI.getUtility() > maxUtility)
                    {
                        maxUtility = maxUtilityI.getUtility();
                        tempAction = i;
                        alpha = maxUtility;
                    }
                } else
                {
                    doneCount++;
                }
            }
            // Take the newest value if a timecutoff didn't occur.
            if(!this.timeCutOff())
            {
                action = tempAction;
                maxUtility = Integer.MIN_VALUE;
            }
        }
        System.out.println("picked action: " + action);

        return action;
    }

    private boolean timeCutOff()
    {
        long ticks = System.currentTimeMillis();
        return (ticks - this.timeStart) > this.timeCutOff;
    }

    private MinimaxIDSResult maxValue(GameBoard state, int depth, int depthCutOff, int alpha, int beta)
    {
        // Checks for terminal state or cutoff.
        TerminalResultType result = state.terminalTest();
        if (result != TerminalResultType.NOT_FINISHED)
            return new MinimaxIDSResult(utility(result, state, depth, this.playerId, this.opponentPlayerId), false);
        else if (depth >= depthCutOff || timeCutOff())
            return new MinimaxIDSResult(utility(result, state, depth, this.playerId, this.opponentPlayerId), true);
        MinimaxIDSResult maxUtility = new MinimaxIDSResult(Integer.MIN_VALUE, false);

        // Maxes minimax iteration
        for (int i = 0; i < state.getGameboard().length; i++)
        {
            if (!state.fullColumn(i))
            {
                state.insertCoin(i, playerId);
                MinimaxIDSResult maxUtilityI = minValue(state, depth + 1, depthCutOff, alpha, beta);
                state.removeCoin(i);
                maxUtility = maxUtilityI.getUtility() > maxUtility.getUtility() ? maxUtilityI : maxUtility;
            }
            if (maxUtility.getUtility() >= beta)
            {
                return maxUtility;
            }
            alpha = maxUtility.getUtility() > alpha ? maxUtility.getUtility() : alpha;
        }
        return maxUtility;
    }

    private MinimaxIDSResult minValue(GameBoard state, int depth, int depthCutOff, int alpha, int beta)
    {
        // Checks for terminal state or cutoff.
        TerminalResultType result = state.terminalTest();
        if (result != TerminalResultType.NOT_FINISHED)
            return new MinimaxIDSResult(utility(result, state, depth, this.opponentPlayerId, this.playerId), false);
        MinimaxIDSResult minUtility = new MinimaxIDSResult(Integer.MAX_VALUE, false);

        // Mins minimax iteration
        for (int i = 0; i < state.getGameboard().length; i++)
        {
            if (!state.fullColumn(i))
            {
                state.insertCoin(i, opponentPlayerId);

                MinimaxIDSResult minUtilityI = maxValue(state, depth + 1, depthCutOff, alpha, beta);
                state.removeCoin(i);
                minUtility = minUtilityI.getUtility() < minUtility.getUtility() ? minUtilityI : minUtility;
            }
            if (minUtility.getUtility() <= alpha)
            {
                return minUtility;
            }
            beta = minUtility.getUtility() < beta ? minUtility.getUtility() : beta;
        }
        return minUtility;
    }

    //Heuristic
    private int utility(TerminalResultType resultType, GameBoard state, int depth, int player, int opponent)
    {
        int result = 0;
        switch (resultType)
        {
            case PLAYER1:
                result = playerId == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                result /= depth;
                break;
            case PLAYER2:
                result = playerId == 2 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                result /= depth;
                break;
            case TIE:
                result = 0;
                break;
            default:
                result = state.stateHeuristic(player, opponent);
        }
        return result;
    }
}
