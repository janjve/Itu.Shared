using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery
{
    public class Helpers
    {
        // Nominal distance is 0 if equal, 1 otherwise.
        public static float ManhattenDistance(DataTuple tuple1, DataTuple tuple2)
        {
            var degreeDistance = tuple1.Degree.Value == tuple2.Degree.Value ? 0.0f : 1.0f;
            var favoriteGameDistance = tuple1.FavoriteGame.Value == tuple2.FavoriteGame.Value ? 0.0f : 1.0f;
            var gameFrequencyDistance = tuple1.GameFrequency.Value == tuple2.GameFrequency.Value ? 0.0f : 1.0f;

            var distance = Math.Abs(tuple1.Age.NormalizedValue - tuple2.Age.NormalizedValue)
                + degreeDistance
                + favoriteGameDistance
                + gameFrequencyDistance
                ;
            return distance;
        }
    }
}
