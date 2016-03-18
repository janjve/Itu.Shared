using DataminingConsole.Processes.DataMiningSpring2016.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataminingConsole.Processes.DataMiningSpring2016.PatternDiscovery.KNearestNeighbors
{
    public class KNearestNeighbor
    {
        /// <summary>
        /// Start the KNearestNeighbor process
        /// </summary>
        /// <typeparam name="T">The classifier type.</typeparam>
        /// <param name="k">number of K nearest neighbore</param>
        /// <param name="tuple">The tuple to assign a class T</param>
        /// <param name="trainingSet">a list of tuple as keys with there respective classes as value</param>
        /// <returns></returns>
        public T CalculateClassification<T>(int k, DataTuple tuple, Dictionary<DataTuple, T> trainingSet)
        {
            var kNearestNeighbors = KNearestNeighbors(k, tuple, trainingSet.Keys.ToList());
            var group = kNearestNeighbors.GroupBy(x => trainingSet[x]).OrderByDescending(x => x.Count());
            return group.Any() ? group.First().Key : default(T);
        }

        /// <summary>
        /// Finds the K-nearest neighbors using the manhatten distance.
        /// </summary>
        /// <param name="k">number of neighbors</param>
        /// <param name="tuple">input tuple</param>
        /// <param name="trainingSet">training set to seach</param>
        /// <returns></returns>
        private static IEnumerable<DataTuple> KNearestNeighbors(int k, DataTuple tuple, List<DataTuple> trainingSet)
        {
            var kNearestNeighbors = new List<DataTupleWithDistance>();

            foreach (var trainingTuple in trainingSet)
            {
                var manhattenDistance = Helpers.ManhattenDistance(tuple, trainingTuple);

                // First k neighbors initialized directly
                if (kNearestNeighbors.Count < k)
                {
                    kNearestNeighbors.Add(new DataTupleWithDistance
                    {
                        Tuple = trainingTuple,
                        Distance = manhattenDistance
                    });
                }
                else
                {
                    // First highest manhattenDistance in nearest neighbors.
                    var currentFurthest = kNearestNeighbors.Aggregate((x1, x2) => x1.Distance > x2.Distance ? x1 : x2);

                    if (currentFurthest.Distance > manhattenDistance)
                    {
                        currentFurthest.Distance = manhattenDistance;
                        currentFurthest.Tuple = trainingTuple;
                    }
                }
            }
            return kNearestNeighbors.Select(x => x.Tuple);
        }

        /// <summary>
        /// Data wrapper
        /// </summary>
        private class DataTupleWithDistance
        {
            public DataTuple Tuple { get; set; }
            public float Distance { get; set; }
        }
    }
}
