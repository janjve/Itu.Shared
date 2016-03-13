using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.Common
{
    public static class NumericMeasurer
    {
        public static T Median<T>(this IEnumerable<T> objects)
        {
            if (objects == null || !objects.Any())
                throw new ApplicationException("Trying to take median of empty list");
            var list = objects.OrderBy(x => x).ToList();
            return list[list.Count / 2];

        }
    }
}
