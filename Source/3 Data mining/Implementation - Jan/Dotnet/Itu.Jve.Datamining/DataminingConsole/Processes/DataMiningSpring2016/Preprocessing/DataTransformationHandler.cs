﻿using System.Collections.Generic;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using static DataminingConsole.Processes.DataMiningSpring2016.Common.Constant;
using System;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;

namespace DataminingConsole.Processes.DataMiningSpring2016.Preprocessing
{
    // Values that can't be transformed are considered "missing"
    public class DataTransformationHandler
    {

        public DataTuple TransformTuple(List<string> tuple, Dictionary<AttributeType, int> attributeIndex)
        {
            return new DataTuple
            {
                Age = TransformAge(tuple[attributeIndex[AttributeType.Age]]),
                Degree = TransformDegree(tuple[attributeIndex[AttributeType.Degree]]),
                FavoriteGame = TransformFavoriteGame(tuple[attributeIndex[AttributeType.FavoriteGame]]),
                GameFrequency = TransformGameFrequency(tuple[attributeIndex[AttributeType.GameFrequency]]),
                PlayedGames = new PlayedGamesAttribute() // TransformPlayedGames(tuple[attributeIndex[AttributeType.PlayedGames]])
            };
        } 

        public AgeAttribute TransformAge(string age)
        {
            int transformedAge;
            return int.TryParse(age, out transformedAge) ? new AgeAttribute {Value = transformedAge } : null;
        }
        
        public DegreeAttribute TransformDegree(string degree) {
            switch (degree.ToLower())
            {
                case "games-a": return new DegreeAttribute { Value = DegreeType.GAMES_A };
                case "games-t": return new DegreeAttribute { Value = DegreeType.GAMES_T };
                case "sdt-se": return new DegreeAttribute { Value = DegreeType.SDT_SE };
                case "sdt-dt": return new DegreeAttribute { Value = DegreeType.SDT_DT };
                case "swu": return new DegreeAttribute { Value = DegreeType.SWU };
                case "guest student": return new DegreeAttribute { Value = DegreeType.Guest_Student };
                case "polit på ku (økonomi)": return new DegreeAttribute { Value = DegreeType.PolIT };
                default: return new DegreeAttribute { MissingValue = true };
            }
        }
        
        public FavoriteGameAttribute TransformFavoriteGame(string favoriteGame) {
            var arrayOfGames = favoriteGame.Split(',');
            favoriteGame = arrayOfGames[0]?.Trim().ToLower();
            var game = favoriteGame != null && Mappings.FavoriteGameMapping.ContainsKey(favoriteGame)
                ? new FavoriteGameAttribute {Value = Mappings.FavoriteGameMapping[favoriteGame]}
                : new FavoriteGameAttribute {Value = FavoriteGameType.Unknown};
            return game;

        }

        public GameFrequencyAttribute TransformGameFrequency(string gameFrequency) {
            
            switch (gameFrequency)
            {
                case ("Never"):
                    return new GameFrequencyAttribute { Value = GameFrequencyType.Never };
                case ("< 5 hours a week"):
                    return new GameFrequencyAttribute { Value = GameFrequencyType.LessThan5HoursAWeek };
                case ("< 10 hours a week"):
                    return new GameFrequencyAttribute { Value = GameFrequencyType.LessThan10HoursAWeek };
                case ("< 15 hours a week"):
                    return new GameFrequencyAttribute { Value = GameFrequencyType.LessThan15HoursAWeek };
                case ("< 20 hours a week"):
                    return new GameFrequencyAttribute { Value = GameFrequencyType.LessThan20HoursAWeek };
                case ("> 20 hours a week"):
                    return new GameFrequencyAttribute { Value = GameFrequencyType.MoreThan20HoursAWeek };                                       
                default: 
                    return new GameFrequencyAttribute { MissingValue = true };
            }
        }
        
        public PlayedGamesAttribute TransformPlayedGames(string playedGames) {
            throw new NotImplementedException("No transformation yet.");
        }
    }
}
