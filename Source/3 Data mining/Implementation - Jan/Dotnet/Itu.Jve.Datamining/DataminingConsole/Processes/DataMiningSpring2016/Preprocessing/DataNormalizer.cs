using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.Preprocessing
{
    public static class DataNormalizer
    {
        /// <summary>
        /// Will always perform min-max normalization in the range [0...1]
        /// </summary>
        /// <param name="dataTuples">The set of datatuples to normalize</param>
        /// <returns>The datatuples with normalization calculated. This will retain the object reference</returns>
        public static List<DataTuple> MinMaxNormalizeDataTuple(this List<DataTuple> dataTuples)
        {
            var ageMin = float.MaxValue;
            var ageMax = float.MinValue;

            dataTuples.ForEach(x =>
            {
                ageMin = x.Age.Value < ageMin ? x.Age.Value : ageMin;
                ageMax = x.Age.Value > ageMax ? x.Age.Value : ageMax;
            });
            if(Math.Abs(ageMax - ageMin) < 0.1f) // All values are the same.
                dataTuples.ForEach(x => x.Age.NormalizedValue = 1); 
            else
                dataTuples.ForEach(x => x.Age.NormalizedValue = (x.Age.Value - ageMin) / (ageMax - ageMin));

            return dataTuples;
        }
    }
}
