using System;
using System.Collections.Generic;
using System.Linq;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;

namespace DataminingConsole.Processes.DataMiningSpring2016.Common
{
    public static class CsvCleaningExtensions
    {

        /// <summary>
        /// Select the chosen selectAttributes. This first inner List should be the list of selectAttributes in the full list.
        /// </summary>
        /// <param name="list">Dataset</param>
        /// /// <param name="attributeListOrder">Attribute list</param>
        /// <param name="selectAttributes">Attributes to select</param>
        /// <returns>A new list containing tuples with the selected selectAttributes</returns>
        public static List<List<string>> SelectAttributes(this List<List<string>> list, List<string> attributeListOrder, List<string> selectAttributes)
        {
            var selectedIndex = attributeListOrder
                .Select((x, i) => new { element = x, index = i })   // Assign "list"-index in full list.
                .Where(x => selectAttributes.Contains(x.element))   // Only take requested selectAttributes 
                .Select((x) => x.index)                             // Select only the "list" index
                .ToLookup(x => x);                                  // Create hash table for constant search time.
            return list
                .Select(x => x
                .Where((y, i) => selectedIndex.Contains(i))         // Get only rows matching a selectedIndex
                .Select((y) => y).ToList()).ToList();               // Return every tuple.
        }

        public static void MapColumn(this List<List<string>> list, Dictionary<AttributeType, int> attributeIndex, AttributeType attributeType, Func<string, string> mappingFunc)
        {
            var index = attributeIndex[attributeType];                      // Get index of Age attribute.
            list.ForEach(x => { x[index] = mappingFunc(x[index]); });       // Apply the mapping on the attribute.
        }
    }
}
