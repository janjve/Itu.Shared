using System;
using System.Collections.Generic;
using System.Linq;

namespace DataminingConsole.Processes.DataMiningSpring2016.Common
{
    public static class DebugStringBuilder
    {
        public static string HeaderString(string header)
        {
            const string divider = "=============================================";
            return $"{divider}{Environment.NewLine}{header}{Environment.NewLine}{divider}{Environment.NewLine}";
        }

        public static string CsvArrayToString(this List<List<string>> results)
        {
            var output = "";
            foreach (var tuple in results)
            {
                output += $"{Environment.NewLine}[DEBUG]\t ";
                output = tuple.Aggregate(output, (current, cell) => current + string.Format("{0} | ", cell));
            }
            return output;
        }
    }
}
