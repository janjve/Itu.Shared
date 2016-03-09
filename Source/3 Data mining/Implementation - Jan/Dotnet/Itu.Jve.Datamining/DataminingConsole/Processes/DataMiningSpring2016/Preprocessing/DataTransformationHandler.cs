using System.Collections.Generic;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Age;
using static DataminingConsole.Processes.DataMiningSpring2016.Common.Constant;
using System;

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
                GameFrequency = TramsformGameFrequency(tuple[attributeIndex[AttributeType.GameFrequency]]),
                //PlayedGames = TransformPlayedGames(tuple[attributeIndex[AttributeType.PlayedGames]])
            };
        } 

        public AgeAttribute TransformAge(string age)
        {
            int transformedAge;

            if (int.TryParse(age, out transformedAge))
                return new AgeAttribute {Value = transformedAge };
            return null;
        }
        
        public DegreeAttribute TransformDegree(string degree) {
            switch (degree)
            {
                case ("GAMES-A"): return new DegreeAttribute { Value = DegreeType.GAMES_A };
                case ("GAMES-T"): return new DegreeAttribute { Value = DegreeType.GAMES_T };
                case ("SDT-SE"): return new DegreeAttribute { Value = DegreeType.SDT_SE };
                case ("SDT_DT"): return new DegreeAttribute { Value = DegreeType.SDT_DT };
                case ("SWU"): return new DegreeAttribute { Value = DegreeType.SWU };
                case ("Guest Student"): return new DegreeAttribute { Value = DegreeType.Guest_Student };
                case ("Polit på KU (økonomi)"): return new DegreeAttribute { Value = DegreeType.PolIT };
                default: return new DegreeAttribute { MissingValue = true };
            }
        }
        
        public FavoriteGameAttribute TransformFavoriteGame(string favoriteGame) {
            var arrayOfGames = favoriteGame.Split(',');
            if(arrayOfGames != null) {
                favoriteGame = arrayOfGames[0];
            }
            
            favoriteGame.ToLower();

            return Mappings.FavoriteGameMapping.ContainsKey(favoriteGame) 
                ? new FavoriteGameAttribute { Value = Mappings.FavoriteGameMapping[favoriteGame] }
                : new FavoriteGameAttribute { Value = FavoriteGameType.Unknown };

        }

        public GameFrequencyAttribute TramsformGameFrequency(string gameFrequency) {
            
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
