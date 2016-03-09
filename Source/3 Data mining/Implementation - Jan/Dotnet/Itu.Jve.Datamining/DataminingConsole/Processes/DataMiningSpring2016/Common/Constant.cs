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
            //public static string PickNumber { get; } = "\"Pick a number\"";
            public static string FavoriteGame { get; } = "\"Favorite game?\"";
            public static string PlayedGames { get; } = "\"Which of these games have you played? \"";
            //public static string Row { get; } = "\"Which row are you sitting/did you sit in during the introduction lecture?\"";
            //public static string Seat { get; } = "\"Which seat are you sitting/did you sit on during the introduction lecture?\"";
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
        
        public static class Mappings
        {
            
            public static Dictionary<string, FavoriteGameType> FavoriteGameMapping { get; } = 
                new Dictionary<string, FavoriteGameType>{
                    {"wot", FavoriteGameType.World_Of_Tanks},
                    {"world of warcraft", FavoriteGameType.World_Of_Warcraft},
                    {"Yu-Gi-Oh", FavoriteGameType.Yu_Gi_Oh},
                    {"Hearthstone", FavoriteGameType.Hearthstone},
                    {"Snake", FavoriteGameType.Snake},
                    {"Hitman", FavoriteGameType.Hitman},
                    {"Streets of Rage", FavoriteGameType.Streets of Rage},
                    {"Tetris", FavoriteGameType.Tetris},
                    {"Witcher 3", FavoriteGameType.Witcher_3},
                    {"Skyrim", FavoriteGameType.Skyrim},
                    {"Gone Home", FavoriteGameType.Gone_Home},
                    {"Counter-Strike", FavoriteGameType.Counter_Strike},
                    {"FEZ", FavoriteGameType.FEZ},
                    {"Diablo 2", FavoriteGameType.Diablo_2},
                    {"Path of Exile", FavoriteGameType.Path_Of_Exile},
                    {"Dota 2", FavoriteGameType.Dota_2},
                    {"Spec Ops: The Line", FavoriteGameType.Spec_Ops_The_Line},
                    {"Fifa 16", FavoriteGameType.Fifa_16},
                    {"Vampire The Masquerade: Bloodlines", FavoriteGameType.Vampire_The_Masquerade_Bloodlines},
                    {"Half-Life", FavoriteGameType.Half_Life} ,
                    {"Trine", FavoriteGameType.Trine},
                    {"Galactic Arms Race", FavoriteGameType.Galactic_Arms_Race},
                    {"Championship manager", FavoriteGameType.Championship_manager},
                    {"Deus Ex", FavoriteGameType.Deus_Ex},
                    {"Transport Tycoon", FavoriteGameType.Transport_Tycoon},
                    {"Baldur's Gate", FavoriteGameType.Baldurs_Gate},
                    {"Journey", FavoriteGameType.Journey},
                    {"Ico", FavoriteGameType.Ico},
                    {"Heroes of Newerth", FavoriteGameType.Heroes_Of_Newerth},
                    {"Call of duty II", FavoriteGameType.Call_of_duty_2)},
                    {"Heroes of the storm", FavoriteGameType.Heroes_of_the_storm},
                    {"The Binding of Isaac: Rebirth", FavoriteGameType.The_Binding_of_Isaac_Rebirth},
                    {"Starcraft", FavoriteGameType.Starcraft},
                    {"Risk", FavoriteGameType.Risk},
                    {"Counter-String: GO", FavoriteGameType.Counter_String_GO},
                    {"Alto's Adventure", FavoriteGameType.Altos_Adventure},
                    {"zelda", FavoriteGameType.Zelda},
                    {"League of Legends", FavoriteGameType.League_of_Legends},
                    {"Last of us", FavoriteGameType.Last_of_us},
                    {"Batman Arkham Knight", FavoriteGameType.Batman_Arkham_Knight},
                    {"Portal", FavoriteGameType.Portal},
                    {"Advanced Wars Dual Strike", FavoriteGameType.Advanced_Wars_Dual_Strike},
                    {"Prototype", FavoriteGameType.Prototype},
                }
                
            public static Dictionary<string, PlayedGamesType> PlayedGamesMapping { get; } = 
                new Dictionary<string, PlayedGamesType> {
                    {"Candy Crush", PlayedGamesType.World_Of_Tanks},
                    {"Wordfeud", PlayedGamesType.World_Of_Tanks},
                    {"Minecraft", PlayedGamesType.World_Of_Tanks},
                    {"FarmVille", PlayedGamesType.World_Of_Tanks},
                    {"Fifa 2016", PlayedGamesType.World_Of_Tanks},
                    {"Star Wars Battlefront", PlayedGamesType.World_Of_Tanks},
                    {"Life is Strange", PlayedGamesType.World_Of_Tanks},
                    {"Battlefield 4", PlayedGamesType.World_Of_Tanks},
                    {"Gone Home", PlayedGamesType.World_Of_Tanks},
                    {"Stanley Parable", PlayedGamesType.World_Of_Tanks},
                    {"Call of Duty: Black Ops III", PlayedGamesType.World_Of_Tanks},
                    {"Rocket League", PlayedGamesType.World_Of_Tanks},
                    {"Bloodborne", PlayedGamesType.World_Of_Tanks},
                    {"Rise of the Tomb Raider", PlayedGamesType.World_Of_Tanks},
                    {"The Witness", PlayedGamesType.World_Of_Tanks},
                    {"Her Story", PlayedGamesType.World_Of_Tanks},
                    {"Fallout 4", PlayedGamesType.World_Of_Tanks},
                    {"Dragon Age: Inquisition", PlayedGamesType.World_Of_Tanks},
                    {"Counter strike: GO", PlayedGamesType.World_Of_Tanks},
                    {"Angry birds", PlayedGamesType.World_Of_Tanks},
                    {"The Last of Us", PlayedGamesType.World_Of_Tanks},
                    {"The Magic Circle", PlayedGamesType.World_Of_Tanks},
                    {"I have not played any of these games", PlayedGamesType.World_Of_Tanks},
               
















            }
        }
    }
}