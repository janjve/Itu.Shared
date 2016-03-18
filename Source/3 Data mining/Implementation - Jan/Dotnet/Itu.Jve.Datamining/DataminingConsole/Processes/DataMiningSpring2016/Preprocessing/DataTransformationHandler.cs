using System.Collections.Generic;
using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using static DataminingConsole.Processes.DataMiningSpring2016.Common.Constant;
using System;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Attributes.Abstract;

namespace DataminingConsole.Processes.DataMiningSpring2016.Preprocessing
{
    public class DataTransformationHandler
    {
        /// <summary>
        /// Map a string to DataTuple.
        /// </summary>
        /// <param name="tuple">string to parse</param>
        /// <param name="attributeIndex">attributeIndex for array.</param>
        /// <returns>DataTuple object</returns>
        public DataTuple TransformTuple(List<string> tuple, Dictionary<AttributeType, int> attributeIndex)
        {
            return new DataTuple
            {
                Age = TransformAge(tuple[attributeIndex[AttributeType.Age]]),
                Degree = TransformDegree(tuple[attributeIndex[AttributeType.Degree]]),
                FavoriteGame = TransformFavoriteGame(tuple[attributeIndex[AttributeType.FavoriteGame]]),
                GameFrequency = TransformGameFrequency(tuple[attributeIndex[AttributeType.GameFrequency]]),
                PlayedGames = new PlayedGamesAttribute() // Unfinished.
            };
        } 
        /// <summary>
        /// Parse age
        /// </summary>
        /// <param name="age"></param>
        /// <returns></returns>
        public AgeAttribute TransformAge(string age)
        {
            int transformedAge;
            return int.TryParse(age, out transformedAge) ? new AgeAttribute {Value = transformedAge } : null;
        }
        
        /// <summary>
        /// Parse degree
        /// </summary>
        /// <param name="degree"></param>
        /// <returns></returns>
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
        
        /// <summary>
        /// Parse favorite game
        /// </summary>
        /// <param name="favoriteGame"></param>
        /// <returns></returns>
        public FavoriteGameAttribute TransformFavoriteGame(string favoriteGame) {
            var arrayOfGames = favoriteGame.Split(',');
            favoriteGame = arrayOfGames[0]?.Trim().ToLower();
            var game = favoriteGame != null && Mappings.FavoriteGameMapping.ContainsKey(favoriteGame)
                ? new FavoriteGameAttribute {Value = Mappings.FavoriteGameMapping[favoriteGame]}
                : new FavoriteGameAttribute {Value = FavoriteGameType.Unknown};
            return game;

        }

        /// <summary>
        /// Parse gameFrequency
        /// </summary>
        /// <param name="gameFrequency"></param>
        /// <returns></returns>
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
        
        [Obsolete("Not finished => ununsed")]
        public PlayedGamesAttribute TransformPlayedGames(string playedGames) {
            throw new NotImplementedException("No transformation yet.");
        }
    }
}
