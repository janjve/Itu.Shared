using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using CsvAttributeName = DataminingConsole.Processes.DataMiningSpring2016.Common.Constant.CsvAttributeName;

namespace DataminingConsole.Processes.DataMiningSpring2016.Utility
{
    public static class DataMappers
    {
        public static AttributeType AttributeTypeMapper(string csvName)
        {
            if (CsvAttributeName.Age.Equals(csvName))
                return AttributeType.Age;

            if (CsvAttributeName.Row.Equals(csvName))
                return AttributeType.Row;

            if (CsvAttributeName.Seat.Equals(csvName))
                return AttributeType.Seat;

            if (CsvAttributeName.Degree.Equals(csvName))
                return AttributeType.Degree;

            if (CsvAttributeName.GameFrequency.Equals(csvName))
                return AttributeType.GameFrequency;

            if (CsvAttributeName.FavoriteGame.Equals(csvName))
                return AttributeType.FavoriteGame;

            if (CsvAttributeName.PickNumber.Equals(csvName))
                return AttributeType.PickNumber;

            else 
                return AttributeType.Unknown; // Tried to parse unknown attribute type
        }
    }
}
