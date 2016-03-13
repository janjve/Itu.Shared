using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.Preprocessing
{
    public static class NoiseHandler
    {
        public static List<DataTuple> RemoveAgeNoise(this List<DataTuple> dataTuples, Func<DataTuple, bool> missingPredicate, NoiseMethod removalMethod)
        {
            switch (removalMethod)
            {
                case NoiseMethod.Median:
                    var median = dataTuples.Where(x => !missingPredicate(x)).Select(x => x.Age).Median();
                    dataTuples.Where(missingPredicate).ToList().ForEach(x => x.Age.Value = median.Value);
                    break;
                default:
                    throw new NotImplementedException("No valid NoiseMethod chosen");
            }
            return dataTuples;
        }

        public enum NoiseMethod
        {
            RemoveTuple,
            UnknownValue,
            Median
        }
    }
}
