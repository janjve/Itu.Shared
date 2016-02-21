using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Schema;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities.Age
{
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

            var split = ageAttributes.Count / quantiles;
            var ordered = ageAttributes.OrderBy(x => x.Value).ToList();
            var quantileValues = new List<int>();
            for (var i = split; i < ordered.Count; i += split)
            {
                quantileValues.Add(ordered[i].Value);
            }
            return quantileValues.ToArray();
        }
        // InterquartileRange
        // Five-number summary

        #endregion
    }
}
