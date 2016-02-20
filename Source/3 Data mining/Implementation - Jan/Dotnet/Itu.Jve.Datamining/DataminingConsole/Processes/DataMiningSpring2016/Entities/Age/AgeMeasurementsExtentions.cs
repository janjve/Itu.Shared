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

            return ageAttributes.OrderBy(x => x.Value).ToList()[ageAttributes.Count/2].Value;
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
        

        #endregion
    }
}
