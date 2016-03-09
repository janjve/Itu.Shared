using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataminingConsole.Processes.DataMiningSpring2016.Entities.Age;

namespace DataminingConsole.Processes.DataMiningSpring2016.Entities
{
    public class DataTuple
    {
        public AgeAttribute Age { get; set; }
        public DegreeAttribute Degree { get; set; }
        public FavoriteGameAttribute FavoriteGame { get; set; }
        public GameFrequencyAttribute GameFrequency { get; set; }
        public PlayedGamesAttribute PlayedGames { get; set; }

        public override string ToString()
        {
            return $"age: {Age}, .... TODO: tostirng in dataTuple";
        }
    }
}
