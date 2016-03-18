using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.Common
{
    [Obsolete("Unused")]
    public class FiveNumberSummary<T>
    {
        public T Min { get; set; }
        public T Q1 { get; set; }
        public T Median { get; set; }
        public T Q3 { get; set; }
        public T Max { get; set; }

        public override string ToString()
        {
            return $"[{Min}, {Q1}, {Median}, {Q3}, {Max}]";
        }
    }
}
