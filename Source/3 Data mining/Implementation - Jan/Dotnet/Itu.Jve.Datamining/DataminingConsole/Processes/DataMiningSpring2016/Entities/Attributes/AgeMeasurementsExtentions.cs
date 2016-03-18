using System;
using System.Collections.Generic;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Common;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes
{
    [Obsolete("Unused")]
    public static class AgeMeasurementsExtentions
    {
        #region Central tendency measures
        public static double? Mean(this List<AgeAttribute> ageAttributes)
        {
            if (ageAttributes == null || !ageAttributes.Any())
                return null;
            return ageAttributes.Sum(x => x.Value) / (double)ageAttributes.Count;
        }

        public static int? Median(this List<AgeAttribute> ageAttributes)
        {
            if (ageAttributes == null || !ageAttributes.Any())
                return null;

            return ageAttributes.OrderBy(x => x.Value).ToList()[ageAttributes.Count / 2].Value;
        }

        public static int? Mode(this List<AgeAttribute> ageAttributes)
        {
            if (ageAttributes == null || !ageAttributes.Any())
                return null;

            var ageGroups = ageAttributes.GroupBy(x => x.Value); // Group by value.
            var max = ageGroups.Max(x => x.Count());             // Find the largest list in the list of groups.
            return ageGroups.First(x => x.Count() == max).Key;   // Find the group with the same number of elements as the max.
        }

        public static double? Midrange(this List<AgeAttribute> ageAttributes)
        {
            if (ageAttributes == null || !ageAttributes.Any())
                return null;

            AgeAttribute minAgeAttribute = null;
            AgeAttribute maxAgeAttribute = null;

            foreach (var ageAttribute in ageAttributes)
            {
                if (minAgeAttribute == null || ageAttribute.Value < minAgeAttribute.Value) // New min
                    minAgeAttribute = ageAttribute;

                if (maxAgeAttribute == null || ageAttribute.Value > maxAgeAttribute.Value) // new max
                    maxAgeAttribute = ageAttribute;
            }

            return new List<AgeAttribute> { minAgeAttribute, maxAgeAttribute }.Mean();
        }
        #endregion


        #region data dispersion
        // Variance and standard deviation
        public static double? Variance(this List<AgeAttribute> ageAttributes)
        {
            if (ageAttributes == null || !ageAttributes.Any())
                return null;

            var mean = ageAttributes.Mean();

            if (!mean.HasValue)
                return null;

            return (1.0 / ageAttributes.Count) * ageAttributes.Sum(x => Math.Pow(x.Value, 2)) - (Math.Pow(mean.Value, 2));
        }

        public static double? StandardDeviation(this List<AgeAttribute> ageAttributes)
        {
            var variance = ageAttributes.Variance();
            return variance.HasValue ? (double?) Math.Sqrt(variance.Value) : null;
        }

        // Range
        public static int? Range(this List<AgeAttribute> ageAttributes)
        {
            if (ageAttributes == null || !ageAttributes.Any())
                return null;

            return ageAttributes.Max(x => x.Value) - ageAttributes.Min(x => x.Value);
        }

        // Quantiles
        public static int[] Quantile(this List<AgeAttribute> ageAttributes, int quantiles)
        {
            if (ageAttributes == null || !ageAttributes.Any())
                return null;

            if (quantiles < 1 || ageAttributes.Count <= quantiles)
                throw new ApplicationException($"Can't get the {quantiles}-quantiles of a list of size {ageAttributes.Count}");

            var ordered = ageAttributes.OrderBy(x => x.Value).ToList();
            var quantileValues = new List<int>();
            var loop = 1;
            for (var i = ageAttributes.Count / quantiles; i < ordered.Count; i = ageAttributes.Count * loop / quantiles)
            {
                quantileValues.Add(ordered[i].Value);
                loop++;
            }
            return quantileValues.ToArray();
        }
        // InterquartileRange
        public static int? InterQuartileRange(this List<AgeAttribute> ageAttributes)
        {
            if (ageAttributes == null || !ageAttributes.Any())
                return null;
            var quartile = ageAttributes.Quantile(4);

            if (quartile.Count() != 3)
                throw new ApplicationException("Could not generate quartile for ageAttribute");

            return quartile[2] - quartile[1];
        }

        // Five-number summary
        public static FiveNumberSummary<int> FiveNumberSummary(this List<AgeAttribute> ageAttributes)
        {
            if (ageAttributes == null || !ageAttributes.Any())
                return null;

            var quartile = ageAttributes.Quantile(4);
            if (quartile.Count() != 3)
                throw new ApplicationException("Could not generate quartile for ageAttribute");

            var min = int.MaxValue;
            var max = int.MinValue;

            foreach (var ageAttribute in ageAttributes)
            {
                if (ageAttribute.Value < min) // New min
                    min = ageAttribute.Value;

                if (ageAttribute.Value > max) // new max
                    max = ageAttribute.Value;
            }

            return new FiveNumberSummary<int>
            {
                Min = min,
                Max = max,
                Q1 = quartile[0],
                Median = quartile[1],
                Q3 = quartile[2]
            };
        }
        #endregion
    }
}
