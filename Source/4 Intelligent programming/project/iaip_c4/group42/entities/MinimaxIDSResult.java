package group42.entities;

/**
 * Created by Sequenze on 13-03-2016.
 */
public class MinimaxIDSResult
{
    private int utility;
    private boolean cutOff;

    public MinimaxIDSResult(int utility, boolean cutOff)
    {
        this.utility = utility;
        this.cutOff = cutOff;
    }

    public int getUtility()
    {
        return utility;
    }

    public boolean getCutOff()
    {
        return cutOff;
    }
}
