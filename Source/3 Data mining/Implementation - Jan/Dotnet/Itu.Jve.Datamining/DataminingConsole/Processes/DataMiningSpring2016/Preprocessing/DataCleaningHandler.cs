using DataminingConsole.Processes.DataMiningSpring2016.Common;

namespace DataminingConsole.Processes.DataMiningSpring2016.Preprocessing
{
    public class DataCleaningHandler
    {
        public string StringCleaner(string initial)
        {
            return initial;
        }

        public string AgeCleaner(string initial)
        {
            int ignored;
            if (string.IsNullOrEmpty(initial))
                return Constant.InternalString.MissingDataError;

            if (!int.TryParse(initial, out ignored))
                return Constant.InternalString.ParsingError;

            return initial;
        }

        public string DegreeCleaner(string initial)
        {

            return initial;
        }
    }
}
