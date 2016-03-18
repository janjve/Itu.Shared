using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using System;
using System.Collections.Generic;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;

namespace DataminingConsole.Processes.DataMiningSpring2016.Common
{
    public static class Constant
    {
        public static class CsvAttributeName
        {
            public static string Age { get; } = "\"Age\"";
            public static string Degree { get; } = "\"What degree are you studying?\"";
            public static string GameFrequency { get; } = "\"How often do you play video games?\"";
            public static string FavoriteGame { get; } = "\"Favorite game?\"";
            public static string PlayedGames { get; } = "\"Which of these games have you played? \"";
        }
        [Obsolete("Unused")]
        private static class ReferenceId
        {
            internal static Guid MissingData { get; } = new Guid("58E0F061-4B75-452A-B377-3031C7E1774C");
            internal static Guid Unparsable { get; } = new Guid("B81E28F0-D8E9-489A-8AD9-C19FC964CCCB");
            internal static Guid Success { get; } = new Guid("69A2E061-713F-4FDC-87C0-E732637F9D30");

        }
        [Obsolete("Unused")]
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
                    {"wot", FavoriteGameType.World_Of_Tank},
                    {"world of warcraft", FavoriteGameType.World_Of_Warcraft},
                    {"yu-gi-oh", FavoriteGameType.Yu_Gi_Oh},
                    {"hearthstone", FavoriteGameType.Hearthstone},
                    {"snake", FavoriteGameType.Snake},
                    {"hitman", FavoriteGameType.Hitman},
                    {"streets of rage", FavoriteGameType.Streets_Of_Rage},
                    {"tetris", FavoriteGameType.Tetris},
                    {"witcher 3", FavoriteGameType.Witcher_3},
                    {"skyrim", FavoriteGameType.Skyrim},
                    {"gone gome", FavoriteGameType.Gone_Home},
                    {"counter-strike", FavoriteGameType.Counter_Strike},
                    {"fez", FavoriteGameType.FEZ},
                    {"diablo 2", FavoriteGameType.Diablo_2},
                    {"path of exile", FavoriteGameType.Path_Of_Exile},
                    {"dota 2", FavoriteGameType.Dota_2},
                    {"spec ops: the line", FavoriteGameType.Spec_Ops_The_Line},
                    {"fifa 16", FavoriteGameType.Fifa_16},
                    {"vampire the masquerade: bloodlines", FavoriteGameType.Vampire_The_Masquerade_Bloodlines},
                    {"half-life", FavoriteGameType.Half_Life} ,
                    {"trine", FavoriteGameType.Trine},
                    {"galactic arms race", FavoriteGameType.Galactic_Arms_Race},
                    {"championship manager", FavoriteGameType.Championship_Manager},
                    {"deus ex", FavoriteGameType.Deus_Ex},
                    {"transport tycoon", FavoriteGameType.Transport_Tycoon},
                    {"baldur's gate", FavoriteGameType.Baldurs_Gate},
                    {"journey", FavoriteGameType.Journey},
                    {"ico", FavoriteGameType.Ico},
                    {"heroes of newerth", FavoriteGameType.Heroes_Of_Newerth},
                    {"call of duty ii", FavoriteGameType.Call_Of_Duty_2},
                    {"heroes of the storm", FavoriteGameType.Heroes_Of_The_Storm},
                    {"the binding of isaac: rebirth", FavoriteGameType.The_Binding_Of_Isaac_Rebirth},
                    {"starcraft", FavoriteGameType.Starcraft},
                    {"risk", FavoriteGameType.Risk},
                    {"counter-string: go", FavoriteGameType.Counter_Strike_Go},
                    {"alto's adventure", FavoriteGameType.Altos_Adventure},
                    {"zelda", FavoriteGameType.Zelda},
                    {"league of legends", FavoriteGameType.League_Of_Legends},
                    {"last of us", FavoriteGameType.Last_Of_Us},
                    {"batman arkham knight", FavoriteGameType.Batman_Arkham_Knight},
                    {"portal", FavoriteGameType.Portal},
                    {"advanced wars dual strike", FavoriteGameType.Advanced_Wars_Dual_Strike},
                    {"prototype", FavoriteGameType.Prototype},
                };
               
        }
    }
}