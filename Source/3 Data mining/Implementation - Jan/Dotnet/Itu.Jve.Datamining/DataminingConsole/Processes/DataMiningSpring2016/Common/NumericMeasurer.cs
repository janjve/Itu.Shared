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
            var enumerable = objects as T[] ?? objects.ToArray();
            if (objects == null || !enumerable.Any())
                throw new ApplicationException("Trying to take median of empty list");

            var list = enumerable.OrderBy(x => x).ToList();
            return list[list.Count / 2];

        }
    }
}
