using DataminingConsole.Processes.DataMiningSpring2016.Common;
using System;

namespace DataminingConsole.Processes.DataMiningSpring2016.Preprocessing
{
    public class DataCleaningHandler
    {
        /// <summary>
        /// Remove trailing and leading " character
        /// </summary>
        /// <param name="initial"></param>
        /// <returns></returns>
        public string StringCleaner(string initial)
        {
            return initial.StartsWith("\"") && initial.EndsWith("\"") 
                ? initial.Substring(1, initial.Length - 2) 
                : initial;
        }

        [Obsolete("Should be handled during string parsing")]
        public string AgeCleaner(string initial)
        {
            int ignored;
            if (string.IsNullOrEmpty(initial))
                return Constant.InternalString.MissingDataError;

            if (!int.TryParse(initial, out ignored))
                return Constant.InternalString.ParsingError;

            return initial;
        }
    }
}
