using System.Collections.Generic;
using DataminingConsole.Processes.DataMiningSpring2016.Common;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Age;

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
                case(degree.equals("GAMES-A")) return new DegreeAttribute { Value = DegreeType.GAMES_A}
                
                case(degree.equals("GAMES-T")) return new DegreeAttribute { Value = DegreeType.GAMES_T}
                
                case(degree.equals("SDT-SE")) return new DegreeAttribute { Value = DegreeType.SDT_SE}
                
                case(degree.equals("SDT_DT")) return new DegreeAttribute { Value = DegreeType.SDT_DT}
                
                case(degree.equals("SWU")) return new DegreeAttribute { Value = DegreeType.SWU}
                
                case(degree.equals("Guest Student")) return new DegreeAttribute { Value = DegreeType.Guest_Student}
                
                case(degree.equals("Polit på KU (økonomi)")) return new DegreeAttribute { Value = DegreeType.PolIt}
                
                default: return new DegreeAttribute { Missing = true }
            }
        }
        
        public FavoriteGameAttribute TransformFavoriteGame(string favoriteGame) {
            var arrayOfGames = favoriteGame.Split(",");
            if(arrayOfGames != null) {
                favoriteGame = arrayOfGames[0];
            }
            
            favoriteGame.ToLowerCase();
            switch (degree)
            {
                case(Prototype.FavoriteGameMapping.Contains(favoriteGame)) 
                return new FavoriteGameAttribute { Value = hashMap.Get(favoriteGame) }
                
                default: return new FavoriteGameAttribute { Value = DegreeType.Unknown }
            }
        }

        public GameFrequencyAttribute TramsformGameFrequency(string gameFrequency) {
            
            switch (gameFrequency)
            {
                case(gameFrequency.Equals("Never"))
                    return new GameFrequencyAttribute { Value = GameFrequencyType.Never }     
                               
                case(gameFrequency.Equals("< 5 hours a week"))
                    return new GameFrequencyAttribute { Value = GameFrequencyType.LessThan5HoursAWeek }
                
                case(gameFrequency.Equals("< 10 hours a week"))
                    return new GameFrequencyAttribute { Value = GameFrequencyType.LessThan10HoursAWeek }                
                
                case(gameFrequency.Equals("< 15 hours a week"))
                    return new GameFrequencyAttribute { Value = GameFrequencyType.LessThan15HoursAWeek }
                
                case(gameFrequency.Equals("< 20 hours a week"))
                    return new GameFrequencyAttribute { Value = GameFrequencyType.LessThan20HoursAWeek }                
               
                case(gameFrequency.Equals("> 20 hours a week"))
                    return new GameFrequencyAttribute { Value = GameFrequencyType.MoreThan20HoursAWeek }                                        
                
                default: 
                    return new GameFrequencyAttribute { Missing = true };
            }
        }
        
        public PlayedGamesAttribute TransformPlayedGames(string playedGames) {
            switch (playedGames)
            {
                case(playedGames.Equals(""))
                return something fucker!
                default:
            }
        }
    }
}
