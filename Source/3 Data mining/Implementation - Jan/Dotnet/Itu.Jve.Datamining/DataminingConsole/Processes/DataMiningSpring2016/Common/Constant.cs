using System;

namespace DataminingConsole.Processes.DataMiningSpring2016.Common
{
    public static class Constant
    {
        public static class CsvAttributeName
        {
            public static string Age { get; } = "\"Age\"";
            public static string Degree { get; } = "\"What degree are you studying?\"";
            public static string GameFrequency { get; } = "\"How often do you play video games?\"";
            public static string PickNumber { get; } = "\"Pick a number\"";
            public static string FavoriteGame { get; } = "\"Favorite game?\"";
            public static string PlayedGames { get; } = "\"Which of these games have you played? \"";
            public static string Row { get; } = "\"Which row are you sitting/did you sit in during the introduction lecture?\"";
            public static string Seat { get; } = "\"Which seat are you sitting/did you sit on during the introduction lecture?\"";
        }

        private static class ReferenceId
        {
            internal static Guid MissingData { get; } = new Guid("58E0F061-4B75-452A-B377-3031C7E1774C");
            internal static Guid Unparsable { get; } = new Guid("B81E28F0-D8E9-489A-8AD9-C19FC964CCCB");
            internal static Guid Success { get; } = new Guid("69A2E061-713F-4FDC-87C0-E732637F9D30");

        }

        public static class InternalString
        {
            public static string Success { get; } = $"SUCCESS (Ref: {ReferenceId.Success})";
            public static string MissingDataError { get; } = $"MISSING DATA (Ref: {ReferenceId.MissingData})";
            public static string ParsingError { get; } = $"PARSING ERROR (Ref: {ReferenceId.Unparsable})";
        }
    }
}