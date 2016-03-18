using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes.Abstract;
using CsvAttributeName = DataminingConsole.Processes.DataMiningSpring2016.Common.Constant.CsvAttributeName;

namespace DataminingConsole.Processes.DataMiningSpring2016.Preprocessing
{
    public static class DataMappers
    {
        /// <summary>
        /// Parse csvname to enum
        /// </summary>
        /// <param name="csvName">name to parse</param>
        /// <returns>the parsed name</returns>
        public static AttributeType AttributeTypeMapper(string csvName)
        {
            if (CsvAttributeName.Age.Equals(csvName))
                return AttributeType.Age;

            if (CsvAttributeName.Degree.Equals(csvName))
                return AttributeType.Degree;

            if (CsvAttributeName.GameFrequency.Equals(csvName))
                return AttributeType.GameFrequency;

            if (CsvAttributeName.FavoriteGame.Equals(csvName))
                return AttributeType.FavoriteGame;


            if (CsvAttributeName.PlayedGames.Equals(csvName))
                return AttributeType.PlayedGames;

            else 
                return AttributeType.Unknown; // Tried to parse unknown attribute type
        }
    }
}
