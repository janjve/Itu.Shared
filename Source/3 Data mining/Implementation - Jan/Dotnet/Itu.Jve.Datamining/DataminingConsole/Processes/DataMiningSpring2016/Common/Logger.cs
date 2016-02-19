using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;

namespace DataminingConsole.Processes.DataMiningSpring2016.Common
{
    public static class Logger
    {
        public static void Log(List<List<string>> results, string header)
        {
            var output = Logger.HeaderString(header)
                + results.CsvArrayToString()
                + $"{Environment.NewLine}[INFO ]\tColumns: {results[0].Count}, Rows: {results.Count - 1}";
            Debug.WriteLine(output);

            LogWriter.WriteLogToFile(@"log.txt", output, false);
        }

        private static string HeaderString(string header)
        {
            const string divider = "=============================================";
            return $"{divider}{Environment.NewLine}{header}{Environment.NewLine}{divider}{Environment.NewLine}";
        }

        private static string CsvArrayToString(this List<List<string>> results)
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
